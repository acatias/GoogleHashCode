package saita;

public class Basic {

	Node[] nodes;
	
	public Basic(Node[] nodes) {
		
		this.nodes = nodes;
	}
	
	public static void main(String[] args) {
		
		Node n = new Node(1);
		
		n.addRoute(new Route(1, 5, 50, 500, false));
		n.addRoute(new Route(2, 1, 20, 200, true));
		
		n.print();
		
		

	}

}
