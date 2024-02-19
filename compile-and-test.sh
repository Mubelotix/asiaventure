#!/bin/sh

rm -rf classes classestests

javac -classpath ./classes:./classestests:/usr/share/java/hamcrest-library.jar:/usr/share/java/junit4.jar -d ./classestest -sourcepath ./srctests/ ./srctests/fr/insaRouen/iti/prog/asiaventure/TestEntite.java
cd classestests
java -ea org.junit.runner.JUnitCore:usr.share.java.hamcrest-library.jar:usr.share.java.junit4.jar: fr.insaRouen.iti.prog.asiaventure.TestEntite
cd ..
