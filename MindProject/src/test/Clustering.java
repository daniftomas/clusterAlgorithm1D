package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Clustering {

	public static void main(String[] args) {

		
		ArrayList<Integer> aNumb = new ArrayList<Integer>();
		int nClusters;
		
		
		readArray(aNumb);
		System.out.println("Final array size: " + aNumb.size());
		System.out.println("Array:" + aNumb);
		
		System.out.println("Insira o numero de Clusters:");		
		
		do{
		nClusters=readInt();
		
		if(nClusters>aNumb.size()){
			System.out.println("Must insert a number smaller then the array's size: "+ aNumb.size());
		}
		}while(nClusters>aNumb.size());
	
		KmeansAlg.group(aNumb, nClusters);
			
	}
	

	
	
	/**
	 * method to read an array of positive integers and add them to one arraList
	 * @param enpty arrayList
	 */
	public static void readArray(ArrayList<Integer> array) {
		Scanner a;
		int x = 0;
		String st ="";
		String stopSign="end";
		boolean badN=true;
		System.out.println("Insert positive integers: \n(write \"" + stopSign+ "\" to terminate)");
		do {
			a = new Scanner(System.in);
			//if it has an int
			if(a.hasNextInt()){
				try{
					x=Integer.parseInt(a.nextLine());
					if(x<0){
						System.out.println("Input not valid. Insert only positive Integers.");	
						continue;
					}
				}catch(NumberFormatException e){
					System.out.println("Input not valid. Insert only a Integer.");	
					continue;
				}
				array.add(x);
				System.out.println("+"+array.size()+" added");
				badN=false;
			}
			else  {
				//if it has String
				st = a.nextLine();
				if(st.equals(stopSign)){
					if(!array.isEmpty()){
					System.out.println("end of array");
					badN=false;
					break;
					}
					else{
						System.out.println("The Array is still empty, please insert Integers.");
						st="";
						continue;
					}
				}
				else{
					System.out.println("Input not valid. Choose an Integer.");
				}
			}
		} while (!st.equals(stopSign) || badN );

	}
	
	/**
	 * method to read one positive Int
	 * @return return a integer 
	 */
	public static int readInt(){
		Scanner b;
		int x = 0;
		boolean badN=true;
		do {
			b = new Scanner(System.in);
			if(!b.hasNextInt()){
				System.out.println("Input not valid. Choose an Integer.");
				continue;	
				
			}
			else  {
				try{
					x=Integer.parseInt(b.nextLine());
					if(x<0){
						System.out.println("Input not valid. Insert only a positive Integer.");	
						continue;
					}
				}catch(NumberFormatException e){
					System.out.println("Input not valid. Insert only a Integer.");	
					continue;
				}
					badN=false;
			}
		} while (badN);
	
		return x;
	
	}

}
