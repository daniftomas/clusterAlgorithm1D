package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Clustering {

	public static void main(String[] args) {

		
		ArrayList<Integer> aNumb = new ArrayList<Integer>();
		int nClusters;
		
		System.out.println("ini size: " + aNumb.size());
		
		readArray(aNumb);
		System.out.println("Final array size" + aNumb.size());
		System.out.println("Array:" + aNumb);
		
		System.out.println("Insira o numero de Clusters:");		
		
		do{
		nClusters=readInt();
		
		if(nClusters>aNumb.size()){
			System.out.println("Must insert a number smaller then the array's size: "+ aNumb.size());
		}
		}while(nClusters>aNumb.size());
	

		KmeansAlg.cenas(aNumb, nClusters);
			
	}
	

	
	//method to read an array of integers
	public static void readArray(ArrayList<Integer> array) {
		Scanner a;
		int x = 0;
		String st ="";
		String stopSign="end";
		boolean badN=true;
		System.out.println("insert numbers");
		do {
			a = new Scanner(System.in);
			if(a.hasNextInt()){
				try{
					x=Integer.parseInt(a.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Char not valid. Insert only numbers numbers.");	
					continue;
				}
				array.add(x);
				System.out.println("+1 added");
				badN=false;
			}
			else  {
				st = a.nextLine();
				if(st.equals(stopSign)){
					if(!array.isEmpty()){
					System.out.println("end of array");
					badN=false;
					break;
					}
					else{
						System.out.println("the Array is still empty, please insert numbers.");
						continue;
					}
				}
				else{
					System.out.println("Char not valid. Choose an Integer.");
				}
			}
		} while (!st.equals(stopSign) || badN );

	}
	
	//method to read one Int
	public static int readInt(){
		Scanner b;
		int x = 0;
		boolean badN=true;
		do {
			b = new Scanner(System.in);
			if(!b.hasNextInt()){
				System.out.println("Char not valid. Choose a number.");
				continue;	
			}
			else  {
				try{
					x=Integer.parseInt(b.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Char not valid. Insert only numbers numbers.");	
					continue;
				}
					badN=false;
			}
		} while (badN);
		//b.close();
		return x;
	
	}

}
