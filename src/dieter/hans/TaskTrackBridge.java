package dieter.hans;

public class TaskTrackBridge extends TrackTask {
	
	private MotorController mc = new MotorController();
	
	@Override
	public int runTrack() {
		mc.setSpeedBridge();
		mc.travelForward(10);
		return 0;
	}

}
