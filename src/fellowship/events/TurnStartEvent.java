package fellowship.events;

public class TurnStartEvent extends Event{
    @Override
    Events getType() {
        return Events.TurnStart;
    }
}
