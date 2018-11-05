package dieter.hans;

import lejos.hardware.sensor.EV3ColorSensor;

public class TaskTrackLine extends TrackTask {
	private final PID pid = new PID(-1, 0, 0);
	float[] lightValue = new float[3];
	
	@Override
	public int runTrack() {
		HansDieter.S_RGB.fetchSample(lightValue, 0);
		double light = LightSensorConstants.getShit(lightValue);
		double steer = pid.tick(light);
		MotorController.steer(steer);
		return 0;
	}
}