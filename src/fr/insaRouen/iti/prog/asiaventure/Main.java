package fr.insaRouen.iti.prog.asiaventure;

import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws ASIAventureException, Exception {
        Simulateur s = new Simulateur(new FileReader(new File("/home/mubelotix/TD/PROGAV/asiaventure/world.txt")));
        System.out.println(s);
    }
}
