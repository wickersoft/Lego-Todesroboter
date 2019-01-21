package dieter.hans;
public class TaskTrackMaze extends TrackTask {
	public static final float __distance_ε = .1f;
 	float[] lightValue = new float[3];
 	float[] touchValue = new float[1];
 	boolean fucking_flag = true;
 	boolean found_red = false;
	
public float getDistance() {
	final int count = 10;
	
	float[] sdist = new float[1];
	float dist = 0;
	
	for (int i = 0; i < count; ++i) {
		HansDieter.S_DST.fetchSample(sdist, 0);
	
	   dist += sdist[0];
	  }
	
	  return dist / (float) count;
	 }
	
public void enter() {
			HansDieter.M_ULT.rotateTo(0);
		}
	 
	 
	 @Override
public int runTrack() {
		 double light = LightSensorConstants.getShit(lightValue);
		 float ref_dist = getDistance();
		 HansDieter.S_RGB.fetchSample(lightValue, 0);
			HansDieter.S_TCH.fetchSample(touchValue, 0);
	  
		if (fucking_flag) {
	
	    HansDieter.S_TCH.fetchSample(touchValue, 0);
	    HansDieter.S_RGB.fetchSample(lightValue, 0);
	
	    double[] dlight = new double[lightValue.length];
	
	    for (int i = 0; i < dlight.length; ++i)
	     dlight[i] = lightValue[i];
	
	    double dist_red = LightSensorConstants.getDistanceSqr(dlight, LightSensorConstants.RED_TAPE);
	
	    if (dist_red < 0.2)
	    {
	     fucking_flag = false;
	     found_red = true;
	    }
	
	    float curr_dist = getDistance();
	    
	    if (curr_dist < ref_dist - __distance_ε)
	     MotorController.setTurnSpeed(-.1f);
	    else if (curr_dist > ref_dist + __distance_ε)
	     MotorController.setTurnSpeed(.1f);
	    else
	     MotorController.setTurnSpeed(0);
	    
	    MotorController.setSpeed(1);
	    sleep(50);
	
	    
	   if (touchValue[0] == 1.0);
	
	   MotorController.setSpeed(0);
	   MotorController.setTurnSpeed(0.5);
	   MotorController.steerLeft(100);
	   MotorController.setTurnSpeed(0);
	   MotorController.setSpeed(1);
	   sleep(250);
	   MotorController.setSpeed(0);
	   MotorController.setTurnSpeed(0.5);
	   MotorController.steerLeft(100);
	   MotorController.setTurnSpeed(0);
	   sleep(250);
	   
	    return 0;
	  }
	
	  // find white tape
	
	  return 0;
	 }
}