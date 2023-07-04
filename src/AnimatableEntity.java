public interface AnimatableEntity extends ExecutableEntity {
    public int getAnimationPeriod();
      public ActionInterface createAnimationAction(int repeatCount);
}
