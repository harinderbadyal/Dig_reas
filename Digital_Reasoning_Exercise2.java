import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


/*---Modify your program from #1 to add rudimentary recognition of proper nouns (“named
entities”) in the input, and print a list of recognized named entities when it runs. The list
of named entities is in the file “ NER.txt ”. Enhance your data structures and output
schema to store information about which portions of the text represent named entities. --- */
 

public class Digital_Reasoning_Exercise2{
        public static void main(String[] args) {
           try
                {
                        //txt file containing data
                        String strFile = "nlp_data.txt"; //original file
                        String nounFile = "NER.txt"; // file with noun
                        
                        //create BufferedReader to read csv file
                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        BufferedReader nbr = new BufferedReader( new FileReader(nounFile));
                        String strLine = "";
                        String nLine = "";
                        StringTokenizer st = null;
                        List<String> al1 = new ArrayList<String>();
                        Vector<Object> vector1 = new Vector<Object>();
                        Vector<Object> vector2 = new Vector<Object>();
                        String col1 = "";
                        String xml_out = "";
                        String vcol1 = "";
                        String vcol2 = "";
                        int lineNumber = 0;
                        int tokenNumber = 0;
                        
                        System.out.println("Working Directory = " +System.getProperty("user.dir"));
                        
                        //read comma separated file line by line
                        while( (strLine = br.readLine()) != null)
                        {
                                lineNumber++;
                                //break comma separated line using ","
                                al1.add(strLine);
                                st = new StringTokenizer(strLine, " ");
                                while(st.hasMoreTokens())
                                {
                                	col1 = st.nextToken();
                                	vector1.add(col1);
                                    //display csv values
                                    tokenNumber++;
                                  }
                                //reset token number
                                tokenNumber = 0;
                        }
                        
                        //-----Storing Noun file in vector
                        while( (nLine = nbr.readLine()) != null)
                        {
                        	vector2.add(nLine);
                        }
                        
                        
                        System.out.println("--------------Print Matching words from Noun File-------------");
                        for(int i=0;i < vector1.size();i++){
                        	vcol1 = vector1.elementAt(i).toString();
                        	for(int j=0;j < vector2.size();j++){	
                        		vcol2 = vector2.elementAt(j).toString();
                        		if (vcol1.equalsIgnoreCase(vcol2))
                        			{
                        			System.out.println(strFile +" file Vector #"+i+":"+vcol1 +"  MATCHES WITH   "+nounFile +" File Line #"+j+":"+vcol2);	
                        			}
                       			}
                     	 }
                        
                        
                }
                catch(Exception e)
                {
                        System.out.println("Exception while reading file: " + e);                   
                }
        }
}
