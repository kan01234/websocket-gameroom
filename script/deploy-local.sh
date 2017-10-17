cd ..

ver=1.0-rc
mvn clean package
mvn deploy:deploy-file -Durl=file:///$HOME/git/aeroplanes-chess/repo -Dfile=$HOME/git/websocket-gameroom/target/websocket-gameroom-$ver.jar -DgroupId=com.dotterbear -DartifactId=websocket-gameroom -Dpackaging=jar -Dversion=$ver