package fr.insaRouen.iti.prog.asiaventure;

public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin {
    private ConditionDeFin[] cfs;

    public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatDuJeu, ConditionDeFin... cfs) {
        super(etatDuJeu);
        this.cfs = cfs;
    }

    @Override
    public EtatDuJeu verifierCondition() {
        boolean verifie = true;
        for (ConditionDeFin cf : this.cfs) {
            EtatDuJeu etat = cf.verifierCondition();
            if (etat == EtatDuJeu.ECHEC) {
                return EtatDuJeu.ECHEC;
            } else if (etat == EtatDuJeu.ENCOURS) {
                verifie = false;
            }
        }
        if (verifie) {
            return this.getEtatConditionVerifiee();
        } else {
            return EtatDuJeu.ENCOURS;
        }
    }
}
