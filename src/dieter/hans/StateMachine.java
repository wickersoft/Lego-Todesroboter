package dieter.hans;

public class StateMachine {
	
	private final RobotTask taskMenu = new MenuTask();
	private final RobotTask taskBridge = new TaskTrackBridge();
	private final RobotTask taskMaze = new TaskTrackMaze();
	private final RobotTask taskLine = new TaskTrackLine();
	private final RobotTask taskObst = new TaskTrackObstacle();
	private final RobotTask taskCannon = new TaskTrackCannon();
	private final RobotTask taskTransgender = new TaskTrackTransition();
	private final RobotTask taskTransgender1 = new TaskTrackTransition1();
	
	private RobotTask currentTask = taskMenu;
	
	public void init() {
		taskMenu.edge(1, taskBridge);
		taskMenu.edge(2, taskMaze);
		taskMenu.edge(3, taskLine);
		taskMenu.edge(4, taskObst);
		taskMenu.edge(5, taskCannon);
		taskMenu.edge(6, taskTransgender);
		
		taskBridge.edge(-1, taskMaze);
		taskMaze.edge(-1, taskMenu);
		taskLine.edge(-1, taskTransgender);
		taskObst.edge(-1, taskTransgender1);
		taskCannon.edge(-1, taskMenu);
		taskTransgender.edge(-1, taskObst);
		taskTransgender1.edge(-1, taskBridge);
	}
	
	public int tick() {
		RobotTask newTask = currentTask.runAndGetNext();
		if(newTask != currentTask && newTask != null) {
			newTask.enter();
		}
		currentTask = newTask;
		if(currentTask == null) {
			return -1;
		}
		return 0;
	}
		
}
