package dieter.hans;

import lejos.hardware.Button;

public abstract class TrackTask extends RobotTask {

	@Override
	public final int run() {
		if((Button.getButtons() & Button.ID_ENTER) != 0){
			return -1;
		}
		return runTrack();
	}
	
	public abstract int runTrack();

	public void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception ex) {
		}
	}
}
