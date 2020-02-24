param ([string]$testplan)

class TestStep {
    [string] $externalId
    [string] $action
    [string] $outcome
}

class TestCase {
    [string] $externalId
    [string] $name
    [TestStep[]] $testStepList
}

class TestGroup {
    [string] $externalId
    [string] $name
    [TestGroup[]] $testGroupList
    [TestCase[]] $testCaseList
}

$auth_header = @{
    Authorization = 'Basic ' + `
        [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(":$(${env:TEST_AUTOMATION_ACCESS_TOKEN})"))
}
$now = [System.DateTime]::Now
Write-Host "Starting at ${now}"

$suite_list = New-Object System.Collections.ArrayList($null)
$base_uri = "${env:SYSTEM_COLLECTIONURI}/_apis"
$base_wit_uri = "${base_uri}/wit"
$base_test_uri = "${base_uri}/test"
$api50_version = "?api-version=5.0"
$api51_version = "?api-version=5.1"
$api_preview = "-preview.1"
$testplan_uri = "${base_test_uri}/plans/${testplan}"
$workitem_uri = "${base_wit_uri}/workitems"

$testplan_group = [TestGroup]::New()

Function GetChildSuite {
    [CmdletBinding()]
    param([TestGroup]$group)

    $suiteid = $group.externalId

    $suite_uri = "${testplan_uri}/suites/${suiteid}${api50_version}"
    $suiteentry_uri = "${base_test_uri}/suiteentry/${suiteid}${api50_version}${api_preview}"

    Write-Host "# Invoking SUITE ENTRIES API @ ${suiteentry_uri}"
    $suite_result = Invoke-RestMethod -Uri "$suite_uri" -Method Get -Headers $auth_header -UseBasicParsing

    Write-Host "# Invoking SUITE DATA API @ ${suite_uri}"
    $rest_result = Invoke-RestMethod -Uri "$suiteentry_uri" -Method Get -Headers $auth_header -UseBasicParsing

    $group.externalId = $suite_result.id
    $group.name = $suite_result.name

    Write-host "# Getting TEST SUITES for" $suite_result.id - $suite_result.name

    if ($rest_result.count -gt 0 ) {
        foreach ($item in $rest_result.value ) {
            if ($item.childSuiteId -eq 0) { continue; }
            # if ($group.testGroupList -eq $null) { $group.testGroupList = [TestGroup[]]::New() }
            $childGroup = [TestGroup]::New()
            $childGroup.externalId = $item.childSuiteId

            $group.testGroupList += $childGroup

            GetChildSuite -group $childGroup
        }
    }

    GetTestCase -parentgroup $group
}

Function GetTestCase {
    [CmdletBinding()]
    param($parentgroup)

    $parentsuiteid = $parentgroup.externalId

    $testcase_uri = "${testplan_uri}/suites/${parentsuiteid}/testcases${api50_version}"

    Write-Host "# Invoking TEST CASES API @ ${testcase_uri}"
    $testcase_result = Invoke-RestMethod -Uri "$testcase_uri" -Method Get -Headers $auth_header -UseBasicParsing

    Write-host "# Getting TEST CASES for" $parentgroup.externalId - $parentgroup.name

    if ($testcase_result.count -gt 0) {
        foreach ($item in $testcase_result.value) {
            $newtestcase = [TestCase]::New()

            $newtestcase.externalId = $item.testCase.id

            $parentgroup.testCaseList += $newtestcase

            GetTestCaseSteps -parenttestcase $newtestcase
        }
    }
}

Function GetTestCaseSteps {
    [CmdletBinding()]
    param($parenttestcase)

    [int] $sequence = 0
    [regex] $id_rgx = '\d+'
    [regex] $step_rgx = '<step id="\d+" [a-zA-Z0-9="]+>(.*?)<\/step>'
    [regex] $param_rgx = '<parameterizedString\s[a-zA-Z0-9="]+>(.*?)<\/parameterizedString>'

    $testcaseid = $parenttestcase.externalId

    $wit_uri = "${workitem_uri}/${testcaseid}${api51_version}"

    Write-Host "# Invoking WITs API @ ${wit_uri}"
    $wit_result = Invoke-RestMethod -Uri "${wit_uri}" -Method Get -Headers $auth_header -UseBasicParsing

    $parenttestcase.name = $wit_result.fields.'System.Title'

    # Lets clean up the steps information filled with
    # HTML noise and parse it down to a collection of steps
    $step_data = `
        $wit_result.fields.'Microsoft.VSTS.TCM.Steps' `
            -replace '(\</*steps(\>*.id=\"\d*" last=\"\d*")?\>*)' `
            -replace '(&lt;\/*[a-zA-Z0-9;=\"\]+\s[a-zA-Z0-9;=\"\-:(,.)]+\/*&gt;)'

    # -replace '<parameterizedString\s[a-zA-Z0-9="]+>'
    foreach ($match in $step_rgx.Matches($step_data)) {
        $stepparams = $param_rgx.Matches($match)

        $step_action = $stepparams[0] `
            -replace '<parameterizedString\s[a-zA-Z0-9="]+>' `
            -replace '<\/parameterizedString>' `
            -replace '\s+', ' '

        $step_outcome = $stepparams[1] `
            -replace '<parameterizedString\s[a-zA-Z0-9="]+>' `
            -replace '<\/parameterizedString>' `
            -replace '\s+', ' '

        if ($step_action.length -eq 0 -OR $step_outcome.length -eq 0) { continue; }

        Write-Host "# STEP ACTION: " $step_action
        Write-Host "# STEP OUTCOME: " $step_outcome

        $newtestcasestep = [TestStep]::New()
        $newtestcasestep.externalId = $sequence++
        $newtestcasestep.action = $step_action
        $newtestcasestep.outcome = $step_outcome

        $parenttestcase.testStepList += $newtestcasestep
    }
}

$remotetestplan = Invoke-RestMethod -Uri "${testplan_uri}" -Method Get -Headers $auth_header

$testplan_group.externalId = $remotetestplan.rootSuite.id

GetChildSuite -group $testplan_group

$testplan_group | ConvertTo-Json -depth 100 | Out-File "result_json.json"
