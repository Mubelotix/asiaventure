#!/bin/sh

javac -classpath ./classes -d ./classes/ -sourcepath ./src/ ./src/fr/insaRouen/iti/prog/asiaventure/Main.java 
cd classes
java fr.insaRouen.iti.prog.asiaventure.Main
cd ..
