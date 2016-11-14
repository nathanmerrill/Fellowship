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
java -jar build/libs/Fellowship.jar download compile
git add .
git commit -m "$FellowshipMessage"
git push origin master
git checkout archives
git merge master
cp build/libs/Fellowship.jar Fellowship.jar
7z a Fellowship.zip Fellowship.jar -aou
git add .
git commit -m "$FellowshipMessage"
git push origin archives
git checkout master
}