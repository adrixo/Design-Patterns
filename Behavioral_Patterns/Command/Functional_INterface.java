import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class CommandFactory {
  @FunctionalInterface
  public interface Command {
    public void execute();
  }

  private final HashMap<String, Command> commands = new HashMap<>();
  private final ArrayList<String> history = new ArrayList<>();

  public void add(String commandName, Command command) {
    commands.put(commandName, command);
  }

  public void storeAndExecute(String commandName) {
    Optional.ofNullable(commands.get(commandName)).ifPresent(command -> {
      command.execute();
      history.add(commandName);  // optional
    });
  }

  public void printHistory() {
    System.out.println(history);
  }

  public static void main(final String[] arguments) {
    var lamp = new Object() {
      void lightOn() {
        System.out.println("Light turned on");
      }
      void lightOff() {
        System.out.println("Light turned off");
      }
    };

    // Commands are added here using method reference.
    // It is also possible to dynamically add commands without editing the code.
    var factory = new CommandFactory();
    factory.add("Light on", lamp::lightOn);
    factory.add("Light off", lamp::lightOff);

    factory.storeAndExecute("Light on");
    factory.storeAndExecute("Light off");
    factory.printHistory();  // [Light on, Light off]
  }
}
