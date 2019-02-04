//The prototype pattern is a creational design pattern in software development. It is used when the type of objects to create is determined by a prototypical instance, which is cloned to produce new objects

// Prototype pattern
public abstract class Prototype implements Cloneable {
  public Prototype clone() throws CloneNotSupportedException{
    return (Prototype) super.clone();
  }
}

public class ConcretePrototype1 extends Prototype {
  @Override
  public Prototype clone() throws CloneNotSupportedException {
    return (ConcretePrototype1)super.clone();
  }
}

public class ConcretePrototype2 extends Prototype {
  @Override
  public Prototype clone() throws CloneNotSupportedException {
    return (ConcretePrototype2)super.clone();
  }
}
