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
        String nom = s.next().replaceAll("\"", "");
        return new Monde(nom);
    }

    private static void construitPiece(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        new Piece(nom, monde);
    }

    private static void construitPorte(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next();
        Piece piece1 = monde.getPiece(s.next());
        Piece piece2 = monde.getPiece(s.next());
        new Porte(nom, monde, piece1, piece2);
    }

    private static void construitPorteSerrure(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        Piece piece1 = monde.getPiece(s.next().replaceAll("\"", ""));
        Piece piece2 = monde.getPiece(s.next().replaceAll("\"", ""));
        Serrure serrure = new Serrure(monde);
        new Porte(nom, monde, serrure, piece1, piece2);
    }

    private static void construitClef(Scanner s, Monde monde) {
        Porte porte = monde.getPorte(s.next().replaceAll("\"", ""));
        Serrure serrure = porte.getSerrure();
        Clef clef = serrure.creerClef();
        Piece piece = monde.getPiece(s.next().replaceAll("\"", ""));
        piece.deposer(clef);
    }

    private static void construitJoueurHumain(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        int pointVie = s.nextInt();
        int pointForce = s.nextInt();
        Piece piece = monde.getPiece(s.next().replaceAll("\"", ""));
        new JoueurHumain(nom, monde, pointVie, pointForce, piece);
    }

    public void enregister(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.monde);
    }

    public String toString() {
        return "Simulateur(monde: " + this.monde + ", conditionsDeFin: " + this.conditionsDeFin + ")";
    }
}
