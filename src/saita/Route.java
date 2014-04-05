package saita;

public class Route {

	int start; 
	int end; 
	int time;
	int dist;
	boolean isDeux;
	
	public Route(int start, int end, int time, int dist, boolean isDeux) {
		this.start = start;
		this.end = end;
		this.time = time;
		this.dist = dist;
		this.isDeux = isDeux;
	}
	
	public void print() {
		System.out.println("Route: ");
		System.out.println("  start: " + start);
		System.out.println("  end: " + end);
		System.out.println("  time: " + time);
		System.out.println("  dist: " + dist);
		System.out.println("  isDeux: " + isDeux);
	}
	
	public static void main(String[] test) {
		
		new Route(0, 1, 10, 5, true).print();
	}

}