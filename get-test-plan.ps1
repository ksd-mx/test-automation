param ([string]$testplan)

class TestStep {
    [string] $externalId
    [string] $name
}

class TestCase {
    [string] $externalId
    [string] $name
    [TestStep[]] $testStepList
}

class TestGroup {
    [string] $externalId
    [string] $name
    [TestCase[]] $testCaseList
}

$personal_access_token = ""
$auth_header = @{Authorization = 'Basic ' + [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(":$($personal_access_token)")) }

$env:SYSTEM_COLLECTIONURI = ""

$suite_list = New-Object System.Collections.ArrayList($null)
$base_uri = "${env:SYSTEM_COLLECTIONURI}/_apis"
$base_wit_uri = "${base_uri}/wit"
$base_test_uri = "${base_uri}/test"
$api50_version = "?api-version=5.0"
$api51_version = "?api-version=5.1"
$api_preview = "-preview.1"
$testplan_uri = "${base_test_uri}/plans/${testplan}"
$workitem_uri = "${base_wit_uri}/workitems"

Function GetChildSuite {
    [CmdletBinding()]
    param([string]$suiteid)

    $suite_uri = "${testplan_uri}/suites/${suiteid}${api50_version}"
    $suiteentry_uri = "${base_test_uri}/suiteentry/${suiteid}${api50_version}${api_preview}"

    Write-Host "# Invoking SUITE ENTRIES API @ ${suiteentry_uri}"
    $suite_result = Invoke-RestMethod -Uri "$suite_uri" -Method Get -Headers $auth_header -UseBasicParsing

    Write-Host "# Invoking SUITE DATA API @ ${suite_uri}"
    $rest_result = Invoke-RestMethod -Uri "$suiteentry_uri" -Method Get -Headers $auth_header -UseBasicParsing

    Write-host "# Getting TEST SUITES for" $suite_result.id - $suite_result.name

    if ($rest_result.count -gt 0 ) {
        foreach ($item in $rest_result.value ) {
            if ($item.childSuiteId -eq 0) { continue; }

            GetChildSuite -suiteid $item.childSuiteId
        }
    }

    GetTestCase -parent $suite_result
}

Function GetTestCase {
    [CmdletBinding()]
    param($parentsuite)

    $parentsuiteid = $parentsuite.id

    $testcase_uri = "${testplan_uri}/suites/${parentsuiteid}/testcases${api50_version}"

    Write-Host "# Invoking TEST CASES API @ ${testcase_uri}"
    $testcase_result = Invoke-RestMethod -Uri "$testcase_uri" -Method Get -Headers $auth_header -UseBasicParsing

    Write-host "# Getting TEST CASES for" $parentsuite.id - $parentsuite.name

    if ($testcase_result.count -gt 0) {
        foreach ($item in $testcase_result.value) {
            GetTestCaseSteps -testcase $item
        }
    }
}

Function GetTestCaseSteps {
    [CmdletBinding()]
    param($testcase)

    [regex] $step_rgx = '<step id="\d+" [a-zA-Z0-9="]+>(.*?)<\/step>'
    [regex] $param_rgx = '<parameterizedString\s[a-zA-Z0-9="]+>(.*?)<\/parameterizedString>'

    $testcaseid = $testcase.testCase.id

    $wit_uri = "${workitem_uri}/${testcaseid}${api51_version}"

    Write-Host "# Invoking WITs API @ ${wit_uri}"
    $wit_result = Invoke-RestMethod -Uri "${wit_uri}" -Method Get -Headers $auth_header -UseBasicParsing

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

        Write-Host "# MATCH: ${match}"
        Write-Host "# STEP ACTION: " $step_action
        Write-Host "# STEP OUTCOME: " $step_outcome
    }
}

$remotetestplan = Invoke-RestMethod -Uri "${testplan_uri}" -Method Get -Headers $auth_header

$result = GetChildSuite -suiteid $remotetestplan.rootSuite.id