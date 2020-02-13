$base_uri = "${env:SYSTEM_COLLECTIONURI}/_apis/test/runs"

$automated_param = "&automated=true"
$build_uri_param = "&baseuri=${env:BUILD_BUILDURI}"
$run_id_param = "&baseuri=${env:BUILD_BUILDURI}"
$api_version = "?api-version=5.1"
$details = "detailsToInclude=WorkItems,Iterations"

$full_uri_runs = "${base_uri}${automated_param}${build_uri_param}"


$full_uri_results = "${base_uri}/${run_id}/results${api_version}"

Write-Host "Invoking ${full_uri}"

Invoke-RestMethod -Uri $full_uri