package main.java.graphe;

public class Arc {
    private int valuation;
    private String source, destination;
    public Arc (String source, String destination, int valuation) {
        this.valuation = valuation;
        this.source = source;
        this.destination = destination;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public int getValuation() {
        return valuation;
    }

}
