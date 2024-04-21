package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrapheLArcs extends Graphe {
    private ArrayList<Arc> listeArc;
    private ArrayList<String> listeSommets;

    public GrapheLArcs () {
        listeArc = new ArrayList<>();
        listeSommets = new ArrayList<>();
    }
    public GrapheLArcs (String s) {
        this();
        peupler(s);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud)) listeSommets.add(noeud);
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        //ajoute le sommet source si inexistant
        if(!contientSommet(source)) ajouterSommet(source);

        //ajoute le sommet destinataire si inexistant
        if(!contientSommet(destination)) ajouterSommet(destination);

        if (contientArc(source,destination))
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);

        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);

        listeArc.add(new Arc(source,destination,valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        listeSommets.remove(noeud);
        for (String sommet : listeSommets) {
            if (contientArc(noeud,sommet)) oterArc(noeud, sommet);
        }
        for (String sommet : listeSommets) {
            if (contientArc(sommet,noeud)) oterArc(sommet, noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientSommet(source) && contientSommet(destination)) {
            if (!contientArc(source, destination)) {
                throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
            }
            for (int i = 0; i < listeArc.size() ; ++i) {
                if (listeArc.get(i).getSource().equals(source) && listeArc.get(i).getDestination().equals(destination))
                    listeArc.remove(i);
            }
        }
        else {
            throw new IllegalArgumentException("Sommet source et/ou sommet de destination introuvable : " + source + ", " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        return listeSommets;
    }

    @Override
    public List<String> getSucc(String sommet) {
        ArrayList<String> listSucc = new ArrayList<>();
        for (Arc arc : listeArc) {
            if (arc.getSource().equals(sommet)) listSucc.add(arc.getDestination());
        }
        return listSucc;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc : listeArc) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return arc.getValuation();
        }
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (String listeSommet : listeSommets) if (listeSommet.equals(sommet)) return true;
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : listeArc) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest))
                return true;
        }
        return false;
    }
}