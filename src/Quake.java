import processing.core.PImage;

import java.util.List;

public class Quake implements AnimatableEntity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int ActionPeriod;
    private int AnimationPeriod;
    private WorldModel world;
    private ImageStore imageStore;

    public Quake(String id, Point position, List<PImage> images, int ActionPeriod, int AnimationPeriod, WorldModel world, ImageStore imageStore) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.ActionPeriod = ActionPeriod;
        this.AnimationPeriod = AnimationPeriod;
        this.world = world;
        this.imageStore = imageStore;
    }


    @Override
    public int getAnimationPeriod() {
        return AnimationPeriod;
    }

    @Override
    public ActionInterface createAnimationAction(int repeatCount) {
        return new Animation(this, world, imageStore, repeatCount);
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public List<PImage> getImages() {
        return images;
    }

    @Override
    public int getImageIndex() {
        return imageIndex;
    }

    @Override
    public void nextImage() {
        imageIndex = (imageIndex + 1) % images.size();
    }

    @Override
    public PImage getCurrentImage() {
        return images.get(imageIndex);
    }

    @Override
    public EntityKind getKind() {
        return EntityKind.QUAKE;
    }

    @Override
    public int getActionPeriod() {
        return ActionPeriod;
    }

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), ActionPeriod);
        scheduler.scheduleEvent(this, new Animation(this, world, imageStore, 0), AnimationPeriod);
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }
}