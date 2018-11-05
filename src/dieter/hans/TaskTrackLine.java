package dieter.hans;

import lejos.hardware.sensor.EV3ColorSensor;

public class TaskTrackLine extends TrackTask {
	private final PID pid = new PID(-1, 0, 0);
	float[] lightValue = new float[3];
	
	double anxiety = 1;

	@Override
	public int runTrack() {
		try {
			HansDieter.S_RGB.fetchSample(lightValue, 0);
			double light = LightSensorConstants.getShit(lightValue);
			double steer = pid.tick(light);
			
			if(Math.abs(steer) > anxiety) {
				anxiety = Math.abs(steer);
			} else {
				anxiety *= 0.99;
			}
			
			System.out.printf("%.2f\n", anxiety);
			System.out.printf("%.2f\n", MotorController.getSpeed());
			
			
			MotorController.setSpeed((int) (100 * (1 - 0.75 * anxiety)));
			MotorController.steer(steer);
			
			Thread.sleep(10);
			// MotorController.travelForward(-1);
			//MotorController.stop();
		} catch (Exception ex) {
		}
		return 0;
	}
}