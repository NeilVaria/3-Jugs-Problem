//Java Program that generates all distinct nodes of the water jug problem from 3 integer inputs, using dfs.

//As this is a dfs, a stack must be used. New nodes are added to the stack. 
//The search iterates through the stack and removes it when it is being expanded.

//A set is used to store all distinct states as sets do not allow for duplicate values, although an array list could have been used in this instance.
//This is because before pushing to the stack, nodes are checked to see if they already exist in the set.
//This means that no duplicate values can ever be stored.
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Set;

public class F135093Source {
    public static Stack<node> visitedStates = new Stack<>(); // Stack to store all nodes to visit
    public static Set<node> distinctStates = new HashSet<node>(); // Set to store all visited nodes -- set used as only distinct states may be stored
    public static int[] jugCapacities = new int[3];
    //Stringbuilder used to store all nodes to be printed, without printing each individual node -- improves efficiency exponentially when there is a large number of nodes
    public static StringBuilder sb = new StringBuilder();
    //int to store node count
    public static int nodeCount = 1;

    static void createNewState(int jug1, int jug2, int jug3) {
        if (jug1 <= jugCapacities[0] && jug2 <= jugCapacities[1] && jug3 <= jugCapacities[2]) {
            node newNode = new node(jug1, jug2, jug3); // create new node with volumes passed into method
            boolean isVisited = false;
            for (node node : distinctStates) {
                if (node.jug1 == newNode.jug1 && node.jug2 == newNode.jug2 && node.jug3 == newNode.jug3) {
                    isVisited = true;
                }
            }
            if (!isVisited) {
                nodeCount++;
                visitedStates.add(newNode);
                distinctStates.add(newNode);
                sb.append("\n" + "Node " + nodeCount + ": (" + newNode.jug1 + ", " + newNode.jug2 + ", " + newNode.jug3
                        + ")");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner jugCapacityInput = new Scanner(System.in)) { // gathers capacitites from user input
            String[] numberString = { "First", "Second", "Third" };
            for (int i = 0; i < jugCapacities.length; i++) {
                System.out.print("Enter Capacity of " + numberString[i] + " Jug: ");
                while (!jugCapacityInput.hasNextInt()) { //checks input is int
                    System.out.print("Please Enter Capacity of " + numberString[i] + " Jug as a positive integer: ");
                    jugCapacityInput.next();
                }
                jugCapacities[i] = jugCapacityInput.nextInt();
            }
        }
        long startTime = System.nanoTime(); //Log execution start time
        int jug1Capacity = jugCapacities[0];
        int jug2Capacity = jugCapacities[1];
        int jug3Capacity = jugCapacities[2];

        node startState = new node(0, 0, 0); // start state inititalised
        visitedStates.push(startState);
        distinctStates.add(startState);
        sb.append("Node " + nodeCount + ": (0, 0, 0)");
        while (!visitedStates.isEmpty()) {
            node currentNode = visitedStates.pop();
            if (currentNode.jug1 > 0) { // if jug1 contains liquid pour jug1 into jug2
                if ((currentNode.jug1 + currentNode.jug2) <= jug2Capacity) { // if pouring all of jug1 into jug2 doesn't exceed jug2 capacity create new node with jug1 poured into jug2
                    createNewState(0, currentNode.jug1 + currentNode.jug2, currentNode.jug3);
                } else { // if pouring all of jug1 into jug2 exceeds jug2 capacity
                    int excess = (currentNode.jug1 - (jug2Capacity - currentNode.jug2)); // get excess from when pouring jug1 into jug2 exceeds jug2 capacity
                    createNewState(excess, jug2Capacity, currentNode.jug3); // create new node with jug1 poured into jug2 and excess left in jug1
                    if (excess + currentNode.jug3 <= jug3Capacity) { // if pouring excess into jug3 doesn't exceed jug3 capacity
                        createNewState(0, jug2Capacity, excess + currentNode.jug3); // create new node with excess poured into jug3
                    } else { // if pouring excess into jug3 exceeds jug3 capacity
                        createNewState(0, jug2Capacity, jug3Capacity); // create new node with jug1 emptied and jug2 and jug3 are filled
                    }
                }
                // pour jug1 into jug3
                if ((currentNode.jug1 + currentNode.jug3) <= jug3Capacity) { // if pouring all of jug1 into jug3 doesn't
                                                                             // exceed jug3 capacity
                    createNewState(0, currentNode.jug2, currentNode.jug1 + currentNode.jug3); // create new node with
                                                                                              // jug1 poured into jug3
                } else { // if pouring all of jug1 into jug3 exceeds jug3 capacity
                    int excess = (currentNode.jug1 - (jug3Capacity - currentNode.jug3)); // get excess from when pouring jug1 into jug3 exceeds jug3capacity
                    createNewState(excess, currentNode.jug2, jug3Capacity); // create new node with jug1 poured into jug3 and excess left in jug1
                    if (excess + currentNode.jug2 <= jug2Capacity) { // if pouring excess into jug2 doesn't exceed jug2 capacity
                        createNewState(0, excess + currentNode.jug2, jug3Capacity); // create new node with excess poured into jug2
                    } else { // if pouring excess into jug2 exceeds jug2 capacity
                        createNewState(0, jug2Capacity, jug3Capacity); // create new node with jug1 emptied and jug2 and jug3 are filled
                    }
                }
                createNewState(0, currentNode.jug2, currentNode.jug3); // create new node with jug1 poured out
            }
            if (currentNode.jug2 > 0) { // if jug2 contains liquid
                // pour jug2 into jug1
                if ((currentNode.jug2 + currentNode.jug1) <= jug1Capacity) { // if pouring all of jug2 into jug1 doesn't exceed jug1 capacity
                    createNewState(currentNode.jug2 + currentNode.jug1, 0, currentNode.jug3); // create new node with  jug2 poured into jug1
                } else { // if pouring all of jug2 into jug2 exceeds jug1 capacity
                    int excess = (currentNode.jug2 - (jug1Capacity - currentNode.jug1)); // get excess from when pouring jug2 into jug1 exceeds jug1 capacity
                    createNewState(jug1Capacity, excess, currentNode.jug3); // create new node with jug2 poured into jug1 and excess left in jug2
                    if (excess + currentNode.jug3 <= jug3Capacity) { // if pouring excess into jug3 doesn't exceed jug3 capacity
                        createNewState(excess, 0, excess + currentNode.jug3); // create new node with excess poured into
                                                                              // jug3
                    } else { // if pouring excess into jug3 exceeds jug3 capacity
                        createNewState(jug1Capacity, 0, jug3Capacity); // create new node with jug2 emptied and jug1 and jug3 are filled
                    }
                }
                // pour jug2 into jug3
                if ((currentNode.jug2 + currentNode.jug3) <= jug3Capacity) { // if pouring all of jug2 into jug3 doesn't
                                                                             // exceed jug3 capacity
                    createNewState(currentNode.jug1, 0, currentNode.jug2 + currentNode.jug3); // create new node with jug2 poured into jug3
                } else { // if pouring all of jug2 into jug3 exceeds jug3 capacity
                    int excess = (currentNode.jug2 - (jug3Capacity - currentNode.jug3)); // get excess from when pouring jug2 into jug3 exceeds jug3 capacity
                    createNewState(currentNode.jug1, excess, jug3Capacity); // create new node with jug2 poured into jug3 and excess left in jug2
                    if (excess + currentNode.jug1 <= jug1Capacity) { // if pouring excess into jug1 doesn't exceed jug1 capacity
                        createNewState(excess + currentNode.jug1, 0, jug3Capacity); // create new node with excess poured into jug1
                    } else { // if pouring excess into jug1 exceeds jug1 capacity
                        createNewState(jug1Capacity, 0, jug3Capacity); // create new node with jug2 emptied and jug1 and jug3 are filled
                    }
                }
                createNewState(currentNode.jug1, 0, currentNode.jug3); // create new node with jug2 poured out
            }
            if (currentNode.jug3 > 0) { // if jug 3 contains liquid
                // pour jug3 into jug1
                if ((currentNode.jug3 + currentNode.jug1) <= jug1Capacity) { // if pouring all of jug3 into jug1 doesn't exceed jug1 capacity 
                    createNewState(currentNode.jug3 + currentNode.jug1, currentNode.jug2, 0); // create new node with jug3 poured into jug 1
                } else { // if pouring all of jug3 into jug1 exceeds jug1 capacity
                    int excess = (currentNode.jug3 - (jug1Capacity - currentNode.jug1)); // get excess from when pouring jug3 into jug1 exceeds jug1 capacity
                    createNewState(jug1Capacity, currentNode.jug2, excess); // create new node with jug3 poured into jug1 and excess left in jug3
                    if (excess + currentNode.jug2 <= jug2Capacity) { // if pouring excess into jug2 doesn't exceed jug2 capacity
                        createNewState(jug1Capacity, excess + currentNode.jug2, 0); // create new node with excess poured into jug2
                    } else { // if pouring excess into jug2 exceeds jug2 capacity
                        createNewState(jug1Capacity, jug2Capacity, 0); // create new node with jug3 emptied and jug1 and jug2 are filled
                    }
                }
                // pour jug3 into jug2
                if ((currentNode.jug3 + currentNode.jug2) <= jug2Capacity) { // if pouring all of jug3 into jug2 doesn't exceed jug2 capacity
                    createNewState(currentNode.jug1, currentNode.jug3 + currentNode.jug2, 0); // create new node with jug3 poured into jug 2
                } else { // if pouring all of jug3 into jug2 exceeds jug2 capacity
                    int excess = (currentNode.jug3 - (jug2Capacity - currentNode.jug2)); // get excess from when pouring jug3 into jug1 exceeds jug1 capacity
                    createNewState(jug2Capacity, jug2Capacity, excess); // create new node with jug3 poured into jug2 and excess left in jug3
                    if (excess + currentNode.jug1 <= jug1Capacity) { // if pouring excess into jug1 doesn't exceed jug1 capacity
                        createNewState(excess + currentNode.jug1, jug2Capacity, 0); // create new node with excess poured into jug1
                    } else { // if pouring excess into jug1 exceeds jug1 capacity
                        createNewState(jug1Capacity, jug2Capacity, 0); // create new node with jug3 emptied and jug2 and jug1 are filled
                    }
                }
                createNewState(currentNode.jug1, currentNode.jug2, 0); // create new node with jug3 poured out
            }
            if (currentNode.jug1 < jug1Capacity) { // if jug1 not full
                createNewState(jug1Capacity, currentNode.jug2, currentNode.jug3); // fill jug1
            }
            if (currentNode.jug2 < jug2Capacity) { // if jug2 not full
                createNewState(currentNode.jug1, jug2Capacity, currentNode.jug3); // fill jug2
            }
            if (currentNode.jug3 < jug3Capacity) { // if jug3 not full
                createNewState(currentNode.jug1, currentNode.jug2, jug3Capacity); // fill jug3
            }
        }
        long endTime = System.nanoTime(); // log execution end time
        long duration = (endTime - startTime / 1000000);
        String nodeString = sb.toString();
        //Print results
        System.out.println(nodeString);
        System.out.println(nodeCount + " Distinct nodes");
        System.out.println("Time taken for execution: " + duration + "ms");
    }

}
