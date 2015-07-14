import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.*;

/*---Modify your program from #2 to use “ nlp_data.zip ” as its input. Use a thread pool to
parallelize the processing of the text files contained in the zip. Aggregate the results
and modify the output schema accordingly. --- */

public class DigitalReasoning_Exercise3_Thread implements Runnable{
	//--------------------------------------Change the File Paths
    private static final String FILE_NAME = "C:\\Harinder\\Profession_SW\\Java\\DigitalReasoning\\NLP_test\\nlp_data.zip";
    private static final String OUTPUT_DIR = "C:/Harinder/Profession_SW/Java/DigitalReasoning/NLP_test/temp/";
    private static final String OUTPUT_FILE =  "C:\\Harinder\\Profession_SW\\Java\\DigitalReasoning\\NLP_test\\Digital_Reasoning_Exercise3_1.out";
    private static final String OUTPUT_FILE2 =  "C:\\Harinder\\Profession_SW\\Java\\DigitalReasoning\\NLP_test\\Digital_Reasoning_Exercise3_2.out";
    
    private static final int BUFFER_SIZE = 1024;
    private static final String nounFile1 = "NER.txt";
    public static int threadCounter = 0;
    public static Vector<Object> vector_all_matches = new Vector<Object>();

    public static void main(String args[]) throws IOException {

     readUsingZipFile();
     
     // Print noun file through vector
     System.out.println("\nVECTOR");
     for(int i=0;i < vector_all_matches.size();i++){
    	 System.out.println("vector_all_matches #"+i+":"+vector_all_matches.elementAt(i));
  	 }
     
    }

    public static void temptest(String st) 
    {
    System.out.println("HELLO-----------------------"+st);
   // readUsingZipFile();
  }
    		
  //---------------------------method run for thread
    public void run()  {
    	threadCounter++;
    }
   
//---------------------------method readUsingZipFile
    private static void readUsingZipFile() throws IOException {
    	 
        final ZipFile file = new ZipFile(FILE_NAME);
                
        System.out.println("Working Directory = " +System.getProperty("user.dir"));
        System.out.println("Iterating over zip file : " + FILE_NAME);
        String nounFile = "NER.txt"; // file with noun
        BufferedReader nbr = new BufferedReader( new FileReader(nounFile));
        String nLine = "";
        Vector<Object> vector2 = new Vector<Object>();
        File file_out = new File(OUTPUT_FILE);
               
       try {
    	   
           //-----Storing Noun file in vector
           while( (nLine = nbr.readLine()) != null)
           {
           	vector2.add(nLine);
           }
    	
  	 
           FileWriter fw = new FileWriter(OUTPUT_FILE);
           fw.close();
           FileWriter fw1 = new FileWriter(OUTPUT_FILE2);
           fw.close();
           
            final Enumeration<? extends ZipEntry> entries = file.entries();
            int ii = 0;
            while (entries.hasMoreElements()) {
            	final ZipEntry entry = entries.nextElement();
            	//System.out.println(entry.getName());
            	if(entry.getName().contains(".txt"))
            	{
            		ii++;
            		System.out.printf("File: %s : Size %d : Modified on %TD %n", entry.getName(), entry.getSize(), new Date(entry.getTime()));
            		DigitalReasoning_Exercise3_Thread t1 = new DigitalReasoning_Exercise3_Thread();
          	        new Thread("" + ii){
          	        public void run() {
          	          System.out.println("Thread: " + getName() + " running");
          	       
          	          try {
          	        	DigitalReasoning_Exercise3_Thread.extractEntry(entry, file.getInputStream(entry), vector2);
          	          } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
          	        }
          	      }.start();
            	}
            }
            System.out.printf("Zip file %s extracted successfully in %s", FILE_NAME, OUTPUT_DIR);
        } 
       finally {
       //     file.close();
        }
    }
     
   public static void extractEntry(final ZipEntry entry, InputStream is, Vector vector21) throws IOException
    {
		String strFile = ""; //original file

		//BufferedReader br = new BufferedReader( new FileReader(strFile));
       BufferedReader br ;
       Vector<Object> vector1 = new Vector<Object>();
       String strLine = "";
       StringTokenizer st = null;
       String col1 = "";
       String exractedFile = OUTPUT_DIR + entry.getName();
       FileOutputStream fos = null;
       System.out.println("exractedFile:"+exractedFile);
 	  // FileWriter fw = new FileWriter(OUTPUT_FILE);
       FileWriter fw = (new FileWriter(OUTPUT_FILE, true));
       FileWriter fw1 = (new FileWriter(OUTPUT_FILE2, true));
       try {
    		fos = new FileOutputStream(exractedFile);
    		final byte[] buf = new byte[BUFFER_SIZE];
    		int length=0;
    		fw.write("\n-------------Matching data for :"+exractedFile +"\n");
    	    
    		//System.out.println("length:"+length + "," + buf.length );
    	   while ((length = is.read(buf, 0, buf.length)) >= 0)
    			{ 
    				fos.write(buf, 0, length);  
    			}
    		br = new BufferedReader( new FileReader(exractedFile));
    		//Read input file and put in  Vector
    		
    		while( (strLine = br.readLine()) != null)
           {
    				for(int i=0;i < vector21.size();i++){
    					String sv22 = vector21.elementAt(i).toString();
        				if(strLine.contains(sv22))
        				{
        					vector_all_matches.add(sv22);
          					fw.write(sv22);
          					fw.write("\n");
        					System.out.println("*****MATCHES FOUND in file:"+entry.getName() + " for String :" +sv22 );
        				}//if
               	 }//for
    			
                   st = new StringTokenizer(strLine, " ");
                   while(st.hasMoreTokens())
                   {
                    	col1 = st.nextToken();
                    	vector1.add(col1);
                   }//while
           }//while
          // System.out.println("--------------Print Matching words from Noun File-------------");
           for(int i=0;i < vector1.size();i++){
           	String vcol1 = vector1.elementAt(i).toString();
              	for(int j=0;j < vector21.size();j++){	
           		String vcol2 = vector21.elementAt(j).toString();
           		if (vcol1.equalsIgnoreCase(vcol2))
           			{
           				fw1.write(exractedFile +" file Vector #"+i+":"+vcol1 +"  MATCHES WITH   "+ nounFile1 +" File Line #"+j+":"+vcol2);
           				fw1.write("\n");
           				System.out.println(exractedFile +" file Vector #"+i+":"+vcol1 +"  MATCHES WITH   "+ nounFile1 +" File Line #"+j+":"+vcol2);	
           			}
          			}
        	 }
           fw.close();
           fw1.close();           
   	 } //try 
    	catch (IOException ioex)
    			{ 
    		   System.out.println("Issue with exception");
    	    	}//catch
    	}
   
     }

