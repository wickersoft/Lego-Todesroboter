package dieter.hans;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.TextMenu;

public class MenuTask extends RobotTask {
	
	@Override
	public int run() {
		LCD.clear();
		TextMenu menu = new TextMenu(new String[] {"Bruecke", "Labyrinth", "Linie", "Hindernis", "Beenden"}, 1, "Task waehlen");
		int selection = menu.select();
		
		LCD.clear();
		TextMenu confirm = new TextMenu(new String[] {"Ja", "Nein"}, 1, "Fo rils");		
		if(confirm.select() == 1) {
			return 0;
		}
		
		LCD.clear();
		
		while(Button.getButtons() != 0) {
			System.out.println("...");
		}
		
		switch(selection) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		case 3:
			return 4;
		case 4:
			return -1;
		}
		return 0;
	}
}
