package main;

public class Menus {
	public static enum GameMenus {
		STARTMENU(),
		PLAYERSELECTIONMENU(),
		CREATENEWPLAYERMENU(),
		GAMEMENU(),
		INVENTORYMENU(),
		SETTINGSMENU(),
		PAUSEMENU()
		
	}
	public static GameMenus currentMenu = GameMenus.STARTMENU;
	public static void startMenu(GamePanel panel) {
		
	}
	public static void changeCurrentMenu(GameMenus Menu) {
		currentMenu = Menu;
	}
	

}
