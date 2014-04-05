package saita;

import java.util.ArrayList;

public class Basic {
	
	public static Integer[] go(Node[] nodes, int nodeStart, int time, int voitures) {
		
		ArrayList <Integer> allVisitedNodes = new ArrayList <Integer> ();
		
		allVisitedNodes.add(voitures);
		
		for(int v = 0; v < voitures; v++) {

			ArrayList <Integer> visitedNodes = new ArrayList <Integer> ();
			
			int goneTime = 0;		
			int nodeIndex = nodeStart;
						
			visitedNodes.add(nodeIndex);
			
			while (goneTime < time && nodeIndex < nodes.length) {
	
				float bestCost = 0;
				float currentCost;
				Route bestRoute = null;
	
				Node currentNode = nodes[nodeIndex];
				
				Route[] routes = (Route[]) currentNode.routes.toArray(new Route [0]);
	
				for (int i = 0; i < routes.length; i++) {
					
					// ToDo:  To revisit if necessary
					
					if (goneTime + routes[i].time <= time && !routes[i].isVisited) {
					
						currentCost = ((float)routes[i].dist) / routes[i].time;
						
						if (currentCost > bestCost) {
							
							bestCost = currentCost;
							bestRoute = routes[i];	
						}
					}
				}
			
				if (bestRoute != null) {
					
					bestRoute.isVisited = true;
					
					goneTime += bestRoute.time;
	
					nodeIndex = bestRoute.start == nodeIndex ? bestRoute.end : bestRoute.start;
				
					visitedNodes.add(nodeIndex);
					
				} else {
					
					break;
					
				}
			}
			
			allVisitedNodes.add(visitedNodes.size());
			
			allVisitedNodes.addAll(visitedNodes);
		
		}
		
		return (Integer[]) allVisitedNodes.toArray(new Integer[0]);
	}
	
	public static void main(String[] args) {
		
		Node n1 = new Node(1);
		
		Route route12 = new Route(2, 1, 20, 200, true); 
		
		n1.addRoute(new Route(1, 3, 50, 500, false));
		n1.addRoute(route12);
		
		n1.print();

		Node n2 = new Node(2);
		
		n2.addRoute(route12);	
		n2.addRoute(new Route(1, 4, 40, 400, false));

		Node[] nodes = new Node[2];
		
		nodes[0] = n1;
		nodes[1] = n2;
		
		System.out.println("Solution");
		
		for(int i : Basic.go(nodes, 1, 3000, 2)) {
			
			System.out.println(i);
		}
	}
}
