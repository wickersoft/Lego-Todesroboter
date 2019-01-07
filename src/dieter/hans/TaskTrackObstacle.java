package dieter.hans;

import lejos.hardware.lcd.LCD;

public class TaskTrackObstacle extends TrackTask {

	float[] ultValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	boolean box_found = false;
	public void enter() {
		MotorController.steerLeft();
		MotorController.steerLeft();
		MotorController.travelForward(2000);
		HansDieter.M_ULT.rotateTo(20);
	}
	
	
	@Override
	public int runTrack() {
		HansDieter.S_DST.fetchSample(ultValue, 0);
		LCD.drawString("" + ultValue[0], 0, 0);
		
		if(ultValue[0] > 0.1 && !box_found) {
			MotorController.setSpeed(-0.3);
			MotorController.setTurnSpeed(0);
			sleep(100);
			return 0;
		} else if (ultValue[0] < 0.05 && !box_found) {
			box_found = true;
			MotorController.stop();
			return 0;
		}
		
		if (box_found) {
			MotorController.travelForward(1000);
			MotorController.steerRight();
			MotorController.steerRight(200);
			MotorController.setSpeed(-0.8);
			sleep(5000);
			return -1;
		}
		
//		else { 
//			MotorController.setSpeed(-0.8);
//			sleep(1000);
//			MotorController.stop();
//			MotorController.setSpeed(0);
//			MotorController.setTurnSpeed(0.5);
//			sleep(30);
//			MotorController.stop();
//			
//		}
		
		//while(true) {
//		HansDieter.M_L.resetTachoCount();
			
//			HansDieter.S_DST.fetchSample(distance, 0);
//			if(distance[0] < 0.15) {
//				int tacho = HansDieter.M_L.getTachoCount();
//				
//				if(tacho < -400) {
//					MotorController.stop();
//					r(45);
//					f(-15);
//					l(45);
//					f(-40);
//					r(130);
//					f(100);
//					break;
//				}
//				
//			}
			
		//}
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
