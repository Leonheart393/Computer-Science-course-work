import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//interface for implemented by the thePosition class
interface Position
{
    int getRow( );
    int getCol( );
    List<Position> getAdjacent();
    String getValue();
    int length();
}
//class BoggleMap generates a puzzle for the game Boggle
class BoggleMap
{
    //thePosition class to be used by BoggleMap to keep track of position 
    //while traversing puzzle
    private class thePosition implements Position{
        //instance variable
        private int row;
        private int col;
        //thePosition constructor
        thePosition(int r, int c)
        {
            row = r;
            col = c;
        }
        //toString method to print a thePosition object
        public String toString()
        {
            return "(" + row + "," + col + ")";
        }
        //equals method for comparison
        public boolean equals(Object position)
        {
            if( ! ( position instanceof thePosition ) )
                return false;

            thePosition rhs = (thePosition) position;

            return row == rhs.row && col == rhs.col;
        }
        //return the adjacent location of a current position
        public List<Position> getAdjacent( )
        {
            int lowRow = ( row == 0 ) ? 0 : ( row - 1 );
            int lowCol = ( col == 0 ) ? 0 : ( col - 1 );
            int highRow = ( row == numRows - 1 ) ? row : ( row + 1 );
            int highCol = ( col == numCols - 1 ) ? col : ( col + 1 );
            
            List<Position> result = new ArrayList<>( );
            
            for( int r = lowRow; r <= highRow; ++r )
                for( int c = lowCol; c <= highCol; ++c )
                    if( r != row || c != col )
                        result.add( new thePosition( r, c ) );
            
            length = result.size();          
            return result;
        }
        //returns the value of a certain position
        public String getValue()
        {
            return grid[row][col];
        }
        // returns the amount of rows in the puzzle
        public int getRow()
        {
            return row;
        }
        //returns the amount of columns in the puzzle
        public int getCol()
        {
            return col;
        }    
        //returns the length of the puzzle
        public int length()
        {
            return length;
        }
    }
    //instance variables
    private String grid [] [];
    private int numRows;
    private int numCols;
    private String dict[];
    private int length;
    //constructor for BoggleMap class
    public BoggleMap(String str) 
    {
        try
        {           
            int count = 0;
            buildDict();
            int k = 0;       
            FileReader file = new FileReader(str);
            ArrayList<String> arr = new ArrayList<>();

            Scanner scan = new Scanner(file);
            arr.add(scan.nextLine());
            int length = arr.get(0).length();
            while(scan.hasNext())
            {
                if(arr.get(count).length() == length)
                {
                    arr.add(scan.nextLine()); 
                }
                else
                {
                    System.out.println("Not a rectangle");
                    System.exit(0);
                }
                count++;

            }
            for(int n = 0; n < arr.size(); n++)
            {
                numRows++;
            }

            int columns = arr.get(0).length();
            for(int v = 0; v < columns; v++)
            {
                numCols++;
            }

            grid = new String [numRows][numCols];
            StringBuilder sb = new StringBuilder();
            for(int d = 0; d < arr.size();d++)
            {
               sb.append(arr.get(d));
            }

            String temp = sb.toString();

            for(int i = 0; i < numRows; i++)
            {            
                for(int j = 0; j < numCols ; j++)
                {
                    grid[i][j] = "" + temp.charAt(k);
                    k++;
                }
            }               
        }
        catch(IOException e)
        {
            System.out.println("File was not found");
        }     
    }
        /**
     * Driver routine to solve the Boggle game.
     * @return a Map containing the strings as keys, and the positions used
     *     to form the string (as a List) as values
     */
    public Map<String,List<Position>> solve( )
    {
        int[] scoreBoard = {0, 0, 0, 1, 2, 3, 4, 6, 10, 
                                    15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15};
        int score = 0;
        int totalScore = 0;
        int words = 0;
        int seven = 0, six = 0, five = 0, four = 0, three = 0;
        Map<String,List<Position>> results = new TreeMap<>( );
        List<Position> path = new ArrayList<>( );
        
        for( int r = 0; r < numRows; r++ )
            for( int c = 0; c < numCols; c++ )
                solve( new thePosition( r, c ), "", path, results );
        
        for(String count : results.keySet())
        {
            words++;
        }
        
        if(words > 200) 
        {      
            for(String key : results.keySet())
            {
                for(int i = 0; i < scoreBoard.length; i++)
                {
                    if(key.length() == i)
                    {
                        score = scoreBoard[i];
                        totalScore = totalScore + score;
                    }
                
                }      
                if(key.length() > 7)
                {
                    System.out.println(score + " points " + key 
                                                    + ": " + results.get(key));
                }
                if(key.length() > 6 && key.length() < 8)
                {
                    seven++; 
                }
                else if(key.length() > 5 && key.length() < 7)
                {
                    six++;
                }
                else if(key.length() > 4 && key.length() < 6)
                {
                    five++;
                }
                else if(key.length() > 3 && key.length() < 5)
                {
                    four++;
                }
                else if(key.length() > 2 && key.length() < 4)
                {
                    three++;
                }
            }
            System.out.println(seven + " seven letter words worth " + seven*6 
                                                                  + " points" );
            System.out.println(six + " six letter words worth " + six*4
                                                                  + " points" );
            System.out.println(five + " five letter words worth " + five*3
                                                                  + " points" );
            System.out.println(four + " four letter words worth " + four*2 
                                                                  + " points" );
            System.out.println(three + " seven letter words worth " + three
                                                                  + " points" );
        }
        else
        {
            for(String key : results.keySet())
            {
                for(int i = 0; i < scoreBoard.length; i++)
                {
                    if(key.length() == i)
                    {
                        score = scoreBoard[i];
                        totalScore = totalScore + score;
                    }
                }    
                if(key.length() > 2)
                {
                    System.out.println(score + " points " + key + ": " 
                                                          +  results.get(key));   
                }
            }     
        }
        
        System.out.println("total score: " + totalScore);
        System.out.println(words + " words found");
       
        return results;
    }     
        /**
     * Hidden recursive routine.
     * @param thisPos the current position
     * @param charSequence the characters in the potential matching string thusfar
     * @param path the List of positions used to form the potential matching string thusfar
     * @param results the Map that contains the strings that have been found as keys
     *       and the positions used to form the string (as a List) as values.
     */
    private void solve(Position thisPos, String charSequence,
                        List<Position> path, Map<String,List<Position>> results)
    {       
         charSequence += thisPos.getValue();
         int num = Arrays.binarySearch(dict, charSequence);
         if (path.contains(thisPos)) 
         {
           return;
         }
         if (num >= 0 || dict[Math.abs(num) - 1].startsWith(charSequence)) 
         {
             path.add(thisPos);

             if (num > -1 && charSequence.length() > 2) 
             {
                results.put(charSequence, new ArrayList(path));
             }

             for (Position position : thisPos.getAdjacent()) 
             {
                solve(position, charSequence, path, results);
             }
             path.remove(thisPos);
         } 
    }
    
