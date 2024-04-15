package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.Executable;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;

import java.util.Map;
import java.util.Set;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Monde implements Serializable {
    private final String nom;
    private Map<String, Entite> entites = new HashMap<>();
    
    public Monde(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public Entite getEntite(String nom) {
        return this.entites.get(nom);
    }

    public Piece getPiece(String nom) {
        Entite entite = this.getEntite(nom);
        if (entite instanceof Piece) {
            return (Piece) entite;
        }
        return null;
    }

    public Porte getPorte(String nom) {
        Entite entite = this.getEntite(nom);
        if (entite instanceof Porte) {
            return (Porte) entite;
        }
        return null;
    }

    public Set<String> getAllNomsEntites() {
        return this.entites.keySet();
    }

    public Collection<Executable> getExecutables() {
        return this.entites.values().stream().filter(entite -> entite instanceof Executable).map(entite -> (Executable) entite).toList();
    }

    public Collection<Entite> getEntites() {
        return this.entites.values();
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
