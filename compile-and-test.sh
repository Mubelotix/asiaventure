#!/bin/sh

rm -rf classes classestests

javac -classpath ./classes ./classestests -d ./classes/ ./classestest -sourcepath ./src/ ./src/fr/insaRouen/iti/prog/asiaventure/Main.java ./srctests/ ./srctests/MondeTests.java
cd classestests
java -ea org.junit.runner.JUnitCore MondeTests
cd ..
