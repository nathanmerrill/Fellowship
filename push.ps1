cd KoTHComm
git add .
git commit -m "Updates"
git push origin HEAD:master
cd ../
git checkout master
gradle build
java -jar build/libs/Fellowship.jar download compile
git add .
git commit -m "Updates"
git push origin master
git checkout archives
git merge master
cp build/libs/Fellowship.jar Fellowship.jar
7z a Fellowship.zip Fellowship.jar -aou
7z a Fellowship.zip submissions -aou
7z a FellowshipPlayers.zip submissions -aou
git add .
git commit -m "Updates"
git push origin archives
git checkout master