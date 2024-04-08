package fr.insaRouen.iti.prog.asiaventure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.Scanner;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.JoueurHumain;

public class Simulateur {
    private Monde monde;
    private Objet[] conditionsDeFin;

    public Simulateur(Monde monde, Objet... conditionsDeFin) {
        this.monde = monde;
        this.conditionsDeFin = conditionsDeFin;
    }

    public Simulateur(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.monde = (Monde) ois.readObject();
        this.conditionsDeFin = new Objet[0];
    }

    public Simulateur(Reader reader) throws NomDEntiteDejaUtiliseDansLeMondeException {
        Monde monde = null;

        Scanner s = new Scanner(reader);
        while (s.hasNext()) {
            switch (s.next()) {
                case "Monde":
                    monde = Simulateur.construitMonde(s);
                    break;
                case "Piece":
                    Simulateur.construitPiece(s, monde);
                    break;
                case "Porte":
                    Simulateur.construitPorte(s, monde);
                    break;
                case "PorteSerrure":
                    Simulateur.construitPorteSerrure(s, monde);
                    break;
                case "Clef":
                    Simulateur.construitClef(s, monde);
                    break;
                case "JoueurHumain":
                    Simulateur.construitJoueurHumain(s, monde);
                    break;
                default:
                    break;
            }
        }

        this.monde = monde;
        this.conditionsDeFin = new Objet[0];
    }

    private static Monde construitMonde(Scanner s) {
        String nom = s.next();
        return new Monde(nom);
    }

    private static void construitPiece(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next();
        new Piece(nom, monde);
    }

    private static void construitPorte(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next();
        String piece1 = s.next();
        String piece2 = s.next();
        new Porte(nom, monde, monde.getPiece(piece1), monde.getPiece(piece2));
    }

    private static void construitPorteSerrure(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next();
        String piece1 = s.next();
        String piece2 = s.next();
        new Porte(nom, monde, new Serrure(monde), monde.getPiece(piece1), monde.getPiece(piece2));
    }

    private static void construitClef(Scanner s, Monde monde) {
        String nom_porte = s.next();
        String nom_piece = s.next();
        Porte porte = monde.getPorte(nom_porte);
        Serrure serrure = porte.getSerrure();
        Clef clef = serrure.creerClef();
        monde.getPiece(nom_piece).deposer(clef);
    }

    private static void construitJoueurHumain(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next();
        int pointVie = s.nextInt();
        int pointForce = s.nextInt();
        String piece = s.next();
        new JoueurHumain(nom, monde, pointVie, pointForce, monde.getPiece(piece));
    }

    public void enregister(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.monde);
    }

    public String toString() {
        return "Simulateur(monde: " + this.monde + ", conditionsDeFin: " + this.conditionsDeFin + ")";
    }
}
