
// A singleton implementation may use lazy initialization,
// where the instance is created when the static method
// is first invoked. If the static method might be called
// from multiple threads simultaneously, measures may need
// to be taken to prevent race conditions that could result
// in the creation of multiple instances of the class

public final class Singleton {

  private static volatile Singleton instance = null;

  private Singleton() {}

  public static Singleton getInstance() {
    if (instance == null) {
      synchronized(Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }

    return instance;
  }
}
