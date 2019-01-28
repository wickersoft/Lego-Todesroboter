package dieter.hans;

import lejos.hardware.Sound;

public class TaskTrackMaze extends TrackTask {
	private static final int FUCK = 14;
	private static final int BLACK = 7;
	private static final int RED = 0;
	private static final int WHITE = 6;
	
	public static final float DISTANCE_EPSILON = .1f;
	private PID pid = new PID(-1, 0.5, 0);
	float[] lightValue = new float[3];
	float[] touchValue = new float[1];
	float[] touchValue1 = new float[1];
	boolean white_found = false;
	boolean red_found = false;

	

	public void enter() {
		HansDieter.M_ULT.rotateTo(0);
	}

	@Override
	public int runTrack() {
		MotorController.setTurnSpeed(0);
		MotorController.setSpeed(1);
		HansDieter.S_TCH.fetchSample(touchValue, 0);
		HansDieter.S_TCH2.fetchSample(touchValue1, 0);
		if (touchValue[0] == 1.0 || touchValue1[0] == 1.0) {
			MotorController.travelForward1(200, -0.7);
			MotorController.setSpeed(0);
			MotorController.setTurnSpeed(0.5);
			sleep(1500 + HansDieter.RND.nextInt() % 1000);
		}
		
		HansDieter.S_RGB.fetchSample(lightValue, 0);
		int colorId = LightSensorConstants.getCustomColor(lightValue);
		switch (colorId) {
		case FUCK:
		case BLACK:
			break;
		case RED:
			red_found = true;
			Sound.beep();
			System.out.println("RED FOUND");
			break;
		case WHITE:
			System.out.println("WHITE FOUND");
			Sound.beep();
			white_found = true;
			break;
		}
		
		if(red_found && white_found) {
			
			// OFFICIALLY FINAL WIN STATE
			
			
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