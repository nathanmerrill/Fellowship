package fellowship.characters;

import fellowship.AbilityRegistry;
import fellowship.Stat;
import fellowship.abilities.Ability;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public final class CharacterTemplate {
    public final static int ABILITY_SLOTS = 4;
    public final static int ATTRIBUTE_POINTS = 20;
    public final static int STARTING_ATTRIBUTE_SCORE = 5;
    private final MutableList<Ability> abilities;
    private final MutableSet<Class<? extends Ability>> usedClasses;
    private MutableIntList attributes;
    private int remainingSlots, remainingPoints;

    public CharacterTemplate(){
        abilities = Lists.mutable.empty();
        usedClasses = Sets.mutable.empty();
        attributes = IntLists.mutable.of(STARTING_ATTRIBUTE_SCORE, STARTING_ATTRIBUTE_SCORE, STARTING_ATTRIBUTE_SCORE);
        remainingSlots = ABILITY_SLOTS;
        remainingPoints = ATTRIBUTE_POINTS;
    }

    public CharacterTemplate(List<Ability> abilities){
        this();
        abilities.forEach(this::addAbility);
    }

    public CharacterTemplate(Map<Stat, Integer> stats){
        this();
        stats.forEach(this::addStat);
    }

    public CharacterTemplate(Map<Stat, Integer> stats, List<Ability> abilities){
        this();
        abilities.forEach(this::addAbility);
        stats.forEach(this::addStat);
    }

    public CharacterTemplate(int str, int agi, int intel){
        this(str, agi, intel, new Ability[]{});
    }

    public CharacterTemplate(Ability...abilities){
        this(0,0,0,abilities);
    }

    public CharacterTemplate(int str, int agi, int intel, Ability...abilities){
        this();
        for (Ability ability: abilities){
            addAbility(ability);
        }
        addStat(Stat.STR, str);
        addStat(Stat.AGI, agi);
        addStat(Stat.INT, intel);
    }

    public boolean canAddAbility(Ability ability){
        return remainingSlots >= ability.getNumSlots()
                && AbilityRegistry.isRegistered(ability)
                && (ability.repeatable() || !usedClasses.contains(ability.getClass()));
    }

    public CharacterTemplate addAbility(Ability ability){
        if (remainingSlots < ability.getNumSlots()){
            throw new RuntimeException("Not enough slots");
        } else if (!AbilityRegistry.isRegistered(ability)) {
            throw new RuntimeException("Invalid ability");
        } else if (!ability.repeatable() && usedClasses.contains(ability.getClass())){
            throw new RuntimeException("Ability cannot be repeated");
        }
        abilities.add(ability);
        usedClasses.add(ability.getClass());
        remainingPoints += ability.attributePoints();
        if (remainingPoints < 0) {
            for (Stat stat : Stat.values()) {
                int remaining = attributes.get(stat.ordinal()) - STARTING_ATTRIBUTE_SCORE;
                int diff = Math.min(remaining, -remainingPoints);
                attributes.set(stat.ordinal(), attributes.get(stat.ordinal())-diff);
                remainingPoints += diff;
            }
        }
        remainingSlots-=ability.getNumSlots();
        return this;
    }

    public boolean isValid(){
        return remainingPoints == 0 && remainingSlots == 0;
    }

    public CharacterTemplate addStat(Stat stat, int amount){
        if (amount >= 0 && amount <= remainingPoints){
            attributes.set(stat.ordinal(), attributes.get(stat.ordinal())+amount);
            remainingPoints -= amount;
        } else {
            if (amount < 0){
                throw new RuntimeException("Must add positive amount");
            } else {
                throw new RuntimeException("Insufficient stat points");
            }
        }
        return this;
    }

    public int getStat(Stat stat){
        return attributes.get(stat.ordinal());
    }

    public int getRemainingPoints() {
        return remainingPoints;
    }

    public int getRemainingSlots() {
        return remainingSlots;
    }

    public MutableList<Ability> currentAbilities(){
        return abilities.clone();
    }

    public MutableMap<Stat, Integer> currentAttributes(){
        return Lists.mutable.of(Stat.values()).toMap(i->i, this::getStat);
    }
}
