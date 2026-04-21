package interfaces;

import java.awt.Graphics2D;

//anything that is able to be rendered will have this interface.
public interface Renderable {
    void render(Graphics2D g, int x, int y, int w, int h);
}
