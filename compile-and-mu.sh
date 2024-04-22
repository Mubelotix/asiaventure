

#rm -rf classestest
#
#javac -classpath ./classes:./classestest:/usr/share/java/hamcrest-library.jar:/usr/share/java/junit4.jar -d ./classestest -sourcepath ./srctests/:./src ./srctests/fr/insaRouen/iti/prog/asiaventure/AllTests.java
#java -classpath /usr/share/java/hamcrest-library.jar:/usr/share/java/junit4.jar:./classes:./classestest org.junit.runner.JUnitCore fr.insaRouen.iti.prog.asiaventure.AllTests
rm -rf classes

javac -classpath ./classes -d ./classes/ -sourcepath ./src/ ./src/fr/insaRouen/iti/prog/asiaventure/Main.java 
cd classes
java -ea fr.insaRouen.iti.prog.asiaventure.Main
cd ..
