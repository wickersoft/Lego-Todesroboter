package dieter.hans;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class MotorController {
	
	private static final DifferentialPilot pilot;
	
	static
	{
		pilot = new DifferentialPilot(8, 11, HansDieter.M_L, HansDieter.M_R);
		setSpeedTravel();
		pilot.setAcceleration(60);
	}
	
	public static void setSpeedTravel() {
		pilot.setTravelSpeed(40);
	}
	
	public static void setSpeedLine() {
		pilot.setTravelSpeed(30);
	}
	
	public static void setSpeedBridge() {
		pilot.setTravelSpeed(20);
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
	
	public static void travelForward(double distance) {
		pilot.travel(distance);
	}
	
	public static boolean isMoving() {
		return pilot.isMoving();
	}
	
	public static void stop() {
		pilot.stop();
	}
	
}
