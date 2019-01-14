package dieter.hans;

public class MotorController {

	private static final int max_speed = 680;
	private static int linearSpeed = 0;
	private static int turnSpeed = 0;
	private static int leftSpeed = 0;
	private static int rightSpeed = 0;

	public static void setMotorSpeeds() {
		leftSpeed = linearSpeed - turnSpeed;
		rightSpeed = linearSpeed + turnSpeed;
		if (leftSpeed >= 0) {
			HansDieter.M_L.setSpeed(leftSpeed);
			HansDieter.M_L.backward();
		} else {
			HansDieter.M_L.setSpeed(-leftSpeed);
			HansDieter.M_L.forward();
		}
		if (rightSpeed >= 0) {
			HansDieter.M_R.setSpeed(rightSpeed);
			HansDieter.M_R.backward();
		} else {
			HansDieter.M_R.setSpeed(-rightSpeed);
			HansDieter.M_R.forward();
		}
	}

	public static void setSpeed(double s) {
		linearSpeed = (int) (s * max_speed);
		setMotorSpeeds();
	}

	public static void setTurnSpeed(double s) {
		turnSpeed = (int) (s * max_speed);
		setMotorSpeeds();
	}

	public static void steerLeft(int angle) {
		HansDieter.M_L.setSpeed(360);
		HansDieter.M_R.setSpeed(360);
		HansDieter.M_L.rotate(angle, true);
		HansDieter.M_R.rotate(-angle, true);
		try {
			Thread.sleep(1250);
		} catch (Exception ex) {
		}
	}

	public static void steerLeft() {
		steerLeft(400);
	}

	public static void steerRight(int angle) {
		steerLeft(-angle);
	}

	public static void steerRight() {
		steerRight(400);
	}
	
	public static void travelForward1(int distance){
        travelForward1(distance, 0.9);
    }

	public static void travelForward1(int distance, double speed){
		setTurnSpeed(0);
		setSpeed(speed);
		try {
			Thread.sleep(distance);
		} catch (InterruptedException ex) {
		}
	}


	public static void travelForward(int distance) {
		stop();
		HansDieter.M_L.setSpeed(linearSpeed);
		HansDieter.M_R.setSpeed(linearSpeed);
		HansDieter.M_L.rotate(distance, true);
		HansDieter.M_R.rotate(distance, true);
		try {
			Thread.sleep(1250);
		} catch (Exception ex) {
		}
		setMotorSpeeds();
	}

	public static void travelBackward(int distance) {
		travelForward(-distance);
	}

	public static void stop() {
		HansDieter.M_L.stop();
		HansDieter.M_R.stop();
	}
}
