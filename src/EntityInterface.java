import processing.core.PImage;

import java.util.List;

// Base interface for all entities in the game
public interface EntityInterface {
    public Point getPosition();
    public void setPosition(Point position);
    public List<PImage> getImages();
    public int getImageIndex();
    public void nextImage();
    public PImage getCurrentImage();
    public EntityKind getKind();
}
