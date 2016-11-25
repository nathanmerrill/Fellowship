package fellowship;

import fellowship.characters.Team;

public class ReadonlyStructure {
    private final TrapStack stack;
    private final WallObject wall;
    private final Team team;
    public ReadonlyStructure(TrapStack stack, Team team){
        this.team = team;
        this.stack = stack;
        this.wall = null;
    }
    public ReadonlyStructure(WallObject wall){
        this.wall = wall;
        this.team = null;
        this.stack = null;
    }

    public boolean isTrap(){
        return stack != null;
    }

    public boolean isWall(){
        return wall != null;
    }

    public int getTotalDamage(){
        if (stack == null){
            return 0;
        }
        return this.stack.getTotalDamage(this.team);
    }

    public int getDamageInstances(){
        if (stack == null){
            return 0;
        }
        return this.stack.getDamageInstances(this.team);
    }
}
