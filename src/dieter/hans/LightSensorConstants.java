package dieter.hans;

import java.util.HashMap;

public class LightSensorConstants {

	public static final HashMap<Integer, String> colorIds = new HashMap<>();
	
	public static final double[] FLOOR = {0.02, 0.02, 0.01};
	public static final double[] WHITE_TAPE = {0.125, 0.185, 0.1};
	public static final double[] BLUE_TAPE = {0.02, 0.09, 0.07};
	public static final double[] RED_TAPE = {0.11, 0.02, 0.01};	
	public static final double[] FUCK_TAPE = {0.02, 0.01, 0.00};
	
	public static final double[][] COLORS = {FLOOR, WHITE_TAPE, BLUE_TAPE, RED_TAPE, FUCK_TAPE};
	public static final int[] customColorIds = {7, 6, 2, 0, 14};
	
	public static int getCustomColor(float[] rgb)
	{
		double[] fugg = new double[rgb.length];
		for(int i = 0; i < rgb.length; i++) {
				fugg[i] = rgb[i];
		}
		
		double distance = Double.POSITIVE_INFINITY;
		int customColor = 14;
		
		for(int i = 0; i <COLORS.length; i++) {
			double d = getDistanceSqr(fugg, COLORS[i]);
			if(d < distance) {
				distance = d;
				customColor = customColorIds[i];
			}
		}
		
		return customColor;
	}
	
	public static double getShit(float[] color) {
		double[] c = new double[3];
		c[0] = color[0];
		c[1] = color[1];
		c[2] = color[2];
		return getShit(c);
	}
	
	public static double getShit(double[] color)
	{
		double dist_black = getDistanceSqr(color, FLOOR);
		double dist_white = getDistanceSqr(color, WHITE_TAPE);
		
		return -1.0 + 2.0 * Math.sqrt(dist_black / (dist_black + dist_white));
	}
	
	public static double getGrayscale(double[] color)
	{
		double res = 0;

		for (int i = 0; i < 3; ++i)
			res += color[i];
		
		return res / 3;
	}
	
	public static double getDistanceSqr(double[] color_1, double[] color_2)
	{
		double res = 0, tmp = 0;
		
		for (int i = 0; i < 3; ++i)
		{
			tmp = color_1[i] - color_2[i];
			res += tmp * tmp;
		}
		
		return res;
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
