package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

public class Porte extends ElementStructurel implements Activable {
    private Etat etat;
    private Object serrure;
    private Piece pieceA;
    private Piece pieceB;

    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pieceA = pieceA;
        this.pieceA = pieceB;
    }

    public Porte(String nom, Monde monde, Object serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pieceA = pieceA;
        this.pieceA = pieceB;
        this.serrure = serrure;
    }

    public boolean activableAvec(Objet obj) {
        return false;
    }

    public void activer() throws ActivationImpossibleException {
        // TODO    
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleException, ActivationImpossibleAvecObjetException {
        throw new ActivationImpossibleAvecObjetException();
    }

    public Etat getEtat() {
        return this.etat; // TODO: Clone
    }

    public Object getSerrure() {
        return this.serrure;
    }

    public Piece getPieceAutreCote(Piece piece) {
        if (piece.equals(pieceA)) {
            return this.pieceB;
        } else {
            return this.pieceA;
        }
    }

    public String toString() {
        return String.format("Porte(nom: %s, monde: %s, etat: %s, serrure: %s, pieceA: %s, pieceB: %s)", this.getNom(), this.getMonde().getNom(), this.getEtat(), this.serrure, this.pieceA, this.pieceB);
    }
}
