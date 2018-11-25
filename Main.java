import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Application;
import javafx.application.Platform;

public class Main extends Application {
	public static State state = new State();
	final JFXPanel fxPanel = new JFXPanel();
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		Platform.setImplicitExit(false);
		primaryStage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                e.consume();
            }
        });
		
		Start start = new Start(state);
		 int[][][] sample = {{{1, 2, 3}, {4, 5, 6} }};
	        //hoặc  sample = new int[][]{{1, 2, 3}, {4, 5, 6} };
		 
	}	
	
}
