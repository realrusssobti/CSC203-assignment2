import processing.core.PImage;

import java.util.List;

public class Ore implements ExecutableEntity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int ActionPeriod;
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
        return EntityKind.ORE;
    }

    @Override
    public int getActionPeriod() {
        return ActionPeriod;
    }

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), ActionPeriod);

    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Point pos = this.getPosition();  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob blob = new OreBlob(this.id + " -- blob", pos, ActionPeriod / BLOB_PERIOD_SCALE,
                BLOB_ANIMATION_MIN +
                        rand.nextInt(BLOB_ANIMATION_MAX - BLOB_ANIMATION_MIN),
                imageStore.getImageList(BLOB_KEY));

        world.addEntity(blob);
        blob.scheduleActions(scheduler, world, imageStore);

    }
}
