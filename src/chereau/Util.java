package chereau;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	public static <T> List<T> clone(List<T> consideredList){
		List<T> resultList = new LinkedList<T>();
		for(int i =0; i<consideredList.size(); i++){
			resultList.add(consideredList.get(i));
		}
		return resultList;
	}	
}
