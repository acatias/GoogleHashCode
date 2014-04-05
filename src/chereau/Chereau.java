package chereau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class chereau {

	public static void main(String[] args){
		//parsing du fichier et création du graph
		FileReader fr;
		try {
			fr = new FileReader(new File("paris_54000.txt"));

			FileWriter fw = new FileWriter(new File ("out.txt"));

			BufferedReader br = new BufferedReader(fr);

			BufferedWriter bw = new BufferedWriter(fw);

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

			System.out.println(nbNode + "$" + time + "$"+nbVehicule+"$"+initialNode);
			
			line = br.readLine();
			
			while(line.contains(".")){
				line=br.readLine();
			}

			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
