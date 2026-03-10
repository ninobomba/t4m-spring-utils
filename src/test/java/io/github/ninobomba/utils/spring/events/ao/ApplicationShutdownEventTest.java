package io.github.ninobomba.utils.spring.events.ao;

import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationShutdownEventTest {

	@Test
	void constructor_shouldSetSourceAndMessage ( ) {
		Object source = new Object ( );
		String message = "Application shutting down";
		ApplicationShutdownEvent event = new ApplicationShutdownEvent ( source, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
	}

	@Test
	void constructorWithClock_shouldSetSourceClockAndMessage ( ) {
		Object source = new Object ( );
		String message = "Application shutting down with clock";
		Clock clock = Clock.fixed ( Instant.now ( ), ZoneId.systemDefault ( ) );
		ApplicationShutdownEvent event = new ApplicationShutdownEvent ( source, clock, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
		assertEquals ( clock.instant ( ).toEpochMilli ( ), event.getTimestamp ( ) );
	}
}
