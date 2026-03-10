package io.github.ninobomba.utils.spring.data;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

	private static class TestEntity extends BaseEntity { }

	@Test
	void prePersist_shouldSetDefaultValues ( ) {
		TestEntity entity = new TestEntity ( );
		entity.prePersist ( );

		assertEquals ( "ENABLED", entity.getStatus ( ) );
		assertEquals ( "SYSTEM", entity.getCreatedBy ( ) );
		assertEquals ( "SYSTEM", entity.getLastModifiedBy ( ) );
		assertNotNull ( entity.getCreatedDate ( ) );
		assertNotNull ( entity.getLastModifiedDate ( ) );
	}

	@Test
	void prePersist_shouldNotOverwriteExistingValues ( ) {
		TestEntity entity = new TestEntity ( );
		entity.setStatus ( "DISABLED" );
		entity.setCreatedBy ( "USER1" );
		Instant now = Instant.now ( );
		entity.setCreatedDate ( now );
		
		entity.prePersist ( );

		assertEquals ( "DISABLED", entity.getStatus ( ) );
		assertEquals ( "USER1", entity.getCreatedBy ( ) );
		assertEquals ( now, entity.getCreatedDate ( ) );
	}

	@Test
	void preUpdate_shouldUpdateLastModifiedDate ( ) {
		TestEntity entity = new TestEntity ( );
		Instant oldDate = Instant.now ( ).minusSeconds ( 100 );
		entity.setLastModifiedDate ( oldDate );
		
		entity.preUpdate ( );

		assertNotEquals ( oldDate, entity.getLastModifiedDate ( ) );
		assertTrue ( entity.getLastModifiedDate ( ).isAfter ( oldDate ) );
	}
}
