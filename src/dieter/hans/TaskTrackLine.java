package dieter.hans;

import lejos.hardware.sensor.EV3ColorSensor;

public class TaskTrackLine extends TrackTask
{
	public static final float L_THRESHOLD = 0.4f;
	public static final float U_THRESHOLD = 0.6f;
	
	@Override
	public int runTrack()
	{
		float[] frgb = new float[3];
		
		HansDieter.S_RGB.fetchSample(frgb, 0);

		double[] rgb = new double[] { frgb[0], frgb[1], frgb[2] };
		double gray = LightSensorConstants.getGrayscale(rgb);
		
		// 0 -> black
		// 1 -> white
		
		if (gray < L_THRESHOLD)
		{
			MotorController.steerLeft(10);

			// looping rotate right
			// continue forward
		}
		else if (gray > U_THRESHOLD)
		{
			MotorController.steerLeft(-10);

			// looping rotate left
			// continue forward
		}

		return 1488;
	}
}
