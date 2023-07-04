import processing.core.PImage;

import java.util.List;

public class Blacksmith implements EntityInterface{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public Blacksmith(String id, Point position, List<PImage> images){
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }
    public Point getPosition(){
        return this.position;
    }
    public void setPosition(Point position){
        this.position = position;
    }
    public List<PImage> getImages(){
        return this.images;
    }
    public int getImageIndex(){
        return this.imageIndex;
    }
    public void nextImage(){
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }
    public PImage getCurrentImage(){
        return this.images.get(this.imageIndex);
    }
    public EntityKind getKind(){
        return EntityKind.BLACKSMITH;
    }
}
