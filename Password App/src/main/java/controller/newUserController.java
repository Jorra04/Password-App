package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import model.Shaker;
import model.DBCredentials;

import java.sql.*;

public class newUserController {
	
	@FXML
	private AnchorPane mainPane;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button register;

	@FXML
	private PasswordField confirmPassword;
	
	private String origStyle;
	private String paneStyler = "-fx-background-color: white;";
	private String validationError = "-fx-border-color: #DBB1B1; " + "-fx-background-color: #FFF0F0";
	Tooltip tooltip = new Tooltip("User already exists!");
	Tooltip tooltip2 = new Tooltip("Passwords do not match!");
	
	
	
	public void initialize() {
		mainPane.setStyle(paneStyler);
		origStyle = username.getStyle();
	}

	@FXML

	protected void validate(ActionEvent event) {
		try {
			password.setStyle(origStyle);
			confirmPassword.setStyle(origStyle);
			username.setStyle(origStyle);
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(DBCredentials.getURL(), DBCredentials.getUsername(), DBCredentials.getPassword());
			PreparedStatement ps = conn.prepareStatement("insert into members(username,password) values(?,?);");
			ResultSet rs = ps.executeQuery("Select * from members");
			while (rs.next()) {
				String name = rs.getString("username");
				String pw = rs.getString("password");
				if (name.equals(username.getText())) {
					Shaker shaker = new Shaker(username);
					username.setStyle(validationError);
					shaker.shake();
					Tooltip.install(username, tooltip);
					return;
				}
			}
			if(!password.getText().equals(confirmPassword.getText())) {
				Tooltip.install(password, tooltip2);
				Tooltip.install(confirmPassword, tooltip2);
				Shaker shaker1 = new Shaker(password);
				Shaker shaker2 = new Shaker(confirmPassword);
				password.setStyle(validationError);
				confirmPassword.setStyle(validationError);
				shaker1.shake();
				shaker2.shake();
				return;
			}
			Tooltip.uninstall(password, tooltip2);
			Tooltip.uninstall(confirmPassword, tooltip2);
			Tooltip.uninstall(username, tooltip);
			ps.setString(1, username.getText());
			ps.setString(2, password.getText());

			ps.execute();
			ps.close();
			view.App.primaryStage.setScene(view.App.scene);

		} catch (Exception e) {

		}
	}

}