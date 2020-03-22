package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import model.Strength;

public class passwordStrength {
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
	private ProgressBar progressBar;

	@FXML
	private TextField password;

	@FXML
	private Button enter;

    

	String cssProps = "-fx-background-color: linear-gradient(#09C6F9, #045DE9);" + "-fx-background-radius: 30;"
			+ "-fx-background-insets: 0;" + "-fx-text-fill: white;";

	Strength strength;
	Tooltip tooltip = new Tooltip("Check the strength of your password.");
	private String paneStyler = "-fx-background-color: white;";

	public void initialize() {
		menubar.setStyle(paneStyler);
		enter.setStyle(cssProps);
		Tooltip.install(enter, tooltip);
	}
	// comment2
	
	
	@FXML
	protected void backToMenu(ActionEvent event) {
		view.App.primaryStage.setScene(view.App.scene);
	}

	@FXML
	protected void checker(ActionEvent event) {
		checkPassword(password.getText());
		if (this.strength.equals(Strength.STRONG)) {
			progressBar.setProgress(1);
			progressBar.setStyle("-fx-accent: green");
		} else if (this.strength.equals(Strength.MEDIUM)) {
			progressBar.setProgress(0.6);
			progressBar.setStyle("-fx-accent: yellow");
		}

		else if (this.strength.equals(Strength.WEAK)) {
			progressBar.setProgress(0.4);
			progressBar.setStyle("-fx-accent: yellow");
		} else if (this.strength.equals(Strength.VERY_WEAK)) {
			progressBar.setProgress(0.2);
			progressBar.setStyle("-fx-accent: red");
		}
	}

	protected void checkPassword(String text) {
		if (allNumeric(text) && text.length() <= 8) {
			System.out.println("here");
			this.strength = Strength.VERY_WEAK;
			return;
		}
		if (allNumeric(text) && (text.length() > 8 && text.length() <= 14)) {
			System.out.println("here2");
			this.strength = Strength.WEAK;
			return;
		}
		if (allNumeric(text) && text.length() > 14) {
			System.out.println("here3");
			this.strength = Strength.MEDIUM;
			return;
		}
		if ((allLowerCase(text) && text.length() <= 10) || (allUpperCase(text) && text.length() <= 10)) {
			System.out.println("here4");
			this.strength = Strength.WEAK;
			return;
		}
		if ((allLowerCase(text) && text.length() > 10) || (allUpperCase(text) && text.length() > 10)) {
			System.out.println("here5");
			this.strength = Strength.MEDIUM;
			return;
		}
		if ((!allLowerCase(text) && (!allUpperCase(text))) && text.length() <= 9) {
			System.out.println("here6");
			this.strength = Strength.WEAK;
			return;
		}
		if ((!allLowerCase(text) && (!allUpperCase(text)) && (!containsDigit(text))) && text.length() > 9) {
			System.out.println("here7");
			this.strength = Strength.MEDIUM;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text))) && text.length() <= 4) {
			System.out.println("here8");
			this.strength = Strength.VERY_WEAK;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)))
				&& (text.length() < 4 && text.length() <= 8)) {
			System.out.println("here9");
			this.strength = Strength.WEAK;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text))) && text.length() == 9) {
			System.out.println("here10");
			this.strength = Strength.MEDIUM;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)) && (!hasSpecial(text)))
				&& text.length() > 9) {
			System.out.println("here11");
			this.strength = Strength.STRONG;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)) && hasSpecial(text))
				&& text.length() <= 4) {
			System.out.println("here12");
			this.strength = Strength.VERY_WEAK;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)) && hasSpecial(text))
				&& (text.length() <= 8 && text.length() >= 5)) {
			System.out.println("here13");
			this.strength = Strength.WEAK;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)) && hasSpecial(text))
				&& text.length() == 9) {
			System.out.println("here14");
			this.strength = Strength.MEDIUM;
			return;
		}
		if (((!allNumeric(text)) && (!allUpperCase(text)) && (!allLowerCase(text)) && hasSpecial(text))
				&& text.length() >= 10) {
			System.out.println("here14");
			this.strength = Strength.STRONG;
			return;
		}
	}

	protected boolean allNumeric(String text) {
		for (char character : text.toCharArray()) {
			if (!Character.isDigit(character)) {
				return false;
			}
		}
		return true;
	}

	protected boolean allUpperCase(String text) {
		for (char character : text.toCharArray()) {
			if (!Character.isUpperCase(character)) {
				return false;
			}
		}
		return true;
	}

	protected boolean allLowerCase(String text) {
		for (char character : text.toCharArray()) {
			if (!Character.isLowerCase(character)) {
				return false;
			}
		}
		return true;
	}

	protected boolean containsDigit(String text) {
		for (char character : text.toCharArray()) {
			if (Character.isDigit(character)) {
				return true;
			}
		}
		return false;
	}

	protected boolean containsUpperCase(String text) {
		for (char character : text.toCharArray()) {
			if (Character.isUpperCase(character)) {
				return true;
			}
		}
		return false;
	}

	protected boolean containsLowerCase(String text) {
		for (char character : text.toCharArray()) {
			if (Character.isLowerCase(character)) {
				return true;
			}
		}
		return false;
	}

	protected boolean hasSpecial(String text) {
		for (char character : text.toCharArray()) {
			if ((!Character.isAlphabetic(character)) && (!Character.isDigit(character))) {
				return true;
			}
		}
		return false;
	}

}
