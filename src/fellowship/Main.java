package fellowship;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        /*
        Directory<Team> directory = new Directory<>();
        GameManager<Team> manager = new GameManager<>(Fellowship::new, directory)
                .maxPlayerCount(2);
                */
        ((Integer)Integer.parseInt("hi")).

        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngine engine = factory.getScriptEngine();
        try {
            engine.eval("function print_array(arr) { arr.append(4); return arr; }");
            engine.eval("function print_native() { return print_array([1, 2, 3, 4]); }");
            Invocable invocable = (Invocable) engine;
            List l = (List)invocable.invokeFunction("print_array", Arrays.asList(1, 2, 3, 4));
            l.forEach(System.out::print);
            Map m = (Map)invocable.invokeFunction("print_native");
            m.values().forEach(System.out::print);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
