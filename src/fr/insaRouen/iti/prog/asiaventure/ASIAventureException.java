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
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf('.') + 1);
        return String.format("%s(%s)", className, this.msg);
    }
}
