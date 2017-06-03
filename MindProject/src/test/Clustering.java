package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Clustering {

    public static void main(String[] args) {

    	ArrayList<Integer> aNumb = new ArrayList<Integer>();
    	
        int nClusters;
        int iterations = 20;
        FinalSol solutions[] = new FinalSol[iterations];
        
        

        readArray(aNumb);
        System.out.println("Final array size: " + aNumb.size());
        System.out.println("Array:" + aNumb);
        System.out.println("Insira o numero de Clusters:");

        do {
            nClusters = readInt();

            if (nClusters > aNumb.size()) {
                System.out.println("Must insert a number smaller then the array's size: " + aNumb.size());
            }
            if (nClusters > numbOfDiffElements(aNumb)) {
                System.out.println("Must insert a number less or equal to the number of different elements: " + numbOfDiffElements(aNumb));
            }

        } while (nClusters > aNumb.size() || numbOfDiffElements(aNumb) < nClusters);

        for (int i = 0; i < iterations; i++) {
            solutions[i] = KmeansAlg.group(aNumb, nClusters);
            
            System.out.println("Printing solution " + (i+1) + ": ");
            System.out.println(solutions[i].toString());
        }

        int finalSolIndex = indexFinalSol(solutions, iterations);
        /*
        double finalDiffTemp = solutions[0].getFinalDiff();
        System.out.println("Initial finalDiffTemp: " + finalDiffTemp);
        for (int i = finalSolIndex+1; i < iterations; i++) {
            if (solutions[i].getFinalDiff() < solutions[finalSolIndex].getFinalDiff()) {
                finalDiffTemp = solutions[i].getFinalDiff();
                finalSolIndex = i;
                System.out.println("Switching to a FinalDiffTemp of: " + finalDiffTemp);
            }
        }
        */
        
        System.out.println("\n\n Best solution found:");
        System.out.println(solutions[finalSolIndex].toString());
    }

    /**
     * method to read an array of positive integers and add them to one arraList
     *
     * @param enpty arrayList
     */
    public static void readArray(ArrayList<Integer> array) {
        Scanner a;
        int x = 0;
        String st = "";
        String stopSign = "end";
        boolean badN = true;
        System.out.println("Insert positive integers: \n(write \"" + stopSign + "\" to terminate)");
        do {
            a = new Scanner(System.in);
            //if it has an int
            if (a.hasNextInt()) {
                try {
                    x = Integer.parseInt(a.nextLine());
                    if (x < 0) {
                        System.out.println("Input not valid. Insert only positive Integers.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input not valid. Insert only a Integer.");
                    continue;
                }
                array.add(x);
                System.out.println("+" + array.size() + " added");
                badN = false;
            } else {
                //if it has String
                st = a.nextLine();
                if (st.equals(stopSign)) {
                    if (!array.isEmpty()) {
                        System.out.println("end of array");
                        badN = false;
                        break;
                    } else {
                        System.out.println("The Array is still empty, please insert Integers.");
                        st = "";
                        continue;
                    }
                } else {
                    System.out.println("Input not valid. Choose an Integer.");
                }
            }
        } while (!st.equals(stopSign) || badN);

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
            if (!b.hasNextInt()) {
                System.out.println("Input not valid. Choose an Integer.");
                continue;

            } else {
                try {
                    x = Integer.parseInt(b.nextLine());
                    if (x < 0) {
                        System.out.println("Input not valid. Insert only a positive Integer.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input not valid. Insert only a Integer.");
                    continue;
                }
                badN = false;
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

        if (diffNum.size() == 1) {
            numOfDifferentVals = 0; // all elements are the same
        } else {
            numOfDifferentVals = diffNum.size();
        }

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
         System.out.println("Initial finalDiffTemp: " + finalDiffTemp);
         for (int i = finalSolIndex+1; i < solutions.length; i++) {
             if (solutions[i].getFinalDiff() < solutions[finalSolIndex].getFinalDiff()) {
                 finalDiffTemp = solutions[i].getFinalDiff();
                 finalSolIndex = i;
                 System.out.println("Switching to a FinalDiffTemp of: " + finalDiffTemp);
             }
         }
         return finalSolIndex;
    }
}
