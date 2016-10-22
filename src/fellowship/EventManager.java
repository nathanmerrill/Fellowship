package fellowship;

import com.ppcg.kothcomm.utils.Pair;
import fellowship.events.Event;
import fellowship.events.Events;

import java.util.*;
import java.util.function.Function;

public class EventManager {
    private final HashMap<Events, List<Function<Event, Boolean>>> listeners;
    private final HashMap<Integer, Function<Event, Boolean>> eventNums;
    private final HashMap<Function<Event, Boolean>, Pair<Integer, Events>> reverseMapping;
    private int counter;

    public EventManager(){
        listeners = new HashMap<>();
        for (Events type: Events.values()){
            listeners.put(type, new ArrayList<>());
        }
        eventNums = new HashMap<>();
        reverseMapping = new HashMap<>();
        counter = 1;
    }

    public int addListener(Events type, Function<Event, Boolean> listener){
        listeners.get(type).add(listener);
        eventNums.put(counter, listener);
        reverseMapping.put(listener, new Pair<>(0, type));
        return counter++;
    }

    public void removeListener(int key){
        removeListener(eventNums.get(key));
    }

    public void removeListener(Function<Event, Boolean> listener){
        Pair<Integer, Events> keys = reverseMapping.get(listener);
        if (keys != null){
            eventNums.remove(keys.first());
            listeners.get(keys.second()).remove(listener);
        }
    }

    public void addEvent(Event event, Events type){
        List<Function<Event, Boolean>> toRemove = new ArrayList<>();
        listeners.get(type).forEach(f -> {
            if (!f.apply(event)){
                toRemove.add(f);
            }
        });
        toRemove.forEach(this::removeListener);
    }

}
