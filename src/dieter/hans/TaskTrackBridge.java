package dieter.hans;

import lejos.hardware.lcd.LCD;

public class TaskTrackBridge extends TrackTask {
	
	float[] touchValue = new float[2];
	float[] ultValue = new float[3];
	float[] gyrValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	double anxiety = 1;
	double zeroAngle = 0;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(90);
		MotorController.setTurnSpeed(0);
		MotorController.setSpeed(0.8);
		sleep(2500);
		//HansDieter.S_ANG.fetchSample(gyrValue, 0);
		//zeroAngle = gyrValue[0];
	}
	
	@Override
	public int runTrack() {
		HansDieter.S_TCH.fetchSample(touchValue, 0);
		HansDieter.S_TCH2.fetchSample(touchValue, 1);
		
		
//		MotorController.setSpeed(0.6);
		HansDieter.S_DST.fetchSample(ultValue, 0);
		LCD.drawString("" + ultValue[0], 0, 0);
		
		
		if(ultValue[0] > 0.1) {
			MotorController.setSpeed(0.3);
			MotorController.setTurnSpeed(-0.3);
			
			ultValue[0] = -1;
		} else if (ultValue[0] == Float.MAX_VALUE) {
			LCD.drawString("shittysolutions", 0, 0);
			HansDieter.M_ULT.rotateTo(0);
			MotorController.steerRight(50);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(0.8);
			sleep(2000);
			return -1;
		} else {
			MotorController.setSpeed(0.3);
			MotorController.setTurnSpeed(0.2);
			ultValue[0] = 1;
		}
		
		
		
		double steer = pid.tick(ultValue[0]);
		
		if (touchValue[0] == 1.0 || touchValue[1] == 1.0) {
			HansDieter.M_ULT.rotateTo(0);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(-0.5);
			sleep(700);
			MotorController.setSpeed(0);
			
			MotorController.steerRight(200);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(0.8);
			sleep(1000);
			return -1;
		}
		

		if (Math.abs(steer) > anxiety) {
			anxiety = Math.abs(steer);
		} else {
			anxiety *= 0.97;
		}
		//MotorController.setTurnSpeed(-0.5 * steer);
		sleep(10);
		return 0;
		
		
	}	

}
