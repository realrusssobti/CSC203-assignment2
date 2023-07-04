public class Animation implements ActionInterface{
    public AnimatableEntity entity;
    public WorldModel world;
    public ImageStore imageStore;
    public int repeatCount;
    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();
        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    this.entity.createAnimationAction(
                            Math.max(this.repeatCount - 1, 0)),
                    this.entity.getAnimationPeriod());
        }
    }
    public Animation( AnimatableEntity entity, WorldModel world,
                  ImageStore imageStore, int repeatCount) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }
}
