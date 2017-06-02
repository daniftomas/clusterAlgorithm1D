package test;

import java.util.ArrayList;
import java.util.Random;

public class KmeansAlg {
	static int d[]; // array w/ nº of the arrayL
    static int k[][]; // matriz w/ solution
    static int tempk[][]; // matriz w/ temp solution
    static double m[]; // array w/ size of clusters asked, means
    static double diff[]; // distances

    /**
     * method to group integers considering their distance
     *
     * @param a arrayList that contains the inserted numbers
     * @param p number of clusters requested
     * @return
     */
    public static FinalSol group(ArrayList a, int p) {

        FinalSol solution = new FinalSol(a.size(), p);
        /* Initialising arrays */

        int n = a.size();// size of the array
        // turn arrayL to array
        d = new int[n];
        for (int i = 0; i < a.size(); i++) {
            d[i] = (int) a.get(i);
        }

        k = new int[p][n]; // solution
        tempk = new int[p][n]; // temp solution
        m = new double[p]; // means
        diff = new double[p]; // diferences
        int count[] = new int[p]; // column position in the solution K

        /* Initializing m */
        for (int i = 0; i < p; ++i) {
            m[i] = -1;
        }

        m = getRandomCentroids(d, p);

        int temp = 0;
        int flag = 0;
        do {
            // create matriz w/ -1 elements
            for (int i = 0; i < p; ++i) {
                for (int j = 0; j < n; ++j) {
                    k[i][j] = -1;
                }
            }
            // for loop will cal cal_diff(int) for every element.
            for (int i = 0; i < n; ++i) {
                temp = cal_diff(d[i], p);

                k[temp][count[temp]++] = d[i];
            }

            // call to method which will calculate mean at this step.
            cal_mean(p, n);

            // check if terminating condition is satisfied.
            flag = check1(p, n);
            if (flag != 1) // Take backup of k in tempk so that it can be checked for  equivalence in next step
            {
                for (int i = 0; i < p; ++i) {
                    for (int j = 0; j < n; ++j) {
                        tempk[i][j] = k[i][j];
                    }
                }
            }

            /*System.out.println("\n\nAt this step");
             System.out.println("\nValue of clusters");
			
             for (int i = 0; i < p; ++i) {
             System.out.print("K" + (i + 1) + "{ ");
             for (int j = 0; k[i][j] != -1 && j < n - 1; ++j)
             System.out.print(k[i][j] + " ");
             System.out.println("}");
             }
             System.out.println("\nValue of m ");
			
             for (int i = 0; i < p; ++i)
             System.out.print("m" + (i + 1) + "=" + m[i] + "  ");
             */
            for (int t = 0; t < count.length; t++) {
                count[t] = 0;
            }
        } while (flag == 0);

        //set solution
        solution.setCentroid(m);
        solution.setSolution(k);
        solution.toString();

        /*System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
         for (int i = 0; i < p; ++i) {
         System.out.print("K" + (i + 1) + "{ ");
         for (int j = 0; k[i][j] != -1 && j < n - 1; ++j)
         System.out.print(k[i][j] + " ");
         System.out.println("}");
         }*/
        return solution;
    }

    /**
     * This method will determine the cluster in which an element go at a
     * particular step.
     *
     * @param a element i of the array
     * @param p number of clusters requested
     * @return the [i], that represents a cluster, in which the element will go
     */
    public static int cal_diff(int a, int p) {
        int temp1 = 0;
        for (int i = 0; i < p; ++i) {
            diff[i] = Math.abs(a - m[i]);

        }
        int val = 0;
        double temp = diff[0];
        for (int i = 0; i < p; ++i) {
            if (diff[i] < temp) {
                temp = diff[i];
                val = i;
            }
        }
        return val;
    }

    /**
     * This method will determine intermediate mean values
     *
     * @param p number of clusters requested
     * @param n number of elements of the array inserted
     */
    public static void cal_mean(int p, int n) {
        for (int i = 0; i < p; ++i) {
            m[i] = 0; // initializing means to 0
        }
        int cnt = 0;
        for (int i = 0; i < p; ++i) {
            cnt = 0;
            for (int j = 0; j < n - 1; ++j) {
                if (k[i][j] != -1) {
                    m[i] += k[i][j];
                    ++cnt;
                }
            }
            m[i] = m[i] / cnt;
        }
    }

    /**
     * // This checks if previous k( tempk) and current k are same.Used as
     * terminating case. Validates solution.
     *
     * @param p number of clusters requested
     * @param n number of elements of the array inserted
     * @return 1 if the tempK is equal to the solution, 0 if its not.
     */
    public static int check1(int p, int n) {
        for (int i = 0; i < p; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tempk[i][j] != k[i][j]) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     *
     * @param array inserted array
     * @param p number of clusters
     * @return array of inicial rondom centroids
     */
    public static double[] getRandomCentroids(int[] array, int p) {
        double[] arrayCent = new double[p];

        for (int i = 0; i < p; i++) {
            do {
                int rndIndex = new Random().nextInt(array.length);
                arrayCent[i] = array[rndIndex];
            } while (currentCentroidEqualToPreviousOnes(arrayCent, i));

        }
        for (int t = 0; t < p; t++) {
            System.out.println("Initial Centroid " + (t+1) + ": " + arrayCent[t]);
        }

        return arrayCent;
    }

    /**
     *
     * @param arrayCent array of centroids
     * @param currentCentroidIndex index of chosen centroid
     * @return true if current has already been chosen
     */
    private static boolean currentCentroidEqualToPreviousOnes(double[] arrayCent, int currentCentroidIndex) {
        boolean currentCentroidEqualToPrevious = false;
        for (int i = 0; i < currentCentroidIndex; i++) {
            if (arrayCent[currentCentroidIndex] == arrayCent[i]) {
                currentCentroidEqualToPrevious = true;
                break;
            }
        }
        return currentCentroidEqualToPrevious;
    }

}
