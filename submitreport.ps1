$now = [System.DateTime]::Now
Write-Host "Starting at ${now}"

$auth_header = @{
    Authorization = 'Basic ' + `
        [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes(":$(${env:TEST_AUTOMATION_ACCESS_TOKEN})"))
}

$contenttype = 'application/json'
$base_uri = "${env:SYSTEM_COLLECTIONURI}/_apis"
$base_test_uri = "${base_uri}/test"
$api51_version = "?api-version=5.1"
$testrun_base_uri = "${base_test_uri}/runs"

$testplan_result = (Get-Content "${env:TESTRESULT_PATH}//${env:TESTPLAN_FILENAME}" | Out-String | ConvertFrom-Json)

$messagebody = @{
    updateRequests = @();
    actionResults = @();
}

foreach ($item in $testplan_result.testCaseList)
{
    $testcaseid = $item.externalId
    $suiteid = $item.testGroup.externalId

    $testpoints_uri = "${base_test_uri}/plans/${env:TESTPLAN_ID}/suites/${suiteid}/points${api51_version}&testCaseId=${testCaseId}"

    $testpoints_result = Invoke-RestMethod `
        -Uri "$testpoints_uri" `
        -Method Get `
        -Headers $auth_header `
        -UseBasicParsing

    $run_id = $testpoints_result.value.lastTestRun.id
    $result_id = $testpoints_result.value.lastResult.id

    if ($run_id -eq 0 -or $result_id -eq 0) { continue }

    $result_uri = "${testrun_base_uri}/${run_id}/results/${result_id}${api51_version}"

    $result = Invoke-RestMethod `
        -Uri "$result_uri" `
        -Method Get `
        -Headers $auth_header `
        -UseBasicParsing

    $updateRequest = @{
        testRunId = $run_id;
        testResultId = $result_id;
        testCaseResult = @{
            id = @{
                testRunId = $run_id;
                testResultId = $result_id;
            };
            owner = $result.owner.id
            testPointId = $result.testPoint.id;
            testCaseRevision = $result.testCaseRevision;
            errorMessage = "";
            configurationName = "Windows 10 + Chrome";
            priority = $result.priority;
            revision = $result.revision;
            _events = @{
                _namedHandlers = @{
                    _namedHandlers = @{
                        _handlers = @{};
                    };
                };
            };
            duration = $result.durationInMs;
            dateStarted = $result.startedDate;
            dataRowCount = 0;
            dateCompleted = $result.completedDate;
            comment = "";
            planId = $env:TESTPLAN_ID;
#            configurationId = 9;
#            state = 1;
            testCaseTitle = $result.testCaseTitle;
#            outcome = 2;
            testCaseId = $item.externalId;
            runBy = $result.runBy.id;
            testCaseArea = "";
        };
    }

    $messagebody.updateRequests += $updateRequest

    foreach ($stepItem in $item.testStepList) {
        $actionResult = @{
            id = @{
                testRunId = $run_id;
                testResultId = $result_id
            };
            actionPath = $step.externalId;
            errorMessage = "";
            revision = $result.revision;
            duration = $result.durationInMs;
            isSubStep = false;
            dateStarted = $result.startedDate;
            dateCompleted = $result.completedDate;
            indexString = $step.externalId;
            actionId = $step.externalId;
            actionLogTimeStamp = $result.startedDate;
#            iterationId = 1;
#            outcome = 2;
        }

        $messagebody.actionResults += $actionResult
    }
}

$tesuris = "${env:SYSTEM_COLLECTIONURI}/_api/_testresult/Update?teamId=&__v=5"

$new_result = Invoke-RestMethod `
    -Uri "$tesuris" `
    -Method POST `
    -Headers $auth_header `
    -UseBasicParsing `
    -Body ($messagebody | ConvertTo-Json -depth 100 | Out-String) `
    -ContentType $contenttype

($messagebody | ConvertTo-Json -depth 100 | Out-String)
($new_result | ConvertTo-Json -depth 100 | Out-String)

$now = [System.DateTime]::Now
Write-Host "Finishing at ${now}"