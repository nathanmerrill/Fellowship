package fellowship.actions.damage;

import fellowship.characters.BaseCharacter;
import fellowship.actions.TargettedAction;
import org.eclipse.collections.api.set.MutableSet;

public class KO extends TargettedAction {
    public final static double KO_LIMIT = .2;

    public KO(BaseCharacter character){
        super(character);
    }
    @Override
    public void perform(BaseCharacter target) {
        if (canTarget(target)) {
            target.damage(character, target.getHealth());
        }
    }

    private boolean canTarget(BaseCharacter target){
        return target.getHealth() <= target.getMaxHealth()*KO_LIMIT;
    }

    @Override
    public MutableSet<BaseCharacter> availableTargets() {
        return super.availableTargets().select(this::canTarget);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 20;
    }
}
