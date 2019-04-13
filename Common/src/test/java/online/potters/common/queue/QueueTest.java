package online.potters.common.queue;

import online.potters.impl.common.queue.CasparQueue;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;

public class QueueTest {

	@Test
	public void testQueue() {
		String[] testData = new String[]{
				"Hello",
				"Hi",
				"Goodbye",
				"Bye",
				"Massive Exdee"
		};

		CasparQueue<String> casparQueue = new CasparQueue<>(Comparator.comparingInt(String::length));

		Collections.addAll(casparQueue, testData);

		assertEquals(casparQueue.peek(), "Hi");
		assertEquals(casparQueue.indexOf("Goodbye"), 2);
		assertEquals(casparQueue.indexOf("Massive ex"), -1);
	}
}