package dieter.hans;

public class TaskTrackTransition1 extends TrackTask {

	private static final int BLUE = 2;
	private final PID pid = new PID(0.75, 0, 0.35);
	private float[] colorValue = new float[3];
	float[] touchValue = new float[1];
	int tick = 0;
	boolean suck = false;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(0);
		HansDieter.M_ULT.stop();
		MotorController.stop();
		MotorController.travelForward1(500, 1);
	}
	
	@Override
	public int runTrack() {
		MotorController.setSpeed(0.4);
		
		if (touchValue[0] == 1) {
			MotorController.travelForward1(500, -0.5);
			MotorController.steerRight(150);
			MotorController.travelForward1(200, 0.3);
			MotorController.steerLeft(150);
		}
		

		HansDieter.S_RGB.fetchSample(colorValue, 0);
		if (LightSensorConstants.getCustomColor(colorValue) == 6 && !suck) {
			MotorController.steerLeft(200);
			MotorController.travelForward1(200);
			MotorController.steerLeft();
			suck = true;
			return 0;
		} else if (LightSensorConstants.getCustomColor(colorValue) == BLUE && suck) {
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
