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
		boolean res = true;
		for (int j = 0; (res) && (j < markedJunctions.size()); j++){
			for (Route road : markedJunctions.get(j).routes){
				if (isAMarkedJunction(getDestinationNode(markedJunctions.get(j).index, road), markedJunctions))
					res = false;
			}
		}
		return (res);
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
			cars[i] = new Car(i);
			cars[i].addNode(nodeIndex);
		}
		return cars;
	}
	
	/**
	 * @return true if there is a car on the specified node
	 * **/
	public static boolean hasACarAvailable(int nodeIndex, Car[] cars){
		boolean found = false;
		for (int i = 0; (!found) && (i < cars.length); i++){
			if (cars[i].currentNodeIndex == nodeIndex){
				found = true;
			}
		}
		return (found);
	}

	/**
	 * @return Reachable the chosen junction given the available junctions and the time
	 * **/
	public static Reachable getBestJunction(ArrayList<Reachable> reacheablesJunctions, int timelimit){
		Reachable best = null;

		for (Reachable reachable : reacheablesJunctions){

			if (reachable.road.time <= timelimit){
				if (best == null){
					best = reachable;
				} else if ((reachable.road.dist / reachable.road.time) < (best.road.dist / best.road.time)){
					best = reachable;
				}
			}
		}
		return (best);
	}

	/**
	 * this method will pick a car and moves it to a node
	 * **/
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
	public static int computeMyPrimAlgorithm(Node[] junctions){
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
		while (hasUnmarkedNeighbour(junctions, markedJunctions)){

			//BUILD A LIST OF JUNCTIONS THAT CAN BE JOINED 
			ArrayList<Reachable> reacheablesJunctions = new ArrayList<Reachable>();

			for (int i = 0; (cars != null) && (i < cars.length); i++){
				for (int j = 0; (j < junctions[cars[i].currentNodeIndex].routes.size()); j++){
					Route road = markedJunctions.get(i).routes.get(j);
					int destNodeId = getDestinationNode(markedJunctions.get(i).index, road);

					//IF THE ROAD HAS NOT BEEN VISITED OR THE REACHABLE JUNCTION IS NOT MARKED ADD THE JUNCTIONS
					if ((!road.isVisited)){
						reacheablesJunctions.add(new Reachable(junctions[destNodeId], road));
					}
				}
			}
			
			//LOOP ON MARKED ARRAY TO GET ALL REACHABLE JUNCTIONS
			for (int i = 0; i < markedJunctions.size(); i++){
				if (!hasACarAvailable(markedJunctions.get(i).index, cars)){
					System.out.println("out for anavailable car");
					continue;
				}
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
		
			//CHECK ON ALL THE REACHABLES THE ONE THE ONE WITH THE HIGHT RATIO DISTANCE TIM
			System.out.println("reachable junction size " + reacheablesJunctions.size());
			Reachable best = getBestJunction(reacheablesJunctions, availableTime);
			if (best == null){
				System.out.println("out for no best junction found");
				break;
			}
			availableTime -= best.road.time;
			best.road.isVisited = true;
			moveCar(cars, best);
			markedJunctions.add(best.node);
		}
		for (int i = 0; (cars != null) && (i < cars.length); i++){
			System.out.println(cars[i].visitedNodes.size());
			for (int j = 0; j < cars[i].visitedNodes.size(); j++){
				System.out.println(cars[i].visitedNodes.get(j));
			}
		}
		return (0);
	}
}
