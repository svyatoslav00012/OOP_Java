package app;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	private String passportData;
	private Date accommodationDate;
	private Date checkOutDate;
	private RoomClass roomClass;
	private Integer places;
	private ArrayList<String> reasons = new ArrayList<>();

	public Order() {

	}

//	public Order(String passportData, String accommodationDate,
//				 String checkOutDate, String roomClass,
//				 String places, String... reasons) {
//		this.passportData = passportData;
//		this.accommodationDate = Helper.getTime(accommodationDate);
//		this.checkOutDate = Helper.getTime(checkOutDate);
//		this.roomClass = RoomClass.
//		this.reasons = new ArrayList<>();
//		this.reasons.addAll(Arrays.asList(reasons));
//	}

//	public Order(String[] params) {
//		this(params[0], params[1], params[2], params[3], params[4],
//				Arrays.copyOfRange(params, 5, params.length));
//	}

	public Order(String passportData, LocalDate accommodation, LocalDate checkout,
				 RoomClass roomClass, Integer places, String reasons) {
		this.passportData = passportData;
		this.accommodationDate = Date.from(accommodation.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.checkOutDate = Date.from(checkout.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.roomClass = roomClass;
		this.places = places;
		this.reasons.add(reasons);
	}

//	public static boolean checkCorrectInputFormat(
//			String passportData, String accommodationDate,
//			String checkOutDate, String roomClass,
//			String places, String... reasons) {
//		return Helper.checkTime(accommodationDate) &&
//				Helper.checkTime(checkOutDate) &&
//				Room.checkCorrect(roomClass, places);
//	}
//
//	public static boolean checkCorrectInputFormat(String[] params) {
//		return checkCorrectInputFormat(params[0], params[1], params[2], params[3],
//				params[4], Arrays.copyOfRange(params, 4, params.length - 1));
//	}

	public String getPassportData() {
		return passportData;
	}

	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}

	public Date getAccommodationDate() {
		return accommodationDate;
	}

	public void setAccommodationDate(Date accommodationDate) {
		this.accommodationDate = accommodationDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public RoomClass getRoomClass() {
		return roomClass;
	}

	public void setRoomClass(RoomClass roomClass) {
		this.roomClass = roomClass;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public ArrayList<String> getReasons() {
		return reasons;
	}

	public void setReasons(ArrayList<String> reasons) {
		this.reasons = reasons;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("PASSPORT DATA: " + passportData + "\n");
		sb.append("ACCOMMODATION DATE: " + accommodationDate + "\n");
		sb.append("CHECKOUT DATE: " + checkOutDate + "\n");
		sb.append("ROOMCLASS: (" + roomClass + ")\n");
		sb.append("PLACES: (" + places + ")\n");
		sb.append("REASONS:\n");
		for (String s : reasons)
			sb.append("\t-" + s + "\n");
		return sb.toString();
	}
}
