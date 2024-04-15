package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLArcs extends Graphe {
    private HashMap<HashMap<String,String>,Integer> listeArc;
    private ArrayList<String> listeSommet;

    public GrapheLArcs(){
        listeArc = new HashMap<>();
        listeSommet = new ArrayList<>();
    }

    public GrapheLArcs(String s){
        this();
        this.peupler(s);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud))
            listeSommet.add(noeud);
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);
        ajouterSommet(destination);

        if (contientArc(source,destination))
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);

        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);

        HashMap<String,String> arc = new HashMap<>();
        arc.put(source,destination);
        listeArc.put(arc,valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        // on supprime les arcs en lien avec ce sommet
        for(Map.Entry<HashMap<String,String>, Integer> entry : listeArc.entrySet()){
            if(entry.getKey().containsKey(noeud) || entry.getKey().containsValue(noeud))
                listeArc.remove(entry);
        }
        // on supprime le sommet
        listeSommet.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientSommet(source) && contientSommet(destination)) {

            if (!contientArc(source, destination)) {
                throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
            }

            HashMap<String,String> arc = new HashMap<>();
            arc.put(source,destination);
            listeArc.remove(arc);
        }
        else {
            throw new IllegalArgumentException("Sommet source et/ou sommet de destination introuvable : " + source + ", " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        return listeSommet;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successors = new ArrayList<>();

        for (Map.Entry<HashMap<String,String>, Integer> entry : listeArc.entrySet()) {
            if(entry.getKey().containsKey(sommet))
                successors.add(entry.getKey().get(sommet));
        }
        return successors;
    }

    @Override
    public int getValuation(String src, String dest) {
        for(Map.Entry<HashMap<String,String>, Integer> entry : listeArc.entrySet()){
            if(entry.getKey().containsKey(src) && entry.getKey().containsValue(dest))
                return entry.getValue();
        }
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeSommet.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for(Map.Entry<HashMap<String,String>, Integer> entry : listeArc.entrySet()){
            if(entry.getKey().containsKey(src) && entry.getKey().containsValue(dest))
                return true;
        }

        return false;
    }
}
