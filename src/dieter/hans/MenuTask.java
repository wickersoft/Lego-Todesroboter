package dieter.hans;

import lejos.utility.TextMenu;

public class MenuTask extends RobotTask {

	private boolean firstRun = true;
	
	@Override
	public int run() {
		TextMenu menu = new TextMenu(new String[] {"Brücke", "Labyrinth", "Linie", "Hindernis"}, 0, "Task wählen");
		TextMenu confirm = new TextMenu(new String[] {"Ja", "Nein"}, 0, "Fo rils?");
		
		if(confirm.select() == 1) {
			return 0;
		}
		
		switch(menu.select()) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		case 3:
			return 4;
		}
		return 0;
	}
}
