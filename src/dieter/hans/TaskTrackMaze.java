package dieter.hans;

public class TaskTrackMaze extends TrackTask
{
 public static final float __distance_ε = .1f; 
 
 
 public float getDistance()
 {
  final int count = 10;
  
  float[] sdist = new float[1];
  float dist = 0;
  
  for (int i = 0; i < count; ++i)
  {
   HansDieter.S_DST.fetchSample(sdist, 0);
   
   dist += sdist[0];
  }
  
  return dist / (float)count;
 }
 
 @Override
 public int runTrack()
 {
  boolean fucking_flag = true;
  
fokken_found_teh_red_tape:
  while (fucking_flag)
  {
   float[] light = new float[3];
   float[] touch = new float[1];
   float ref_dist = getDistance();
   
   MotorController.setTurnSpeed(0);
   MotorController.setSpeed(1);
 
   do
   {
    HansDieter.S_TCH.fetchSample(touch, 0);
    HansDieter.S_RGB.fetchSample(light, 0);

    double[] dlight = new double[light.length];

    for (int i = 0; i < dlight.length; ++i)
     dlight[i] = light[i];

    double dist_red = LightSensorConstants.getDistanceSqr(dlight, LightSensorConstants.RED_TAPE);
    
    if (dist_red < 0.2)
    {
     fucking_flag = false;
     
     break fokken_found_teh_red_tape;
    }
    
    sleep(50);

    float curr_dist = getDistance();
    
    if (curr_dist < ref_dist - __distance_ε)
     MotorController.setSpeed(-.1f);
    else if (curr_dist > ref_dist + __distance_ε)
     MotorController.setSpeed(.1f);
    else
     MotorController.setSpeed(0);
   }
   while (touch[0] == 1.0);
 
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
  }

  // find white tape
  
  return 0;
 }
}