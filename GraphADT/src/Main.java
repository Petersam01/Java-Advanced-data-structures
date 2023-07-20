import gui.GUICanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Connect Entrepreneurs");
		GUICanvas can = new GUICanvas(800,509);
		Scene scene = new Scene(can,800,510,Color.SKYBLUE);
		stage.setScene(scene);
		
		stage.show();
	}

}
