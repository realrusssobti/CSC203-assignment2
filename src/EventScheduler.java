import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

final class EventScheduler {
    public PriorityQueue<Event> eventQueue;
    public Map<EntityInterface, List<Event>> pendingEvents;
    public double timeScale;

    public EventScheduler(double timeScale) {
        this.eventQueue = new PriorityQueue<>(new EventComparator());
        this.pendingEvents = new HashMap<>();
        this.timeScale = timeScale;
    }

    public void scheduleEvent(EntityInterface entity, ActionInterface action, long afterPeriod) {
        long time = System.currentTimeMillis() +
                (long) (afterPeriod * this.timeScale);
        Event event = new Event(action, time, entity);

        this.eventQueue.add(event);

// update list of pending events for the given entity
        List<Event> pending = this.pendingEvents.getOrDefault(entity,
                new LinkedList<>());
        pending.add(event);
        this.pendingEvents.put(entity, pending);
    }

    public void unscheduleAllEvents(
            EntityInterface entity) {
        List<Event> pending = this.pendingEvents.remove(entity);

        if (pending != null) {
            for (Event event : pending) {
                this.eventQueue.remove(event);
            }
        }
    }


    public void removePendingEvent(Event event) {
        List<Event> pending = pendingEvents.get(event.entity);

        if (pending != null) {
            pending.remove(event);
        }
    }

    public void updateOnTime(long time) {
        while (!eventQueue.isEmpty() &&
                eventQueue.peek().time < time) {
            Event next = eventQueue.poll();

            removePendingEvent(next);

            next.action.executeAction(this);
        }
    }


}
