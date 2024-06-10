//Java Program that generates all distinct nodes of the water jug problem from 3 integer inputs, using dfs.

//A stack is used to store states to visit as the search method is dfs
//The search iterates through the stack and removes it when it is being expanded.

//A set is used to store all distinct states as sets do not allow for duplicate values, although an array list could have been used in this instance.
//This is because before pushing to the stack, nodes are checked to see if they already exist in the set.
//This means that no duplicate values can ever be stored.
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Set;

public class F135093Source {
    public static Stack<node> statesToVisit = new Stack<>(); // Stack to store all nodes to visit
    public static Set<node> distinctStates = new HashSet<node>(); // Set to store all visited nodes -- set used as only distinct states may be stored
    public static int[] jugCapacities = new int[3];
    //Stringbuilder used to store all nodes to be printed, without printing each individual node -- improves efficiency exponentially when there is a large number of nodes
    public static StringBuilder sb = new StringBuilder();
    //int to store node count
    public static int nodeCount = 1;

    static void createNewNode(int jug1, int jug2, int jug3) {
        if (jug1 <= jugCapacities[0] && jug2 <= jugCapacities[1] && jug3 <= jugCapacities[2]) {
            // create new node with volumes that are passed into method
            node newNode = new node(jug1, jug2, jug3); 
            boolean isVisited = false;
            for (node node : distinctStates) {
                if (node.jug1 == newNode.jug1 && node.jug2 == newNode.jug2 && node.jug3 == newNode.jug3) {
                    isVisited = true;
                }
            }
            if (!isVisited) {
                nodeCount++;
                statesToVisit.add(newNode);
                distinctStates.add(newNode);
                sb.append("\n" + "Node " + nodeCount + ": (" + newNode.jug1 + ", " + newNode.jug2 + ", " + newNode.jug3
                        + ")");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // gather capacitites from user input
        try (Scanner jugCapacityInput = new Scanner(System.in)) { 
            String[] numberString = { "First", "Second", "Third" };
            for (int i = 0; i < jugCapacities.length; i++) {
                System.out.print("Enter Capacity of " + numberString[i] + " Jug: ");
                //checks input is int
                while (!jugCapacityInput.hasNextInt()) { 
                    System.out.print("Please Enter Capacity of " + numberString[i] + " Jug as an integer: ");
                    jugCapacityInput.next();
                }
                jugCapacities[i] = jugCapacityInput.nextInt();
                //if input is negative take it as 0
                if (jugCapacities[i] < 0){
                    jugCapacities[i] = 0;
                }
            }
        }

        //Log execution start time
        long startTime = System.nanoTime(); 
        //Set Jug Capacitites
        int jug1Capacity = jugCapacities[0];
        int jug2Capacity = jugCapacities[1];
        int jug3Capacity = jugCapacities[2];
        // start state inititalised
        node startState = new node(0, 0, 0); 
        statesToVisit.push(startState);
        distinctStates.add(startState);
        sb.append("Node " + nodeCount + ": (0, 0, 0)");
        while (!statesToVisit.isEmpty()) {
            node currentNode = statesToVisit.pop();
            //if jug 1 contains liquid
            if (currentNode.jug1 > 0) {
                //pour jug1 into jug2
                if ((currentNode.jug1 + currentNode.jug2) <= jug2Capacity) {
                    createNewNode(0, currentNode.jug1 + currentNode.jug2, currentNode.jug3);
                // if pouring all of jug1 into jug2 exceeds jug2 capacity, leave excess in jug1
                } else { 
                    int excess = (currentNode.jug1 - (jug2Capacity - currentNode.jug2));
                    createNewNode(excess, jug2Capacity, currentNode.jug3);}
                // pour jug1 into jug3
                if ((currentNode.jug1 + currentNode.jug3) <= jug3Capacity) {
                    createNewNode(0, currentNode.jug2, currentNode.jug1 + currentNode.jug3);
                // if pouring all of jug1 into jug3 exceeds jug3 capacity, leave excess in jug1
                } else {
                    int excess = (currentNode.jug1 - (jug3Capacity - currentNode.jug3));
                    createNewNode(excess, currentNode.jug2, jug3Capacity);}
                 // empty jug1
                createNewNode(0, currentNode.jug2, currentNode.jug3);
            }

            // if jug2 contains liquid
            if (currentNode.jug2 > 0) {
                // pour jug2 into jug1
                if ((currentNode.jug2 + currentNode.jug1) <= jug1Capacity) {
                    createNewNode(currentNode.jug2 + currentNode.jug1, 0, currentNode.jug3);
                // if pouring all of jug2 into jug2 exceeds jug1 capacity, leave excess in jug2
                } else { 
                    int excess = (currentNode.jug2 - (jug1Capacity - currentNode.jug1)); // get excess from when pouring jug2 into jug1 exceeds jug1 capacity
                    createNewNode(jug1Capacity, excess, currentNode.jug3); // create new node with jug2 poured into jug1 and excess left in jug2
                }
                // pour jug2 into jug3
                if ((currentNode.jug2 + currentNode.jug3) <= jug3Capacity) {
                    createNewNode(currentNode.jug1, 0, currentNode.jug2 + currentNode.jug3);
                // if pouring all of jug2 into jug3 exceeds jug3 capacity, leave excess in jug2
                } else { 
                    int excess = (currentNode.jug2 - (jug3Capacity - currentNode.jug3));
                    createNewNode(currentNode.jug1, excess, jug3Capacity);
                }
                 // empty jug2
                createNewNode(currentNode.jug1, 0, currentNode.jug3);
            }

            // if jug 3 contains liquid
            if (currentNode.jug3 > 0) {
                // pour jug3 into jug1
                if ((currentNode.jug3 + currentNode.jug1) <= jug1Capacity) {
                    createNewNode(currentNode.jug3 + currentNode.jug1, currentNode.jug2, 0);
                // if pouring all of jug3 into jug1 exceeds jug1 capacity, leave excess in jug3
                } else {
                    int excess = (currentNode.jug3 - (jug1Capacity - currentNode.jug1));
                    createNewNode(jug1Capacity, currentNode.jug2, excess);
                }
                // pour jug3 into jug2
                if ((currentNode.jug3 + currentNode.jug2) <= jug2Capacity) { 
                    createNewNode(currentNode.jug1, currentNode.jug3 + currentNode.jug2, 0);
                // if pouring all of jug3 into jug2 exceeds jug2 capacity, leave excess in jug3
                } else { 
                    int excess = (currentNode.jug3 - (jug2Capacity - currentNode.jug2));
                    createNewNode(jug2Capacity, jug2Capacity, excess);
                }
                 // empty jug3
                createNewNode(currentNode.jug1, currentNode.jug2, 0);
            }
            // fill jug1 if not full
            if (currentNode.jug1 < jug1Capacity) {
                createNewNode(jug1Capacity, currentNode.jug2, currentNode.jug3);
            }
            // fill jug2 if not full
            if (currentNode.jug2 < jug2Capacity) {
                createNewNode(currentNode.jug1, jug2Capacity, currentNode.jug3);
            }
            // fill jug3 if not full
            if (currentNode.jug3 < jug3Capacity) {
                createNewNode(currentNode.jug1, currentNode.jug2, jug3Capacity); 
            }
        }

        long endTime = System.nanoTime(); // log execution end time
        long duration = (endTime - startTime);
        String nodeString = sb.toString();
        //Print results
        System.out.println(nodeString);
        System.out.println(nodeCount + " Distinct nodes");
        System.out.println("Time taken for execution: " + duration / 1000000 + "ms");
    }
}
