package dieter.hans;


public class TaskTrackBridge extends TrackTask {
	
	private MotorController mc = new MotorController();
	
	@Override
	public int runTrack() {
		init();
		
		return 5;
	}
	
	private void init() {
		mc.setSpeedBridge();
		mc.travelForward(10);
	}
	
	private void start() {
		
	}
	
	private void top() {
		
	}
	
	private void down() {
		
	}
	
	private int checkForColorChange() {
		return 0;
	}
	

}
