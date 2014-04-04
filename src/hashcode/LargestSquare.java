package hashcode;


public class LargestSquare {

	

	

	/**

	 * 1) Construct a sum matrix S[R][C] for the given M[R][C].

     a)	Copy first row and first columns as it is from M[][] to S[][]

     b)	For other entries, use following expressions to construct S[][]

         If M[i][j] is 1 then

            S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1

         Else /*If M[i][j] is 0

            S[i][j] = 0

2) Find the maximum entry in S[R][C]

3) Using the value and coordinates of maximum entry in S[i], print 

   sub-matrix of M[][]

	 */




	public static void main(String[] args) {

		

		System.out.println("Hello, World!");

		

		System.out.println("Hello, World!");

	}

	

	public static Square getMaxSquare(int[][] M){

		int numLines = M.length;

		int numColumns = M[0].length;

		int[][] S = new int[numLines][numColumns];

		

		/* Set first column of S[][]*/

		  for(int i = 0; i < numLines; i++){

		     S[i][0] = M[i][0];

		  }

		  

		  /* Set first row of S[][]*/    

		  for(int j = 0; j < numColumns; j++){

		     S[0][j] = M[0][j];

		  }

		      

		  /* Construct other entries of S[][]*/

		  for(int i = 1; i < numLines; i++){

		    for(int j = 1; j < numColumns; j++){

		      if(M[i][j] == 1) 

		        S[i][j] = Math.min(S[i][j-1], Math.min(S[i-1][j], S[i-1][j-1])) + 1;

		      else

		        S[i][j] = 0;

		    }    

		  }

		  

		  int maxSquareSize = 0;

		  Square maxSquare = null;

		  for(int i = 0; i < numLines; i++){

			    for(int j = 0; j < numColumns; j++){

			    	int squareSize = S[i][j];


			    	
if(squareSize!=0){

			    		if(squareSize >maxSquareSize && (squareSize%2!=0)){

			    			maxSquare = new Square(i, j,squareSize);

			    			maxSquareSize = squareSize;

			    		}else{

			    			if(squareSize >maxSquareSize && (squareSize%2!=1)){

				    			maxSquare = new Square(i, j,squareSize-1);

				    			maxSquareSize = squareSize-1;


			    			}

			    		}

			    	}

			 }    

		  } 

		return maxSquare;

	}

	

	

	public static class Square{

		int middleLine, middleColumn;

		int startLine,startColumn;





		int size;

		public Square(int startLine, int startColumn, int size) {

			super();

			this.size = size;

			this.middleLine = startLine - size +1 +size/2;

			this.middleColumn = startColumn  - size +1 +size/2;


			this.startLine = startLine;

			this.startColumn = startColumn;

		}

	}




}