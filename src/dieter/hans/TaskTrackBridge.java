package dieter.hans;


public class TaskTrackBridge extends TrackTask {
	
	@Override
	public int runTrack() {
		init();
		start();
		top();
		down();
		return 3;
	}
	
	private void init() {
		MotorController.setSpeedTravel();
		while (checkForColorChange() == 0) {
			MotorController.travelForward(2);
		}
	}
	
	private void start() {
		MotorController.setSpeedBridge();
		while (checkForColorChange() == 0) {
			MotorController.travelForward(2);
		}
		
	}
	
	private void top() {
		MotorController.setSpeedTravel();
		MotorController.steerLeft(90);
		int distance = 50;
		while (distance >= 0 && checkForColorChange() == 0) {
			MotorController.travelForward(5);
			distance -= 5;
		}
		MotorController.steerLeft(90);
	}
	
	private void down() {
		MotorController.setSpeedBridge();
		while (checkForColorChange() == 0) {
			MotorController.travelForward(2);
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
