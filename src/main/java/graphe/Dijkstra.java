package main.java.graphe;

import java.util.*;

public class Dijkstra {

    public static void dijkstraPile(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
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
        System.out.println(dist);
    }

    public static void DijkstraFile(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
        Set<String> visitee = new LinkedHashSet<String>();
        Queue<String> file = new LinkedList<String>();
        file.add(source);
        visitee.add(source);
        dist.put(source,0);

        while (!file.isEmpty()) {
            String noeud = file.poll(); // remove head of list and affect it to noeud

            int tempValSucc = 0;
            String tempSucc = noeud;

            for (String succ : g.getSucc(noeud)) {
                if (!visitee.contains(succ)) {
                    visitee.add(succ);
                    file.add(succ);


                    // on rajoute le chemin le plus court si il n'a pas été visité
                    if (!prev.containsKey(succ)) {
                        prev.put(succ, noeud);
                        //System.out.println(prev);
                    }
                    // on rajoute le plus court chemin si il n'existe pas sinon on remplace
                    if (!dist.containsKey(succ)) {
                        dist.put(succ, calculChemin(dist, prev, succ, noeud, g));
                    } else if (tempValSucc > dist.get(succ)) {
                        dist.replace(tempSucc, calculChemin(dist, prev, succ, noeud, g));
                        prev.replace(succ, noeud);
                    }
                }


                tempValSucc = dist.get(succ);
                tempSucc = succ;
            }
        }
        System.out.println(dist);
    }

    public static void DijkstraTest(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<String> visited = new HashSet<>();

        for (String noeud : g.getSommets()){
            dist.put(noeud, Integer.MAX_VALUE);
            prev.put(noeud, null);
        }

        dist.put(source,0);
        queue.add(source);

        while (!queue.isEmpty()) {
            String currentNode = queue.poll();

            if(!visited.add(currentNode)){
                continue;
            }

            //parcourir tous les successeurs du noeud en evaluation
            for (String succ : g.getSucc(currentNode)) {
                int succDist = dist.get(currentNode) + g.getValuation(currentNode, succ);

                if (succDist < dist.get(succ)) {
                    dist.put(succ, succDist);
                    prev.put(succ,currentNode);
                    queue.add(succ);

                }
            }
        }

        for (String noeud : dist.keySet()) {
            if (dist.get(noeud) == Integer.MAX_VALUE) {
                dist.put(noeud, -1);
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
            return g.getValuation(noeud,succ) + dist.get(noeud);
        }
    }

}




