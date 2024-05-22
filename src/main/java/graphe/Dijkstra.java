package main.java.graphe;

import java.util.*;

public class Dijkstra {

    public static void dijkstraLucie1(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
        Set<String> visitee = new LinkedHashSet<>(); // liste d'element unique
        Stack<String> pile = new Stack<>();

        pile.push(source);
        dist.put(source,0);

        while(!pile.isEmpty()){ // tant qu'on a pas visité tous les sommets du graphe
            String noeud = pile.pop();

            if(!visitee.contains(noeud)){ // si on a pas encore visité ce noeud
                visitee.add(noeud);

                // on rajoute à la pile tout les successeurs de ce noeud
                for (String succ : g.getSucc(noeud)){
                    pile.push(succ);

                    // on rajoute le chemin le plus court si il n'a pas été visité
                    if(!prev.containsKey(succ)){
                        prev.put(succ,noeud);
                    }
                    // on rajoute le plus court chemin si il n'existe pas sinon on remplace
                    if(!dist.containsKey(succ)){
                        dist.put(succ, calculChemin(dist,prev,succ,noeud,g));
                    } else if (calculChemin(dist,prev,succ,noeud,g) < dist.get(succ)) {
                        dist.replace(succ,calculChemin(dist,prev,succ,noeud,g));
                        prev.replace(succ,noeud);
                    }
                }
            }
        }
    }

    public static void DijkstraLucie2(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
        Set<String> visitee = new LinkedHashSet<String>();
        Queue<String> file = new LinkedList<String>();
        file.add(source);
        visitee.add(source);
        dist.put(source,0);

        while (!file.isEmpty()) {
            String noeud = file.poll();
            for (String succ : g.getSucc(noeud)) {
                if (!visitee.contains(succ)) {
                    visitee.add(succ);
                    file.add(succ);

                    // on rajoute le chemin le plus court si il n'a pas été visité
                    if(!prev.containsKey(succ)){
                        prev.put(succ,noeud);
                        //System.out.println(prev);
                    }

                    // on rajoute le plus court chemin si il n'existe pas sinon on remplace
                    if(!dist.containsKey(succ)){
                        dist.put(succ, calculChemin(dist,prev,succ,noeud,g));
                    }

                    if (calculChemin(dist,prev,succ,noeud,g) < dist.get(succ)) {
                        dist.replace(succ,calculChemin(dist,prev,succ,noeud,g));
                        System.out.println("replace condition");
                        prev.replace(succ,noeud);
                    }
                    //System.out.println(dist);
                    //System.out.println("test");
                    //System.out.println(noeud);
                    //System.out.println(succ);
                    System.out.println(calculChemin(dist,prev,succ,noeud,g));
                    System.out.println(dist.get(succ));



                }
            }
        }
    }

    // calcul chemin entre le noeud et la source
    public static int calculChemin(Map<String, Integer> dist, Map<String, String> prev, String succ, String noeud,IGraphe g){
        // chemin = valuation entre noeud de g et son successeur + valuation des chemins d'avant
        if(dist.get(prev.get(noeud)) == null){
            return g.getValuation(noeud,succ);
        }
        else{
            return g.getValuation(noeud,succ) + dist.get(prev.get(noeud));
        }
    }
}
