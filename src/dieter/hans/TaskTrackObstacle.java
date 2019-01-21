package dieter.hans;

import lejos.hardware.lcd.LCD;

public class TaskTrackObstacle extends TrackTask {

	float[] ultValue = new float[3];
	PID pid = new PID(-1, 0, 0);
	boolean bridge = true;
	boolean box_found = false;
	boolean suck = false;
	boolean far = false;
	float[] distance = new float[500];
	int index = 0;
	public void enter() {
		MotorController.steerLeft();
		MotorController.steerLeft();
		HansDieter.M_ULT.rotateTo(20);
		HansDieter.M_ULT.stop();
		MotorController.steerLeft(200);
		MotorController.travelForward1(500, -0.3);
	}
	
	
	@Override
	public int runTrack() {
		HansDieter.S_DST.fetchSample(ultValue, 0);
		LCD.drawString("" + ultValue[0], 0, 0);
		
		while (bridge) {
			if (ultValue[0] > 0.6) {
				bridge = false;
				System.out.println("Brücke vorbei");
			}
			MotorController.setTurnSpeed(0);
			MotorController.travelForward1(10, -0.3);
			return 0;
			
		}
		
		if(ultValue[0] > 0.27 && !box_found && !suck && !bridge) {
			MotorController.setTurnSpeed(0);
			MotorController.travelForward1(10, -0.3);
			return 0;
		} else if (ultValue[0] <= 0.15 && !box_found && !suck && !bridge) {
			suck = true;
			LCD.drawString("suck: " + suck, 0, 0);
			sleep(100);
			return 0;
		} else if (ultValue[0] <= 0.15 && !box_found && suck && !bridge) {
			box_found = true;
			LCD.drawString("box found:" + box_found, 0, 0);
			sleep(100);
			return 0;
		} else if (ultValue[0] < 0.28 && ultValue[0] > 0.15 && !box_found && !suck && !bridge) {
			suck = true;
			sleep(100);
			return 0;
		} else if (ultValue[0] < 0.28 && ultValue[0] > 0.15 && !box_found && suck && !bridge) {
			box_found = true;
			far = true;
			sleep(100);
			return 0;
		}
		
		
		
		if (box_found && suck /*&& index >= 5*/) {
			System.out.println("Box schieben");

			if (!far) {
				MotorController.travelForward1(2500, -0.3);
				MotorController.steerRight();
				MotorController.steerRight(230);
				MotorController.travelForward1(2000, -0.8);
				MotorController.steerRight(100);
				MotorController.travelForward1(5000, -0.8);
				suck = false;
				box_found = false;
				bridge = true;
				far = false;
				return -1;
			} else {
				MotorController.travelForward1(2600, -0.3);
				MotorController.steerRight();
				MotorController.steerRight(200);
				MotorController.travelForward1(2200, -1);
				MotorController.steerRight(200);
				MotorController.travelForward1(50, -1);
				MotorController.travelForward1(4000, -1);
				suck = false;
				box_found = false;
				bridge = true;
				far = false;
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
