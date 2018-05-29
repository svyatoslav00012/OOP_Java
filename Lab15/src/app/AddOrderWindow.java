package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Date;


public class AddOrderWindow extends Stage {

	private Order order;

	private TextField pasportData = new TextField();
	private DatePicker accommodationDate = new DatePicker();
	private DatePicker checkOutDate = new DatePicker();
	private ChoiceBox roomClass = new ChoiceBox();
	private TextField places = new TextField();
	private TextArea reasons = new TextArea();

	private Button save = new Button("SAVE");
	private VBox box;

	public AddOrderWindow() {
		box = new VBox(
				20.0,
				pasportData,
				accommodationDate,
				checkOutDate,
				roomClass,
				places,
				reasons,
				save
		);
		box.setAlignment(Pos.CENTER);
		setScene(new Scene(box));
		initModality(Modality.APPLICATION_MODAL);
		initSize();
		initFields();
		initButton();
		initChoiceBox();
		box.setStyle("-fx-background-color: darkslategrey;");
	}

	public void initChoiceBox(){
		roomClass.getItems().addAll(
			RoomClass.ECONOMY,
			RoomClass.MIDDLE,
			RoomClass.LUX,
			RoomClass.PRESIDENT
		);
		roomClass.getSelectionModel().selectFirst();
	}

	public void initFields() {
		pasportData.setTooltip(new Tooltip("pasportData"));
		pasportData.setPromptText("pasportData");

		accommodationDate.setTooltip(new Tooltip("accomodation date"));
		accommodationDate.setValue(new Date(System.currentTimeMillis())
				.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		checkOutDate.setTooltip(new Tooltip("checkout date"));
		checkOutDate.setValue(new Date(System.currentTimeMillis() + 1000 * 3600 * 24)
				.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		roomClass.setTooltip(new Tooltip("room class"));
		places.setTooltip(new Tooltip("places"));
		places.setPromptText("places");
		reasons.setTooltip(new Tooltip("reasons"));
		reasons.setPromptText("reasons");
	}

	public void initSize() {
		box.setPrefSize(500, 500);
		pasportData.setPrefSize(200, 40);
		accommodationDate.setPrefSize(200, 40);
		checkOutDate.setPrefSize(200, 40);
		roomClass.setPrefSize(200, 40);
		places.setPrefSize(200, 40);
		reasons.setPrefSize(200, 200);
		save.setPrefSize(100, 50);
		box.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
	}

	public void initButton() {
		save.setOnAction(save -> {
			if (check())
				saveOrder();
			else
				WindowHelper.showWarning("some fields are wrong or empty");
		});
	}

	private void saveOrder() {
		order = new Order(
				pasportData.getText(),
				accommodationDate.getValue(),
				checkOutDate.getValue(),
				(RoomClass)roomClass.getValue(),
				Integer.parseInt(places.getText()),
				reasons.getText()
		);
		WindowHelper.closeWindow(pasportData);
	}

	public boolean check() {
		try{
			Integer.parseInt(places.getText());
			return true;
		} catch (Exception e){
			WindowHelper.showWarning("wrong places field");
		}
		return false;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
