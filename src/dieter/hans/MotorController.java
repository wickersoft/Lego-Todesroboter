package dieter.hans;

import lejos.robotics.navigation.DifferentialPilot;

public class MotorController {

	private static final DifferentialPilot pilot;
	private static int speed = 60;

	static {
		pilot = new DifferentialPilot(8, 11, HansDieter.M_L, HansDieter.M_R);
		pilot.setAcceleration(100);
	}

	public static void setSpeed(int s) {
		speed = s;
		pilot.setTravelSpeed(s);
	}
	
	public static double getSpeed() {
		return speed;
	}

	public static void steerLeft(int angle) {
		pilot.stop();
		pilot.rotate(angle);
	}

	public static void steerLeft() {
		steerLeft(90);
	}
	
	public static void steerRight(int angle) {
		pilot.stop();
		pilot.rotate(-angle);
	}
	
	public static void steerRight() {
		steerRight(90);
	}

	public static void steer(double value) {
		int leftSpeed = (int) (speed * (0.5 + value));
		int rightSpeed = (int) (speed * (0.5 - value));
		HansDieter.M_L.setSpeed( Math.abs(leftSpeed));
		HansDieter.M_R.setSpeed( Math.abs(rightSpeed));
		if (leftSpeed < 0) {
			HansDieter.M_L.backward();
		} else {
			HansDieter.M_L.forward();
		}
		if (rightSpeed < 0) {
			HansDieter.M_R.backward();
		} else {
			HansDieter.M_R.forward();
		}
	}

	public static void travelInf() {
		HansDieter.M_L.backward();
		HansDieter.M_R.backward();
	}

	public static void travelForward(double distance) {
		pilot.travel(distance);
	}
	
	public static void travelBackward(int distance) {
		HansDieter.M_L.forward();
		HansDieter.M_R.forward();
		HansDieter.M_R.rotate(distance);
		HansDieter.M_L.rotate(distance);
		pilot.travel(distance);
	}

	public static boolean isMoving() {
		return pilot.isMoving();
	}

	public static void stop() {
		HansDieter.M_L.stop();
		HansDieter.M_R.stop();
		pilot.stop();
	}

}
