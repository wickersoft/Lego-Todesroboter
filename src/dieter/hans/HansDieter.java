package dieter.hans;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;


public class HansDieter {
	public static final RegulatedMotor M_R = Motor.A;
	public static final RegulatedMotor M_L = Motor.B;
	public static final RegulatedMotor M_ULT = Motor.C;
	public static final RegulatedMotor M_CAN = Motor.D;
	public static final EV3TouchSensor S_BTN = new EV3TouchSensor(SensorPort.S2);
	private static final EV3ColorSensor S_LIT = new EV3ColorSensor(SensorPort.S1);
	public static final EV3UltrasonicSensor S_ULT = new EV3UltrasonicSensor(SensorPort.S3);
	public static final EV3GyroSensor S_GYR = new EV3GyroSensor(SensorPort.S4);
	public static final SensorMode S_RGB = S_LIT.getRGBMode();
	public static final SampleProvider S_DST = S_ULT.getDistanceMode();
	//public static final SensorMode S_COL = S_LIT.getColorIDMode();

	
	public static final int LIN_SPD = 25;
	public static final int TURN_PROP = 1;
	public static float[] rgbValues = new float[3];


	public static void main(String[] args) {
		StateMachine sm = new StateMachine();
		sm.init();
		while(sm.tick() == 0) {
		}
	}
	
	
	
	
	
	public static void lightSensorDebug(String[] args) {

		S_LIT.setFloodlight(Color.WHITE);
		S_LIT.setFloodlight(true);

		while (true) {
			int shit = S_LIT.getColorID();
			String colorName = LightSensorConstants.colorIds.get(shit);
			if(colorName == null) {
				colorName = "FUCK";
			}
			
			S_RGB.fetchSample(rgbValues, 0);
			System.out.println("R: " + rgbValues[0]);
			System.out.println("G: " + rgbValues[1]);
			System.out.println("B: " + rgbValues[2]);
			System.out.println("C: " + colorName);
			System.out.println("");
			if(Button.waitForAnyPress(200) == Button.ID_DOWN) {
				return;
			};	
		}
	}
}