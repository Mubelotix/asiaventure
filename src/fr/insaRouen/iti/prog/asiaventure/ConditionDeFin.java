package fr.insaRouen.iti.prog.asiaventure;

public abstract class ConditionDeFin implements java.io.Serializable {
    private EtatDuJeu etatConditionVerifiee;

    public ConditionDeFin(EtatDuJeu etatConditionVerifiee) {
        this.etatConditionVerifiee = etatConditionVerifiee;
    }

    public EtatDuJeu getEtatConditionVerifiee() {
        return this.etatConditionVerifiee;
    }

    public abstract EtatDuJeu verifierCondition();
}
