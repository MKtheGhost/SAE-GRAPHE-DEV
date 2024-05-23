package main.java.graphe;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

// ajouterSommet ne fait rien si un sommet est deja present
// ajouterArc leve une IllegalArgumentException si l'arc est deja present
// ajouterArc ajoute les sommets s'ils ne sont pas deja presents
// oterSommet ne fait rien si le sommet n'est pas dans le graphe
// oterArc leve une IllegalArgumentException si l'arc n'est pas present
public interface IGraphe extends IGrapheConst {
	void ajouterSommet(String noeud);
	void ajouterArc(String source, String destination, Integer valeur);
	void oterSommet(String noeud);
	void oterArc(String source, String destination);
	
	// construit un graphe vide a partir d'une chaine
	// au format "A-B(5), A-C(10), B-C(3), C-D(8), E:";
	default void peupler(String str) {
	    assert this.getSommets().isEmpty();
	    String[] arcs = str.split(",\\s*");
	    for (String arc : arcs) {
	        String[] elements = arc.trim().split("-");
	        // extrait le noeud source et ote ":" si nécessaire dans le nom
	        String src = elements[0].replaceAll(":", "");
	        ajouterSommet(src);
	        if (elements.length == 1)
	        	continue; // pas de destination
	        String target = elements[1];
	        if (!target.isEmpty()) {
	             String dest = target.substring(0, target.indexOf('('));
	             int val = Integer.parseInt(target
	                	.substring(target.indexOf('(') + 1,
	                				   target.indexOf(')')));
	             ajouterArc(src, dest, val);
	        }
	    }
	}

	public default void Dijkstra(Graphe g, String source, HashMap<String,Integer> dist, HashMap<String,String> prev) {
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
						dist.put(succ, calculChemin(dist,prev,succ,noeud));
					} else if (calculChemin(dist,prev,succ,noeud) < dist.get(succ)) {
						dist.replace(succ,calculChemin(dist,prev,succ,noeud));
						prev.replace(succ,noeud);
					}
				}
			}
		}
	}
	public default int calculChemin(HashMap<String, Integer> dist, HashMap<String, String> prev, String succ, String noeud){
		return getValuation(noeud,succ) + dist.get(prev.get(noeud));
	}
}
