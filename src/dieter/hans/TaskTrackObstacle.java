package dieter.hans;

public class TaskTrackObstacle extends TrackTask {

	private float[] distance = new float[1];
	
	
	@Override
	public int runTrack() {
		HansDieter.M_L.resetTachoCount();
		MotorController.setSpeed(0.5);
    	
		while(true) {
			HansDieter.S_DST.fetchSample(distance, 0);
			if(distance[0] < 0.15) {
				int tacho = HansDieter.M_L.getTachoCount();
				
				if(tacho < -400) {
					MotorController.stop();
					r(45);
					f(-15);
					l(45);
					f(-40);
					r(130);
					f(100);
					break;
				}
				
			}
			
			
		}
		
		//MotorController.steerRight(100);
		//MotorController.travelForward(60);
		//MotorController.steerLeft(20);
		//MotorController.travelForward(20);
		return 0;
	}
	
	private final void r(int angle) {
		MotorController.steerRight(angle);
	}

	private final void l(int angle) {
		MotorController.steerLeft(angle);
	}

	private final void f(double distance) {
		MotorController.travelForward((int) distance);
	}

}
