package dieter.hans;

public class TaskTrackBridge extends TrackTask {
	
	private MotorController mc = new MotorController();
	
	@Override
	public int runTrack() {
		init();
		start();
		top();
		down();
		return 3;
	}
	
	private void init() {
		mc.setSpeedTravel();
		while (checkForColorChange() == 0) {
			mc.travelForward(2);
		}
	}
	
	private void start() {
		mc.setSpeedBridge();
		while (checkForColorChange() == 0) {
			mc.travelForward(2);
		}
		
	}
	
	private void top() {
		mc.setSpeedTravel();
		mc.steerLeft(90);
		int distance = 50;
		while (distance >= 0 && checkForColorChange() == 0) {
			mc.travelForward(5);
			distance -= 5;
		}
		mc.steerLeft(90);
	}
	
	private void down() {
		mc.setSpeedBridge();
		while (checkForColorChange() == 0) {
			mc.travelForward(2);
		}
	}
	
	private int checkForColorChange() {
		while (true) {
			float[] rgbValues = new float[3];
			HansDieter.S_RGB.fetchSample(rgbValues, 0);
			if (rgbValues[0] == 0.02 && rgbValues[1] == 0.02 && rgbValues[2] == 0.01) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	

}
