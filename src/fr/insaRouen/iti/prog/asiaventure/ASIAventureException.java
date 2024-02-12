package fr.insaRouen.iti.prog.asiaventure;

public class ASIAventureException extends Exception {
    String msg;

    public ASIAventureException() {
        super();
    }

    public ASIAventureException(String msg) {
        super();
        this.msg = msg;
    }
}
