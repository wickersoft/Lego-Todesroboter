package dieter.hans;

import java.io.File;

import lejos.hardware.Sound;

public class TaskTrackTransition extends TrackTask {

	private static final int BLUE = 2;
	private final PID pid = new PID(0.75, 0, 0.35);
	private float[] colorValue = new float[3];
	int tick = 0;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(10);
		HansDieter.M_ULT.stop();
		MotorController.travelForward1(600, 0.8);
		MotorController.steerRight(300);
	}
	
	@Override
	public int runTrack() {
		MotorController.setSpeed(0.3);
		float distance = 100 * getDistance();
		if(distance == Float.POSITIVE_INFINITY) {
			distance = 0;
		}
		
		float normDistance = (distance - 5) / 12;
		double steer = pid.tick(normDistance);
		MotorController.setTurnSpeed(0.5 * steer);

		if (++tick % 100 == 0) {
			System.out.println("D: " + distance);
			System.out.println("N: " + normDistance);
			System.out.println("S: " + steer);
		}

		HansDieter.S_RGB.fetchSample(colorValue, 0);
		if (LightSensorConstants.getCustomColor(colorValue) == BLUE) {
			System.out.println("Drain the swamp");
			return -1;
		}
		return 0;
	}

	public float getDistance() {
		final int count = 10;

		float[] sdist = new float[1];
		float dist = 0;

		for (int i = 0; i < count; ++i) {
			HansDieter.S_DST.fetchSample(sdist, 0);
			dist += sdist[0];
		}

		return dist / (float) count;
	}

}
