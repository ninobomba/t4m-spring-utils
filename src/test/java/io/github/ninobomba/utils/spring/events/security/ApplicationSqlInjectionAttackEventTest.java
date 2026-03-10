package io.github.ninobomba.utils.spring.events.security;

import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationSqlInjectionAttackEventTest {

	@Test
	void constructor_shouldSetSourceAndMessage ( ) {
		Object source = new Object ( );
		String message = "SQL Injection detected";
		ApplicationSqlInjectionAttackEvent event = new ApplicationSqlInjectionAttackEvent ( source, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
	}

	@Test
	void constructorWithClock_shouldSetSourceClockAndMessage ( ) {
		Object source = new Object ( );
		String message = "SQL Injection detected with clock";
		Clock clock = Clock.fixed ( Instant.now ( ), ZoneId.systemDefault ( ) );
		ApplicationSqlInjectionAttackEvent event = new ApplicationSqlInjectionAttackEvent ( source, clock, message );

		assertEquals ( source, event.getSource ( ) );
		assertEquals ( message, event.getMessage ( ) );
		assertEquals ( clock.instant ( ).toEpochMilli ( ), event.getTimestamp ( ) );
	}
}
