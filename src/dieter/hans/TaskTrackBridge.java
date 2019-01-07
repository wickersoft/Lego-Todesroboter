package dieter.hans;

import lejos.hardware.lcd.LCD;

public class TaskTrackBridge extends TrackTask {
	
	float[] touchValue = new float[1];
	float[] ultValue = new float[3];
	float[] gyrValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	double anxiety = 1;
	double zeroAngle = 0;
	
	public void enter() {
		HansDieter.M_ULT.rotateTo(95);
		MotorController.setSpeed(0.8);
		sleep(2500);
		//HansDieter.S_ANG.fetchSample(gyrValue, 0);
		//zeroAngle = gyrValue[0];
	}
	
	@Override
	public int runTrack() {
		HansDieter.S_TCH.fetchSample(touchValue, 0);
		
		
//		MotorController.setSpeed(0.6);
		HansDieter.S_DST.fetchSample(ultValue, 0);
		LCD.drawString("" + ultValue[0], 0, 0);
		
		
		if(ultValue[0] > 0.1) {
			MotorController.setSpeed(0.35);
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
			MotorController.setSpeed(0.35);
			MotorController.setTurnSpeed(0.4);
			ultValue[0] = 1;
		}
		
		
		
		double steer = pid.tick(ultValue[0]);
		
		if (touchValue[0] == 1.0) {
			HansDieter.M_ULT.rotateTo(0);
			MotorController.setTurnSpeed(0);
			MotorController.setSpeed(-0.5);
			sleep(600);
			MotorController.setSpeed(0);
			
			MotorController.steerRight(100);
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
