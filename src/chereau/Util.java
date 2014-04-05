package chereau;

import java.util.ArrayList;

import saita.Node;
import saita.Route;

public class Util {

	public static Node clone (Node node){
		Node clonedNode = new Node(node.index);
		for(Route aRoute: node.routes){
			clonedNode.addRoute(clone(aRoute));
		}
		return clonedNode;
	}

	public static Route clone(Route route) {
		return new Route(route.start,route.end,route.time,route.dist,route.isDeux);
	}
	
	public static Node[] clone(Node[] consideredGraph){
		Node[] result = new Node[consideredGraph.length];
		for(int i =0; i<consideredGraph.length; i++){
			result[i]=clone(consideredGraph[i]);
		}
		return result;
	}
}
