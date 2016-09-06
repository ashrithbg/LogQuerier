#!/bin/bash 
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home

if [ "$#" -lt 1 ]
then
	echo "Please enter a data path"
    exit 3
fi

if [ "$1" == "--help" ]
then 
	echo "To run this file: ./query.sh <DATA_PATH>"
	exit 3
fi

if [ "$#" -gt 1 ]
then
	echo "Wrong number of arguments : "
    exit 3
fi

CLASSPATH=/Users/ashrith/Documents/Sites/LogQuerier
$JAVA_HOME/bin/java -jar $CLASSPATH/logquerier.jar "$1"
exit 0