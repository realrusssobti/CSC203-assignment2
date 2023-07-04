public class Activity implements ActionInterface {
    public ExecutableEntity entity;
    public WorldModel world;
    public ImageStore imageStore;
    public void executeAction(EventScheduler scheduler) {
           entity.executeActivity(world,imageStore,scheduler);
    }

    public Activity(ExecutableEntity entity, WorldModel world,
                    ImageStore imageStore)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }
}
