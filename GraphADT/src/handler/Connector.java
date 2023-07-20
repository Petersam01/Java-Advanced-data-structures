package handler;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import models.Positions;
import models.EntrePreneur;

public class Connector {
	

	/*
	 * loading the connectons of the entreprenur id's to enable connections
	 * @return array list
	 * using a scanner to scan read the data
	 */
	public static ArrayList<String> loadEntreConnectons() {

		ArrayList<String> connecting = new ArrayList<String>();
	    String path = "data/Connecting.txt";

	    try (Scanner sc = new Scanner(new File(path))) {
	    
	        while (sc.hasNextLine()) {
	            connecting.add(sc.nextLine());

	        }

	    } catch (Exception ex) {
	        System.out.println(ex);
	    }

	    return connecting; 
	}
	
		  
     

}
