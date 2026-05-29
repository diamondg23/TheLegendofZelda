package interfaces;

import java.awt.Graphics2D;

public interface Manager {

	void update();
	void draw(Graphics2D g2);
	void load(String path);
	void unload();
}
