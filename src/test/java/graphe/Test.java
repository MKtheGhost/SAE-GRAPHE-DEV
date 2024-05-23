package test.java.graphe;

import main.java.graphe.IGraphe;
import test.java.graphe.IGrapheTest;

import java.io.FileNotFoundException;

public final class Test {
    public static void main(final String[] args) {
        IGrapheTest test3_1 = new IGrapheTest();
        // peupler toutes les graphes et les tester
        test3_1.exo3_1Maths();

        //importer graphe de test dans toutes les représentation de graphe
        IGrapheTest testImportation = new IGrapheTest();
        testImportation.petitTestImportation();

        //test de Dijkstra
        IGrapheTest testImporter = new IGrapheTest();
        try {
            testImporter.importer();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