    //generates the dictionary that will be used for word comparisons
    private void buildDict() 
    {
        try
        {
            int i = 0;
            FileReader file = new FileReader("dict.txt");
            Scanner scan = new Scanner(file);     
            BufferedReader br = new BufferedReader(file);
            dict = new String[88984];

            while(scan.hasNext())
            {
                dict[i] = scan.nextLine();
                i++;
            } 

            if(!isSorted())
            {
                Arrays.sort(dict);
            }
        }
        catch(IOException e)
        {
            System.out.println("File was not found");
        }
        
        
    }
    //check if the dictionary is sorted
    public boolean isSorted()
    {
        for(int j = 0; j < dict.length; j++)
        {
            if(dict[j].compareTo(dict[j+1]) > 0)
            {
                return false;
            }
        }
        return true;
    }
    //toString method for boggleMap to show the puzzle being used  
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( );
        
        for( int i = 0; i < numRows; ++i )
        {
            for( int j = 0; j < numCols; ++j )
                sb.append( grid[ i ][ j ]);
            sb.append( '\n' );
        } 
        
        return new String( sb );
    }   
}
//class Boggle creates a boggles puzzle and solves it
public class Boggle
{
    //main for generating a Boggle Puzzle
    public static void main( String [ ] args )
    {
        BoggleMap bm = new BoggleMap("puz1.txt");
        System.out.println("Puzzle:");
        System.out.println(bm.toString());
        System.out.println("Solution:");
        bm.solve();
        System.out.println("");
        BoggleMap bm2 = new BoggleMap("puz2.txt");
        System.out.println("Puzzle:");
        System.out.println(bm2.toString());
        System.out.println("Solution:");
        bm2.solve();
    } 
}
