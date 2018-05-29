package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Order {

	private String passportData;
	private Date accommodationDate;
	private Date checkOutDate;
	private Room room;
	private ArrayList<String> reasons;

	public Order() {

	}

	public Order(String passportData, String accommodationDate,
				 String checkOutDate, String roomClass,
				 String places, String... reasons) {
		this.passportData = passportData;
		this.accommodationDate = Helper.getTime(accommodationDate);
		this.checkOutDate = Helper.getTime(checkOutDate);
		this.room = new Room(roomClass, places);
		this.reasons = new ArrayList<>();
		this.reasons.addAll(Arrays.asList(reasons));
	}

	public Order(String[] params){
		this(params[0], params[1], params[2], params[3], params[4],
			Arrays.copyOfRange(params, 5, params.length));
	}

	public static boolean checkCorrectInputFormat(
			String passportData, String accommodationDate,
			String checkOutDate, String roomClass,
			String places, String... reasons) {
		return Helper.checkTime(accommodationDate) &&
				Helper.checkTime(checkOutDate) &&
				Room.checkCorrect(roomClass, places);
	}

	public static boolean checkCorrectInputFormat(String[] params) {
		return checkCorrectInputFormat(params[0], params[1], params[2], params[3],
				params[4], Arrays.copyOfRange(params, 4, params.length - 1));
	}

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<String> getReasons() {
		return reasons;
	}

	public void setReasons(ArrayList<String> reasons) {
		this.reasons = reasons;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("PASSPORT DATA: " + passportData + "\n");
		sb.append("ACCOMMODATION DATE: " + accommodationDate + "\n");
		sb.append("CHECKOUT DATE: " + checkOutDate + "\n");
		sb.append("ROOM: (" + room + ")\n");
		sb.append("REASONS:\n");
		for(String s : reasons)
			sb.append("\t-" + s + "\n");
		return sb.toString();
	}
}
