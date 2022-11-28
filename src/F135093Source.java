import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Set;

public class F135093Source {
    public static Stack<node> visitedStates = new Stack<>();  //Stack to store all nodes to visit
    public static Set<node> distinctStates = new HashSet<node>(); //Set to store all visited nodes -- set used as only distinct states may be stored

    static void createNewState(int jug1, int jug2, int jug3){
        node newNode = new node(jug1, jug2, jug3); //create new node with volumes passed into method
        visitedStates.add(newNode);
        distinctStates.add(newNode);
    }

    public static void main(String[] args) throws Exception {
        int[] jugCapacities = new int[3];
        
        try (Scanner jugCapacityInput = new Scanner(System.in)) { //gathers capacitites from user input
            String[] numberString = {"First", "Second", "Third"};
            for(int i = 0; i < jugCapacities.length; i++){
                System.out.print("Enter Capacity of "+numberString[i]+" Jug: ");
                jugCapacities[i] = jugCapacityInput.nextInt();
            }
        }
        int jug1Capacity = jugCapacities[0];
        int jug2Capacity = jugCapacities[1];
        int jug3Capacity = jugCapacities[2];
        
        node startState = new node(0, 0, 0); //state used to start from
        visitedStates.push(startState);
        distinctStates.add(startState);


        while (!visitedStates.isEmpty()){
            node currentNode = visitedStates.pop();
            
            if (currentNode.jug1 > 0){ //if jug1 contains liquid
                //pour jug1 into jug2
                if ((currentNode.jug1 + currentNode.jug2) <= jug2Capacity){ //if pouring all of jug1 into jug2 doesn't exceed jug2 capacity
                    createNewState(0, currentNode.jug1 + currentNode.jug2, currentNode.jug3); //create new node with jug1 poured into jug2
                }else{ //if pouring all of jug1 into jug2 exceeds jug2 capacity
                    int excess = (currentNode.jug1 - (jug2Capacity - currentNode.jug2)); //get excess from when pouring jug1 into jug2 exceeds jug2 capacity
                    createNewState(excess, jug2Capacity, currentNode.jug3); //create new node with jug1 poured into jug2 and excess left in jug1
                    if (excess + currentNode.jug3 <= jug3Capacity ){  //if pouring excess into jug3 doesn't exceed jug3 capacity
                        createNewState(0, jug2Capacity, excess + currentNode.jug3); //create new node with excess poured into jug3
                    }else{ //if pouring excess into jug3 exceeds jug3 capacity
                        createNewState(0, jug2Capacity, jug3Capacity); //create new node with jug1 emptied and jug2 and jug3 are filled 
                    }
                }

                //pour jug1 into jug3
                if ((currentNode.jug1 + currentNode.jug3) <= jug3Capacity){ //if pouring all of jug1 into jug3 doesn't exceed jug3 capacity
                    createNewState(0, currentNode.jug2, currentNode.jug1 + currentNode.jug3); //create new node with jug1 poured into jug3
                }else{ //if pouring all of jug1 into jug3 exceeds jug3 capacity
                    int excess = (currentNode.jug1 - (jug3Capacity - currentNode.jug3)); //get excess from when pouring jug1 into jug3 exceeds jug3 capacity
                    createNewState(excess, currentNode.jug2, jug3Capacity); //create new node with jug1 poured into jug3 and excess left in jug1
                    if (excess + currentNode.jug2 <= jug2Capacity ){  //if pouring excess into jug2 doesn't exceed jug2 capacity
                        createNewState(0, excess + currentNode.jug2, jug3Capacity); //create new node with excess poured into jug2
                    }else{ //if pouring excess into jug2 exceeds jug2 capacity
                        createNewState(0, jug2Capacity, jug3Capacity); //create new node with jug1 emptied and jug2 and jug3 are filled 
                    }
                }
                createNewState(0, currentNode.jug2, currentNode.jug3); //create new node with jug1 poured out
            }

            
            if (currentNode.jug1 > 0){ //if jug2 contains liquid
                //pour jug2 into jug1
                if ((currentNode.jug2 + currentNode.jug1) <= jug1Capacity){ //if pouring all of jug2 into jug1 doesn't exceed jug2 capacity
                    createNewState(currentNode.jug2 + currentNode.jug1, 0 , currentNode.jug3); //create new node with jug2 poured into jug1
                }else{ //if pouring all of jug2 into jug2 exceeds jug1 capacity
                    int excess = (currentNode.jug2 - (jug1Capacity - currentNode.jug1)); //get excess from when pouring jug2 into jug1 exceeds jug1 capacity
                    createNewState(jug1Capacity, excess, currentNode.jug3); //create new node with jug2 poured into jug1 and excess left in jug2
                    if (excess + currentNode.jug3 <= jug3Capacity ){  //if pouring excess into jug3 doesn't exceed jug3 capacity
                        createNewState(excess, 0, excess + currentNode.jug3); //create new node with excess poured into jug3
                    }else{ //if pouring excess into jug3 exceeds jug3 capacity
                        createNewState(jug1Capacity, 0, jug3Capacity); //create new node with jug2 emptied and jug1 and jug3 are filled 
                    }
                }

                //pour jug2 into jug3
                if ((currentNode.jug2 + currentNode.jug3) <= jug3Capacity){ //if pouring all of jug2 into jug3 doesn't exceed jug3 capacity
                    createNewState(currentNode.jug1, 0, currentNode.jug1 + currentNode.jug3); //create new node with jug2 poured into jug3
                }else{ //if pouring all of jug2 into jug3 exceeds jug3 capacity
                    int excess = (currentNode.jug2 - (jug3Capacity - currentNode.jug3)); //get excess from when pouring jug2 into jug3 exceeds jug3 capacity
                    createNewState(currentNode.jug1, excess, jug3Capacity); //create new node with jug2 poured into jug3 and excess left in jug2
                    if (excess + currentNode.jug1 <= jug1Capacity ){  //if pouring excess into jug1 doesn't exceed jug1 capacity
                        createNewState(excess + currentNode.jug1, 0, jug3Capacity); //create new node with excess poured into jug1
                    }else{ //if pouring excess into jug1 exceeds jug1 capacity
                        createNewState(jug1Capacity, 0, jug3Capacity); //create new node with jug2 emptied and jug1 and jug3 are filled 
                    }
                }
                createNewState(currentNode.jug1, 0, currentNode.jug3); //create new node with jug2 poured out
            }


            if (currentNode.jug1 > 0){ //if jug 3 contains liquid
                //pour jug3 into jug1
                if ((currentNode.jug3 + currentNode.jug1) <= jug1Capacity){ //if pouring all of jug3 into jug1 doesn't exceed jug1 capacity
                    createNewState(currentNode.jug3 + currentNode.jug1, currentNode.jug2, 0); //create new node with jug3 poured into jug 1
                }else{ //if pouring all of jug3 into jug1 exceeds jug1 capacity
                    int excess = (currentNode.jug3 - (jug1Capacity - currentNode.jug1)); //get excess from when pouring jug3 into jug1 exceeds jug1 capacity
                    createNewState(jug1Capacity, currentNode.jug2, excess); //create new node with jug3 poured into jug1 and excess left in jug3
                    if (excess + currentNode.jug2 <= jug2Capacity ){  //if pouring excess into jug2 doesn't exceed jug2 capacity
                        createNewState(jug1Capacity, excess + currentNode.jug2, 0); //create new node with excess poured into jug2
                    }else{ //if pouring excess into jug2 exceeds jug2 capacity
                        createNewState(jug1Capacity, jug2Capacity, 0); //create new node with jug3 emptied and jug1 and jug2 are filled 
                    }
                }

                //pour jug3 into jug2
                if ((currentNode.jug3 + currentNode.jug2) <= jug2Capacity){ //if pouring all of jug3 into jug2 doesn't exceed jug2 capacity
                    createNewState(currentNode.jug1,currentNode.jug3 + currentNode.jug2, 0); //create new node with jug3 poured into jug 2
                }else{ //if pouring all of jug3 into jug2 exceeds jug2 capacity
                    int excess = (currentNode.jug3 - (jug2Capacity - currentNode.jug2)); //get excess from when pouring jug3 into jug1 exceeds jug1 capacity
                    createNewState(jug2Capacity, jug2Capacity, excess); //create new node with jug3 poured into jug2 and excess left in jug3
                    if (excess + currentNode.jug1 <= jug1Capacity ){  //if pouring excess into jug1 doesn't exceed jug1 capacity
                        createNewState(excess + currentNode.jug1, jug2Capacity, 0); //create new node with excess poured into jug1
                    }else{ //if pouring excess into jug1 exceeds jug1 capacity
                        createNewState(jug1Capacity, jug2Capacity, 0); //create new node with jug3 emptied and jug2 and jug1 are filled 
                    }
                }
                createNewState(currentNode.jug1, currentNode.jug2, 0); //create new node with jug3 poured out
            }

            if (currentNode.jug1 < jug1Capacity){ //if jug1 not full
                createNewState(jug1Capacity, currentNode.jug2, currentNode.jug3); //fill jug1
            }

            if (currentNode.jug2 < jug2Capacity){ //if jug2 not full
                createNewState(currentNode.jug1, jug2Capacity, currentNode.jug3); //fill jug2
            }

            if (currentNode.jug3 < jug3Capacity){ //if jug3 not full
                createNewState(currentNode.jug1, currentNode.jug2, jug3Capacity); //fill jug3
            }

        }
        System.out.println(distinctStates.size() + "Distinct nodes");

    }


    
}
