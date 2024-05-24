package main.java.graphe;

import java.util.*;

public class GrapheLAdj extends Graphe{
    private HashMap<String,HashMap<String,Integer>> listeAdj;

    public GrapheLAdj(){
        listeAdj = new HashMap<>();
    }
    public GrapheLAdj(String s) {
        this();
        peupler(s);
    }
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud))
            listeAdj.put(noeud, new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        // on ajoute les sommets si ils n'existent pas
        ajouterSommet(source);
        ajouterSommet(destination);

        if (contientArc(source,destination))
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);

        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);

        // on ajoute le sommet
        listeAdj.get(source).put(destination,valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        // on supprime les arcs en lien avec ce sommet
        for(HashMap<String,Integer> successeur : listeAdj.values()){
            if(successeur.containsKey(noeud) || successeur.containsValue(noeud))
                listeAdj.values().remove(successeur);
        }
        // on supprime le sommet
        listeAdj.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientSommet(source) && contientSommet(destination)) {
            if (!contientArc(source, destination)) {
                throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
            }
             listeAdj.get(source).remove(destination);
        }
        else {
            throw new IllegalArgumentException("Sommet source et/ou sommet de destination introuvable : " + source + ", " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>(listeAdj.keySet());
        Collections.sort(sommets);
        return sommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successors = new ArrayList<>();
        if (listeAdj.containsKey(sommet)) {
            for (Map.Entry<String, Integer> entry : listeAdj.get(sommet).entrySet()) {
                successors.add(entry.getKey());
            }
        }
        return successors;
    }

    @Override
    public int getValuation(String src, String dest) {
        if(contientArc(src,dest)){
            for(Map.Entry<String, Integer> successeurs : listeAdj.get(src).entrySet()){
                if(successeurs.getKey().equals(dest))
                    return successeurs.getValue();
            }
        }
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeAdj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return listeAdj.get(src).containsKey(dest);
    }
}
