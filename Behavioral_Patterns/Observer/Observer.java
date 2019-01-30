//The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.

""
import java.util.Observable;
import java.util.Scanner;

class EventSource extends Observable implements Runnable {
  public void run() {
    while (true) {
      String response = new Scanner(System.in).next();
      setChanged();
      notifyObservers(response);
    }
  }
}
""

""
import java.util.Observable;
import java.util.Observer;

public class MyApp {
  public static void main(String[] args) {
    System.out.println("Enter Text: ");
    EventSource eventSource = new EventSource();

    eventSource.addObserver((obj, arg) -> {
      System.out.println("Received response: " + arg);
    });

    new Thread(eventSource).start();
  }
}
""
