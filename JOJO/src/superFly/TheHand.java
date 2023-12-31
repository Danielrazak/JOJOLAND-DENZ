package superFly;
import java.util.*;
public class TheHand{
    public static List<Edge> assignGraph(String graphStr) {
        List<Edge> edges = new ArrayList<>();
        String[] edgeList = graphStr.split(",");
        for (String edgeStr : edgeList) {
            String[] parts = edgeStr.split(":"); //split into vertices and edge
            String[] vertex = parts[0].split("-"); //split vertices
            String vertex1 = vertex[0];
            String vertex2 = vertex[1];
            int weight = Integer.parseInt(parts[1]); //parts 1=edge as weight
            edges.add(new Edge(vertex1, vertex2, weight));
        }
        return edges;
    }

    public static List<Edge> prim(String graphStr, String rootVertex) {
        List<Edge> edges = assignGraph(graphStr);
        List<Edge> prims = new ArrayList<>();
        List<String> visited = new ArrayList<>();
        visited.add(rootVertex);

        while (visited.size() < getTotalVertices(edges)) {
            int minWeight = Integer.MAX_VALUE;
            Edge minEdge = null;

            for (Edge edge : edges) {
                boolean isConnectedToVisited = visited.contains(edge.vertex1) ^ visited.contains(edge.vertex2);//xor to see connected to either vertex
                if (isConnectedToVisited && edge.weight < minWeight) {
                    minWeight = edge.weight;
                    minEdge = edge;
                }
            }

            if (minEdge != null) {   
                visited.add(visited.contains(minEdge.vertex1) ? minEdge.vertex2 : minEdge.vertex1);//checks which vertex is already visisted or not
                prims.add(minEdge);
            }
        }

        return prims;
    }

    private static int getTotalVertices(List<Edge> edges) {
        ArrayList<String> vertices = new ArrayList<>();
        for (Edge edge : edges) {
            if (!vertices.contains(edge.vertex1)) {
                vertices.add(edge.vertex1);
            }
            if (!vertices.contains(edge.vertex2)) {
                vertices.add(edge.vertex2);
            }
        }
        return vertices.size();
    }

    public void printTheHand() {
        String graphStr = "Town Hall-Morioh Grand Hotel:5,Town Hall-Jade Garden:5,Town Hall-Cafe Deux Magots:4,"
                + "Morioh Grand Hotel-Trattoria Trussardi:6,Morioh Grand Hotel-Jade Garden:3,"
                + "Jade Garden-San Giorgio Maggiore:2,Jade Garden-Joestar Mansion:2,"
                + "Cafe Deux Magots-Jade Garden:3,Cafe Deux Magots-Savage Garden:4,Cafe Deux Magots-Polnareff Land:4,"
                + "Savage Garden-Polnareff Land:6,Savage Garden-Joestar Mansion:4,Savage Garden-Vineyard:8,"
                + "Vineyard-Joestar Mansion:3,Vineyard-Libeccio:6,Vineyard-DIO's Mansion:3,"
                + "Libeccio-Joestar Mansion:6,Libeccio-DIO's Mansion:2,Libeccio-Green Dolphin Street Prison:3,Libeccio-San Giorgio Maggiore:4,"
                + "Trattoria Trussardi-San Giorgio Maggiore:3,Trattoria Trussardi-Green Dolphin Street Prison:6,"
                + "Angelo Rock-DIO's Mansion:3,Angelo Rock-Green Dolphin Street Prison:2";

        String rootVertex = "Town Hall"; // Set the desired root vertex
        List<Edge> minimumSpanningTree = prim(graphStr, rootVertex);
        System.out.println("=".repeat(200));
        System.out.println("Unnecessary water connections to be removed :"); 
        System.out.println();
        int count = 1;
        int totalLength = 0;
        for (Edge edge : minimumSpanningTree) {
            System.out.println(count + ". " + edge.vertex1 + " -- " + edge.vertex2 +" (" + edge.weight +"km)");
            totalLength += edge.weight;
            count++;
        }
        System.out.println();
        System.out.println("Total Length: " + totalLength +"km");
    }
}