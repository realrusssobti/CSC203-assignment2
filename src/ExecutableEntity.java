public interface ExecutableEntity extends EntityInterface{
    public int getActionPeriod();
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
