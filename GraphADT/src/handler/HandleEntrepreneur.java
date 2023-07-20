package handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;

import models.EntrePreneur;
import models.Positions;

/*
 * class for handling entrepreneur data 
 * writng and readimg from a text file
 * @funcion read entre
 * @function add entre
 */
public class HandleEntrepreneur { 
	

	/*
	 * reading from a textfile 
	 * using a uffered reder to read data
	 * string tokenizer to read the tokens line by line
	 * @return arraylist
	 */
	 public static ArrayList<EntrePreneur> readEntrepreneur() {
	        ArrayList<EntrePreneur> entrepreneur = new ArrayList<EntrePreneur>();
	        String path = "data/entreData.txt";
	        
	        try {
	            BufferedReader br = new BufferedReader(new FileReader(path));
	            String line = br.readLine();
	            while (line != null) {
	                StringTokenizer st = new StringTokenizer(line, " ");
	                String entreID = st.nextToken();
	                String name = st.nextToken();
	                String business = st.nextToken();
	                int profit = Integer.parseInt(st.nextToken());
	                
	                //using an array to read the positions of the student 
	                //separating the x and y coordinates with x
	                String[] coords = st.nextToken().split("x");
	                int xCoord = Integer.parseInt(coords[0]);
	                int yCoord = Integer.parseInt(coords[1]);
	                Positions position = new Positions(xCoord, yCoord);
	                EntrePreneur entreP = new EntrePreneur(entreID,name, business,profit, position);
	                entrepreneur.add(entreP);
	                line = br.readLine();
	            }
	            br.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return entrepreneur;
	    }
	 
	 /*
	  * print writer and file writer to write to file
	  * using the student to capture data intp the entrePreneur class
	  */
	public static void addEntrepreneur(EntrePreneur entrepreneur) {
	    try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("data/entreData.txt", true)))) {
	        String line = entrepreneur.getEntreID() + " " + entrepreneur.getName() + " "  +
	                      entrepreneur.getBusiness() + " " + entrepreneur.getProfit() + " " +
	                      entrepreneur.getPos().getXCoord() + "x" +
	                      entrepreneur.getPos().getYCoord();

	        printWriter.println(line);
	        printWriter.flush();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	



}
