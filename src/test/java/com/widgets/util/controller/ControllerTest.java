package com.widgets.util.controller;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void testSyncControllerWithoutEventInheritance() {

		SynchronousController c = SynchronousController.Singleton.INSTANCE
				.instance();
		final AtomicInteger countA = new AtomicInteger();
		final AtomicInteger countB = new AtomicInteger();
		c.addListener(EventA.class, new ControllerListener<EventA>() {

			@Override
			public void event(EventA event) {
				countA.incrementAndGet();
			}
		});
		c.addListener(EventB.class, new ControllerListener<EventB>() {

			@Override
			public void event(EventB event) {
				countB.incrementAndGet();
			}
		});
		c.event(new EventA());
		assertEquals(1, countA.get());
		assertEquals(0, countB.get());
		c.event(new EventB());
		assertEquals(1, countA.get());
		assertEquals(1, countB.get());
	}

	@Test
	public void testControllerWithEventInheritance() {
		SynchronousController c = SynchronousController.Singleton.INSTANCE
				.instance();
		final AtomicInteger countA = new AtomicInteger();
		final AtomicInteger countSuper = new AtomicInteger();
		c.addListener(EventA.class, new ControllerListener<EventA>() {

			@Override
			public void event(EventA event) {
				countA.incrementAndGet();
			}
		});
		c.addListener(EventSuper.class, new ControllerListener<EventSuper>() {

			@Override
			public void event(EventSuper event) {
				countSuper.incrementAndGet();
			}
		});
		c.event(new EventA());
		assertEquals(1, countA.get());
		assertEquals(0, countSuper.get());
		c.event(new EventSuper());
		assertEquals(2, countA.get());
		assertEquals(1, countSuper.get());

	}

	private static class EventA implements Event {

	}

	private static class EventB implements Event {

	}

	private static class EventSuper extends EventA {

	}

}
