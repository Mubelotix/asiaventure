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

public class Simulateur implements java.io.Serializable {
    private Monde monde;
    private Objet[] conditionsDeFin;

    public Simulateur(Monde monde, Objet... conditionsDeFin) {
        this.monde = monde;
        this.conditionsDeFin = conditionsDeFin;
    }

    public Simulateur(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        Simulateur deserialized = (Simulateur) ois.readObject();
        this.monde = deserialized.monde;
        this.conditionsDeFin = deserialized.conditionsDeFin;
    }

    public Simulateur(Reader reader) throws NomDEntiteDejaUtiliseDansLeMondeException {
        Scanner s = new Scanner(reader);
        while (s.hasNext()) {
            switch (s.next()) {
                case "Monde":
                    this.monde = construitMonde(s);
                    break;
                case "Piece":
                    construitPiece(s, this.monde);
                    break;
                case "Porte":
                    construitPorte(s, this.monde);
                    break;
                case "PorteSerrure":
                    construitPorteSerrure(s, this.monde);
                    break;
                case "Clef":
                    construitClef(s, this.monde);
                    break;
                case "JoueurHumain":
                    construitJoueurHumain(s, this.monde);
                    break;
                default:
                    break;
            }
        }

        this.conditionsDeFin = new Objet[0];
    }

    private Monde construitMonde(Scanner s) {
        String nom = s.next().replaceAll("\"", "");
        return new Monde(nom);
    }

    private void construitPiece(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        new Piece(nom, monde);
    }

    private void construitPorte(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        Piece piece1 = monde.getPiece(s.next().replaceAll("\"", ""));
        Piece piece2 = monde.getPiece(s.next().replaceAll("\"", ""));
        new Porte(nom, monde, piece1, piece2);
    }

    private void construitPorteSerrure(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        Piece piece1 = monde.getPiece(s.next().replaceAll("\"", ""));
        Piece piece2 = monde.getPiece(s.next().replaceAll("\"", ""));
        Serrure serrure = new Serrure(monde);
        new Porte(nom, monde, serrure, piece1, piece2);
    }

    private void construitClef(Scanner s, Monde monde) {
        Porte porte = monde.getPorte(s.next().replaceAll("\"", ""));
        Serrure serrure = porte.getSerrure();
        Clef clef = serrure.creerClef();
        Piece piece = monde.getPiece(s.next().replaceAll("\"", ""));
        piece.deposer(clef);
    }

    private void construitJoueurHumain(Scanner s, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = s.next().replaceAll("\"", "");
        int pointVie = s.nextInt();
        int pointForce = s.nextInt();
        Piece piece = monde.getPiece(s.next().replaceAll("\"", ""));
        new JoueurHumain(nom, monde, pointVie, pointForce, piece);
    }

    public void enregister(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this);
    }

    public String toString() {
        return "Simulateur(monde: " + this.monde + ", conditionsDeFin: [])";
    }
}
