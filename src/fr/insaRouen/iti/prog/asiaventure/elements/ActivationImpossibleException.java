package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;

public class ActivationImpossibleException extends ASIAventureException {
    public ActivationImpossibleException() {
        super();
    }

    public ActivationImpossibleException(String msg) {
        super(msg);
    }
}
