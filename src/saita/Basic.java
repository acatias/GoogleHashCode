package saita;

import java.util.ArrayList;

public class Basic {
	
	public static Integer[] go(Node[] nodes, int nodeStart, int time, int voitures) {
		
		ArrayList <Integer> allVisitedNodes = new ArrayList <Integer> ();
		
		allVisitedNodes.add(voitures);
		
		for(int v = 0; v < voitures; v++) {

			System.out.println("Voiture: " + v);
			
			ArrayList <Integer> visitedNodes = new ArrayList <Integer> ();
			
			int goneTime = 0;		
			int nodeIndex = nodeStart;
						
			visitedNodes.add(nodeIndex);
			
			while (goneTime < time && nodeIndex < nodes.length) {
	
				float bestCost = 0;
				float currentCost;
				Route bestRoute = null;
	
				Node currentNode = nodes[nodeIndex];
				
				System.out.println("Visited node: " + nodeIndex);
				
				currentNode.print();
				
				Route[] routes = (Route[]) currentNode.routes.toArray(new Route [0]);
	
				for (int i = 0; i < routes.length; i++) {
					
					// ToDo:  To revisit if necessary
					
					routes[i].print();
					
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
		
		Node n0 = new Node(0);
		
		Route route01 = new Route(1, 0, 10, 100, true); 
		
		n0.addRoute(route01); // 0 -> 1
		n0.addRoute(new Route(0, 2, 10, 200, false)); // 0 -> 2
		
		n0.print();

		Node n1 = new Node(1); 
		n1.addRoute(route01); // 1 -> 0	
		
		Node n2 = new Node(2); 
		n2.addRoute(new Route(2, 1, 10, 300, false)); // 2 -> 1
		
		Node[] nodes = new Node[3];
		
		nodes[0] = n0;
		nodes[1] = n1;
		nodes[2] = n2;
		
		System.out.println("Solution");
		
		for(int i : Basic.go(nodes, 0, 3000, 2)) {
			
			System.out.println(i);
		}
	}
}
