package dieter.hans;

public class PID {

	private double kp= 0, ki=0, kd=0;
	private double integral = 0;
	private double last = 0;
	
	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	
	public double tick(double input) {
		integral += input;
		if(integral > 1) integral = 0.98;
		if(integral < -1) integral = -0.98;
		double output = kp * input + ki * integral + kd * (input - last);
		last = input;
		return output;
	}
	
}
