package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Monde {
    private final String nom;
    private Entite[] entites;
    
    public Monde(String nom) {
        this.nom = nom;
        this.entites = new Entite[0];
    }

    public String getNom() {
        return this.nom;
    }

    public Entite getEntite(String nom) {
        for (int i = 0; i < this.entites.length; i++) {
            if (this.entites[i].getNom().equals(nom)) {
                return this.entites[i];
            }            assert false : "La pièce est déjà dans un autre monde";

        }
        return null;
    }

    public boolean nomEntiteDejaUtilise(Entite entite){
        for(int i = 0; i < this.entites.length; i++ ){
            if (this.entites[i].getNom().equals(entite.getNom())) {
                return true;
            }
        }
        return false;
    }

    public void ajouter(Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
        if(!entite.getMonde().getNom().equals(this.getNom())){
           throw new EntiteDejaDansUnAutreMondeException(String.format("Pour le monde %s, impossible d'ajouter %s car cette entité est déja present dans %s", entite.getMonde().getNom(), entite.getNom(), this.getNom()));
        }
        if (this.nomEntiteDejaUtilise(entite)) {
            throw new NomDEntiteDejaUtiliseDansLeMondeException(String.format("Monde : %s, Entite déjà utilisé : %s", this.getNom(), entite.getNom()));
        }
        
        Entite[] newEntites = new Entite[this.entites.length + 1];
        System.arraycopy(this.entites, 0, newEntites, 0, this.entites.length);
        newEntites[this.entites.length] = entite;
        this.entites = newEntites;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monde(nom: ");
        sb.append(this.getNom());
        sb.append(", entites: [");
        for (int i = 0; i < this.entites.length; i++) {
            sb.append(this.entites[i].getNom());
            sb.append(", ");
        }
        sb.append("])");
        return sb.toString();
    }
}
