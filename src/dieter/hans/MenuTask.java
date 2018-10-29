package dieter.hans;

import lejos.utility.TextMenu;

public class MenuTask extends RobotTask {

	private boolean firstRun = true;
	
	@Override
	public RobotState run() {
		TextMenu menu = new TextMenu(new String[] {"Brücke", "Labyrinth", "Linie", "Hindernis"}, 0, "Task wählen");
		TextMenu confirm = new TextMenu(new String[] {"Ja", "Nein"}, 0, "Fo rils?");
		
		if(confirm.select() == 1) {
			return RobotState.MENU;
		}
		
		switch(menu.select()) {
		case 0:
			return RobotState.BRIDGE;
		case 1:
			return RobotState.MAZE;
		case 2:
			return RobotState.ROUNDLINES;
		case 3:
			return RobotState.OBSTACLE;
		}
		return RobotState.MENU;
	}
}
