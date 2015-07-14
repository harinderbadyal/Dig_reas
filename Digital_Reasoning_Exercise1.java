import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/*---Write a program that identifies sentence boundaries and tokenizes the text in the file
“ nlp_data.txt ” into words. It should correctly process all symbols, including punctuation
and whitespace. Every word must fall into a sentence. Create data structures that
efficiently express the data you have processed. When your program runs it should
output an XML representation of your Java object model. --- */
 

public class Digital_Reasoning_Exercise1{
	
	private static final String FILE_NAME_XML = "DigitalReasoning_Exercise1.xml";
	
        public static void main(String[] args) {
           try
                {
                        //txt file containing data
                        String strFile = "nlp_data.txt";
                        String nounFile = "nounfile.txt"; 
                        
                        //create BufferedReader to read csv file
                        BufferedReader br = new BufferedReader( new FileReader(strFile));
                        BufferedReader nbr = new BufferedReader( new FileReader(nounFile));
                        String strLine = "";
                        StringTokenizer st = null;
                        List<String> al1 = new ArrayList<String>();
                        Vector<Object> vector1 = new Vector<Object>();
                        String col1 = "";
                        String xml_out = "";
                        int lineNumber = 0, tokenNumber = 0;
                        FileWriter fw = new FileWriter(FILE_NAME_XML);
                        
                        System.out.println("Working Directory = " +System.getProperty("user.dir"));
                        //read comma separated file line by line
                        while( (strLine = br.readLine()) != null)
                        {
                                lineNumber++;
                                //break comma separated line using ","
                                System.out.println("------------Data for Line Number in Token-----------:"+lineNumber);
                                al1.add(strLine);
                                st = new StringTokenizer(strLine, " ");
                                while(st.hasMoreTokens())
                                {
                                	col1 = st.nextToken();
                                	vector1.add(col1);
                                    //display csv values
                                    tokenNumber++;
                                    System.out.println("Line # " + lineNumber+", Token # " + tokenNumber+ ", Token : "+ col1);
                                }
                                //reset token number
                                tokenNumber = 0;
                        }
                        // print vector
                        for(int i=0;i < vector1.size();i++){
                        	 System.out.println("Vector #"+i+":"+vector1.elementAt(i));
                      	 }
                        

                        String xmlout = "";
                        xmlout ="<?xml version=\"1.0\" encoding=\"ISO8859-1\"?>  \n" 
                        		+ "<note> \n"										
                        	    + "<heading>Digital Reasoning Exercise 1</heading> \n"
                        	    ;

                        //print array list     

                        System.out.println("---------------ArrayList print-------------");
                        for (String readline : al1) { 
                        	
                            System.out.println(readline); 
                            xmlout += "<body> \n"+readline + "\n</body>\n";
                        } 
                        xmlout += "\n</note>";
                        
                        System.out.println("---------------XML-------------");
                        System.out.println(xmlout);
                		fw.write(xmlout +"\n");
                        fw.close();
                }
                catch(Exception e)
                {
                        System.out.println("Exception while reading file: " + e);                   
                }
        }
}
