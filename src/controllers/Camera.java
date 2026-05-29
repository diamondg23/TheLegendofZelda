package controllers;

public class Camera {

    public double zoom = 3.0;

    public int worldToScreen(int worldCoord) {
        return (int)(worldCoord * zoom);
    }

    public int screenToWorld(int screenCoord) {
        return (int)(screenCoord / zoom);
    }
}
