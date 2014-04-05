package chereau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import saita.Node;
import saita.Route;

public class Chereau {

	public static void main(String[] args){
		//parsing du fichier et création du graph
		FileReader fr;
		try {
			fr = new FileReader(new File("paris_54000.txt"));

			BufferedReader br = new BufferedReader(fr);

			String line = "";

			line = br.readLine();

			int nbNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			line=line.substring(line.indexOf(" ")+1);
			line=line.substring(line.indexOf(" ")+1);
			int time = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			line=line.substring(line.indexOf(" ")+1);
			int nbVehicule = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			line=line.substring(line.indexOf(" ")+1);
			int initialNode = Integer.parseInt(line);

			int test= extractTime();
				test = extractInitialNode();
				test = extractNbVoiture();
			
			System.out.println(nbNode + "$" + time + "$"+nbVehicule+"$"+initialNode);
			
			line = br.readLine();
			
			while(line.contains(".")){
				line=br.readLine();
			}

			Node[] graph = new Node[nbNode];
			for(int i=0; i<graph.length; i++){
				graph[i]=new Node(i);
			}
			
			while(line!=null){
				int firstNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int secondNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int direction = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int roadTime = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int length = Integer.parseInt(line);
				if(direction!=1){
					graph[secondNode].addRoute(new Route(firstNode, secondNode, roadTime, length, direction!=1));
				}
				graph[firstNode].addRoute(new Route(firstNode, secondNode, roadTime, length, direction!=1));
				line=br.readLine();
			}
			
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static Node[] generateGraph() throws IOException{
		//parsing du fichier et création du graph
		FileReader fr;
		try {
			fr = new FileReader(new File("paris_54000.txt"));

			BufferedReader br = new BufferedReader(fr);

			String line = "";

			line = br.readLine();

			int nbNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			
			line = br.readLine();
			
			while(line.contains(".")){
				line=br.readLine();
			}

			Node[] graph = new Node[nbNode];
			for(int i=0; i<graph.length; i++){
				graph[i]=new Node(i);
			}
			
			while(line!=null){
				int firstNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int secondNode = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int direction = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int roadTime = Integer.parseInt(line.substring(0, line.indexOf(" ")));
				line=line.substring(line.indexOf(" ")+1);
				int length = Integer.parseInt(line);
				if(direction!=1){
					graph[secondNode].addRoute(new Route(firstNode, secondNode, roadTime, length, direction!=1));
				}
				graph[firstNode].addRoute(new Route(firstNode, secondNode, roadTime, length, direction!=1));
				line=br.readLine();
			}
			
			return graph;
		} catch (IOException e) {
			throw e;
		}
	}
	
	public static int extractTime() throws IOException{
		FileReader fr = new FileReader(new File("paris_54000.txt"));

		BufferedReader br = new BufferedReader(fr);

		String line = "";

		line = br.readLine();

		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		return Integer.parseInt(line.substring(0, line.indexOf(" ")));
	}
	
	public static int extractNbVoiture() throws IOException{
		FileReader fr = new FileReader(new File("paris_54000.txt"));

		BufferedReader br = new BufferedReader(fr);

		String line = "";

		line = br.readLine();

		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		return Integer.parseInt(line.substring(0, line.indexOf(" ")));
	}

	public static int extractInitialNode() throws IOException{
		FileReader fr = new FileReader(new File("paris_54000.txt"));

		BufferedReader br = new BufferedReader(fr);

		String line = "";

		line = br.readLine();

		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		line=line.substring(line.indexOf(" ")+1);
		return Integer.parseInt(line);
	}

}
