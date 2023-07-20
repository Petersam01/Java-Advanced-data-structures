package gui;

import java.util.ArrayList;
import java.util.StringTokenizer;

import gADT.Graph;
import handler.Connector;
import handler.HandleEntrepreneur;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.Positions;
import models.EntrePreneur;


public class Draw {
	
	private GraphicsContext gc;
	private int width;
	private int height;
	private CanvasTwoD canvas;

	public Draw(CanvasTwoD canvas){
		this.gc = canvas.getGC();
		this.width = canvas.getCanvasWidth();
		this.height = canvas.getCanvasheight();
		this.canvas = canvas;
	}

	
	/*
	 * draw entrepreneur node
	 * string to write the entrepreneur data next to the node
	 * @param entrepreneur
	 * @return void
	 */
	private void entrePreDraw(EntrePreneur entrepreneur, double dist){
		
		int x = entrepreneur.getPos().getXCoord();
		int y = entrepreneur.getPos().getYCoord();
		int size = 20;

	    gc.setFill(Color.RED);
	    gc.fillOval(x,y,size,size);

	    int index = x + size + 5;
	    
	    String ID  = entrepreneur.getEntreID();
	    String name = entrepreneur.getName();
	    String business = entrepreneur.getBusiness();
	    int profit = entrepreneur.getProfit();

	    String strDataToPrint = "ID: " +ID+"\t"+ "Name: "+name+"\n";
	           strDataToPrint += "Business: "+business+"\n";
	    		strDataToPrint += "Profit: " +profit+ "%" + "\n";
	    		
	    		
	    gc.setFill(Color.BLACK);
	    gc.fillText(strDataToPrint, index, y);

	}


public static Graph<EntrePreneur> entrePreGraph(){
	
	ArrayList<Graph.Vertex<EntrePreneur> > vertices = Draw.entrepreneurVertices();
	ArrayList<Graph.Edge<EntrePreneur> > edges = Draw.entrepreneurEdges(vertices);
	Graph<EntrePreneur> graph = new Graph<EntrePreneur>(vertices,edges);

	return graph;
}

/*
 * creating entrepreneurs vertices
 * @param graph vertex of entrepreneurs
 * @return arraylist of vertices
 */
private static ArrayList<Graph.Vertex<EntrePreneur>> entrepreneurVertices() {
	// TODO Auto-generated method stub
	ArrayList<Graph.Vertex<EntrePreneur> > arrVert = new ArrayList<Graph.Vertex<EntrePreneur> >();
     //looping through the entrepreneurs in the student data textfile
	//adding the entrepreneur to the vertex
	for (EntrePreneur student : HandleEntrepreneur.readEntrepreneur()) {
		Graph.Vertex<EntrePreneur> vertex = new Graph.Vertex<EntrePreneur>(student);
		arrVert.add(vertex);
		if(student == null) {
			HandleEntrepreneur.addEntrepreneur(student);
		}
	}
	return arrVert;
}


/*
 * creating edges in the graph
 * @param vertices
 * @return arrayList
 */
@SuppressWarnings("unused")
private static ArrayList<Graph.Edge<EntrePreneur> > entrepreneurEdges(ArrayList<Graph.Vertex<EntrePreneur> > vertices){
	
	ArrayList<Graph.Edge<EntrePreneur> > arrEdges = new ArrayList<Graph.Edge<EntrePreneur> >();
	 ArrayList<String> entrePreneur = Connector.loadEntreConnectons();
	 StringTokenizer tk = null;
	 
	 String stdID = null;
     String name = null;
	 String business = null;
	 int profit = 0;
	 Positions pos = null;;
	 EntrePreneur std = new EntrePreneur(stdID,name,business,profit,pos);
	   /*
	    * looping through each edges of the entrepreneurs 
	    * using string tokenizer to read the data from the connecting text file to make connections
	    */
	    for (int i = 0; i < entrePreneur.size(); i++) {
	    	tk = new StringTokenizer(entrePreneur.get(i), "@");
	      
	        String strVert = tk.nextToken();
	        String strVert2 = tk.nextToken();
	        int number = Integer.parseInt(tk.nextToken());
	        
	        Graph.Vertex<EntrePreneur> vertice = null;
	        Graph.Vertex<EntrePreneur> vertice2 = null;
	        
	        /*
	         * comparing the entrepreneurs ID and vertices through a loop
	         */
	        for (int j = 0; j < vertices.size(); j++) {
	        	EntrePreneur entrePre = vertices.get(j).getValue();
				String entreID = entrePre.getEntreID();
	        	
	        	if(entreID.equals(strVert)){
	        		vertice = vertices.get(j);
	        		
	        		}else if(entreID.equals(strVert2)){
					vertice2 = vertices.get(j);
				}
	        	
	            
	        }
	        if (vertice != null && vertice2 != null) {
	            Graph.Edge<EntrePreneur> edge = new Graph.Edge<>(number, vertice, vertice2);
	            arrEdges.add(edge);
	        }
	      
	    }
	   /*
	    * if the entrepreneurs data is null get the data from the entrepreneurs class
	    */
	   
	    if(stdID == null && name == null && business == null && profit == 0 && pos == null) {
	    	stdID = std.getEntreID();
	    	name = std.getName();
	    	business = std.getBusiness();
	    	profit = std.getProfit();
	    	pos = std.getPos();
	    }

	    return arrEdges;
	}

/*
 * print graph to display graph on canvas
 * using graphic context to draw the lines
 * @param graph from graph ADT
 * @return void
 */
public void printGraph(Graph<EntrePreneur> graph){
	int intDist = 0;
	int lineShift = 10;
	gc.setStroke(Color.BLACK);
	gc.setLineWidth(4);

	/*
	 * looping through the edges of each student
	 */
	for(int i = 0;i<graph.getEdges().size();i++) {
        Graph.Edge<EntrePreneur> edge = graph.getEdges().get(i);	
		Positions pos1 = edge.getFromVertex().getValue().getPos();
		Positions pos2 = edge.getToVertex().getValue().getPos();
		Positions pos3 = edge.getFromVertex().getValue().getPos();
		Positions pos4 = edge.getToVertex().getValue().getPos();
		
		double xCoord = pos1.getXCoord() - pos3.getXCoord();
		double yCoord = pos2.getYCoord() - pos4.getYCoord();
		
		double distance = Math.sqrt((xCoord * xCoord) + (yCoord * yCoord)); 
		intDist = (int) distance;
		
		
		 String strDataToPrint = "Dist: " +intDist;

		gc.strokeLine(pos1.getXCoord() + lineShift,pos1.getYCoord() 
		+ lineShift,pos2.getXCoord() + lineShift,pos2.getYCoord() + lineShift);
		
		/*
		 * itearating through the vertex of the entrepreneurs and making connections
		 */
		 for (int x=0;x<graph.getVertices().size();x++) {
	            Graph.Vertex<EntrePreneur> vert = graph.getVertices().get(x);
				this.entrePreDraw(vert.getValue(),distance);	

			}
		 if(graph.getVertices().size() == 0) {
			 i++;
			 Graph.Vertex<EntrePreneur> vert = graph.getVertices().get(i);
			this.entrePreDraw(vert.getValue(),distance);	
		 }
	}	
	
  }     

}
