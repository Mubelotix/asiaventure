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
        this.etat = Etat.OUVERT;
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
    }

    public Porte(String nom, Monde monde, Object serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.etat = Etat.OUVERT;
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.serrure = serrure;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
    }

    public boolean activableAvec(Objet obj) {
        return false;
    }

    public void activer() throws ActivationImpossibleException {
        switch(this.etat) {
            case OUVERT:
                this.etat = Etat.FERME;
                break;
            case FERME:
                this.etat = Etat.OUVERT;
                break;
            default:
                throw new ActivationImpossibleException("La porte est fermée.");
        }
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleException, ActivationImpossibleAvecObjetException {
        throw new ActivationImpossibleAvecObjetException("La porte ne peut pas être activée avec un objet.");
    }

    public Etat getEtat() {
        return this.etat;
    }

    public Object getSerrure() {
        return this.serrure;
    }

    public Piece getPieceAutreCote(Piece piece) {
        if (piece.getNom().equals(this.pieceA.getNom())) {
            return this.pieceB;
        } else {
            return this.pieceA;
        }
    }

    public String toString() {
        return String.format("Porte(nom: %s, monde: %s, etat: %s, serrure: %s, pieceA: %s, pieceB: %s)", this.getNom(), this.getMonde().getNom(), this.getEtat(), this.serrure, this.pieceA, this.pieceB);
    }
}
