
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//K Nearest Neighbors Example.

//Given Height and Weight will determine clothing size
//Data stored in KNNData.txt

public class KNN {
	
	public static void main(String[] args) throws FileNotFoundException {		//Read and prepare data for KNN algorithm
		File file = new File("KNNData.txt");
		Scanner sc = new Scanner(file);
		
		ArrayList<Row> data = new ArrayList<Row>();
		
		sc.nextLine();		
		while(sc.hasNext()) {
			data.add(new Row(sc.nextInt(), sc.nextInt(), sc.next()));			//Load data into ArrayList
		}
		
		System.out.println(knn(data, 161, 61, 5));													//Run KNN with Height = 161, Weight = 61 and K = 5
	}
	
	@SuppressWarnings("unchecked")
	public static String knn(ArrayList<Row> data, int h, int w, int kn) {		//KNN Algorithm
		ArrayList<String> nn = new ArrayList<String>();
		for(Row r : data) {	
			double dist = Math.sqrt(Math.pow(h-r.height, 2) + Math.pow(w-r.weight, 2));		//Calculate Euclidean distance
			r.distance = dist;
		}
		Collections.sort(data);													//Sort data by shortest distance
		for(int i = 0; i < kn; i++) {											//Get size attribute of top-k neighbors
			nn.add(data.get(i).size);
		}
		String[] sizes = {"S", "M", "L"};
		int max = 0; String out = "";											
	    for(String s: sizes) {													//Find highest size occurrence
	    	int a = Collections.frequency(nn, s);
	    	if(a > max) {
	    		max = a;
	    		out = s;
	    	}
	    }
	    //System.out.println(out + " : " + max + "/" + nn.size() + " neighbors.");  //Print result and ratio of result
	    return out;
	}
}

class Row implements Comparable<Object> {										//Object to store each piece of data
	int height, weight;
	double distance;
	String size;
	public Row(int a, int b, String c) {
		height = a;
		weight = b;
		size = c;
	}
	
	public String print() {														//Print all attributes
		return height + " " + weight + " " + size + " " + distance;
	}

	@Override
	public int compareTo(Object r) {											//Compare Rows on Euclidean Distance
		if(r.getClass().equals(this.getClass())) {
			return (int) ((distance - ((Row)r).distance) * 10000000);
		}
		return 0;
	}
}
