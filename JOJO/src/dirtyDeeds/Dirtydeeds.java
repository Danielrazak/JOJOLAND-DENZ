package dirtyDeeds;
import java.util.*;
public class Dirtydeeds {
    static class Graph {
        private Map<String, List<Edge>> adj;
        Graph() {
            this.adj = new HashMap<>();
        }

        public void addEdge(String src, String dest, int weight) {
            List<Edge> neighbors = adj.getOrDefault(src, new ArrayList<>());
            neighbors.add(new Edge(dest, weight));
            adj.put(src, neighbors);
        }

        public void findKShortestPaths(String src, String dest, int k) {
            PriorityQueue<Path> pq = new PriorityQueue<>();

            pq.offer(new Path(src, 0, new ArrayList<>(Collections.singletonList(src))));

            List<Path> paths = new ArrayList<>();

            while (!pq.isEmpty() && paths.size() < k) {
                Path currPath = pq.poll();
                String currNode = currPath.path.get(currPath.path.size() - 1);

                if (currNode.equals(dest)) {
                    paths.add(currPath);
                }
                List<Edge> neighbors = adj.getOrDefault(currNode, new ArrayList<>());
                for (Edge neighbor : neighbors) {
                    if (!currPath.path.contains(neighbor.dest)) { // Check if the neighbor is already visited
                        List<String> newPath = new ArrayList<>(currPath.path);
                        newPath.add(neighbor.dest);
                        int distance = currPath.distance + neighbor.weight;
                        pq.offer(new Path(neighbor.dest, distance, newPath));
                    }
                }
            }

            // Print the paths
            System.out.println("Source: " + src);
            System.out.println("Destination: " + dest);
            System.out.println("=".repeat(200));
            System.out.println("3 shortest paths:");

            if (paths.isEmpty()) {
                System.out.println("No paths found.");
            } else {
                for (int i = 0; i < paths.size(); i++) {
                    Path path = paths.get(i);
                    System.out.printf("%d. %s (%d km)%n", (i + 1), formatPath(path.path), path.distance);
                }
            }
            System.out.println("=".repeat(200));
        }

        private String formatPath(List<String> path) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i < path.size() - 1) {
                    sb.append(" -> ");
                }
            }
            return sb.toString();
        }
    }

    static class Edge {
        String dest;
        int weight;

        Edge(String dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Path implements Comparable<Path> {
        String node;
        int distance;
        List<String> path;

        Path(String node, int distance, List<String> path) {
            this.node = node;
            this.distance = distance;
            this.path = path;
        }

        @Override
        public int compareTo(Path other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // method to be called
    public static void printDirtyDeeds() {
        Graph graph = new Graph();

        // Add edges to the graph
        graph.addEdge("Town Hall", "Morioh Grand Hotel", 5);
        graph.addEdge("Town Hall", "Jade Garden", 5);
        graph.addEdge("Town Hall", "Cafe Deux Magots", 4);

        graph.addEdge("Morioh Grand Hotel", "Town Hall", 5);
        graph.addEdge("Morioh Grand Hotel", "Jade Garden", 3);
        graph.addEdge("Morioh Grand Hotel", "Trattoria Trussardi", 6);

        graph.addEdge("Jade Garden", "Town Hall", 5);
        graph.addEdge("Jade Garden", "Morioh Grand Hotel", 3);
        graph.addEdge("Jade Garden", "Cafe Deux Magots", 3);
        graph.addEdge("Jade Garden", "San Giorgio Maggiore", 2);
        graph.addEdge("Jade Garden", "Joestar Mansion", 2);

        graph.addEdge("Cafe Deux Magots", "Town Hall", 4);
        graph.addEdge("Cafe Deux Magots", "Jade Garden", 3);
        graph.addEdge("Cafe Deux Magots", "Savage Garden", 4);
        graph.addEdge("Cafe Deux Magots", "Polnareff Land", 4);

        graph.addEdge("Trattoria Trussardi", "Morioh Grand Hotel", 6);
        graph.addEdge("Trattoria Trussardi", "San Giorgio Maggiore", 3);
        graph.addEdge("Trattoria Trussardi", "Green Dolphin Street Prison", 6);

        graph.addEdge("San Giorgio Maggiore", "Jade Garden", 2);
        graph.addEdge("San Giorgio Maggiore", "Trattoria Trussardi", 3);
        graph.addEdge("San Giorgio Maggiore", "Libeccio", 4);

        graph.addEdge("Joestar Mansion", "Jade Garden", 2);
        graph.addEdge("Joestar Mansion", "Savage Garden", 4);
        graph.addEdge("Joestar Mansion", "Libeccio", 6);
        graph.addEdge("Joestar Mansion", "Vineyard", 3);

        graph.addEdge("Savage Garden", "Cafe Deux Magots", 4);
        graph.addEdge("Savage Garden", "Joestar Mansion", 4);
        graph.addEdge("Savage Garden", "Polnareff Land", 6);
        graph.addEdge("Savage Garden", "Vineyard", 8);

        graph.addEdge("Polnareff Land", "Cafe Deux Magots", 4);
        graph.addEdge("Polnareff Land", "Savage Garden", 6);

        graph.addEdge("Green Dolphin Street Prison", "Trattoria Trussardi", 6);
        graph.addEdge("Green Dolphin Street Prison", "Libeccio", 3);
        graph.addEdge("Green Dolphin Street Prison", "Angelo Rock", 2);

        graph.addEdge("Libeccio", "San Giorgio Maggiore", 4);
        graph.addEdge("Libeccio", "Joestar Mansion", 6);
        graph.addEdge("Libeccio", "Green Dolphin Street Prison", 3);
        graph.addEdge("Libeccio", "Vineyard", 6);
        graph.addEdge("Libeccio", "DIO's Mansion", 2);

        graph.addEdge("Vineyard", "Joestar Mansion", 3);
        graph.addEdge("Vineyard", "Savage Garden", 8);
        graph.addEdge("Vineyard", "Libeccio", 6);
        graph.addEdge("Vineyard", "DIO's Mansion", 3);

        graph.addEdge("Angelo Rock", "Green Dolphin Street Prison", 2);
        graph.addEdge("Angelo Rock", "DIO's Mansion", 3);

        graph.addEdge("DIO's Mansion", "Libeccio", 2);
        graph.addEdge("DIO's Mansion", "Vineyard", 3);
        graph.addEdge("DIO's Mansion", "Angelo Rock", 3);

        // Find the K shortest paths
        Scanner sc= new Scanner (System.in);
        System.out.print("Source: ");
        String source = sc.nextLine();
        System.out.print("Destination: ");
        String destination = sc.nextLine();
        int k = 3; // Number of shortest paths to find
        graph.findKShortestPaths(source, destination, k);
    }
}