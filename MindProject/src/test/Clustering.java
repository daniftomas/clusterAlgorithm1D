package test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Clustering {

    public static void main(String[] args) {

		ArrayList<Integer> aNumb = new ArrayList<Integer>();

		int nClusters;
		int iterations = 1000;
		FinalSol solutions[] = new FinalSol[iterations];
		boolean badArray = true;

		do {
			badArray = readArrayInput(aNumb);
		} while (badArray);

		System.out.println("\nFinal array size: " + aNumb.size());
		System.out.println("Array:" + aNumb);
		System.out.println("\nInsira o numero de Clusters:");

		do {
			nClusters = readInt();

			if (nClusters > aNumb.size()) {
				System.out.println("Must insert a number smaller or equal then the array's size: " + aNumb.size());
			} else if (nClusters > numbOfDiffElements(aNumb)) {
				System.out.println("Must insert a number smaller or equal to the number of different elements: "
						+ numbOfDiffElements(aNumb));
			}

		} while (nClusters > aNumb.size() || numbOfDiffElements(aNumb) < nClusters);
		
		if (nClusters == 1) {
			System.out.println("\n\nBEST SOLUTION FOUND:");
			System.out.println("K1:" + aNumb);
		}

		else { 
			// Find n (iterations) solutions
			for (int i = 0; i < iterations; i++) {
				solutions[i] = KmeansAlg.group(aNumb, nClusters);

			}

			System.out.println("For " + iterations + " iterations.");
			int finalSolIndex = indexFinalSol(solutions, iterations);

			System.out.println("\n\nBEST SOLUTION FOUND:");
			System.out.println(solutions[finalSolIndex].toString());

		}

	}
     
    
    
    /**
     * read an Array w/ regular expression
     * 
     * @param arrayList
     * @return true if it is a valid input, false if it is a invalid input
     */
 	public static boolean readArrayInput(ArrayList<Integer> array) {
 		Scanner a;
 		int x = 0;
 		String st = "";
 		boolean badN = true;

 		System.out.println("Insert group of positive numbers(at least 2 Integers): \nExample:" + " 16, 45, 67, 23, 12, 34, 45, 23, 67, 23, 67");
 		a = new Scanner(System.in);
 		st = a.nextLine();
 		
 		if (st.matches("(\\s)?[0-9]*(\\s)?(,(\\s)?[0-9]*(\\s)?)*")) {
 			
 				String[] items = st.replaceAll("\\]", "").replaceAll("\\s", "").split(",");
 				
 				if(items.length<2){
 					System.out.println("Input not valid. Insert at least 2 Integers.");
 					return badN;
 				}
 				
 				for (int i = 0; i < items.length; i++) {
 					try {
 						x = Integer.parseInt(items[i]);
 						array.add(x);

 					} catch (NumberFormatException nfe) {
 						//if is a number too long or it doesn't have a number
 						System.out.println("Input not valid. Insert only Integers.");
 			 			array.clear();
 						return badN;
 					}
 				}
 			return badN=false;
 		}
 		else{
 			System.out.println("Input not valid. Insert integers as showned in the example.\n");
 			return badN;
 		}
 	}
 	
   

    /**
     * method to read one positive Int
     *
     * @return return a integer
     */
    public static int readInt() {
        Scanner b;
        int x = 0;
        boolean badN = true;
        do {
            b = new Scanner(System.in);
            if (b.hasNextInt()) {
            	try {
                    x = Integer.parseInt(b.nextLine());
                    if (x < 1) {
                        System.out.println("Input not valid. Insert a Integer bigger than 1.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input not valid. Insert only a Integer.");
                    continue;
                }
                badN = false;
            	           	
                
            } else {
            	System.out.println("Input not valid. Choose a positive Integer.");
                continue;
            }
        } while (badN);

        return x;
    }

    /**
     *
     * @param arrayOfNumbs
     * @return number of diff numbers in the array
     */
    public static int numbOfDiffElements(ArrayList arrayOfNumbs) {
        int numOfDifferentVals = 0;

        ArrayList<Integer> diffNum = new ArrayList<>();

        for (int i = 0; i < arrayOfNumbs.size(); i++) {
            if (!diffNum.contains((int)arrayOfNumbs.get(i))) {
                diffNum.add((Integer) arrayOfNumbs.get(i));
            }
        }
            numOfDifferentVals = diffNum.size();
        
        return numOfDifferentVals;
    }
    
    
    /**
     * Compares all the solutions by their total diff of distances and choose the one that's more precise
     * @param solutions array of solutions
     * @param iterations number iterations
     * @return index of the solution w/ better results
     */
    public static int indexFinalSol(FinalSol solutions[], int iterations){
    	
    	 double finalDiffTemp = solutions[0].getFinalDiff();
    	 int finalSolIndex = 0;
    	 System.out.println("Comparing the best solutions found... ");
         System.out.println("Initial Diff of distances: " + finalDiffTemp);
         System.out.println(solutions[finalSolIndex].toString());
         for (int i = finalSolIndex+1; i < solutions.length; i++) {
             if (solutions[i].getFinalDiff() < solutions[finalSolIndex].getFinalDiff()) {
                 finalDiffTemp = solutions[i].getFinalDiff();
                 finalSolIndex = i;
                 System.out.println("Switching to better solution: ");
                 System.out.println(solutions[finalSolIndex].toString());
             }
         }
         return finalSolIndex;
    }
        
   
  
    
}
