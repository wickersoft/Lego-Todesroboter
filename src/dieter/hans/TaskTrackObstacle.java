package dieter.hans;

public class TaskTrackObstacle extends TrackTask
{
	private float[] distance = new float[1];
	
	
	@Override
	public int runTrack() {
		MotorController.steerLeft(10);
		MotorController.travelInf();
		
		do
			HansDieter.S_DST.fetchSample(distance, 0);
		while (distance[0] > 10);
		
		MotorController.steerRight(120);
		
		return 0;
	}
}
