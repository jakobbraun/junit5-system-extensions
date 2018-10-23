# junit5-system-extensions (J5SE)

This project provides a set of JUnit 5 extension that allow testing behavior related to functions related to `java.lang.System` (e.g. asserting exit status codes). 

## Acknowledgments

The extensions in this project were inspired by a set of JUnit4 rules called "[System Rules](https://stefanbirkner.github.io/system-rules/)" which were written by Stefan Brikner and licensed under the Common Public License 1.0 (CPL).

## Runtime Dependencies

| Dependency                                                                   | Purpose                                                | License                       |
-------------------------------------------------------------------------------|--------------------------------------------------------|--------------------------------
| [JUnit5](https://junit.org/junit5/)                                          | Unit test framework                                    | Eclipse Public License v2.0   |

## Usage

### Asserting `System.exit(int)` Calls

To trap and check calls to `System.exit(int)` follow these steps:

1. Extend the test class with the class `ExitGuard`
2. Use `AssertExit.assertExit(Runnable)` or `AssertExit.assertExit(int, Runnable)` to check for exit calls

Example:

```java
import static org.itsallcode.junit.AssertExit.assertExit;

import org.itsallcode.junit.sysextensions.ExitGuard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ExitGuard.class)
class TestSystemExit
{
    @Test
    void testSystemExit()
    {
        assertExit(() -> System.exit(1));
    }
}
```

Note that in order to be able to trap system exit, the `ExitGuard` temporarily replaces the existing security manager (if any).

## Asserting Data Sent to `System.out`

To capture data sent to `System.out`, follow these steps:

1. Extend the test class with `SystemOutGuard`
2. Add a parameter of type `CapturingOutputStream` to the test method (or the before-all-method)
3. Activate capturing on the stream
4. Run code under test
5. Check captured data

Example:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.itsallcode.io.CapturingOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SystemOutGuard.class)
class TestSystemOut
{
    @Test
    void testCaptureA(final CapturingOutputStream stream)
    {
        stream.capture();
        final String expected = "This text must be captured.";
        System.out.print(expected);
        assertEquals(stream.getCapturedData(), expected);
    }
}
```

## Contributing, Feature Requests and Error Reporting

Please check our [contribution guide](.github/CONTRIBUTING.md) to learn how you can help with the project, report errors or request features.

## Development

### Build Time Dependencies

The list below show all build time dependencies in alphabetical order. Note that except the Maven build tool all required modules are downloaded automatically by Maven.

| Dependency                                                                   | Purpose                                                | License                       |
-------------------------------------------------------------------------------|--------------------------------------------------------|--------------------------------
| [Apache Maven](https://maven.apache.org/)                                    | Build tool                                             | Apache License 2.0            |

### Essential Build Steps

* `git clone https://github.com/itsallcode/junit5-system-extensions.git`
* Run `mvn test` to run unit tests.
* Run `mvn package` to create the JAR file.
