package fellowship;

import fellowship.characters.BaseCharacter;
import fellowship.characters.Team;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.impl.factory.primitive.ObjectIntMaps;

public class TrapStack implements MapObject{
    public final int DAMAGE_PER_TRAP = 15;
    private MutableObjectIntMap<Team> damageInstances;
    public TrapStack(){
        damageInstances = ObjectIntMaps.mutable.empty();
    }

    public int getDamageInstances(Team team){
        return damageInstances.get(team);
    }

    public int getTotalDamage(Team team){
        return getDamageInstances(team)*DAMAGE_PER_TRAP;
    }

    public void addDamage(Team team){
        damageInstances.put(team, damageInstances.getIfAbsentPut(team, 0)+1);
    }

    public void onStep(BaseCharacter character){
        int instances = damageInstances.removeKeyIfAbsent(character.getTeam(), 0);
        for (int i = 0; i < instances; i++){
            character.damage(DAMAGE_PER_TRAP);
        }
    }
}
