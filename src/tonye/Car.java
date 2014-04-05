package tonye;

import java.util.ArrayList;

public class Car {

	public int carIndex;
	public int currentNodeIndex;
	public int availableTime;
	public boolean canMove;
	public ArrayList<Integer> visitedNodes;
	
	public Car(int carIndex){
		this.carIndex = carIndex;
		this.visitedNodes = new ArrayList<Integer>();
		this.canMove= true; 
	}
	
	public void addNode(int nodeIndex){
		this.visitedNodes.add(nodeIndex);
		this.currentNodeIndex = nodeIndex;
	}
}
