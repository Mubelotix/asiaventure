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

    public String toString() {
        return String.format("%s(%s)", this.getClass().getName(), this.msg);
    }
}
