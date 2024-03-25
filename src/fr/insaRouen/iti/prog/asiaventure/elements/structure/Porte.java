package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Serrure;

public class Porte extends ElementStructurel implements Activable {
    private boolean ouvert = false;
    private boolean casse = false;
    private Serrure serrure;
    private Piece pieceA;
    private Piece pieceB;

    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
    }

    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.serrure = serrure;
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
    }

    public boolean activableAvec(Objet obj) {
        return (obj instanceof Clef && obj != null && this.serrure != null) || (obj instanceof PiedDeBiche && obj != null);
    }

    public void activer() throws ActivationImpossibleException {
        switch(this.getEtat()) {
            case OUVERT:
                this.ouvert = false;
                break;
            case FERME:
                this.ouvert = true;
                break;
            case VERROUILLE:
                break;
            case CASSE:
                break;
            default:
                throw new ActivationImpossibleException("La porte est dans un état imprévu.");
        }
    }

    public void activerAvec(Objet obj) throws ActivationException, ActivationImpossibleException {
        switch(this.getEtat()) {
            case OUVERT:
                if (obj instanceof Clef && obj != null && this.serrure != null) {
                    this.activer();
                    this.serrure.activerAvec(obj);
                }
                break;
            case FERME:
                if (obj instanceof Clef && obj != null && this.serrure != null) {
                    this.serrure.activerAvec(obj);
                } else if (obj instanceof PiedDeBiche && obj != null) {
                    this.casse = true;
                }
                break;
            case VERROUILLE:
                if (obj instanceof Clef && obj != null && this.serrure != null) {
                    this.serrure.activerAvec(obj);
                    this.activer();
                } else if (obj instanceof PiedDeBiche && obj != null) {
                    this.casse = true;
                }
                break;
            case CASSE:
                break;
            default:
                throw new ActivationImpossibleException("La porte est dans un état imprévu.");
        }
    }

    public Etat getEtat() {
        if (this.casse) {
            return Etat.CASSE;
        } else if (this.ouvert) {
            return Etat.OUVERT;
        } else if (this.serrure != null && this.serrure.getEtat() == Etat.VERROUILLE) {
            return Etat.VERROUILLE;
        } else {
            return Etat.FERME;
        }
    }

    public Object getSerrure() {
        return this.serrure;
    }

    public Piece getPieceAutreCote(Piece piece) {
        return piece.getNom().equals(this.pieceA.getNom()) ? this.pieceB : this.pieceA;
    }

    public String toString() {
        return String.format("Porte(nom: %s, monde: %s, etat: %s, serrure: %s, pieceA: %s, pieceB: %s)", this.getNom(), this.getMonde().getNom(), this.getEtat(), this.serrure, this.pieceA, this.pieceB);
    }
}
