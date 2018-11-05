package dieter.hans;

import lejos.hardware.sensor.EV3ColorSensor;

public class TaskTrackLine extends TrackTask
{
	public static final float L_THRESHOLD = 0.4f;
	public static final float U_THRESHOLD = 0.6f;
	
	@Override
	public int runTrack()
	{
		float[] rgb = new float[3];
		
		HansDieter.S_RGB.fetchSample(rgb, 0);
		
		float gray = LightSensorConstants.getGrayscale(rgb);
		
		// 0 -> black
		// 1 -> white
		
		if (gray < L_THRESHOLD)
		{
			HansDieter.StopRobot();

			// looping rotate right
			// continue forward
		}
		else if (gray > U_THRESHOLD)
		{
			HansDieter.StopRobot();

			// looping rotate left
			// continue forward
		}

		return 1488;
	}
}
