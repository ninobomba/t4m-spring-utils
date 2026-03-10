package io.github.ninobomba.utils.spring.events.ao;

import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationStartupEventTest {

	@Test
	void constructor_shouldSetSourceAndMessage ( ) {
		Object source = new Object ( );
		String message = "Application starting";
		ApplicationStartupEvent event = new ApplicationStartupEvent ( source, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
	}

	@Test
	void constructorWithClock_shouldSetSourceClockAndMessage ( ) {
		Object source = new Object ( );
		String message = "Application starting with clock";
		Clock clock = Clock.fixed ( Instant.now ( ), ZoneId.systemDefault ( ) );
		ApplicationStartupEvent event = new ApplicationStartupEvent ( source, clock, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
		assertEquals ( clock.instant ( ).toEpochMilli ( ), event.getTimestamp ( ) );
	}
}
