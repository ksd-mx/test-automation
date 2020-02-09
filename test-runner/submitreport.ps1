$base_uri = "${env:SYSTEM_COLLECTIONURI}/_apis/test/runs?api-version=5.1"
$automated_param = "&automated=true"
$build_uri_param = "&baseuri=${env:BUILD_BUILDURI}"
$full_uri = "${base_uri}${automated_param}${build_uri_param}"

Write-Host "Invoking ${full_uri}"

Invoke-RestMethod -Uri $full_uri