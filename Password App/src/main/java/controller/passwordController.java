package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.DBCredentials;
import model.Shaker;

import java.sql.*;

public class passwordController {
	@FXML
	private AnchorPane mainPane;
	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button enter;

	private String validationError = "-fx-border-color: #DBB1B1; " + "-fx-background-color: #FFF0F0";
	private String normal = "-fx-border-color: white; " + "-fx-border-width: 2px;";

	private String textBoxStyler = "-fx-background-color: #a9a9a9 , white , white;"
			+ "-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;";

	private String paneStyler = "-fx-background-color: white;";
	private String origStyle;


	public void initialize() {
//		password.setStyle(textBoxStyler);
//		username.setStyle(textBoxStyler); //use this to stylize the textboxes.
		mainPane.setStyle(paneStyler);
		origStyle = username.getStyle();
	}

	@FXML
	protected void newUser(ActionEvent event) {
		view.App.primaryStage.setScene(view.App.newUser);
	}

	@FXML
	protected void validate(ActionEvent event) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(DBCredentials.getURL(), DBCredentials.getUsername(), DBCredentials.getPassword());
			PreparedStatement ps = conn.prepareStatement("insert into members(username,password) values(?,?);");
			ResultSet rs = ps.executeQuery("Select * from members");
			username.setStyle(origStyle);
			password.setStyle(origStyle);
			while(rs.next()) {
				String name = rs.getString("username");
				String pw = rs.getString("password");
				
				if(name.equals(username.getText()) && pw.equals(password.getText())) {
					System.out.println("successful login");
					break;
				}
				else if(name.equals(username.getText())) {
					System.out.println("Wront password");
					Shaker shaker = new Shaker(password);
					password.setStyle(validationError);
					shaker.shake();
				}
				else if(pw.equals(password.getText())) {
					System.out.println("Wrong username");
					//comment
					Shaker shaker = new Shaker(username);
					username.setStyle(validationError);
					shaker.shake();
				}
				else {
					System.out.println("both wrong");
					Shaker shaker1 = new Shaker(password);
					password.setStyle(validationError);
					shaker1.shake();
					Shaker shaker = new Shaker(username);
					username.setStyle(validationError);
					shaker.shake();
				}
			}

			ps.execute();
			ps.close();

		} catch (Exception e) {

		}
	}
}
