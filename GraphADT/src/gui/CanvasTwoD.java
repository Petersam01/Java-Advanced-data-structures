package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasTwoD extends Canvas{
	private int width;

	private int height;

	private GraphicsContext gc;

	public CanvasTwoD(int width, int height){
		super(width,height);
		this.width = width;
		this.height = height;
		gc = getGraphicsContext2D();
		clearCanvas();

		
	}


	public void clearCanvas(){
		gc.setFill(Color.SKYBLUE);
		gc.fillRect(0,0,width,height);		
	}


	public int getCanvasWidth(){
		return this.width;
	}



	public int getCanvasheight(){
		return this.height;
	}



	public GraphicsContext getGC(){
		return this.gc;
	}


}

