package lab10;

public class Room {

	private RoomClass roomClass;
	private int places;

	public static boolean checkCorrect(String roomC, String plcs){
		boolean a = RoomClass.check(roomC);
		if(!a)
			System.err.println("WRONG FIELD: ROOM CLASS");
		boolean b = true;
		try{
			Integer.parseInt(plcs);
		} catch (Exception e){
			b = false;
			System.err.println("WRONG FIELD: PLACES");
		}
		return a && b;
	}

	public Room(){

	}

	public Room(String roomClass, String places){
		this.roomClass = RoomClass.getClass(roomClass);
		this.places = Integer.parseInt(places);
	}

	public RoomClass getRoomClass() {
		return roomClass;
	}

	public void setRoomClass(RoomClass roomClass) {
		this.roomClass = roomClass;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public String toString(){
		return "Class: " + roomClass + ", Places: " + places;
	}
}
