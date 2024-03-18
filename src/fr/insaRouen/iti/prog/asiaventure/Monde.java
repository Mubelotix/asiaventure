package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import java.util.Map;
import java.util.HashMap;

public class Monde{
    private final String nom;
    private Map<String, Entite> entites = new HashMap<>();
    
    public Monde(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public Entite getEntite(String nom) {
        if(this.entites.isEmpty()){
            return null;
        }
        return this.entites.get(nom);
    }

    private boolean nomEntiteDejaUtilise(Entite entite){
        return this.entites.containsKey(entite.getNom());
    }

    public void ajouter(Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException{
        if(!entite.getMonde().getNom().equals(this.getNom())){
           throw new EntiteDejaDansUnAutreMondeException(String.format("Pour le monde %s, impossible d'ajouter %s car cette entité est déja present dans %s", entite.getMonde().getNom(), entite.getNom(), this.getNom()));
        }
        if (this.nomEntiteDejaUtilise(entite)) {
            throw new NomDEntiteDejaUtiliseDansLeMondeException(String.format("Monde : %s, Entite déjà utilisé : %s", this.getNom(), entite.getNom()));
        }
        
       this.entites.put(entite.getNom(), entite);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monde(nom: ");
        sb.append(this.getNom());
        sb.append(", entites: [");
        this.entites.keySet().stream().forEach(key->{
            sb.append(key);
            sb.append(", ");
        });
        sb.append("])");
        return sb.toString();
    }
}
