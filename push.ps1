$KoTHCommMessage = Read-Host -Prompt 'KoTHComm Commit message:'
$FellowshipMessage = Read-Host -Prompt 'Fellowship Commit message:'
if (!([string]::IsNullOrEmpty($KoTHCommMessage))){
cd KoTHComm
git add .
git commit -m "$KoTHCommMessage"
git push origin HEAD:master
cd ../
}
if (!([string]::IsNullOrEmpty($FellowshipMessage))){
git checkout master
gradle build
git add .
git commit -m "$FellowshipMessage"
git push origin master
}