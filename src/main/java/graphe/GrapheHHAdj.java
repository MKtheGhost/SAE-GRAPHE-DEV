package main.java.graphe;

import java.util.*;

public class GrapheHHAdj extends Graphe {
    private Map<String, Map<String, Integer>> adjList;

    public GrapheHHAdj() {
        adjList = new HashMap<>();
    }

    public GrapheHHAdj(String s) {
        this();
        this.peupler(s);
    }

    @Override
    public void ajouterSommet(String noeud) {
        adjList.putIfAbsent(noeud, new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (valeur < 0) {
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);
        }
        ajouterSommet(source);
        ajouterSommet(destination);
        if (contientArc(source, destination)) {
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        }
        adjList.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        if (adjList.containsKey(noeud)) {
            adjList.remove(noeud);
            for (Map<String, Integer> edges : adjList.values()) {
                edges.remove(noeud);
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source, destination)) {
            throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
        }
        adjList.get(source).remove(destination);
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(adjList.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        if (adjList.containsKey(sommet)) {
            return new ArrayList<>(adjList.get(sommet).keySet());
        }
        return new ArrayList<>();
    }

    @Override
    public int getValuation(String src, String dest) {
        if (contientArc(src, dest)) {
            return adjList.get(src).get(dest);
        }
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return adjList.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return adjList.containsKey(src) && adjList.get(src).containsKey(dest);
    }
}