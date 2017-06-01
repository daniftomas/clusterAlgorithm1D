package test;

import java.util.ArrayList;

public class KmeansAlg {
	static int d[]; // array w/ nº of the arrayL
	static int k[][]; // matriz w/ solution
	static int tempk[][]; // matriz w/ solution
	static double m[]; // array w/ size of clusters asked, mean!
	static double diff[]; // distances 

	public static void cenas(ArrayList a, int p) {

		/* Initialising arrays */

		int n = a.size();//size of the array
		//turn arrayL to array 
		d=new int[n];
		for (int i=0; i<a.size(); i++){
			d[i]= (int) a.get(i);
		}
		
		k = new int[p][n]; //solution
		tempk = new int[p][n]; // temp solution
		m = new double[p]; //means
		diff = new double[p]; //diferences
		int count[] = new int[p]; // column position in the solution K
		/* Initializing m */
		for (int i = 0; i < p; ++i)
			m[i] = d[i];

		int temp = 0;
		int flag = 0;
		do {
			for (int i = 0; i < p; ++i)
				for (int j = 0; j < n; ++j) {
					k[i][j] = -1;
				}
			for (int i = 0; i < n; ++i) // for loop will cal cal_diff(int) for
										// every element.
			{
				temp = cal_diff(d[i], p);

				k[temp][count[temp]++] = d[i];
			}
			 cal_mean(p, n); // call to method which will calculate mean at this
			// step.
			 flag=check1(p,n); // check if terminating condition is satisfied.
			if (flag != 1)
				/*
				 * Take backup of k in tempk so that you can check for
				 * equivalence in next step
				 */
				for (int i = 0; i < p; ++i)
					for (int j = 0; j < n; ++j)
						tempk[i][j] = k[i][j];

			System.out.println("\n\nAt this step");
			System.out.println("\nValue of clusters");
			for (int i = 0; i < p; ++i) {
				System.out.print("K" + (i + 1) + "{ ");
				for (int j = 0; k[i][j] != -1 && j < n - 1; ++j)
					System.out.print(k[i][j] + " ");
				System.out.println("}");
			} // end of for loop
			System.out.println("\nValue of m ");
			for (int i = 0; i < p; ++i)
				System.out.print("m" + (i + 1) + "=" + m[i] + "  ");

			for (int t = 0; t < count.length; t++) {
				count[t] = 0;
			}

		} while (flag == 0);

		System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
		for (int i = 0; i < p; ++i) {
			System.out.print("K" + (i + 1) + "{ ");
			for (int j = 0; k[i][j] != -1 && j < n - 1; ++j)
				System.out.print(k[i][j] + " ");
			System.out.println("}");
		}
	}

	
	/**
	 * This method will determine the cluster in which an element go at a particular step.
	 * @param a element i of the array
	 * @param p number of clusters requested
	 * @return the [i], that represents a cluster,  in which the element will go
	 */
	static int cal_diff(int a, int p) {
		int temp1 = 0;
		for (int i = 0; i < p; ++i) {
			diff[i] =  Math.abs(a - m[i]);
			/*
			if (a > m[i])
				diff[i] = a - m[i];
			else
				diff[i] = m[i] - a;
				*/
		}
		int val = 0;
		double temp = diff[0];
		for (int i = 0; i < p; ++i) {
			if (diff[i] < temp) {
				temp = diff[i];
				val = i;
			}
		} // end of for loop
		return val;
	}
	
/**
 *  This method will determine intermediate mean values
 * @param p number of clusters requested
 * @param n number of elements  of the array inserted
 */
	static void cal_mean(int p, int n) 
	{
		for (int i = 0; i < p; ++i)
			m[i] = 0; // initializing means to 0
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
	 * // This checks if previous k( tempk) and current k are same.Used as terminating  case. Validates solution.
	 * @param p number of clusters requested
	 * @param n number of elements of the array inserted
	 * @return 1 if the tempK is equal to the solution, 0 if its not.
	 */
	static int check1(int p, int n) 
	{
		for (int i = 0; i < p; ++i)
			for (int j = 0; j < n; ++j)
				if (tempk[i][j] != k[i][j]) {
					return 0;
				}
		return 1;
	}
}

/*
Enter the number of elements
8
Enter 8 elements:
2 3 6 8 12 15 18 22
Enter the number of clusters:
3

At this step
Value of clusters
K1{ 2 }
K2{ 3 }
K3{ 6 8 12 15 18 22 }
Value of m
m1=2.0  m2=3.0  m3=13.5

At this step
Value of clusters
K1{ 2 }
K2{ 3 6 8 }
K3{ 12 15 18 22 }
Value of m
m1=2.0  m2=5.666666666666667  m3=16.75

At this step
Value of clusters
K1{ 2 3 }
K2{ 6 8 }
K3{ 12 15 18 22 }
Value of m
m1=2.5  m2=7.0  m3=16.75

At this step
Value of clusters
K1{ 2 3 }
K2{ 6 8 }
K3{ 12 15 18 22 }
Value of m
m1=2.5  m2=7.0  m3=16.75

The Final Clusters By Kmeans are as follows:
K1{ 2 3 }
K2{ 6 8 }
K3{ 12 15 18 22 } */
