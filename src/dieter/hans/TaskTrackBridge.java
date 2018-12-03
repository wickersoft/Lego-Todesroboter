package dieter.hans;


public class TaskTrackBridge extends TrackTask {
	
	float[] ultValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	double anxiety = 1;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(-70);
	}
	
	@Override
	public int runTrack() {
		MotorController.setSpeed(1 - 0.5 * anxiety);
		HansDieter.S_DST.fetchSample(ultValue, 0);
		System.out.println(ultValue[0]);
		if(ultValue[0] > 0.08) {
			ultValue[0] = -1;
		} else {
			ultValue[0] = 1;
		}
		double steer = pid.tick(ultValue[0]);
		
		
		
		MotorController.setTurnSpeed(steer);
		
		if (Math.abs(steer) > anxiety) {
			anxiety = Math.abs(steer);
		} else {
			anxiety *= 0.98;
		}
		MotorController.setTurnSpeed(0.3 * steer);
		sleep(10);
		return 0;
	}	
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception ex) {
		}
	}

}
