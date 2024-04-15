package graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheMAdj extends Graphe {

    private int[][] matrice;
    private ArrayList<String> listeSommet;

    public GrapheMAdj(){
        matrice = new int[0][0];
        ArrayList<String> listeSommet = new ArrayList<>();
    }

    public GrapheMAdj(String str){
        this();
        this.peupler(str);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud)){
            listeSommet.add(noeud);
            int taille = listeSommet.size();

            int[][] newMatrice = new int[taille][taille];
            for(int i = 0; i< matrice.length; ++i){
                System.arraycopy(matrice[i], 0, newMatrice[i], 0, matrice.length);
            }
            matrice = newMatrice;
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);
        ajouterSommet(destination);

        int indexSource = listeSommet.indexOf(source);
        int indexDestination = listeSommet.indexOf(destination);

        if (matrice[indexSource][indexDestination] > 0) {
            throw new IllegalArgumentException("Un arc existe déjà entre les sommets : " + source + " et " + destination);
        }
        if (valeur < 0)
            throw new IllegalArgumentException("Les valuations ne doivent pas etre negatives " + valeur);

        matrice[indexSource][indexDestination] = valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        if(contientSommet(noeud)){
            int index = listeSommet.indexOf(noeud);
            listeSommet.remove(noeud);

            int taille = listeSommet.size();
            int[][] newMatrice = new int[taille][taille];

            int newLigne = 0;
            for(int i = 0; i < taille; ++i){
                int newColonne = 0;
                if(i != index){
                    for(int j = 0; j < taille; ++j){
                        if(index != j){
                            newMatrice[newLigne][newColonne] = matrice[i][j];
                            newColonne++;
                        }
                    }
                    newLigne++;
                }
            }
            matrice = newMatrice;
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientSommet(source) && contientSommet(destination)) {

            if (!contientArc(source, destination)) {
                throw new IllegalArgumentException("Aucun arc n'existe entre les sommets : " + source + " et " + destination);
            }

            matrice[listeSommet.indexOf(source)][listeSommet.indexOf(destination)] = 0;
        } else {
            throw new IllegalArgumentException("Sommet source et/ou sommet de destination introuvable : " + source + ", " + destination);
        }
    }

    @Override
    public List<String> getSommets() {
        return listeSommet;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        int indexSommet = listeSommet.indexOf(sommet);
        if(contientSommet(sommet)){
            for(int i=0; i< matrice.length; ++i) {
                if (matrice[indexSommet][i] != 0)
                    successeurs.add(listeSommet.get(i));
            }
        }
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[listeSommet.indexOf(src)][listeSommet.indexOf(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return listeSommet.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return (matrice[listeSommet.indexOf(src)][listeSommet.indexOf(dest)] > 0);
    }
}
