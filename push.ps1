$KoTHCommMessage = Read-Host -Prompt 'KoTHComm Commit message:'
$FellowshipMessage = Read-Host -Prompt 'Fellowship Commit message:'
if (!([string]::IsNullOrEmpty($KoTHCommMessage))){
cd KoTHComm
git add .
git commit -m "$KoTHCommMessage"
git push origin HEAD:master
cd ../
git checkout master
}
if (!([string]::IsNullOrEmpty($FellowshipMessage))){
gradle build
git add .
git commit -m "$FellowshipMessage"
git push origin master
git checkout archives
git merge master
7z a Fellowship.7z build/libs/Fellowship.jar -aou
git add .
git commit -m "$FellowshipMessage"
git push origin archives
git checkout master
}