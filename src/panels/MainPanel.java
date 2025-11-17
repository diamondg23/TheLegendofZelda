package panels;

import java.awt.CardLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel{

	CardLayout cardLayout = new CardLayout();
	
	GamePanel gamePanel = new GamePanel();
	InventoryPanel iPanel = new InventoryPanel();
	StartingPanel startingPanel = new StartingPanel(() -> showGame());
	NameSelectionPanel namePanel = new NameSelectionPanel();
	public MainPanel() {
		this.setLayout(cardLayout);
		this.add(gamePanel,"game");
		this.add(iPanel, "inventory");
		this.add(startingPanel, "start");
		this.add(namePanel, "nameSelection");
		cardLayout.show(this, "start");
		
	}
	public void showGame() {
		cardLayout.show(this, "game");
		gamePanel.requestFocusInWindow();  
	}
	public void showInventory() {
		
	}

}
