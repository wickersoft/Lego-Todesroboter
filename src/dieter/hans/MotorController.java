package dieter.hans;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class MotorController {

	static Wheel wheel_left = WheeledChassis.modelWheel(Motor.B, 8).offset(-5.5);
	static Wheel wheel_right = WheeledChassis.modelWheel(Motor.A, 8).offset(5.5);
	static Chassis chassis = new WheeledChassis(new Wheel[]{wheel_left, wheel_right}, WheeledChassis.TYPE_DIFFERENTIAL);
	private static MovePilot pilot = new MovePilot(chassis);
	
	public MotorController() {
		setSpeedTravel();
		pilot.setLinearAcceleration(60);
	}
	
	public void setSpeedTravel() {
		pilot.setLinearSpeed(40);
	}
	
	public void setSpeedLine() {
		pilot.setLinearSpeed(30);
	}
	
	public void setSpeedBridge() {
		pilot.setLinearSpeed(20);
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
