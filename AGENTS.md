# AGENTS.md

## Project Context
**Tools for Monkeys (T4M)** is a Java Commons library providing utilities for ID generation, API responses, persistence, and more.
- **Modules**:
    - **t4m-java-utils**: Pure Java utilities with minimal external dependencies.
    - **t4m-spring-utils**: Spring-specific utilities, depending on `t4m-java-utils`.
- **Java Version**: 21 (Uses modern features like records, sealed interfaces, switch expressions)
- **Build System**: Maven
- **Frameworks**: Spring Boot (components), Lombok, Jackson

## Essential Commands

### Build
Use Maven directly. Note the parallel build and skip tests flags often used.
```bash
# Recommended build command (skips tests)
mvn clean install -DskipTests=true -T 1C
```

### Test
```bash
# Run all tests
mvn test

# Run specific test class
mvn -pl :t4m-commons -am test -Dtest=JsonUtilsTest
```

## Code Style & Conventions
**CRITICAL**: This project uses a distinct spacing style that you **MUST** preserve when editing files.

### Formatting Rules
- **Spaces inside parentheses**: `if ( condition )`, `method ( arg )`
- **Spaces around generics**: `List < String >`, `class MyClass < T >`
- **Spaces in records/constructors**: `record Success < T >( T data )`
- **Lombok**: Heavily used (`@Getter`, `@EqualsAndHashCode`, `@Builder`).
- **Final**: Classes are often `final`.

**Example**:
```java
public sealed interface OperationResult < T >
    permits OperationResult.Success, OperationResult.Failure {

    static < T > OperationResult < T > success ( T value ) {
        return new Success <> ( value );
    }
}
```

### Architecture Patterns
- **Result Pattern**: Uses `OperationResult<T>` (sealed interface) instead of exceptions for flow control.
- **Exceptions**: Prefers `RuntimeException` hierarchies located in `io.github.ninobomba.commons.exceptions`.
- **Singletons/Factories**: Common patterns for utilities (e.g., `IdGeneratorSnowFlakeSupport.getINSTANCE()`).

## Testing Strategy
- **Framework**: JUnit 5 + AssertJ + Mockito.
- **Visibility**: Test classes and methods are typically package-private (default visibility).
- **Assertions**: Mix of JUnit assertions (`assertNotNull`), AssertJ, and Java `assert` keyword.
- **Location**: `src/test/java` mirroring the source package structure.

## Gotchas & Observations
1.  **Missing Packages**: The `notification` package is missing from `src/main/java` despite being mentioned in older README versions. It has been removed from the main documentation.
2.  **Commented Tests**: Some tests may be commented out (e.g., in `IdGeneratorSnowFlakeSupportTest`). Check `git blame` or context before enabling.
3.  **Strict Formatting**: The Edit tool may fail if you don't match the extensive whitespace exactly. **Always read the file first** and copy the exact spacing.
4.  **Java 21**: You can and should use modern Java features (records, patterns).

## Directory Structure
- `t4m-java-utils/src/main/java/io/github/ninobomba/utils/java/`
    - `api/`: Geolocation, IP.
    - `architecture/`: Port/Adapter patterns for Web and Persistence.
    - `data/`: ETL actions, Mappers, Persistence/Service interfaces.
    - `id/`: Snowflake, UUID, ULID, and Queue/HashSet based generators.
    - `patterns/`: Lazy loading and OperationResult/Result patterns.
    - `exceptions/`: Centralized exception handling, abstract factories, and message builders.
    - `validator/`: Custom annotations and validators (Age, Email, FutureDate, Password, Phone, etc.).
- `t4m-spring-utils/src/main/java/io/github/ninobomba/utils/spring/`
    - `api/`: Response Builders (Success/Error/HATEOAS), Token validation.
    - `data/`: Auditable entities/DTOs.
    - `web/`: HTTP (Jakarta/Javax), User Agent device parsing, and MDC/Filtering.
    - `spring/`: Integration with Spring events and i18n support.
- `*/src/test/java/io/github/ninobomba/utils/`: Comprehensive test suite mirroring source.
- `scripts/`: Development and deployment utilities.
