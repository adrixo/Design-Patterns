//In object-oriented programming, the command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time. This information includes the method name, the object that owns the method and values for the method parameters.

import java.util.ArrayList;

/** The Command interface */
public interface Command {
   void execute();
}

/** The Invoker class */
public class Switch {
   private final ArrayList<Command> history = new ArrayList<>();

   public void storeAndExecute(Command cmd) {
      this.history.add(cmd); // optional
      cmd.execute();
   }
}

/** The Receiver class */
public class Light {
   public void turnOn() {
      System.out.println("The light is on");
   }

   public void turnOff() {
      System.out.println("The light is off");
   }
}

/** The Command for turning on the light - ConcreteCommand #1 */
public class FlipUpCommand implements Command {
   private final Light light;

   public FlipUpCommand(Light light) {
      this.light = light;
   }

   @Override    // Command
   public void execute() {
      light.turnOn();
   }
}

/** The Command for turning off the light - ConcreteCommand #2 */
public class FlipDownCommand implements Command {
   private final Light light;

   public FlipDownCommand(Light light) {
      this.light = light;
   }

   @Override    // Command
   public void execute() {
      light.turnOff();
   }
}

/* The test class or client */
public class PressSwitch {
   public static void main(final String[] arguments){
      // Check number of arguments
      if (arguments.length != 1) {
         System.err.println("Argument \"ON\" or \"OFF\" is required!");
         System.exit(-1);
      }

      Light lamp = new Light();

      Command switchUp = new FlipUpCommand(lamp);
      Command switchDown = new FlipDownCommand(lamp);

      Switch mySwitch = new Switch();

      switch(arguments[0]) {
         case "ON":
            mySwitch.storeAndExecute(switchUp);
            break;
         case "OFF":
            mySwitch.storeAndExecute(switchDown);
            break;
         default:
            System.err.println("Argument \"ON\" or \"OFF\" is required.");
            System.exit(-1);
      }
   }
}
