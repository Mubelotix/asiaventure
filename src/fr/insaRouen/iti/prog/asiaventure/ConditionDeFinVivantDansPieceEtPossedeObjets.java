package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFinConjonctionConditionDeFin {
    ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece, Objet[] objets) {
        super(etatConditionVerifiee,
        new ConditionDeFinVivantDansPiece(
            etatConditionVerifiee,
            vivant,
            piece
        ),
        new ConditionDeFinVivantPossedeObjets(
            etatConditionVerifiee,
            vivant,
            objets
        ));
    }
    
    //@Override
    //public EtatDuJeu verifierCondition() {
    //    for (Objet objet : this.objets) {
    //        if (!this.vivant.possede(objet)) {
    //            return EtatDuJeu.ENCOURS;
    //        }
    //    }
    //    if (this.vivant.getPiece().getNom().equals(this.piece.getNom())) {
    //        return this.getEtatConditionVerifiee();
    //    }
    //    return EtatDuJeu.ENCOURS;
    //}
}
