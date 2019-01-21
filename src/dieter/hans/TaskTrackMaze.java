package dieter.hans;

public class TaskTrackMaze extends TrackTask {
	private static final int FUCK = 14;
	private static final int BLACK = 7;
	private static final int RED = 0;
	private static final int BLUE = 2;
	
	public static final float DISTANCE_EPSILON = .1f;
	private PID pid = new PID(-1, 0.5, 0);
	float[] lightValue = new float[3];
	float[] touchValue = new float[1];
	boolean blue_found = false;
	boolean red_found = false;

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

	public void enter() {
		HansDieter.M_ULT.rotateTo(0);
	}

	@Override
	public int runTrack() {
		MotorController.setTurnSpeed(0);
		MotorController.setSpeed(1);
		HansDieter.S_TCH.fetchSample(touchValue, 0);
		if (touchValue[0] == 1.0) {
			MotorController.setSpeed(0);
			MotorController.setTurnSpeed(0.5);
			MotorController.steerLeft(100);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(1);
			sleep(1000);
		}
		
		LightSensorConstants.getShit(lightValue);
		int colorId = LightSensorConstants.getCustomColor(lightValue);
		switch (colorId) {
		case FUCK:
		case BLACK:
			break;
		case RED:
			red_found = true;
			break;
		case BLUE:
			blue_found = true;
			break;
		}
		
		if(red_found && blue_found) {
			
			// OFFICIALLY FINAL WIN STATE
			
			
			return 0;
			
		}
		return 0;
	}
}