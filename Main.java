import java.awt.*;
import java.util.*;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
	public static State state = new State();
	final JFXPanel fxPanel = new JFXPanel();
	public static void main(String[] args) {
		Application.launch(args);
	
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		Start start = new Start(state);
		start.NewTest();
	}	
	
}
