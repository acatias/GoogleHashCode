package tonye;

import java.util.ArrayList;

public class Car {

	public int carIndex;
	public int currentNodeIndex;
	public ArrayList<Integer> visitedNodes;
	
	public Car(int carIndex){
		this.carIndex = carIndex;
	}
	
	public void addNode(int nodeIndex){
		this.visitedNodes.add(nodeIndex);
		this.currentNodeIndex = nodeIndex;
	}
}
