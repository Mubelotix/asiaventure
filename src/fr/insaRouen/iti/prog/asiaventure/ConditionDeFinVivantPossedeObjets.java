package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin {
    private Vivant vivant;
    private Objet[] objets;
    
    ConditionDeFinVivantPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Objet[] objets) {
        super(etatConditionVerifiee);
        this.vivant = vivant;
        this.objets = objets;
    }

    @Override
    public EtatDuJeu verifierCondition() {
        for (Objet objet : this.objets) {
            if (!this.vivant.possede(objet)) {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }    
}
