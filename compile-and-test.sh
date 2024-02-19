#!/bin/sh

rm -rf classes classestests

javac -classpath ./classes:./classestests:/lib/hamcrest-library.jar:/lib/junit4.jar -d ./classestest -sourcepath ./srctests/ ./srctests/fr/insaRouen/iti/prog/asiaventure/TestEntite.java
cd classestests
java -ea org.junit.runner.JUnitCore:lib.hamcrest-library.jar:lib.junit4.jar: fr.insaRouen.iti.prog.asiaventure.TestEntite
cd ..
