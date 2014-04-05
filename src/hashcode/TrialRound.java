package hashcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TrialRound {

	public static void trialV1() {
		
		try {
			FileReader fr = new FileReader(new File("doodle.txt"));
			
			FileWriter fw = new FileWriter(new File ("out.txt"));
						
			BufferedReader br = new BufferedReader(fr);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = "";
			
			try {
				line = br.readLine();
				
				String rows = line.substring(0, line.indexOf(" "));
				String columns = line.substring(line.indexOf(" ") + 1);
				
				System.out.println(rows + "$" + columns + "$");
				
				int rowNum = Integer.valueOf(rows);
				
				int colNum = Integer.valueOf(columns);
						
				System.out.println(rowNum + "$" + colNum + "$");
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			long numComs = 0;
			long rowNum = 0;
			
			while(line != null) {
				
				try {
					
					line = br.readLine();
					
					if (line == null) {
						break;
					}
					
					for (int i = 0; i < line.length(); i++) {
						
						if (line.charAt(i) == '#') {
							
							bw.write("PAINTSQ " + rowNum + " " + i + " 0");
							bw.write("\n");
							
							numComs++;
							
						}
					}
					
					rowNum++;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			bw.close();
			
			FileReader fr1 = new FileReader(new File(
									"C:/github/GoogleHashCode/doodle.txt"));
			
			FileWriter fw1 = new FileWriter(new File ("C:/github/GoogleHashCode/out1.txt"));
						
			BufferedReader br1 = new BufferedReader(fr1);
			
			BufferedWriter bw1 = new BufferedWriter(fw1);
			
			String line1 = "";
			
			try {
				line1 = br1.readLine();
				
				String rows1 = line1.substring(0, line1.indexOf(" "));
				String columns1 = line1.substring(line1.indexOf(" ") + 1);
				
				System.out.println(rows1 + "$" + columns1 + "$");
				
				int rowNum1 = Integer.valueOf(rows1);
				
				int colNum1 = Integer.valueOf(columns1);
						
				System.out.println(rowNum1 + "$" + colNum1 + "$");
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			long rowNum1 = 0;
			
			bw1.write(numComs + "\n");
			
			while(line1 != null) {
				
				try {
					
					line1 = br1.readLine();
					
					if (line1 == null) {
						break;
					}
					
					for (int i = 0; i < line1.length(); i++) {
						
						if (line1.charAt(i) == '#') {
							
							bw1.write("PAINTSQ " + rowNum1 + " " + i + " 0");
							bw1.write("\n");
														
						}
					}
					
					rowNum1++;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				br1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			bw1.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void eraseMatrix(int[][] matrix, LargestSquare.Square sq) {
		
		for(int i = sq.endRow; i < sq.endRow - sq.size; i-- ) {
			
			for(int j = sq.endColumn; j < sq.endColumn - sq.size; j-- ) {
				
				matrix[i][j] = 0;				
			}
		}
	}

	public static void trialV2() {
		
		try {
			FileReader fr = new FileReader(new File("doodle.txt"));
			
			FileWriter fw = new FileWriter(new File ("out4.txt"));
						
			BufferedReader br = new BufferedReader(fr);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			String line = "";

			int rowNum = 0;
			
			int colNum = 0;

			try {
				line = br.readLine();
				
				String rows = line.substring(0, line.indexOf(" "));
				String columns = line.substring(line.indexOf(" ") + 1);
				
				rowNum = Integer.valueOf(rows);
				
				colNum = Integer.valueOf(columns);
						
				System.out.println("Rows: " + rowNum + " Columns: " + colNum);

			} catch (IOException e) {
				e.printStackTrace();
			}

			int[][] matrix = new int[rowNum][colNum];
			
			rowNum = 0;
			
			line = br.readLine();
			
			while(line != null) {
				
				try {
					
					for (int i = 0; i < line.length(); i++) {
						
						if (line.charAt(i) == '#') {
																					
							matrix[rowNum][i] = 1;
							
						} else {
							
							matrix[rowNum][i] = 0;
						}
					}
					
					rowNum++;

					line = br.readLine();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			StringBuilder sb = new StringBuilder();
			
			int numInst = 0;
			
			LargestSquare.Square sq = LargestSquare.getMaxSquare(matrix);
			
			while (sq != null && sq.size > 1) {
				
				numInst++;
				
				//sq.println();
				
				String command = "PAINTSQ " + sq.centerRow + " " + sq.centerColumn + " " + sq.paintSize + "\n"; 

				System.out.print("Command " + numInst + ": " + command);

				sb.append(command);
				
				for(int i = sq.startRow; i <= sq.endRow; i++ ) {

					for(int j = sq.startColumn; j <= sq.endColumn; j++) {
						
						matrix[i][j] = 0;				
					}
				}				

				sq = LargestSquare.getMaxSquare(matrix);
			}
			
			int numRows = matrix.length;
			int numCols = matrix[0].length;
			
			for (int i = 0; i < numRows; i++) {
				
				for (int j = 0; j < numCols; j++) {
					
					if (matrix[i][j] != 0) {
						
						numInst++;

						String command = "PAINTSQ " + i + " " + j + " 0\n"; 

						System.out.print("Command " + numInst + ": " + command);

						sb.append(command);
					}
				}			
			}

			bw.write(numInst + "\n");
			
			bw.write(sb.toString());
			
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
