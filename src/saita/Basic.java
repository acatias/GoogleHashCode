package saita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import chereau.Chereau;



public class Basic {
	
	public static Integer[] go(Node[] nodes, int nodeStart, int time, int voitures) {

		Long km = 0l;
		
		Hashtable visitedRoutes = new Hashtable();
				
		ArrayList <Integer> allVisitedNodes = new ArrayList <Integer> ();
		
		allVisitedNodes.add(voitures);
		
		for(int v = 0; v < voitures; v++) {

			System.out.println("Voiture: " + v);
			
			ArrayList <Integer> visitedNodes = new ArrayList <Integer> ();
			
			int goneTime = 0;		
			int nodeIndex = nodeStart;
						
			visitedNodes.add(nodeIndex);

			Route lastRoute = null;
			
			while (goneTime < time) {
	
				float bestCost = 0;
				float currentCost;
				Route bestRoute = null;
	
				Node currentNode = nodes[nodeIndex];
				
				//System.out.println("Visited node: " + nodeIndex);
				
				//currentNode.print();
				
				Route[] routes = (Route[]) currentNode.routes.toArray(new Route [0]);
	
				// Best not visited in max (dist/time)
				
				for (int i = 0; i < routes.length; i++) {
										
					//routes[i].print();
										
					if (goneTime + routes[i].time <= time && !routes[i].isVisited) {
					
						currentCost = ((float)routes[i].dist) / routes[i].time;
												
						if (currentCost > bestCost) {
							
							bestCost = currentCost;
							bestRoute = routes[i];	
						}
						
						//System.out.println("Current cost: " + currentCost + " Max cost: " + bestCost);
					}
				}

				if (bestRoute != null) {
					
					km += bestRoute.dist;
				}
				
				// Best visited in min (time) with less than x visits

				if (bestRoute == null) { 

					int bestTime = Integer.MAX_VALUE;
					int currentTime;
					
					for (int i = 0; i < routes.length; i++) {
						
						//routes[i].print();
						
						if (goneTime + routes[i].time <= time) {
						
							currentTime = routes[i].time;
							
							int howManyTimes = 0;
							
							if (visitedRoutes.containsKey(routes[i])) {
								
								howManyTimes = (Integer) (visitedRoutes.get(routes[i]));
								
							}
													
							if (currentTime < bestTime && lastRoute != routes[i] && howManyTimes < 2) {
								
								bestTime = currentTime;
								bestRoute = routes[i];	
							}
							
							//System.out.println("Current time: " + currentTime + " Min time: " + bestTime);
						}
					}
				}

				// Best visited with less than y visits

				if (bestRoute == null) { 
	
					for (int i = 0; i < routes.length; i++) {
						
						//routes[i].print();
						
						if (goneTime + routes[i].time <= time) {
													
							int howManyTimes = 0;
							
							if (visitedRoutes.containsKey(routes[i])) {
								
								howManyTimes = (Integer) (visitedRoutes.get(routes[i]));
							}
													
							if (lastRoute != routes[i] && howManyTimes < 4) {
								
								bestRoute = routes[i];	
							}
							
							//System.out.println("Current time: " + currentTime + " Min time: " + bestTime);
						}
					}
				}

				if (bestRoute == null) { 

					int count = 0;
					
					while (true) {
						
						count++;
						
						int random = (int) (Math.random() * routes.length);

						if (goneTime + routes[random].time <= time && ! routes[random].isVisited) {
							
							bestRoute = routes[random];
							
							break;
						}
						
						if (count > 100) {
							
							break;
						}
					}
				}

				if (bestRoute == null) { 

					int count = 0;
					
					while (true) {
						
						count++;
						
						int random = (int) (Math.random() * routes.length);

						if (goneTime + routes[random].time <= time) {
							
							bestRoute = routes[random];
							
							break;
						}
						
						if (count > 100) {
							
							break;
						}
					}
				}

				
				if (bestRoute != null) {
					
					bestRoute.isVisited = true;
										
					goneTime += bestRoute.time;
						
					nodeIndex = bestRoute.start == nodeIndex ? bestRoute.end : bestRoute.start;					
									
					System.out.println("Voiture: " + v 
										+ " Node: " + nodeIndex 
										+ " Time: " + goneTime + " Km: " + km); 
					
//										+ " Last route: " + lastRoute
//										+ " Best route: " + bestRoute
//										);
					
//					if (lastRoute != null) {
//						lastRoute.print();
//					}
//					
//					if (bestRoute != null) {
//						bestRoute.print();
//					}
					
					lastRoute = bestRoute;
					
					if (visitedRoutes.containsKey(bestRoute)) {
						
						visitedRoutes.put(bestRoute, (Integer) (visitedRoutes.get(bestRoute)) + 1);
						
					} else {
						
						visitedRoutes.put(bestRoute, 1);
					}

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
		
		//n0.print();

		Node n1 = new Node(1); 
		n1.addRoute(route01); // 1 -> 0	
		
		Node n2 = new Node(2); 
		n2.addRoute(new Route(2, 1, 10, 300, false)); // 2 -> 1
		
		Node[] nodes = new Node[3];
		
		nodes[0] = n0;
		nodes[1] = n1;
		nodes[2] = n2;
		
//		System.out.println("Solution");
//		
//		for(int i : Basic.go(nodes, 0, 3000, 2)) {
//			
//			System.out.println(i);
//		}
		
		Node[] graph;
		int voitnum;
		int time;
		int startNode = 0;
		
		try {
			graph = Chereau.generateGraph();
			voitnum = Chereau.extractNbVoiture();
			time = Chereau.extractTime();
			startNode = Chereau.extractInitialNode();
			Integer[] trajets = Basic.go(graph, startNode, time, voitnum);			
			Chereau.createOutput(trajets);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
