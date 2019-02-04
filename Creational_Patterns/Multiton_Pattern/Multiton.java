//In software engineering, the multiton pattern is a design pattern which generalizes the singleton pattern. Whereas the singleton allows only one instance of a class to be created, the multiton pattern allows for the controlled creation of multiple instances, which it manages through the use a map.

/*
public final class Singleton {
  private static final Singleton INSTANCE = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
    return INSTANCE;
  }
}

*/


public class Multiton {

  private static final HashMap<String, Multiton> multitons = new HashMap<>();

  private Multiton() {super();}

  public static Multiton getInstance(String key) {
    if(multitons.containsKey(key)) {
      return multitons.get(key);
    }
    else {
      Multiton multiton = new Multiton();
      multitons.put(key, multiton);
      return multiton;
    }
  }
}
