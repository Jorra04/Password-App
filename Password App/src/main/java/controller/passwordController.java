package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Shaker;

import java.sql.*;


public class passwordController {
	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button enter;
	
	public void initialize() {
		
	}
	
	@FXML
	protected void newUser(ActionEvent event) {
		view.App.primaryStage.setScene(view.App.newUser);
	}
	
	@FXML
	protected void validate(ActionEvent event) {
		if(password.getText().equals("hello")) {
			Shaker shaker = new Shaker(password);
			shaker.shake();
		}
	}
	

	
	
//	@FXML
//	protected void validateEnter(KeyEvent event) {
//		if(event.getCode().equals(KeyCode.ENTER)) {
//			System.out.println("Hello");
//		}
//		if(password.getText().equals("Redrooster05")) {
//			System.out.println("valid");
//		}
//		else {
//			password.requestFocus();
//			System.out.println("Invalid");
//		}
//	}
}
