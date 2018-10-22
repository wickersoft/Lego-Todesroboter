package dieter.hans;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class HansDieter {

	public static final RegulatedMotor M_R = Motor.A;
	public static final RegulatedMotor M_L = Motor.B;
	public static final RegulatedMotor M_ULT = Motor.C;
	public static final RegulatedMotor M_CAN = Motor.D;
	public static final EV3ColorSensor S_LIT = new EV3ColorSensor(SensorPort.S1);
	public static final EV3TouchSensor S_BTN = new EV3TouchSensor(SensorPort.S2);
	public static final EV3UltrasonicSensor S_ULT = new EV3UltrasonicSensor(SensorPort.S3);
	public static final EV3GyroSensor S_GYR = new EV3GyroSensor(SensorPort.S4);
	
	
	public static void main(String[] args) {
		System.out.println("Hello Peter");
		Button.waitForAnyPress();
		M_R.setSpeed(50);
		M_L.setSpeed(50);
		Motor.A.backward();
		Motor.B.backward();
		Button.waitForAnyPress();
		System.out.println(Motor.A.getTachoCount());
		System.out.println(Motor.B.getTachoCount());
		Motor.A.setSpeed(0);
		Motor.B.setSpeed(0);
		Button.waitForAnyPress();
	}
}