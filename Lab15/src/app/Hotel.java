package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class Hotel extends Application {

	private ContextMenu contextMenu;
	private Orders orders = new Orders();
	private TableView table = new TableView();
	private Button add = new Button("add order");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initColumns();
		initAnchor();
		initButton();
		initMenu();
		primaryStage.setScene(new Scene(new AnchorPane(table, add)));
		primaryStage.getScene().getRoot().setStyle("-fx-font-size: 14px;");
		primaryStage.show();
	}

	public void initColumns() {

		TableColumn pasportData = new TableColumn();
		pasportData.setCellValueFactory(new PropertyValueFactory<Order, String>("passportData"));
		pasportData.setText("passport data");

		TableColumn accommodationDate = new TableColumn();
		accommodationDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("accommodationDate"));
		accommodationDate.setText("accommodation date");

		TableColumn checkOutDate = new TableColumn();
		checkOutDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("checkOutDate"));
		checkOutDate.setText("checkout date");

		TableColumn roomClass = new TableColumn();
		roomClass.setCellValueFactory(new PropertyValueFactory<Order, RoomClass>("roomClass"));
		roomClass.setText("room");

		TableColumn places = new TableColumn();
		places.setCellValueFactory(new PropertyValueFactory<Order, Integer>("places"));
		places.setText("places");

		TableColumn reasons = new TableColumn();
		reasons.setCellValueFactory(new PropertyValueFactory<Order, ArrayList<String>>("reasons"));
		reasons.setText("reasons");

		table.setItems(orders.getList());

		table.getColumns().addAll(
				pasportData,
				accommodationDate,
				checkOutDate,
				roomClass,
				places,
				reasons
		);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void initAnchor() {
		AnchorPane.setLeftAnchor(table, 10.0);
		AnchorPane.setTopAnchor(table, 10.0);
		AnchorPane.setRightAnchor(table, 10.0);
		AnchorPane.setBottomAnchor(table, 100.0);

		AnchorPane.setLeftAnchor(add, 10.0);
		AnchorPane.setRightAnchor(add, 10.0);
		AnchorPane.setBottomAnchor(add, 10.0);
	}

	public void initButton() {
		add.setPrefSize(100, 80);
		add.setOnAction(addOrder -> {
			Order o = WindowHelper.showAddWindow();
			if(o != null)
				orders.addOrder(o);
		});
		add.setStyle(
				"-fx-font-weight: bold;"
						+ "-fx-cursor: hand;" +
						"-fx-font-size: 30px"
		);
	}

	public void initContextMenu(){
		MenuItem menuItem = new MenuItem("Delete");
		menuItem.setOnAction(rem ->
				orders.deleteOrder((Order)table.getSelectionModel().getSelectedItem())
		);
		contextMenu = new ContextMenu(menuItem);
	}

	public void initMenu(){
		initContextMenu();
		table.setRowFactory( tv -> {
			TableRow<Order> row = new TableRow<>();
			row.setOnMouseReleased(event -> {
				if (event.getButton() == MouseButton.SECONDARY && (! row.isEmpty()) ) {
					contextMenu.show(table, event.getScreenX(), event.getScreenY());
				}
			});
			return row ;
		});
	}
}
