package hashcode;

public class LargestSquare {

/**

1) Construct a sum matrix S[R][C] for the given M[R][C].

     a)	Copy first row and first columns as it is from M[][] to S[][]

     b)	For other entries, use following expressions to construct S[][]

         If M[i][j] is 1 then

            S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1

         Else If M[i][j] is 0

            S[i][j] = 0

2) Find the maximum entry in S[R][C]

3) Using the value and coordinates of maximum entry in S[i], print sub-matrix of M[][]

*/

	public static void main(String[] args) {

		int[][] M = {{0, 0, 1, 1},
					 {0, 1, 1, 1},
					 {0, 1, 1, 1},
					 {0, 1, 1, 1},
					 {1, 1, 1, 0},
					 {1, 1, 1, 0}};
		
		getMaxSquare(M).println();

		
		int[][] N = {{0, 0, 1, 1},
				 	 {0, 0, 0, 0},
				 	 {0, 0, 0, 0},
				 	 {0, 0, 0, 0},
				 	 {1, 1, 1, 0},
				 	 {1, 1, 1, 0}};
	
		getMaxSquare(N).println();
		
		int[][] P = {{0, 0, 1, 1},
			 	     {0, 0, 0, 0},
			 	     {0, 0, 0, 0},
			 	     {0, 0, 0, 0},
			 	     {1, 1, 1, 0},
			 	     {1, 0, 1, 0}};

		getMaxSquare(P).println();
	}

	public static Square getMaxSquare(int[][] M) {

		int numRows = M.length;

		int numColumns = M[0].length;

		int[][] S = new int[numRows][numColumns];

		/* Set first column of S[][]*/

		for(int i = 0; i < numRows; i++){
			
			S[i][0] = M[i][0];

		}

		/* Set first row of S[][]*/    
		
		for(int j = 0; j < numColumns; j++){

			S[0][j] = M[0][j];

		}

		/* Construct other entries of S[][]*/

		for(int i = 1; i < numRows; i++){

			for(int j = 1; j < numColumns; j++){

				if (M[i][j] == 1) { 

					S[i][j] = Math.min(S[i][j-1], Math.min(S[i-1][j], S[i-1][j-1])) + 1;
		        
				} else {

					S[i][j] = 0;
				}
			}    
		}

		int maxSquareSize = 0;

		Square maxSquare = null;

		for(int i = 0; i < numRows; i++){

			for(int j = 0; j < numColumns; j++){

				int squareSize = S[i][j];
			    	
				if (squareSize > maxSquareSize) {

					if (squareSize % 2 == 1) {

						maxSquare = new Square(i, j, squareSize);

						maxSquareSize = squareSize;

					} else {

						maxSquare = new Square(i, j, squareSize - 1);
							
						maxSquareSize = squareSize - 1;
					}
				}
			}    
		} 

		return maxSquare;
	}

	public static class Square {

		int centerRow, centerColumn;
		
		int startRow, startColumn;

		int endRow, endColumn;

		int size;
		
		int paintSize;

		public Square(int endRow, int endColumn, int size) {

			super();

			this.size = size;

			this.centerRow = endRow - size + 1 + size / 2;

			this.centerColumn = endColumn - size + 1 + size / 2;

			this.startRow = endRow - size + 1;
			
			this.startColumn = endColumn - size + 1;
			
			this.endRow = endRow;

			this.endColumn = endColumn;
			
			this.paintSize = (size - 1) / 2;
		}
		
		public void println() {
			System.out.println("Largest square: ");
			System.out.println("  size: " + size);
			System.out.println("  paintSize: " + paintSize);
			System.out.println("  centerRow: " + centerRow);
			System.out.println("  centerColumn: " + centerColumn);
			System.out.println("  startRow: " + startRow);
			System.out.println("  startColumn: " + startColumn);
			System.out.println("  endRow: " + endRow);
			System.out.println("  endColumn: " + endColumn);
		}
	}
}