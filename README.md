# Tools 4 Monkeys - Spring Utilities (t4m-spring-utils)

`t4m-spring-utils` is a comprehensive set of Spring-specific utilities for Java projects, designed to simplify development and provide reusable components for common Spring Boot tasks. It extends the core functionality provided by `t4m-java-utils`.

## Project Overview

- **Name**: Tools for Monkeys - Spring Utilities
- **Group ID**: `io.github.ninobomba`
- **Artifact ID**: `t4m-spring-utils`
- **Version**: `1.0.0.40`
- **License**: Apache License 2.0
- **Java Version**: 21
- **Maven Central**: [t4m-spring-utils on Central](https://central.sonatype.com/artifact/io.github.ninobomba/t4m-spring-utils)

## Key Features

### API Response Builders
- **Generic Record-based Responses**: Lightweight and immutable API response wrappers using Java records (`ApiRecordResponse`).
- **HATEOAS Support**: Simplified creation of HATEOAS-compliant success (`ApiResponseHateoasSuccess`) and error (`ApiResponseHateoasError`) responses.
- **Response Creator**: Utility `ApiResponseCreator` for standardized response generation.
- **Request ID Tracking**: Retrieve and manage unique request IDs for distributed tracing (`RequestIdRetriever`, `IKVWebRequest`).

### Web & HTTP Utilities
- **Multi-API Support**: Comprehensive request/response data utilities for both modern `jakarta.servlet` and legacy `javax.servlet` environments.
- **User Agent Parsing**: Advanced device and browser detection integrated with `uadetector` (`UADeviceParser`).
- **Parameter Sanitization**: Automatic sanitization of HTTP parameters to prevent common web attacks (`HttpParameterSanitizer`).
- **MDC Support**: Simplified integration with SLF4J Mapped Diagnostic Context (MDC) for contextual logging (`EncodingService`).

### Spring Integration
- **Lifecycle Events**: Specialized application events for monitoring `Startup` and `Shutdown` phases.
- **Security Monitoring**: Built-in event triggers for detecting security incidents like SQL Injection (`ApplicationSqlInjectionAttackEvent`) and Cross-Site Scripting (`ApplicationXSSAttackEvent`).
- **i18n Support**: Utilities for handling localized field errors in multi-language Spring applications (`I18nFieldsErrorsSupport`).

### Data & Persistence
- **JPA Auditing Base**: A standardized `BaseEntity` providing automatic creation and modification timestamp tracking for JPA entities.

## Architecture

The library is organized into specialized packages:

- **`api`**: Modern API development utilities.
    - `response`: Standardized response models (Record-based, HATEOAS) and request ID management.
    - `token`: Flexible interfaces for user token validation (`IUserTokenValidator`).
- **`data`**: Persistence-related utilities, including auditable base entities for JPA.
- **`spring`**: Deep integration with Spring Framework internals.
    - `events`: Custom application lifecycle and security-related events.
    - `i18n`: Robust internationalization and field error message support.
- **`web`**: Low-level web and HTTP handling.
    - `agent`: User agent parsing, device detection, and HTTP parameter sanitization.
    - `http`: Utility classes for extracting data from `jakarta` and `javax` servlet requests/responses.
    - `mdc`: Logging context utilities for request-based tracing.

## Getting Started

Add the dependency to your Maven project:

```xml
<dependency>
    <groupId>io.github.ninobomba</groupId>
    <artifactId>t4m-spring-utils</artifactId>
    <version>1.0.0.40</version>
</dependency>
```

## Requirements

- **Java**: 21 or higher
- **Maven**: 3.6 or higher
- **Dependency**: `t4m-java-utils` (automatically included)

## Dependencies

The library integrates several key frameworks:

- **Spring Framework**: Core, Context, TX, Data JPA, Hateoas.
- **Servlet APIs**: Jakarta Servlet 6.0 and Javax Servlet support.
- **User Agent Detection**: `uadetector`.
- **Lombok**: For boilerplate reduction (provided scope).
- **t4m-java-utils**: The core utility library.

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome. Please feel free to submit a Pull Request on [GitHub](https://github.com/ninobomba/OSSRH-77248).

## Contact

- **Developer**: Fernando Farfan
- **GitHub**: [ninobomba](https://github.com/ninobomba)
