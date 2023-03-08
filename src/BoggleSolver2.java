import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver2
{
	String[] dictionary;
	
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver2(String dictionaryName) throws FileNotFoundException
	{
		String[] tempDictionary = new String[1000000];
		File file = new File(dictionaryName);
		Scanner input = new Scanner(file);
		int counter = 0;
		while(input.hasNext())
		{
			tempDictionary[counter] = input.next();
			counter++;
		}
		dictionary = new String[counter];
		for(int i = 0; i < counter; i++)
		{
			dictionary[i] = tempDictionary[i];
		}
	}
	
	public boolean isInDictionary(String str)
	{
		for(int i = 0; i < dictionary.length; i++)
		{
			if(dictionary[i].equals(str))
			{
				return true;
			}
		}
		return false;
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	@SuppressWarnings("null")
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		HashSet<String>str = null;
		boolean [][] visited = new boolean[board.rows()][board.cols()];
		for(int i = 0; i < board.rows(); i++)
		{
			for(int x = 0; x < board.cols(); x++)
			{
				str.add(validWordsHelper(i,x,"", board, visited));
			}
		}
		return str;
	}
	
	private String validWordsHelper(int row, int col, String soFar , BoggleBoard board, boolean hasVisited[][])
	{
		System.out.println(soFar);
		System.out.println("HERE");
		hasVisited[row][col] = true;
		if(isInDictionary(soFar)==true)
		{
			return soFar;
		}
		else
		{
			if(soFar.length()>= 1)
			{
				soFar = "" + soFar.charAt(soFar.length()-1);
				hasVisited[row][col] = false;
			}
		}

			if(row != 0 && row!= board.rows()-1 && col!=0 && col!=board.cols()-1)
	    	{
	        	//top mid
				soFar +=board.getLetter(row-1, col);
	        	 soFar = validWordsHelper(row-1,col, soFar,board, hasVisited);
	        	
	        	//top left
				soFar +=board.getLetter(row-1, col-1);
				soFar =  validWordsHelper(row-1,col-1, soFar,board, hasVisited);
	        	
	        	//top right
				soFar +=board.getLetter(row-1, col+1);
				soFar =  validWordsHelper(row-1,col+1, soFar,board, hasVisited);
	     
	        	//left
	        	soFar +=board.getLetter(row, col-1);
	        	soFar =  validWordsHelper(row,col-1, soFar,board, hasVisited);
	        	
	        	//right
	        	soFar +=board.getLetter(row, col+1);
	        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
	        	
	        	//bottom mid
	        	soFar +=board.getLetter(row+1, col);
	        	soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
	        	
	        	//bottom left
				soFar +=board.getLetter(row+1, col-1);
				soFar =  validWordsHelper(row+1,col-1, soFar,board, hasVisited);
	        	
	        	//bottom right
				soFar +=board.getLetter(row+1, col+1);
				soFar =  validWordsHelper(row+1,col+1, soFar,board, hasVisited);
	        	
	    	}
			else if(row==0)
	    	{
	    		if(col!=0 && col!= board.cols())
	    		{
	    			//left
	    			soFar +=board.getLetter(row, col-1);
	    			soFar =  validWordsHelper(row,col-1, soFar,board, hasVisited);
	    			
	            	//bottom mid
		        	soFar +=board.getLetter(row+1, col);
		        	soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
	            	
	            	//right
		        	soFar +=board.getLetter(row, col+1);
		        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
		        	
	    			
	    		}
	    		else if(col==0)
	    		{
	    			
	            	//bottom mid
	    			soFar +=board.getLetter(row+1, col);
	    			soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
		        	
	            	//right
		        	soFar +=board.getLetter(row, col+1);
		        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
	    		}
	    		else if(col==board.cols()-1)
	    		{
	    			//bottom mid
		        	soFar +=board.getLetter(row+1, col);
		        	soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
		        	
		        	//bottom left
					soFar +=board.getLetter(row+1, col-1);
					soFar =  validWordsHelper(row+1,col-1, soFar,board, hasVisited);
	    		}
	    	}
	    	else if(row==board.rows()-1)
	    	{
	    		
	    		if(col!=0 && col!= board.cols()-1)
	    		{
	    			
	    			//top mid
					soFar +=board.getLetter(row-1, col);
					soFar =  validWordsHelper(row-1,col, soFar,board, hasVisited);
	            	
	            	//left
		        	soFar +=board.getLetter(row, col-1);
		        	soFar =  validWordsHelper(row,col-1, soFar,board, hasVisited);
		        	
		        	//right
		        	soFar +=board.getLetter(row, col+1);
		        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
	    		}
	    		else if (col==0)
	    		{
	    			//top mid
					soFar +=board.getLetter(row-1, col);
					soFar =  validWordsHelper(row-1,col, soFar,board, hasVisited);
	            	
		        	
		        	//right
		        	soFar +=board.getLetter(row, col+1);
		        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
	    		}
	    		else if(col==board.cols()-1)
	    		{
	    			//top mid
					soFar +=board.getLetter(row-1, col);
					soFar =  validWordsHelper(row-1,col, soFar,board, hasVisited);
	            	
	            	//left
		        	soFar +=board.getLetter(row, col-1);
		        	soFar =  validWordsHelper(row,col-1, soFar,board, hasVisited);
	    		}
	    	}
	    	else if(col==0)
	    	{
	    		if(row!=0 && row!= board.rows()-1)
	    		{
	    			//top mid
					soFar +=board.getLetter(row-1, col);
					soFar =  validWordsHelper(row-1,col, soFar,board, hasVisited);
	            	
		        	
		        	//right
		        	soFar +=board.getLetter(row, col+1);
		        	soFar =  validWordsHelper(row,col+1, soFar,board, hasVisited);
	    		
		        	//bottom mid
		        	soFar +=board.getLetter(row+1, col);
		        	soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
	            	
	    		}
	    	}
	    	else if (col==board.cols()-1)
	    	{
	    		if(row!=0 && row!= board.rows()-1)
	    		{
	    			
	    			//top mid
					soFar +=board.getLetter(row-1, col);
					soFar =  validWordsHelper(row-1,col, soFar,board, hasVisited);
	            	
		        	
		        	//left
		        	soFar +=board.getLetter(row, col-1);
		        	soFar =  validWordsHelper(row,col-1, soFar,board, hasVisited);
	    		
		        	//bottom mid
		        	soFar +=board.getLetter(row+1, col);
		        	soFar =  validWordsHelper(row+1,col, soFar,board, hasVisited);
	    		}
	    	}
			//soFar = "" + soFar.charAt(soFar.length()-1);
		return null;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		int score = 0;
		for(int i = 0; i < dictionary.length; i++)
		{
			if(word.equals(dictionary[i]))
			{
				if(word.length() <= 2)
				{
					score = 0;
				}
				else if (word.length()==3 || word.length()==4)
				{
					score = 1;
				}
				else if (word.length()==5)
				{
					score = 2;
				}
				else if (word.length()==6)
				{
					score = 3;
				}
				else if (word.length()==7)
				{
					score = 5;
				}
				else if (word.length()>=8)
				{
					score = 11;
				}
				if(word.contains("qu"))
				{
					score++;
				}
			}
		}
		return score;
	}

	public static void main(String[] args) throws FileNotFoundException {

		final String PATH   = "";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver2 solver = new BoggleSolver2(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
