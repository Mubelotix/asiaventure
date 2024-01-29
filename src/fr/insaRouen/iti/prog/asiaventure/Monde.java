package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Monde {
    private final String nom;
    private Entite[] entites;
    
    public Monde(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public Entite getEntite(String nom) {
        for (int i = 0; i < this.entites.length; i++) {
            if (this.entites[i].getNom().equals(nom)) {
                return this.entites[i];
            }
        }
        return null;
    }

    public void ajouter(Entite entite) {
        Entite[] newEntites = new Entite[this.entites.length + 1];
        System.arraycopy(this.entites, 0, newEntites, 0, this.entites.length);
        newEntites[this.entites.length] = entite.deepCopy();
        this.entites = newEntites;
    }
}
