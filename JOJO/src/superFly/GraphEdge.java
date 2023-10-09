package superFly;

public class GraphEdge implements Comparable<GraphEdge> {
    String vertex1;
    String vertex2;
    int weight;

    public GraphEdge(String vertex1, String vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int compareTo(GraphEdge other) {
        return this.weight - other.weight;
    }
}