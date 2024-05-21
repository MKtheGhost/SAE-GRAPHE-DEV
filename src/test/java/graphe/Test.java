package test.java.graphe;

import main.java.graphe.IGraphe;
import test.java.graphe.IGrapheTest;

public final class Test {
    public static void main(final String[] args) {
        IGrapheTest test = new IGrapheTest();

        // peupler toutes les graphes et les tester
        test.exo3_1Maths();
        //importer graphe de test dans toutes les représentation de graphe
        test.petitTestImportation();

        //importer();
    }
}
