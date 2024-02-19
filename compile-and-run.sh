#!/bin/sh

rm -rf classes
rm lib/asiaventure.jar

javac -classpath ./classes -d ./classes/ -sourcepath ./src/ ./src/fr/insaRouen/iti/prog/asiaventure/Main.java 
jar cvf lib/asiaventure.jar -C classes .
cd classes
java -ea fr.insaRouen.iti.prog.asiaventure.Main
cd ..
