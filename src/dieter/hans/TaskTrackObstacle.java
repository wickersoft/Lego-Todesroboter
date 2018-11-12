package dieter.hans;

public class TaskTrackObstacle extends TrackTask {

	private float[] distance = new float[1];
	
	
	@Override
	public int runTrack() {
		MotorController.steerLeft(10);
		MotorController.travelInf();
		do {
			HansDieter.S_DST.fetchSample(distance, 0);
		} while (distance[0] > 0.2);
		//MotorController.steerRight(100);
		//MotorController.travelForward(60);
		//MotorController.steerLeft(20);
		//MotorController.travelForward(20);
		MotorController.steerRight(40);
		MotorController.travelForward(60);
		MotorController.steerLeft(70);
		MotorController.travelForward(30);
		return 0;
	}


}
