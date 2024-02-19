#!/bin/sh

rm -rf classestest

javac -classpath ./classes:./classestest:/usr/share/java/hamcrest-library.jar:/usr/share/java/junit4.jar -d ./classestest -sourcepath ./srctests/ ./srctests/fr/insaRouen/iti/prog/asiaventure/AllTests.java
java -classpath /usr/share/java/hamcrest-library.jar:/usr/share/java/junit4.jar:./classes:./classestests org.junit.runner.JUnitCore fr.insaRouen.iti.prog.asiaventure.AllTests
