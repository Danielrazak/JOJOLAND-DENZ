package goldenSpirit;
import java.util.*;
class GraphNode {
    String name;
    List<GraphNode> neighbours;
    public GraphNode(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>(); //graph is connected
    }
}
public class TheGoldenSpirit {
    public static void printGoldenSpirit() {
        GraphNode georgeImary = new GraphNode("George Joestar I and Mary Joestar");
        GraphNode jonathan = new GraphNode("Jonathan Joestar");
        GraphNode erina = new GraphNode("Erina Joestar");
        GraphNode dio = new GraphNode("DIO");
        GraphNode georgeII = new GraphNode("George Joestar II");
        GraphNode lisa = new GraphNode("Lisa Lisa");
        GraphNode giorno = new GraphNode("Giorno Giovanna");
        GraphNode joseph = new GraphNode("Joseph Joestar");
        GraphNode suzi = new GraphNode("Suzi Q");
        GraphNode tomoko = new GraphNode("Tomoko Higashikata");
        GraphNode josuke = new GraphNode("Josuke Higashikata");
        GraphNode holy = new GraphNode("Holy Kujo");
        GraphNode sadao = new GraphNode("Sadao Kujo");
        GraphNode jotaro = new GraphNode("Jotaro Kujo");
        GraphNode jolyne = new GraphNode("Jolyne Cujoh");

        //The relationships of each joestar where neighbours=child
        georgeImary.neighbours.add(jonathan);
        jonathan.neighbours.add(georgeII);
        jonathan.neighbours.add(giorno);
        erina.neighbours.add(georgeII);
        dio.neighbours.add(giorno);
        georgeII.neighbours.add(joseph);
        lisa.neighbours.add(joseph);
        joseph.neighbours.add(holy);
        joseph.neighbours.add(josuke);
        tomoko.neighbours.add(josuke);
        suzi.neighbours.add(holy);
        sadao.neighbours.add(jotaro);
        holy.neighbours.add(jotaro);
        jotaro.neighbours.add(jolyne);

        // Ask user to enter two names
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the names of first Joestar: ");
        String joestarName1 = scanner.nextLine();
        System.out.print("Enter the names of second Joestar: ");
        String joestarName2 = scanner.nextLine();

        //Root is georgeImary because they are the first
        GraphNode node1 = findNode(georgeImary, joestarName1);
        GraphNode node2 = findNode(georgeImary, joestarName2);
        
        //conditions to find and print lowest common ancestor(lca)
        if (node1 != null && node2 != null) { //makes sure names are valid
            GraphNode lca = getLCA(georgeImary, node1, node2);
            if (lca != null) {
            	System.out.println("=".repeat(200));
                System.out.print("Lowest Common Ancestor of "+ joestarName1 + " and " + joestarName2 + " : " + lca.name);
                //for those with spouses, extra condition apply:
                if (lca.name.equals("Holy Kujo")) {
                    System.out.print(" and Sadao Kujo");
                } else if (lca.name.equals("George Joestar II")) {
                    System.out.print(" and Lisa Lisa");
                }
                System.out.println();
            } else {
                System.out.println("No common ancestor found.");
            }
        } else {
            System.out.println("Invalid Joestar names.");
        }
    }

    public static GraphNode findNode(GraphNode root, String nodeName) {
        if (root == null || nodeName == null) {
            return null;
        }

        if (root.name.equals(nodeName)) {
            return root; 
        }
        //recursively find for nodeName under neighbours
        for (GraphNode neighbour : root.neighbours) {
            GraphNode node = findNode(neighbour, nodeName);
            if (node != null) {
                return node;
            }
        }

        return null;
    }
    //method to find LCA
    public static GraphNode getLCA(GraphNode root, GraphNode node1, GraphNode node2) {
        if (root == null || node1 == null || node2 == null)
            return null;
        if (root == node1 || root == node2) //either one is ancestor of itself so return null
            return null;

        GraphNode lca = null;
        for (GraphNode neighbour : root.neighbours) {
            GraphNode temp = getLCA(neighbour, node1, node2);

            if (temp != null) {
                if (lca != null) {
                    // if found another common ancestor, return root
                    return root;
                }
                lca = temp;
            }
        }
        if (lca != null && lca != node1 && lca != node2) 
            return lca;
        boolean node1Found = isNodeExist(root, node1);
        boolean node2Found = isNodeExist(root, node2);

        if (node1Found && node2Found) 
            return root;
        return null;
    }

    public static boolean isNodeExist(GraphNode root, GraphNode node) {
        if (root == null || node == null)//graph doesnt exist
            return false;
        if (root == node)//root found, means present
            return true;
        for (GraphNode neighbor : root.neighbours) { //search for node recursively in reighbours
            if (isNodeExist(neighbor, node)) {
                return true;
            }
        }
        return false;
    }
}