import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class OreBlob implements MovableEntity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int ActionPeriod;
    private int AnimationPeriod;
    private WorldModel world;
    private ImageStore imageStore;

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
        return EntityKind.ORE_BLOB;
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
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler){
        Optional<EntityInterface> blobTarget = world.findNearest(position, EntityKind.VEIN);
        long nextPeriod = ActionPeriod;

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(world, blobTarget.get(), scheduler)) {
                Quake Quake = new Quake(tgtPos, imageStore.getImageList("quake"));

                world.addEntity(Quake);
                nextPeriod += ActionPeriod;
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), nextPeriod);
    }

    @Override
    public void move(WorldModel world, MovableEntity target, EventScheduler scheduler) {

    }
}
