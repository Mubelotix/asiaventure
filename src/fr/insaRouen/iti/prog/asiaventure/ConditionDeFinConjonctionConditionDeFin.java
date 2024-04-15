package fr.insaRouen.iti.prog.asiaventure;

public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin {
    private ConditionDeFin[] cfs;

    public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatDuJeu, ConditionDeFin... cfs) {
        super(etatDuJeu);
        this.cfs = new ConditionDeFin[cfs.length];
        for (int i = 0; i < cfs.length; i++) {
            this.cfs[i] = cfs[i];
        }
    }

    @Override
    public EtatDuJeu verifierCondition() {
        for (ConditionDeFin cf : this.cfs) {
            if (cf.verifierCondition() == EtatDuJeu.ENCOURS) {
                return EtatDuJeu.ENCOURS;
            }
        }
        return this.getEtatConditionVerifiee();
    }
}
