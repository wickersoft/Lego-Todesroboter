package dieter.hans;

public enum RobotState {
	MENU(new MenuTask()), ROUNDLINES(new TaskTrackLine()), MAZE(new TaskTrackMaze()), OBSTACLE(new TaskTrackObstacle()), BRIDGE(new TaskTrackBridge());
	
	private final RobotTask task;
	
	private RobotState(RobotTask task) {
		this.task = task;
	}
	
	
}
