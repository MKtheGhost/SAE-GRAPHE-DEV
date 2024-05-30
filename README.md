# SAE 2.02 GRAPHES ET DEVELOPPEMENT OBJET
## Présentation du projet 
  Ce projet portera sur la création et manipulation d'un graphe avec Java et l'étude de l'algorithme de Dijkstra pour calculer le plus court chemin entre les stations de métro de Paris.
## Notions étudiées :
- polymorphisme
- sous-typage
  
## Membres de l'équipe :
- Lucie YAN (luciole)
- Nathan MACIEL LAVERGNE (JAIDISSHASOWN)
- Caio ESMAGNOTO (esmagnoto)
- Thi Minh Khuê PHAM (MKtheGhost)
- Salma GUIRAT

## Partie 1 :
Création des représentations des graphes Matrice d'adjacence, liste d'adjacence, liste d'arc

![UML](https://github.com/MKtheGhost/SAE-GRAPHE-DEV/blob/main/images/UML.png?raw=true)


## Partie 2 :
Création et test de la classe Dijkstra et comparaison du temps d'exécution de Dijkstra sur les différentes représentation de graphes, en utilisant des graphes de taille différentes.

### Tests de de d'exécution (résultat de test dans fichier images) :

Graphes de 10 sommets :
- GrapheLArcs : 3 millisecondes
- GrapheMAdj : 3 millisecondes
- GrapheLAdj : 7 millisecondes
- GrapheHHAdj : 6 millisecondes

Graphes de 1 000 sommets :
- GrapheLArcs : 55 550 millisecondes
- GrapheMAdj : 34 millisecondes
- GrapheLAdj : 20 millisecondes
- GrapheHHAdj : 25 millisecondes

Graphes de 10 000 sommets :
- GrapheLArcs : indéterminable avec un pc portable
- GrapheMAdj : indéterminable avec un pc portable
- GrapheLAdj : 94 millisecondes
- GrapheHHAdj : 88 millisecondes

Graphes de 100 000 sommets :
- GrapheLArcs : indéterminable avec un pc portable
- GrapheMAdj : indéterminable avec un pc portable
- GrapheLAdj : 637 millisecondes
- GrapheHHAdj : 666 millisecondes

Conclusion au test : les représentation de graphe les plus rapides sont GrapheHHAdj et GrapheLAdj, avec les deux autres algorithmes inexécutable sur des graphes de très grande taille sur une machine peu performante.
