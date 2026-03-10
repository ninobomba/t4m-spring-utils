package io.github.ninobomba.utils.spring.events.security;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class ApplicationXSSAttackEvent extends ApplicationEvent {

	private final String message;


	public ApplicationXSSAttackEvent ( Object source, String message ) {
		super ( source );
		this.message = message;
	}

	public ApplicationXSSAttackEvent ( Object source, Clock clock, String message ) {
		super ( source, clock );
		this.message = message;
	}
}
