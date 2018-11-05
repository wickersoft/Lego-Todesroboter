package dieter.hans;

import lejos.robotics.navigation.DifferentialPilot;

public class MotorController {

	private static final DifferentialPilot pilot;

	static {
		pilot = new DifferentialPilot(8, 11, HansDieter.M_L, HansDieter.M_R);
		setSpeedTravel();
		pilot.setAcceleration(100);
	}

	public static void setSpeed(int speed) {
		pilot.setTravelSpeed(speed);
	}
	
	public static void setSpeedTravel() {
		pilot.setTravelSpeed(75);
	}

	public static void setSpeedLine() {
		pilot.setTravelSpeed(50);
	}

	public static void setSpeedBridge() {
		pilot.setTravelSpeed(30);
	}
	
	public static double getSpeed() {
		return pilot.getTravelSpeed();
	}

	public static void steerLeft(int angle) {
		pilot.stop();
		pilot.rotate(angle);
	}

	public static void steerRight() {
		pilot.stop();
		pilot.rotate(-90);
	}

	public static void steerLeft() {
		pilot.stop();
		pilot.rotate(90);
	}

	public static void steer(double value) {
		int leftSpeed = (int) (pilot.getTravelSpeed() * (1 - value));
		int rightSpeed = (int) (pilot.getTravelSpeed() * (1 + value));
		HansDieter.M_L.setSpeed( Math.abs(leftSpeed));
		HansDieter.M_R.setSpeed( Math.abs(rightSpeed));
		if (leftSpeed < 0) {
			HansDieter.M_L.forward();
		} else {
			HansDieter.M_L.backward();
		}
		if (rightSpeed < 0) {
			HansDieter.M_R.forward();
		} else {
			HansDieter.M_R.backward();
		}
	}

	public static void travelInf() {
		HansDieter.M_L.backward();
		HansDieter.M_R.backward();
	}

	public static void travelForward(double distance) {
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
