package tonye;

import java.io.IOException;
import java.util.ArrayList;

import chereau.Chereau;

public class MyPrimAlgorithm {
	
	//THIS CLASS WILL HANDLE THE REACHEABLE NODE
	public static class Reachable{
		public Node node;
		public Route road;
		public Reachable(Node node, Route road){
			this.node = node;
			this.road = road;
		}
	}
	
	public static boolean isAMarkedJunction(int nodeId, ArrayList<Node> markedJunctions){
		boolean isMarked = false;
		for (int j = 0; (!isMarked) && (j < markedJunctions.size()); j++){
			isMarked = (j == markedJunctions.get(j).index);
		}
		
		return (isMarked);
	}
	
	public static boolean hasUnmarkedNeighbour(Node[] junctions, ArrayList<Node> markedJunctions){
		boolean found = false;
		for (int i = 0; (!found) && (i < junctions.length); i++){
			for (int j = 0; (!found) && (j < markedJunctions.size()); j++){
				found = (i == markedJunctions.get(j).index);
			}
		}
		return (found);
	}
	
	/**
	 * @return the destination node of the road
	 * **/
	public static int getDestinationNode(int nodeId, Route route){
		return ((route.start == nodeId) ? route.end : route.start);
	}
	
	public static Car[] initializeCars(int nbCars, int nodeIndex){
		Car[] cars = new Car[nbCars];
		for (int i = 0; i < nbCars; i++){
			cars[i].carIndex = i;
			cars[i].visitedNodes.add(nodeIndex);
		}
		return null;
	}
	
	public static boolean hasACarAvailable(int nodeIndex, Car[] cars){
		boolean found = false;
		for (int i = 0; (!found) && (i < cars.length); i++){
			if (cars[i].currentNodeIndex == nodeIndex){
				found = true;
			}
		}
		return (found);
	}
	
	public static Reachable getBestJunction(ArrayList<Reachable> reacheablesJunctions, int timelimit){
		Reachable best = null;
		for (Reachable reachable : reacheablesJunctions){
			if (reachable.road.time <= timelimit){
				if ((reachable.road.dist / reachable.road.time) < (best.road.dist / best.road.time)){
					best = reachable;
				}
			}
		}
		return (best);
	}
	
	public static void moveCar(Car[] cars, Reachable best){
		int startNodeIndex = getDestinationNode(best.node.index, best.road);
		Car car = null;
		for (int i = 0; (car == null) && (i < cars.length); i++){
			if (cars[i].currentNodeIndex == startNodeIndex){
				car = cars[i];
			}
		}
		car.addNode(best.node.index);
	}
	
	/**
	 * @param Node[] all the junctions
	 * this method will build a minimum tree coverage for the junctions
	 * **/
	public static int MyPrimAlgorithm(Node[] junctions){
		//EXTRACT THE STARTING NODE
		Node startJunction;
		int availableTime;
		Car[] cars;
		try {
			startJunction = junctions[Chereau.extractInitialNode()];
			cars = initializeCars(Chereau.extractNbVoiture(), startJunction.index);
			availableTime = Chereau.extractTime();
		} catch (IOException e) {
			e.printStackTrace();
			return (0);
		}
		
		
		//BUILD THE MARKED NODES ARRAY
		ArrayList<Node> markedJunctions = new ArrayList <Node> ();
		markedJunctions.add(startJunction);
		
		//LOOP WHILE THE TREE CAN GROW
		while (!hasUnmarkedNeighbour(junctions, markedJunctions)){
			
			//BUILD A LIST OF JUNCTIONS THAT CAN BE JOINED 
			ArrayList<Reachable> reacheablesJunctions = new ArrayList<Reachable>();
			
			//LOOP ON MARKED ARRAY TO GET ALL REACHABLE JUNCTIONS
			for (int i = 0; i < markedJunctions.size(); i++){
				if (!hasACarAvailable(markedJunctions.get(i).index, cars))
					continue;
				//CHECK EACH MARKED JUNCTION ROADS TO GET THE REACHABLE NODES
				for (int j = 0; (j < markedJunctions.get(i).routes.size()); j++){
					Route road = markedJunctions.get(i).routes.get(j);
					int destNodeId = getDestinationNode(markedJunctions.get(i).index, road);
					
					//IF THE ROAD HAS NOT BEEN VISITED OR THE REACHABLE JUNCTION IS NOT MARKED ADD THE JUNCTIONS
					if ((!road.isVisited) && !isAMarkedJunction(destNodeId, markedJunctions)){
						reacheablesJunctions.add(new Reachable(junctions[destNodeId], road));
					}
				}
			}
		
			//CHECK ON ALL THE REACHABLES THE ONE THE ONE WITH THE HIGHT RATIO DISTANCE TIME
			Reachable best = getBestJunction(reacheablesJunctions, availableTime);
			if (best == null)
				break;
			availableTime -= best.road.time;
			best.road.isVisited = true;
			moveCar(cars, best);
		}
		return (0);
	}
}
