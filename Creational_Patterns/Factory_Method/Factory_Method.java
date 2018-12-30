//In class-based programming, the factory method pattern is a creational pattern that uses factory methods to deal with the problem of creating objects without having to specify the exact class of the object that will be created. This is done by creating objects by calling a factory method—either specified in an interface and implemented by child classes, or implemented in a base class and optionally overridden by derived classes—rather than by calling a constructor.

//The MazeGame uses Rooms but it puts the responsibility of creating Rooms to its subclasses which create the concrete classes. The regular game mode could use this template method:
public abstract class Room {
  abstract void connect(Room room);
}

public class MagicRoom extends Room {
  public void connect(Room room) {}
}

public class OrdinaryRoom extends Room {
  public void connect(Room room) {}
}

public abstract class MazeGame {
  private final List<Room> rooms = new ArrayList<>();

  public MazeGame() {
    Room room1 = makeRoom();
    Room room2 = makeRoom();
    room1.connect(room2);
    rooms.add(room1);
    rooms.add(room2);
  }

  abstract protected Room makeRoom();
}

public class MagicMazeGame extends MazeGame {
  @Override
  protected Room makeRoom() {
    return new MagicRoom();
  }
}

public class OrdinaryMazeGame extends MazeGame {
  @Override
  protected Room makeRoom() {
    return new OrdinaryRoom(); 
  }
}
