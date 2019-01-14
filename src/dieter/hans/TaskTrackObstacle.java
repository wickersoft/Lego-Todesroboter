package dieter.hans;

import lejos.hardware.lcd.LCD;

public class TaskTrackObstacle extends TrackTask {

	float[] ultValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	boolean box_found = false;
	boolean suck = false;
	float[] distance = new float[500];
	int index = 0;
	public void enter() {
		MotorController.steerLeft();
		MotorController.steerLeft();
		MotorController.steerLeft(200);
		HansDieter.M_ULT.rotateTo(20);
	}
	
	
	@Override
	public int runTrack() {
		HansDieter.S_DST.fetchSample(ultValue, 0);
		LCD.drawString("" + ultValue[0], 0, 0);
		
		
		if(ultValue[0] > 0.2 && !box_found && !suck) {
			MotorController.setSpeed(-0.3);
			MotorController.setTurnSpeed(0);
			sleep(20);
			return 0;
		} else if (ultValue[0] < 0.2 && !box_found && !suck) {
			suck = true;
			LCD.drawString("suck: " + suck, 0, 0);
			distance[index] = ultValue[0];
			index++;
			MotorController.stop();
			return 0;
		} else if (ultValue[0] < 0.2 && !box_found && suck) {
			box_found = true;
			LCD.drawString("box found:" + box_found, 0, 0);
			MotorController.stop();
			return 0;
		}
		
		
		
		if (box_found && suck && index >= 5) {
			double average = 0;
			for (int j = 0; j <= 5; j++) {
				average += distance[index - j];
			}
			average /= 5;
			LCD.clear();
			LCD.drawString("Average: " + average, 0, 0);
			if (average < 0.2) {
				MotorController.travelForward(2000);
				MotorController.steerRight();
				MotorController.steerRight(200);
				MotorController.setSpeed(-0.8);
				sleep(4000);
				return -1;
			}
		}
		 
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
