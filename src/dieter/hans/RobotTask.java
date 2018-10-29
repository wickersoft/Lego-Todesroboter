package dieter.hans;

import java.util.HashMap;

public abstract class RobotTask {
	
	private final HashMap<Integer, RobotTask> transitions = new HashMap<>();
	
	public void edge(int returnCode, RobotTask neighbor) {
		transitions.put(returnCode, neighbor);
	}

	public abstract int run();
	
	public RobotTask runAndGetNext() {
		int code = run();
		if(code == 0) {
			return this;
		} else {
			return transitions.get(code);
		}
	}
}
