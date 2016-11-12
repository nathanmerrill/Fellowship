package fellowship.actions;

import fellowship.Range;
import fellowship.characters.BaseCharacter;
import fellowship.characters.ReadonlyCharacter;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Sets;

public abstract class TargettedAction extends Action {

    private final Range defaultRange = new Range(1000);
    private BaseCharacter target;

    public TargettedAction(BaseCharacter character){
        super(character);
    }

    @Override
    public MutableSet<BaseCharacter> availableTargets(){
        MutableSet<BaseCharacter> availableTargets = Sets.mutable.empty();
        if (canTargetEnemies()){
            availableTargets.addAll(character.visibleEnemies(getRange()));
        }
        if (canTargetTeam()){
            availableTargets.addAll(character.teammates(getRange()));
        }
        return availableTargets;
    }

    public Range getRange(){
        return defaultRange;
    }

    public boolean canTargetEnemies(){
        return true;
    }

    public boolean canTargetTeam(){
        return false;
    }

    @Override
    public boolean isAvailable() {
        return super.isAvailable()
                && !availableTargets().isEmpty();
    }

    public void setTarget(ReadonlyCharacter character){
        try {
            this.target = availableTargets().select(i -> i.readonly().equals(character)).getOnly();
        } catch (IllegalStateException e){
            throw new RuntimeException("Invalid character");
        }
    }

    @Override
    public boolean needsTarget() {
        return true;
    }

    public void clearSelection(){
        this.target = null;
    }

    @Override
    public void perform() {
        perform(target);
    }

    protected abstract void perform(BaseCharacter target);
}
