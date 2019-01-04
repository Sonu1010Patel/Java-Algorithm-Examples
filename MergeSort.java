

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		double[] d = {4,2,3,5,4,1,6};								//Input data
		System.out.println(Arrays.toString(mergesort(d)));			
	}
	
	public static double[] mergesort(double[] input) {				//Separates input array into 2 arrays of equal size 
		int size = input.length;
		if (size <= 1) {
			return input;
		}
		
		double[] a = new double[size/2];
		double[] b = new double[size - size/2];
		
		for (int i = 0; i < a.length; i++) {
			a[i] = input[i];
		}
		for (int i = 0; i < b.length; i++) {
			b[i] = input[i + size/2];
		}
		
		return merge(mergesort(a), mergesort(b));					//Calls merge function
	}
	
	private static double[] merge(double[] a, double[] b) {			//Creates an ordered array from 2 ordered arrays
		double[] c = new double[a.length + b.length];
		int i = 0;
		int j = 0;
		
		for (int k = 0; k < c.length; k++) {
			if (i >= a.length) {
				c[k] = b[j++];
			}
			else if (j >= b.length) { 
				c[k] = a[i++];
			}
			else if (a[i] <= b[j]) {
				c[k] = a[i++];
			}
			else {
				c[k] = b[j++];
			}
		}
		return c;
	}

}