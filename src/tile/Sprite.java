package tile;

import java.awt.image.BufferedImage;

public class Sprite {
	public BufferedImage image;
	private String name;
	public Sprite(BufferedImage image) {
		this.image = image;
	}
	public Sprite(BufferedImage image, String name) {
		this.image = image;
		this.name = name;
	}
	String getName() {
		return name;
	}
}
