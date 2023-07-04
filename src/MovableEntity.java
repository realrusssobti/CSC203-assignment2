public interface MovableEntity extends AnimatableEntity{
    public void move(WorldModel world, MovableEntity target, EventScheduler scheduler);
}
