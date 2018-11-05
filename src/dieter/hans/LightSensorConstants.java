package dieter.hans;

import java.util.HashMap;

public class LightSensorConstants {

	public static final HashMap<Integer, String> colorIds = new HashMap<>();
	
	public static final double[] FLOOR = {0.02, 0.02, 0.01};
	public static final double[] WHITE_TAPE = {0.125, 0.185, 0.1};
	public static final double[] BLUE_TAPE = {0.02, 0.09, 0.07};
	public static final double[] RED_TAPE = {0.11, 0.02, 0.01};	
	public static final double[] FUCK_TAPE = {0.02, 0.01, 0.00};
	
	public static String getCustomColor(float[] rgb)
	{
		
		return "no color";
	}
	
	public static float getGrayscale(float[] color)
	{
		float res = 0;

		for (int i = 0; i < 3; ++i)
			res += color[i];
		
		return res / 3;
	}
	
	public static float getDistance(float[] color_1, float[] color_2)
	{
		float res = 0, tmp = 0;
		
		for (int i = 0; i < 3; ++i)
		{
			tmp = color_1[i] - color_2[i];
			res += tmp * tmp;
		}
		
		return (float)Math.sqrt(res);
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
		colorIds.put(9,  "GAY");
		colorIds.put(10,  "LIGHT_GAY");
		colorIds.put(11,  "DARK_GAY");
		colorIds.put(12,  "CYAN");
		colorIds.put(13,  "BROWN");
		colorIds.put(14,  "NONE");
	}
}
