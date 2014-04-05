package saita;

import java.util.ArrayList;
import java.util.Iterator;

public class Node {
	
	int index;
	
	ArrayList <Route> routes = new ArrayList <Route> ();
	
	public Node(int index) {
		this.index = index;
	}
	
	public void addRoute(Route route) {
		
		routes.add(route);
	}
	
	public void print() {
		System.out.println("Node " + index + ": ");
		
		Iterator <Route> iter = routes.iterator();
		
		while(iter.hasNext()) {
			
			Route route = iter.next();
			
			route.print();
		}
	}
	
	public static void main(String[] test) {
		
		Node n = new Node(1);
		
		n.addRoute(new Route(1, 5, 50, 500, false));
		n.addRoute(new Route(2, 1, 20, 200, true));
		
		n.print();
	}

}
