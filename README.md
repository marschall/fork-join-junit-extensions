Fork/Join JUnit Extensions
==========================

This project allows you to run JUnit tests in parallel using Java 7 [Fork/Join](http://www.oracle.com/technetwork/articles/java/fork-join-422606.html).

The Maven Surefire Plugin can [achieve the same](http://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html#threadCount) but this project also works for unit tests executed in Eclipse or dynamically generated tests.

Usage
-----

You have to run your tests with `ForkJoinSuite`.

```java
@RunWith(ForkJoinSuite.class)
public class MyTest {

  @Test
  public void test1() {
    // test code
  }
  
  // more tests methods

}
```

Customization
-------------

With  `@ForkJoinParameters` you can to set the actual runner to use (defaults to `JUnit4Builder`) and optionally the parallelism you want to have (defaults to number of CPUs).

```java
@RunWith(ForkJoinSuite.class)
@ForkJoinParameters(runnerBuilder = ParameterizedBuilder.class, parallelism = 2)
public class MyParameterizedTest {

  @Parameter
  String parameter;

  @Test
  public void test1() {
    // test code
  }
  
  // more tests methods

  @Parameters
  public static Collection<Object[]> parameters() {
    // build parameters
  }

}
```
