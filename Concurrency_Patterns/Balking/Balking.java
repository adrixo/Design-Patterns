//The balking pattern is a software design pattern that only executes an action on an object when the object is in a particular state. For example, if an object reads ZIP files and a calling method invokes a get method on the object when the ZIP file is not open, the object would "balk" at the request. In the Java programming language, for example, an IllegalStateException might be thrown under these circumstances.



public class Example {
    private boolean jobInProgress = false;

    public void job() {
        synchronized(this) {
           if (jobInProgress) {
               return;
           }
           jobInProgress = true;
        }
        // Code to execute job goes here
        // ...
        jobCompleted();
    }

    void jobCompleted() {
        synchronized(this) {
            jobInProgress = false;
        }
    }
}
