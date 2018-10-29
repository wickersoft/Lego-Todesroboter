package dieter.hans;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class MotorController {
	
	private static DifferentialPilot pilot = new DifferentialPilot(8, 11, Motor.B, Motor.A);
	
	public MotorController() {
		setSpeedTravel();
		pilot.setAcceleration(60);
	}
	
	public void setSpeedTravel() {
		pilot.setTravelSpeed(40);
	}
	
	public void setSpeedLine() {
		pilot.setTravelSpeed(30);
	}
	
	public void setSpeedBridge() {
		pilot.setTravelSpeed(20);
	}
	
	public void steer(int angle) {
		pilot.stop();
		pilot.rotate(angle);
	}
	
	public void steerRight() {
		pilot.stop();
		pilot.rotate(-90);
	}
	
	public void steerLeft() {
		pilot.stop();
		pilot.rotate(90);
	}
	
	public void travelForward(double distance) {
		pilot.travel(distance);
	}
	
	public boolean isMoving() {
		return pilot.isMoving();
	}
	
	public void stop() {
		pilot.stop();
	}
	
}
