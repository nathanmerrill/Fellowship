package fellowship;

import com.nmerrill.kothcomm.gui.GameNode;
import fellowship.actions.Action;
import fellowship.characters.BaseCharacter;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.tuple.Tuples;

public class CharacterView extends VBox implements GameNode {
    private final BaseCharacter character;
    private final TableView<Action> actionTable;
    private final TableView<Pair<String, String>> attributes;

    public CharacterView(BaseCharacter character){
        this.character = character;
        this.actionTable = new TableView<>();
        this.attributes = new TableView<>();
        this.setWidth(300);
        this.getChildren().addAll(this.attributes, this.actionTable);
        showAttributes();
        showActions();
        draw();
    }

    public void showActions(){
        TableColumn<Action, String> name = new TableColumn<>("Action");
        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getName()));
        TableColumn<Action, Integer> cooldownLeft = new TableColumn<>("Cooldown left");
        cooldownLeft.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getRemainingCooldown()));
        TableColumn<Action, Integer> cooldown = new TableColumn<>("Cooldown");
        cooldown.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getCooldown()));
        TableColumn<Action, Integer> mana = new TableColumn<>("Mana cost");
        mana.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getManaCost()));
        actionTable.getColumns().add(name);
        actionTable.getColumns().add(cooldownLeft);
        actionTable.getColumns().add(cooldown);
        actionTable.getColumns().add(mana);
    }

    @Override
    public void draw() {
        actionTable.getItems().setAll(character.getActions());
        attributes.getItems().clear();
        addAttribute("HP",character.getHealth()+"/"+character.getMaxHealth());
        addAttribute("Mana",character.getMana()+"/"+character.getMaxMana());
        for (Stat stat: Stat.values()) {
            addAttribute(stat, character.getStat(stat));
        }
        MutableList<String> statuses = Lists.mutable.empty();
        if (character.isDead()){
            statuses.add("Dead");
        }
        if (character.isStunned()){
            statuses.add("Stunned");
        }
        if (character.isSilenced()){
            statuses.add("Silenced");
        }
        if (character.isInvisible()){
            statuses.add("Invisible");
        }
        if (character.isPoisoned()){
            statuses.add("Poisoned");
        }
        addAttribute("Statuses", statuses.makeString(","));
    }

    private void addAttribute(Object one, Object two){
        attributes.getItems().add(Tuples.pair(one.toString(), two.toString()));
    }

    public void showAttributes(){
        TableColumn<Pair<String, String>, String> root = new TableColumn<>(character.getTeam().getPlayer().getName());
        TableColumn<Pair<String, String>, String> stat = new TableColumn<>("Stat");
        stat.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getOne()));
        TableColumn<Pair<String, String>, String> value = new TableColumn<>("Value");
        value.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getTwo()));
        root.getColumns().add(stat);
        root.getColumns().add(value);
        attributes.getColumns().add(root);

    }
}
