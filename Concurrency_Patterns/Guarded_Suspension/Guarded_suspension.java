//In concurrent programming, guarded suspension[1] is a software design pattern for managing operations that require both a lock to be acquired and a precondition to be satisfied before the operation can be executed. The guarded suspension pattern is typically applied to method calls in object-oriented programs, and involves suspending the method call, and the calling thread, until the precondition (acting as a guard) is satisfied.

public class Example {
    synchronized void guardedMethod() {
        while (!preCondition()) {
            try {
                // Continue to wait
                wait();
                // …
            } catch (InterruptedException e) {
                // …
            }
        }
        // Actual task implementation
    }
    synchronized void alterObjectStateMethod() {
        // Change the object state
        // …
        // Inform waiting threads
        notify();
    }
}
