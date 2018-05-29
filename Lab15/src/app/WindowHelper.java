package app;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class WindowHelper {

	public static Order showAddWindow() {
		AddOrderWindow a = new AddOrderWindow();
		a.showAndWait();
		return a.getOrder();
	}

	public static void showWarning(String text){
		new Alert(Alert.AlertType.WARNING, text, ButtonType.OK).show();
	}

	public static void closeWindow(Node child){
		((Stage)child.getScene().getWindow()).close();
	}
}
