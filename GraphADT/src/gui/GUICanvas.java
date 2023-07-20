package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import gADT.Graph;
import handler.HandleEntrepreneur;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import models.Positions;
import models.EntrePreneur;

public class GUICanvas extends GridPane{
	
	private int width;
	private int height;
	private int x = 0;
	private int y = 0;
	
		//GUI things
		
		// textfields;
		private TextField txtEntrePreneurID;
		private TextField txtName;
		private TextField txtBusiness;
		private TextField txtProfit;
		private TextField txtEntreID1;
		private TextField txtEntreID2;
		private TextField txtRemove;

		//buttons
		private Button btnViewGraph;
		private Button btnAddEntrepreneurs;
		private Button btnRemoveEntrepre;
		private Button btnCalcDistance;
		private Button btnConnectEntrePre;
		

		Label lbPosEntrePre = new Label("Click on Canvas to get Position");
		Label lbPosEntrePre2 = new Label("Canvas Position");
		Button btnEntrePrePos = new  Button("Position...Click Canvas");
		/*
		 * constructor
		 * @param width and height
		 */
	public GUICanvas(int width,int height){
		this.width = width;
		this.height = height;	
		graphicUserInterFace();

		
		}

	//generate random numbers
	public int genRandom() {
		Random rand = new Random();
		int number = rand.nextInt(100);
		return number;
	}
		/*
		 * my graphical user interface 		
		 */
	public void graphicUserInterFace() {
		
		CanvasTwoD canvas = new CanvasTwoD((int)(this.width * .80) ,this.height);
		HBox mArea = new HBox();
		HBox CArea = new HBox();
		CArea.getChildren().add(canvas);		
		HBox hBox = new HBox();
		VBox vBox = new VBox();
		vBox.setStyle("-fx-padding-left: 20;");
		GridPane pane = new GridPane();
		
		pane.setVgap(10);
		pane.setHgap(10);
		pane.getColumnConstraints().add(new ColumnConstraints(150));
		pane.setAlignment(Pos.CENTER);
		
		HBox gridsConatiner = new HBox();
		GridPane addConnectionGridPane = new GridPane();


		gridsConatiner.getChildren().addAll(pane,addConnectionGridPane);

		vBox.getChildren().addAll(
			gridsConatiner
		);
		hBox.getChildren().add(vBox);
		mArea.getChildren().addAll(CArea,hBox);
		getChildren().add(mArea);
		
		//textfields
		txtEntrePreneurID =  new TextField();
		txtName = new TextField();
		txtBusiness = new TextField();
		txtProfit = new TextField();
		txtEntreID1 = new TextField();
		txtEntreID2 = new TextField();
		txtRemove = new TextField();
		
		txtEntrePreneurID.setPromptText("Entrepreneur ID");
		txtName.setPromptText("Entrepreneur Name");
		txtBusiness.setPromptText("Business NAme");
		txtProfit.setPromptText("profit per month");
		txtEntreID1.setPromptText("Id Of enterepreneur 1");
		txtEntreID2.setPromptText("Id Of entrepreneurs 2");
		txtRemove.setPromptText("Id of entrepreneurs to remove");
		
		//buttons
		btnViewGraph = new Button("ViewGraph");
		btnAddEntrepreneurs = new Button("Add Entrepreneurs");
		btnRemoveEntrepre = new Button("Remove Entrepreneur");
		btnCalcDistance = new Button("Calc_Distance");
		btnConnectEntrePre = new Button("Connect Entrepreneurs");
		

		
		//adding to the GUI
		pane.add(btnViewGraph,0,0);
		pane.add(btnAddEntrepreneurs,0,1);
		pane.add(txtEntrePreneurID, 0, 2);;
		pane.add(txtName,0,3);
		pane.add(txtBusiness,0,4);
		pane.add(txtProfit,0,5);
		pane.add(btnConnectEntrePre,0,11);
		pane.add(txtEntreID1,0,12);
		pane.add(txtEntreID2,0,13);
		pane.add(btnRemoveEntrepre,0,9);
		pane.add(txtRemove, 0, 10);
		pane.add(btnEntrePrePos,0,8);
		pane.add(btnCalcDistance, 0, 14);
	
	
	 //butto view graph to displa the graaph
			btnViewGraph.setOnAction( e -> {
				Graph<EntrePreneur> g_Entre = Draw.entrePreGraph();
				Draw draw = new Draw(canvas);
				draw.printGraph(g_Entre);
			
			});
			
			//button ad entrepreneurs to add entrepreneurs to canvas
			btnAddEntrepreneurs.setOnAction(e->{
				
					String ID =  txtEntrePreneurID.getText();
					String name = txtName.getText();
					String business = txtBusiness.getText();
					int profit = Integer.parseInt(txtProfit.getText());
					Positions position = new Positions(x,y);
					
					EntrePreneur entrepre = new EntrePreneur(ID,name,business,profit,position);
					HandleEntrepreneur.addEntrepreneur(entrepre);
					canvas.setOnMouseClicked(e2 -> {});
					btnEntrePrePos.setText("Position");

					Graph<EntrePreneur> g_EntrePre = Draw.entrePreGraph();
					Draw draw = new Draw(canvas);
					draw.printGraph(g_EntrePre);
					
					
		});
			//button to get position from canavas
			btnEntrePrePos.setOnAction(e ->{
				canvas.setOnMouseClicked(e2 ->{
					x = (int)e2.getX();
					y = (int)e2.getY();
					btnEntrePrePos.setText("Position : (" + x + "," + y + ")");
				});

			});
			
			
             btnCalcDistance.setOnAction(e->{

			});
			
             //button to connect the entrepreneurs into a line graph
			btnConnectEntrePre.setOnAction(e->{
				
				try {
					String strline = txtEntreID1.getText() + "@" + txtEntreID2.getText() + "@" + genRandom();
					File file = new File("data/Connecting.txt");
					BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
					bw.write(strline + System.getProperty("line.separator"));
					Graph<EntrePreneur> g_EntrePre = Draw.entrePreGraph();
					Draw draw = new Draw(canvas);
					draw.printGraph(g_EntrePre);
					
					bw.close();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			});
			
			//button to delete student from graph
			btnRemoveEntrepre.setOnAction(e ->{
				
				try {
					
					String strline;
					String Removeln;
					Removeln = txtRemove.getText();
					
					File file = new File("data/entreData.txt");
					File tempFile = new File("data/tempFile.txt");
					
					BufferedReader br = new BufferedReader(new FileReader(file));
					BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
					
					while((strline=br.readLine()) != null) {
						String deletedLine = strline.trim();
						if(deletedLine.equals(Removeln)) {
							continue;
						}
						
						bw.write(strline + System.getProperty("line.separator"));
						Graph<EntrePreneur> g_Entrepre = Draw.entrePreGraph();
						Draw draw = new Draw(canvas);
						draw.printGraph(g_Entrepre);
					}
					br.close();
					bw.close();
					file.delete();
					tempFile.renameTo(file);
					
				
					
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				
			});
			
			
			
	}
	
}
