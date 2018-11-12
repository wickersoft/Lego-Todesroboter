package dieter.hans;

public class StateMachine {
	
	private final RobotTask taskMenu = new MenuTask();
	private final RobotTask taskBridge = new TaskTrackBridge();
	private final RobotTask taskMaze = new TaskTrackMaze();
	private final RobotTask taskLine = new TaskTrackLine();
	private final RobotTask taskObst = new TaskTrackObstacle();
	private final RobotTask taskCannon = new TaskTrackCannon();
	
	private RobotTask currentTask = taskMenu;
	
	public void init() {
		taskMenu.edge(1, taskBridge);
		taskMenu.edge(2, taskMaze);
		taskMenu.edge(3, taskLine);
		taskMenu.edge(4, taskObst);
		taskMenu.edge(5, taskCannon);
		
		taskBridge.edge(-1, taskMenu);
		taskMaze.edge(-1, taskMenu);
		taskLine.edge(-1, taskMenu);
		taskObst.edge(-1, taskMenu);
		taskCannon.edge(-1, taskMenu);
	}
	
	public int tick() {
		currentTask = currentTask.runAndGetNext();
		if(currentTask == null) {
			return -1;
		}
		return 0;
	}
		
}
