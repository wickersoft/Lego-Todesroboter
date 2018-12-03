package dieter.hans;

public class TaskTrackCannon extends TrackTask {

	public int runTrack() {
		HansDieter.M_CAN.setSpeed(100);
		HansDieter.M_CAN.rotate(100);
		HansDieter.M_CAN.setSpeed(100);
		HansDieter.M_CAN.rotate(-50);
		
		return -1;
	}
	
}
