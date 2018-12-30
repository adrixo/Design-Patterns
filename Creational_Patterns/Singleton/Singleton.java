//In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one. This is useful when exactly one object is needed to coordinate actions across the system. The term comes from the mathematical concept of a singleton.

public final class Singleton {
  private static final Singleton INSTANCE = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
    return INSTANCE;
  }
}
