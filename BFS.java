
import java.util.ArrayList;

public class BFS {
	static Node start;
	static ArrayList<Node> nodesVisited;
	
	public static void main(String[] args) {
		graph g = new graph();
		start = g.createGraph();							//Create data
		
		bfs("Fred");										//Start Breadth-first search
	}
	
	public static void bfs(String d) {						//Breadth-first search algorithm
		nodesVisited = new ArrayList<Node>();
		int ret;
		if(start.getName().equals(d)) { 					//Check first node
			ret = start.data;
		}
		else{					
			ret = bfs2(start, d);							//Use BFS algorithm
		}
		if(ret == -999) {
			System.out.println("No record found.");
		}
		else {
			System.out.println(d + ": " + ret);
		}
	}
	
	public static int bfs2(Node n, String s) {				//Checks all connected nodes without repeating
		nodesVisited.add(n);
		for(Node n1 : n.nodes) {							//Check all directly connected nodes
			if(!nodesVisited.contains(n1)) {
				if(n1.getName().equals(s)) {
					return n1.data;
				}
			}
		}
		for(Node n1 : n.nodes) {
			if(!nodesVisited.contains(n1)) {
				int ret = bfs2(n1, s);						//Recursive call on each directly connected node
				if(ret != -999) {
					return ret;
				}
			}
		}
		return -999;
	}
}

class graph {												//Create graph / data
	public Node createGraph() {
		Node a = new Node(1, "Adam");
		Node b = new Node(2, "Benny");
		Node c = new Node(3, "Carrie");
		Node d = new Node(4, "Danny");
		Node e = new Node(5, "Edward");
		Node f = new Node(6, "Fred");
		a.addNode(b);
		a.addNode(c);
		a.addNode(d);
		b.addNode(a);
		b.addNode(e);
		c.addNode(a);
		c.addNode(d);
		d.addNode(a);
		d.addNode(c);
		d.addNode(e);
		d.addNode(f);
		e.addNode(b);
		e.addNode(d);
		f.addNode(d);
		return a;
	}
}

class Node {												//Graph node objects
	int data;
	String name;
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public Node(int i, String s) {
		data = i;
		name = s;
	}
	
	public void addNode(Node n) {
		nodes.add(n);
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public int getData() {
		return data;
	}
	
	public String getName() {
		return name;
	}
}

