package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//merged




public class App extends Application{
	public static Stage primaryStage;
	public static Scene secondaryScene;
	public static Scene newUser;
	public static Scene scene;


	public static void main(String[] args) {
		launch(args);
	}



	@Override
	public void start(Stage stage) throws Exception{
		primaryStage = stage;
		Parent root = FXMLLoader.load(getClass().getResource("/view/mainPage.fxml"));
		Parent root2 = FXMLLoader.load(getClass().getResource("/view/passwordStrength.fxml"));
		Parent root3 = FXMLLoader.load(getClass().getResource("/view/createNewUser.fxml"));
		   
	    scene = new Scene(root, 460, 300);
	    secondaryScene = new Scene(root2,460,300);
	    newUser = new Scene(root3,370,390);
	    
		
		
	
	    primaryStage.setTitle("Password");
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(true);
	    primaryStage.show();
	}
	

}