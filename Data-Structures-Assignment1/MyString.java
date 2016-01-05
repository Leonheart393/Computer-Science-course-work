import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//Class MyString to be used as a substitue for String class in this program
class MyString implements Comparable<MyString>{
        //Instance variables
        final private String original;
        final private int startIdx;
        final private int len;
        //MyString constructor
        public MyString( String str, int start )
        {
            original = str;
            startIdx = start;
            len = str.length() - start;
        }    
        @Override
        //Compares two MyString objects and returns the difference
        public int compareTo(MyString rhs)
        {
            int li = startIdx;
            int ri = rhs.startIdx;
       
            int le = startIdx + len;
            int re = rhs.startIdx + rhs.len;
       
            String lo = original;
            String ro = rhs.original;
       
            while(li < le && ri < re && lo.charAt(li) == ro.charAt(ri))
            {
                li++; ri++;
            }
       
            if (li == le)
            {
              if (ri == re)
                return 0;
              else 
                return -1;
            }
            if(ri == re)
               return 1;
       
            return ro.charAt(ri) - lo.charAt(li);
        }
        //CharAt method replacement for MyString object
        public char charAt( int idx )
        {  
            if(idx < 0 || idx >= length())
                throw new UnsupportedOperationException( );
            
            return original.charAt(startIdx + idx);     
        }
        // Length method replacement for MyString object
        public int length()
        {            
            return len;
        }
        //Substring method replacement for MyString object
        public String substring(int si, int ei)
        {
            return original.substring(this.startIdx + si, this.startIdx + ei);   
        }
        //toString method replacement for MyString object
        @Override
        public String toString( )
        {
            return original.substring(startIdx, startIdx + len);
        }
        //Supplemenental MyString constructor could be used in this program
        private MyString(String source, int start, int newLen)
        {
            original = source;
            startIdx = start;
            len = newLen;
        }
    }
//Class calculates the longest common substring between two text files
public class LongestCommonSubstring{
    
    //Returns the longest prefix between two MyString objects
    public static int longestPrefix(MyString lhs, MyString rhs, int mid)
    {
      int len = 0;
      
      if((lhs.length() < mid) && (rhs.length() < mid))
      {
         return 0;
      }
      else if((lhs.length() < mid) || (rhs.length() < mid))
      {
         while(len < lhs.length() && len < rhs.length() 
                                  && lhs.charAt(len) == rhs.charAt(len)) 
            ++len;         
            return len;       
      }
      else
      {
         return len;
      }
    }
    //Method for reading from a file and storing the content in a String
    public static String readFile(FileReader file) throws IOException
    {
        BufferedReader reader = new BufferedReader(file);
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        
        while( ( line = reader.readLine() ) != null ) 
        {
            stringBuilder.append( line );
            stringBuilder.append( " " );
        }
        return stringBuilder.toString();
    }
    //Main method
    public static void main(String [] args)
    {
        /*Handles cases in which there are more or less input from the command
          line than is needed
        */
        if(args.length > 2)
        {
            System.out.println("Please choose only 2 files to compare.");
            System.exit(0);
        }
        if(args.length == 0)
        {
            System.out.println("No files chosen.");
            System.exit(0);
        }
        try{
            FileReader file1 = new FileReader(args[0]);
            FileReader file2 = new FileReader(args[1]);
            String fileRead1 = readFile(file1);
            String fileRead2 = readFile(file2);
            String str1 = fileRead1.replaceAll(" +", " ");
            str1 = str1.trim();
            String str2 = fileRead2.replaceAll(" +", " ");
            str2 = str2.trim();
            String str3 = str1 + "#" + str2;
            
              int N = str2.length() + 1;
              
              MyString[] suffixes = new MyString[str3.length()];
              int[] LCP = new int[str3.length()];
      
              for(int i = 0; i < str3.length(); i++)
              {
                suffixes[i] = new MyString(str3 , i);
              }
 
              System.out.println("Starting sort");
              Arrays.sort(suffixes);
              System.out.println("Sort Complete");
      
              for(int i = 1; i < str3.length(); i++)
                 LCP[i] = longestPrefix(suffixes[i], suffixes[i-1], N);
      
              int maxLCPIndex = 0;
      
              for(int i = 1; i < LCP.length; i++)
              if(LCP[i] > LCP[maxLCPIndex])
                maxLCPIndex = i;
      
              if(LCP[maxLCPIndex] >= 30)
              {
                    System.out.println("Longest Common Substring = " 
                            + LCP[maxLCPIndex] + " characters " 
                            + "\n" + "Substring is: " 
                            + suffixes[maxLCPIndex].substring(0, 30) + "..." 
                            + "\n" + "maxLCPIndex = " + maxLCPIndex);
              }
              else
              {
                   System.out.println("Longest Common Substring = " 
                                       + LCP[maxLCPIndex] + " characters " + "\n"
                                       + "Substring is: "+ 
                           suffixes[maxLCPIndex].substring(0, LCP[maxLCPIndex])
                           + "\n" + "maxLCPIndex = " + maxLCPIndex);
              }
            }
            catch(IOException e)
            {
                System.out.println("File was not found");
            }
    } 
} 


