package dieter.hans;


public class TaskTrackBridge extends TrackTask {
	
	float[] touchValue = new float[1];
	float[] ultValue = new float[3];
	float[] gyrValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	double anxiety = 1;
	double zeroAngle = 0;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(85);
		MotorController.setSpeed(0.8);
		sleep(1000);
		//HansDieter.S_ANG.fetchSample(gyrValue, 0);
		//zeroAngle = gyrValue[0];
	}
	
	@Override
	public int runTrack() {
		HansDieter.S_TCH.fetchSample(touchValue, 0);
		if (touchValue[0] == 1.0) {
			MotorController.setSpeed(-0.3);
			sleep(1500);
			MotorController.setSpeed(0);
			MotorController.setTurnSpeed(0.3);
			sleep(1000);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(0.5);
		}
		
		
//		MotorController.setSpeed(0.6);
		HansDieter.S_DST.fetchSample(ultValue, 0);
		HansDieter.S_ANG.fetchSample(gyrValue, 0);

		System.out.println("");
		System.out.println(gyrValue[0] );
		
		
		if(ultValue[0] > 0.1) {
			MotorController.setSpeed(0.2);
			MotorController.setTurnSpeed(-0.5);
			
			ultValue[0] = -1;
		} else {
			MotorController.setSpeed(0.6);
			MotorController.setTurnSpeed(0.3);
			ultValue[0] = 1;
		}
		
		
		
		double steer = pid.tick(ultValue[0]);
		
		if (touchValue[0] == 1.0) {
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(-0.5);
			sleep(500);
			MotorController.setSpeed(0);
			
			MotorController.steerRight(200);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(0.8);
			sleep(250);
			
			MotorController.steerLeft(200);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(0.6);
			sleep(500);
			
		}
		
		
		

		if (Math.abs(steer) > anxiety) {
			anxiety = Math.abs(steer);
		} else {
			anxiety *= 0.98;
		}
		//MotorController.setTurnSpeed(-0.5 * steer);
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
