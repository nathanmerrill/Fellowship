package fellowship;

import fellowship.abilities.Ability;
import fellowship.abilities.ActionAbility;
import fellowship.abilities.attacking.*;
import fellowship.abilities.damage.*;
import fellowship.abilities.defensive.*;
import fellowship.abilities.mobility.*;
import fellowship.abilities.stats.*;
import fellowship.abilities.statuses.*;
import fellowship.abilities.vision.*;
import fellowship.actions.Action;
import fellowship.actions.attacking.*;
import fellowship.actions.damage.*;
import fellowship.actions.defensive.*;
import fellowship.actions.mobility.*;
import fellowship.actions.other.*;
import fellowship.actions.stats.*;
import fellowship.actions.statuses.*;
import fellowship.actions.vision.*;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.factory.Sets;


@SuppressWarnings("unchecked")
public class AbilityRegistry {
    private static ImmutableSet<Class<? extends Ability>> availableAbilities = Sets.immutable.of(
            Absorb.class,
            Cleave.class,
            Critical.class,
            Feast.class,
            Flexible.class,
            ManaSteal.class,
            Ranged.class,
            Reflexive.class,
            Swipe.class,
            Static.class,
            Evasive.class,
            Pillar.class,
            Resurrect.class,
            Spikes.class,
            Dash.class,
            Mobile.class,
            Responsive.class,
            Buff.class,
            Clever.class,
            Focused.class,
            Regenerate.class,
            Smart.class,
            Strong.class,
            Weak.class,
            Cold.class,
            Immune.class,
            Darkness.class,
            FarSight.class,
            Invisible.class,
            TrueSight.class
    );
    private static ImmutableSet<Class<? extends Action>> availableActions = Sets.immutable.of(
            Quick.class,
            Weave.class,
            Lightning.class,
            Drain.class,
            KO.class,
            Trap.class,
            Zap.class,
            ForceField.class,
            Ghost.class,
            Heal.class,
            Restore.class,
            Shield.class,
            Alert.class,
            Blink.class,
            Charge.class,
            Run.class,
            Swap.class,
            Teleport.class,
            Bear.class,
            Clone.class,
            Steal.class,
            Wall.class,
            Werewolf.class,
            Dispel.class,
            Duel.class,
            Knockout.class,
            Leash.class,
            Poison.class,
            Slow.class,
            Stun.class,
            Cloak.class,
            Hide.class,
            Phase.class,
            Track.class
    );

    public static boolean isRegistered(Ability ability) {
        if (ability instanceof ActionAbility) {
            return availableActions.contains(((ActionAbility) ability).actionClass());
        }
        return availableAbilities.contains(ability.getClass());
    }
}
