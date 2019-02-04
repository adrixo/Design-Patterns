//The object pool pattern is a software creational design pattern that uses a set of initialized objects kept ready to use – a "pool" – rather than allocating and destroying them on demand. A client of the pool will request an object from the pool and perform operations on the returned object. When the client has finished, it returns the object to the pool rather than destroying it; this can be done manually or automatically.

//Object pools are primarily used for performance: in some circumstances, object pools significantly improve performance. Object pools complicate object lifetime, as objects obtained from and returned to a pool are not actually created or destroyed at this time, and thus require care in implementation.

//Java supports thread pooling via java.util.concurrent.ExecutorService and other related classes. The executor service has a certain number of "basic" threads that are never discarded. If all threads are busy, the service allocates the allowed number of extra threads that are later discarded if not used for the certain expiration time. If no more threads are allowed, the tasks can be placed in the queue. Finally, if this queue may get too long, it can be configured to suspend the requesting thread.

public class PooledObject {
	public String temp1;
	public String temp2;
	public String temp3;

	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getTemp3() {
		return temp3;
	}
	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}}
public class PooledObjectPool {
	private static long expTime = 6000;//6 seconds
	public static HashMap<PooledObject, Long> available = new HashMap<PooledObject, Long>();
	public static HashMap<PooledObject, Long> inUse = new HashMap<PooledObject, Long>();

	public synchronized static PooledObject getObject() {
		long now = System.currentTimeMillis();
		if (!available.isEmpty()) {
			for (Map.Entry<PooledObject, Long> entry : available.entrySet()) {
				if (now - entry.getValue() > expTime) { //object has expired
					popElement(available);
				} else {
					PooledObject po = popElement(available, entry.getKey());
					push(inUse, po, now);
					return po;
				}
			}
		}

		// either no PooledObject is available or each has expired, so return a new one
		return createPooledObject(now);
	}

	private synchronized static PooledObject createPooledObject(long now) {
		PooledObject po = new PooledObject();
		push(inUse, po, now);
		return po;
        }

	private synchronized static void push(HashMap<PooledObject, Long> map,
			PooledObject po, long now) {
		map.put(po, now);
	}

	public static void releaseObject(PooledObject po) {
		cleanUp(po);
		available.put(po, System.currentTimeMillis());
		inUse.remove(po);
	}

	private static PooledObject popElement(HashMap<PooledObject, Long> map) {
		 Map.Entry<PooledObject, Long> entry = map.entrySet().iterator().next();
		 PooledObject key= entry.getKey();
		 //Long value=entry.getValue();
		 map.remove(entry.getKey());
		 return key;
	}

	private static PooledObject popElement(HashMap<PooledObject, Long> map, PooledObject key) {
		map.remove(key);
		return key;
	}

	public static void cleanUp(PooledObject po) {
		po.setTemp1(null);
		po.setTemp2(null);
		po.setTemp3(null);
	}
}
