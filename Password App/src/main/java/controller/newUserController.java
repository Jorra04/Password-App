package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import model.Shaker;
import javafx.util.Duration;
import model.DBCredentials;


import java.sql.*;

public class newUserController {
	
	@FXML
	private AnchorPane mainPane;
	@FXML
    private MenuBar menubar;

    @FXML
    private MenuItem back;

    @FXML
    private MenuItem quit;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuItem about;


	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button register;

	@FXML
	private PasswordField confirmPassword;
	@FXML
    private CheckBox showPassword;
	
	private String hoverButton = "-fx-background-color: linear-gradient(#09C6F9, #045DE9);" + "-fx-background-radius: 30;"
			+ "-fx-background-insets: 0;" + "-fx-text-fill: white;";
	
	
    
	
	private String origStyle;
	private String paneStyler = "-fx-background-color: white;";
	private String validationError = "-fx-border-color: #DBB1B1; " + "-fx-background-color: #FFF0F0";
	Tooltip tooltip = new Tooltip("User already exists!");
	Tooltip tooltip2 = new Tooltip("Passwords do not match!");
	Tooltip tooltip3 = new Tooltip();
	Tooltip tooltip4 = new Tooltip("Username size too small");
	Tooltip tooltip5 = new Tooltip("Password size too small");
	
	
	public void initialize() {
		menubar.setStyle(paneStyler);
		origStyle = username.getStyle();
		register.setStyle(hoverButton);
		tooltip4.setShowDelay(Duration.millis(500));
		tooltip2.setShowDelay(Duration.millis(500));
		tooltip3.setShowDelay(Duration.millis(500));
		tooltip4.setShowDelay(Duration.millis(500));
		tooltip5.setShowDelay(Duration.millis(500));
		//comment
	}
	
	
	@FXML
	protected void backToMenu(ActionEvent event) {
		view.App.primaryStage.setScene(view.App.scene);
	}

	

	
	
	@FXML
	protected void validate(ActionEvent event) {
		try {
			resetTooltip();
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
			
			
			if(password.getText().length() == 0 && username.getText().length() == 0) {
				Shaker shaker1 = new Shaker(password);
				Shaker shaker3 = new Shaker(username);
				Tooltip.install(username, tooltip4);
				Tooltip.install(password, tooltip5);
				password.setStyle(validationError);
				username.setStyle(validationError);
				shaker1.shake();
				shaker3.shake();
			
				return;
			}
			else if(password.getText().length() == 0) {
				Shaker shaker1 = new Shaker(password);
				Tooltip.install(password, tooltip5);
				password.setStyle(validationError);
				shaker1.shake();
			
				return;
			}
			else if(username.getText().length() == 0) {
				Shaker shaker3 = new Shaker(username);
				Tooltip.install(username, tooltip4);
				username.setStyle(validationError);
				shaker3.shake();
				
				return;
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
			resetTooltip();
			ps.setString(1, username.getText());
			ps.setString(2, password.getText());

			ps.execute();
			ps.close();
			view.App.primaryStage.setScene(view.App.scene);

		} catch (Exception e) {

		}
	}
	
	protected void resetTooltip() {
		Tooltip.uninstall(password, tooltip5);
		Tooltip.uninstall(username, tooltip4);
		Tooltip.uninstall(password, tooltip2);
		Tooltip.uninstall(confirmPassword, tooltip2);
		Tooltip.uninstall(username, tooltip);
	}
	
	
	

}