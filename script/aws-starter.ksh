#!/bin/bash
##command-jenkins- BUILD_ID=dontKillMe /data/jenkins-scripts/test-script.sh 9000 DEV

# COMMAND LINE VARIABLES
# deploy port SECOND ARGUMENT
# Ex: 8090 | 8091 | 8092 
echo start
appJarName=$3
echo appJarName

serverPort=$1
echo $1

environment=$2
echo $2
echo "***************"
echo $WORKSPACE
echo "***************"
whoami
sudo su
whoami
echo $2
echo "***************"
echo $WORKSPACE
echo "***************"
#####
##### DONT CHANGE HERE ##############
#jar file
# $WORKSPACE is a jenkins var

echo $WORKSPACE
sourFile=$WORKSPACE/target/spring-rest-1.0.8.jar
echo $sourFile
destFile=/opt/jars/$environment/spring-rest-1.0.8.jar
echo $destFile
### FUNCTIONS
##############
ls -l /opt/
ls -l /opt/jars/
ls -l /opt/jars/dev/

stopServer(){
	echo “Stoping process on port: $serverPort”
	echo insideStop
	echo ” “
	echo “Stoping process on port: $serverPort”
	fuser -k $severport/tcp > redirection &
	echo ” “
}

deleteFiles(){
	echo “Deleting $destFile”
	rm -rf $destFile
	echo ” “
}

copyFiles(){
	echo “Copying files from $sourFile”
	cp $sourFile $destFile
	chmod 775 $destFile
	ls -l $destFile
	echo ” “
}

run(){
	echo “java -jar $destFile –server.port=$serverPort $properties” | at now + 1 minutes

	nohup nice java -jar $destFile –server.port=$serverPort &

	echo “COMMAND: nohup nice java -jar $destFile “

	echo ” “
}

### FUNCTIONS CALLS
#####################

# 1 – stop server on port …
stopServer

# 2 – delete destinations folder content
deleteFiles

# 3 – copy files to deploy dir
copyFiles

#changeFilePermission
# 4 – start server
run
exit
