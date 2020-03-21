package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Shaker;

import java.sql.*;

public class newUserController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private PasswordField confirmPassword;
    
    
    
    @FXML
    
    protected void validate(ActionEvent event) {
    	try {
    		if(password.getText().equals(confirmPassword.getText())) {
    			Class.forName("com.mysql.jdbc.Driver");
    			Connection conn = null;
    			conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root", "");
    			PreparedStatement ps = conn.prepareStatement("insert into members(username,password) values(?,?);");
    			ps.setString(1, username.getText());
    			ps.setString(2, password.getText());
    			
    			
    			ps.execute();
    			ps.close();
    			view.App.primaryStage.setScene(view.App.scene);
    		}
    		else {
    			Shaker shaker = new Shaker(confirmPassword);
    			shaker.shake();
    		}
			
			
		
		}
		catch(Exception e) {
			
		}
    }
    

}