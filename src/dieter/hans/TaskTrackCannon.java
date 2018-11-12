package dieter.hans;

public class TaskTrackCannon extends TrackTask {

	public int runTrack() {
		HansDieter.M_CAN.setSpeed(100);
		HansDieter.M_CAN.rotate(300);
		HansDieter.M_CAN.setSpeed(180);
		HansDieter.M_CAN.rotate(-300);
		
		return -1;
	}
	
}
