#!/bin/sh

rm -rf classes

javac -classpath ./classes -d ./classes/ -sourcepath ./src/ ./src/fr/insaRouen/iti/prog/asiaventure/Main.java 
cd classes
java -ea fr.insaRouen.iti.prog.asiaventure.Main
cd ..
