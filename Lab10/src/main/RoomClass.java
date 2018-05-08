package main;

public enum RoomClass {

	ECONOMY,
	MIDDLE,
	LUX,
	PRESIDENT;

	public static boolean check(String string){
		return (ECONOMY.toString().equals(string) ||
				MIDDLE.toString().equals(string) ||
				LUX.toString().equals(string) ||
				PRESIDENT.toString().equals(string)
		);
	}

	public static RoomClass getClass(String string){
		if(ECONOMY.equals(string))
			return ECONOMY;
		if(MIDDLE.equals(string))
			return MIDDLE;
		if(LUX.equals(string))
			return LUX;
		if(PRESIDENT.equals(string))
			return PRESIDENT;
		return null;
	}
}
