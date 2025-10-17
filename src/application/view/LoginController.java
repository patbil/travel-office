package application.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.TravelOfficeHelper;
import application.service.database.SqlQuerys;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class LoginController {
	private TravelOfficeHelper travelOfficeHelper;
	private MainWindow mainWindow;
	
	public LoginController() {
		travelOfficeHelper = TravelOfficeHelper.getInstance();
		mainWindow = travelOfficeHelper.getMainWindow();
	}
	
	@FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    void login(ActionEvent event) throws SQLException {
    	String passwordFromDB = "";
    	long idUser = 0;
    	
		ResultSet resultSet = travelOfficeHelper.getDatabase().executeQuery(SqlQuerys.getUserPasswordFromUsername(loginField.getText()));
    	while (resultSet.next()) {
			idUser = resultSet.getLong("id_user");
			passwordFromDB = resultSet.getString("password");
		}
    	
    	if (passwordFromDB.equals(passwordField.getText()) && (passwordField.getText().trim().length() > 0)) {
    		mainWindow.getStage().setTitle(travelOfficeHelper.getBASE_TITLE() + "zalogowany u�ytkownik: " + loginField.getText());
    		mainWindow.getMainWindowController().getLoginMenuItem().setText("Wyloguj");
    		mainWindow.getMainWindowController().getHistoryMenuItem().setDisable(false);
    		mainWindow.getMainWindowController().setIdUser(idUser);
    		close(event);
    	} else {
    		Alert alert = new Alert(AlertType.ERROR, "Nieprawid�owa nazwa u�ytkownika lub has�o!");
    		alert.initModality(Modality.APPLICATION_MODAL);
    		alert.setTitle("B��d");
    		alert.setHeaderText(null);
    		alert.show();
    	}
    }
    
    @FXML
    void close(ActionEvent event) {
    	travelOfficeHelper.getMainWindow().getMainWindowController().getLoginStage().close();
    }
}
