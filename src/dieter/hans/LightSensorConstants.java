package dieter.hans;

import java.util.HashMap;

public class LightSensorConstants {

	public static final HashMap<Integer, String> colorIds = new HashMap<>();
	
	public static final double[] FLOOR = {0.02, 0.02, 0.01};
	public static final double[] WHITE_TAPE = {0.125, 0.185, 0.1};
	public static final double[] BLUE_TAPE = {0.02, 0.09, 0.07};
	public static final double[] RED_TAPE = {0.11, 0.02, 0.01};	
	public static final double[] FUCK_TAPE = {0.02, 0.01, 0.00};
	
	public static String getCustomColor(float[] rgb) {
		
		
		return "no color";
	}
	
	
	
	static {
		colorIds.put(0,  "RED");
		colorIds.put(1,  "GREEN");
		colorIds.put(2,  "BLUE");
		colorIds.put(3,  "YELLOW");
		colorIds.put(4,  "MAGENTA");
		colorIds.put(5,  "ORANGE");
		colorIds.put(6,  "WHITE");
		colorIds.put(7,  "BLACK");
		colorIds.put(8,  "PINK");
		colorIds.put(9,  "GRAY");
		colorIds.put(10,  "LIGHT_GRAY");
		colorIds.put(11,  "DARK_GRAY");
		colorIds.put(12,  "CYAN");
		colorIds.put(13,  "BROWN");
		colorIds.put(14,  "NONE");
	}
}
