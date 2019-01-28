package dieter.hans;

public class TaskTrackTransition1 extends TrackTask {

	private static final int BLUE = 2;
	private final PID pid = new PID(0.75, 0, 0.35);
	private float[] colorValue = new float[3];
	int tick = 0;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(0);
		HansDieter.M_ULT.stop();
		MotorController.travelForward1(800, 1);
		MotorController.steerLeft();
		MotorController.steerLeft(200);
	}
	
	@Override
	public int runTrack() {
		MotorController.setSpeed(0.4);
		

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
