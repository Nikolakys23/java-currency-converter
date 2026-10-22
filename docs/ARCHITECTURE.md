# Architecture

## Current responsibilities

The foundation is one non-modular Maven project targeting Java 21 and JavaFX 21.
It contains two application classes:

- `CurrencyConverterApp`: JavaFX entry point, application lifecycle, scene and stage.
- `ui.BootstrapView`: the basic layout and implementation-status message.

There are no API, domain or controller implementations yet. JUnit Jupiter is
configured for future tests. No background worker is needed for the static window.

## Planned boundaries

| Area | Responsibility | Dependencies |
| --- | --- | --- |
| `api` | Rate requests, response parsing, metadata, timeouts and failure handling | Java HttpClient; no JavaFX controls |
| `domain` | Currency/rate values, amount rules, decimal conversion and rounding | Plain Java; no HTTP or JavaFX |
| `ui` | Views, controls, user interactions, validation feedback and presentation | JavaFX and the agreed API/domain contracts |

Use `java.net.http.HttpClient` from the JDK for HTTP work. Verify the Frankfurter
v2 contract before choosing how to parse JSON; do not add a parsing dependency
without a concrete requirement. The planned pair-rate endpoint is
<https://api.frankfurter.dev/v2/rate/EUR/USD>.

WS1 coordinates the smallest concrete contracts needed by the workstreams:
currency identifiers, base/quote rate direction, `BigDecimal` values, rate date,
amount constraints, rounding and error results. Record those agreements before
parallel feature work. Avoid interfaces, services or model classes added only
to reserve names.

## Expected data flow

1. The controller reads selections and validates input on the JavaFX thread.
2. It schedules a rate request away from that thread using a small JavaFX task
   or executor chosen when the behavior is implemented.
3. The API client returns the agreed rate data or a useful failure.
4. Domain logic performs decimal conversion with the documented rounding policy.
5. The controller updates the view on the JavaFX thread and restores usable controls.

Do not wait for network work with `get()`, `join()` or synchronous requests on
the JavaFX thread. Handle loading, retries, stale responses and shutdown when
implementing requests. Deterministic tests should use fixed data or local HTTP
fixtures rather than relying on the live API.

## Windows compatibility

Use portable paths and normal Maven resources. JavaFX native dependencies are
resolved for the build host, so build Windows distributions on Windows. CI
compilation on Windows does not replace a Windows 11 desktop check.

The current JAR does not bundle a Java runtime or provide an installer. The
packaging Issue will select a minimal JDK 21 `jpackage` approach, document required
tools and verify a clean Windows 11 environment without a development JDK.
Module configuration is not required by the current foundation; add it only if
the selected packaging approach needs it.
