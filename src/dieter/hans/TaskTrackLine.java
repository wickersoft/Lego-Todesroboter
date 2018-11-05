package dieter.hans;

public class TaskTrackLine extends TrackTask {

	private final PID pid = new PID(-1, 0, 0);
	
	
	@Override
	public int runTrack() {
		double light = LightSensorConstants.getShit();	
		double steer = pid.tick(light);
		MotorController.steer(steer);
		return 0;
	}


}
