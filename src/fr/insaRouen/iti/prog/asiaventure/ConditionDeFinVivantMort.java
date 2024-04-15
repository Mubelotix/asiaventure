package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

public class ConditionDeFinVivantMort extends ConditionDeFin {
    Vivant vivant;
    
    public ConditionDeFinVivantMort(EtatDuJeu etatConditionVerifiee, Vivant vivant) {
        super(etatConditionVerifiee);
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierCondition() {
        if (this.vivant.estMort()) {
            return this.getEtatConditionVerifiee();
        }
        return EtatDuJeu.ENCOURS;
    }

}
