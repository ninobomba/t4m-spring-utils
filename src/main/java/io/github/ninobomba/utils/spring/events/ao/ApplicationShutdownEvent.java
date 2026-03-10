package io.github.ninobomba.utils.spring.events.ao;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;


@Getter
public class ApplicationShutdownEvent extends ApplicationEvent {

	private final String message;

	public ApplicationShutdownEvent ( Object source, Clock clock, String message ) {
		super ( source, clock );
		this.message = message;
	}

	public ApplicationShutdownEvent ( Object source, String message ) {
		super ( source );
		this.message = message;
	}

}
