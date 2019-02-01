
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

//K Nearest Neighbor Example

//Given Height and Weight will determine clothing size
//Data stored in MySQL Database

//Before running this class: 
//   1) Run KNN-MySQL-Data.sql to create database, table and data.
//   2) Edit JDBC URL to work with your system.


public class JavaMySQLExample {
	
	static ArrayList<Row2> data = new ArrayList<Row2>();
	
	public static void main(String[] args) {												//Connect to database and prepare data
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Loaded driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","toor");	//Connect to database
            System.out.println("Connected to MySQL");
            
            Statement stmt = conn.createStatement();										//Select all data from table "sizes"
            String query = "SELECT * FROM sizes";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
            	data.add(new Row2(rs.getInt("height"), rs.getInt("weight"), rs.getString("size")));
            }
            
            conn.close();
		} 
		catch (Exception ex) {
            ex.printStackTrace();
		}
		
		knn(data, 161, 61, 5);	
	}
	
	@SuppressWarnings("unchecked")
	public static String knn(ArrayList<Row2> data2, int h, int w, int kn) {					//KNN Algorithm
		ArrayList<String> nn = new ArrayList<String>();
		for(Row2 r : data2) {	
			double dist = Math.sqrt(Math.pow(h-r.height, 2) + Math.pow(w-r.weight, 2));		//Calculate Euclidean distance
			r.distance = dist;
		}
		Collections.sort(data2);															//Sort data by shortest distance
		for(int i = 0; i < kn; i++) {														//Get size attribute of top-k neighbors
			nn.add(data2.get(i).size);
		}
		String[] sizes = {"S", "M", "L"};
		int max = 0; String out = "";											
	    for(String s: sizes) {																//Find highest size occurrence
	    	int a = Collections.frequency(nn, s);
	    	if(a > max) {
	    		max = a;
	    		out = s;
	    	}
	    }
	    System.out.println(out + " : " + max + "/" + nn.size() + " neighbors.");			//Print results
	    return out;
	}
}

class Row2 implements Comparable<Object> {													//Object to store each pieces of data
	int height, weight;
	double distance;
	String size;
	public Row2(int a, int b, String c) {
		height = a;
		weight = b;
		size = c;
	}
	
	public String print() {																	//Print all attributes
		return height + " " + weight + " " + size + " " + distance;
	}

	@Override
	public int compareTo(Object r) {														//Compare Rows on Euclidean Distance
		if(r.getClass().equals(this.getClass())) {
			return (int) ((distance - ((Row2)r).distance) * 10000000);
		}
		return 0;
	}
}
