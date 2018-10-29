package dieter.hans;

import lejos.hardware.Button;

public abstract class TrackTask extends RobotTask {

	@Override
	public final RobotState run() {
		if((Button.getButtons() & Button.ID_ENTER) != 0){
			return RobotState.MENU;
		}
		return runTrack();
	}
	
	public abstract RobotState runTrack();

}
