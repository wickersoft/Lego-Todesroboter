package dieter.hans;


public class TaskTrackLine extends TrackTask {
	private final PID pid = new PID(-1, 0, 0);
	float[] lightValue = new float[3];
	float[] touchValue = new float[1];
	private int gapNumber = 0;

	int tcReference = Integer.MAX_VALUE;

	double anxiety = 1;

	public void enter() {
		gapNumber = 0;
		tcr();
	}

	@Override
	public int runTrack() {
		MotorController.setSpeed(1 - anxiety);
		try {
			HansDieter.S_RGB.fetchSample(lightValue, 0);
			HansDieter.S_TCH.fetchSample(touchValue, 0);

			double light = LightSensorConstants.getShit(lightValue);
			double steer = pid.tick(light);

			if (light < -0.9) {
				if (!honestSuchViech()) {
					if (++gapNumber >= 6) {
						return -1;
					} else {
						MotorController.setTurnSpeed(0);
						MotorController.setSpeed(0.8);
						sleep(3000);
					}
				}
			}
			
			if (touchValue[0] == 1.0) {
				MotorController.setTurnSpeed(0);
				MotorController.setSpeed(-0.5);
				sleep(1000);
				MotorController.setSpeed(0);

				MotorController.steerRight(400);
				MotorController.setTurnSpeed(0);
				MotorController.setSpeed(0.8);
				sleep(1000);
				
				MotorController.steerLeft(200);
				MotorController.setTurnSpeed(0);
				MotorController.setSpeed(0.8);
				sleep(2300);

				MotorController.steerLeft(500);
				MotorController.setTurnSpeed(0);
				MotorController.setSpeed(0.5);
				sleep(3000);

				return 0;
			}
			
			if (Math.abs(steer) > anxiety) {
				anxiety = Math.abs(steer);
			} else {
				anxiety *= 0.98;
			}

			MotorController.setTurnSpeed(0.3 * steer);
			Thread.sleep(10);
		} catch (Exception ex) {
		}
		return 0;
	}

	private boolean honestSuchViech() {
		MotorController.setSpeed(0);
		MotorController.setTurnSpeed(0.5);
		for (int i = 0; i < 75; i++) {
			HansDieter.S_RGB.fetchSample(lightValue, 0);
			double light = LightSensorConstants.getShit(lightValue);
			if (light > 0.2) {
				return true;
			}
			try {
				Thread.sleep(25);
			} catch (Exception ex) {
			}
		}
		MotorController.setSpeed(0);
		MotorController.setTurnSpeed(-0.5);
		for (int i = 0; i < 150; i++) {
			HansDieter.S_RGB.fetchSample(lightValue, 0);
			double light = LightSensorConstants.getShit(lightValue);
			if (light > 0.2) {
				return true;
			}
			try {
				Thread.sleep(25);
			} catch (Exception ex) {
			}
		}
		MotorController.setTurnSpeed(0.5);
		MotorController.setSpeed(0);
		for (int i = 0; i < 70; i++) {
			HansDieter.S_RGB.fetchSample(lightValue, 0);
			double light = LightSensorConstants.getShit(lightValue);
			try {
				Thread.sleep(26);
			} catch (Exception ex) {
			}
		}
		MotorController.stop();
		return false;
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception ex) {
		}
	}

	private int tc() {
		return HansDieter.M_L.getTachoCount() - tcReference;
	}

	private void tcr() {
		tcReference = HansDieter.M_L.getTachoCount();
	}
}