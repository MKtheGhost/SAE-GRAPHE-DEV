package main.java.graphe;

import java.util.*;

public class Dijkstra {


        public static void dijkstraPile(IGraphe g, String source, Map<String, Integer> dist, Map<String, String> prev) {
            Set<String> visitee = new LinkedHashSet<>();
            Stack<String> pile = new Stack<>();

            pile.push(source);
            dist.put(source, 0);

            while (!pile.isEmpty()) {
                String noeud = pile.pop();

                if (!visitee.contains(noeud)) {
                    visitee.add(noeud);

                    for (String succ : g.getSucc(noeud)) {
                        if (!visitee.contains(succ)) {
                            pile.push(succ);
                        }

                        int newDist = dist.get(noeud) + g.getValuation(noeud, succ);
                        if (newDist < dist.getOrDefault(succ, Integer.MAX_VALUE)) {
                            dist.put(succ, newDist);
                            prev.put(succ, noeud);
                        }
                    }
                }
            }
        }


    public static void dijkstraFile(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
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
    }

    // calcul chemin entre le noeud et la source
    private static int calculChemin(Map<String, Integer> dist, Map<String, String> prev, String succ, String noeud,IGraphe g){
        // chemin = valuation entre noeud de g et son successeur + valuation des chemins d'avant
        if(dist.get(prev.get(noeud)) == null){
            return g.getValuation(noeud,succ);
        }
        else{
            return g.getValuation(noeud,succ) + dist.get(noeud);
        }
    }

    public static void dijkstraTest(IGraphe g, String source, Map<String,Integer> dist, Map<String,String> prev) {
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




    // Method 1
    // Dijkstra's Algorithm
    public static void dijkstra(IGraphe g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<String> settled = new HashSet<>();
        Map<String, Integer> pqMap = new HashMap<>(); // Track the values in the priority queue

        // Initialisation
        for (String sommet : g.getSommets()) {
            dist.put(sommet, Integer.MAX_VALUE);
            prev.put(sommet, null);
        }
        dist.put(source, 0);
        pq.add(source);
        pqMap.put(source, 0);

        while (!pq.isEmpty()) {
            String u = pq.poll();
            pqMap.remove(u);

            if (!settled.add(u)) {
                continue;
            }

            // Traiter les voisins
            for (String voisin : g.getSucc(u)) {
                if (!settled.contains(voisin)) {
                    int newDist = dist.get(u) + g.getValuation(u, voisin);

                    if (newDist < dist.get(voisin)) {
                        dist.put(voisin, newDist);
                        prev.put(voisin, u);

                        // Only add to the queue if it's not already there or the new distance is shorter
                        if (!pqMap.containsKey(voisin) || newDist < pqMap.get(voisin)) {
                            pq.add(voisin);
                            pqMap.put(voisin, newDist);
                        }
                    }
                }
            }
        }
    }



}




