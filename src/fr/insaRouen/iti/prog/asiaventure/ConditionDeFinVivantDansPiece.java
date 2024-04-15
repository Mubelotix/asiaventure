package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin {
    Vivant vivant;
    Piece piece;
    
    ConditionDeFinVivantDansPiece(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece) {
        super(etatConditionVerifiee);
        this.vivant = vivant;
        this.piece = piece;
    }

    @Override
    public EtatDuJeu verifierCondition() {
        if (this.vivant.getPiece().getNom().equals(this.piece.getNom())) {
            return this.getEtatConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }
}
