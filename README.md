Fork/Join JUnit Extensions
==========================

This project allows you to run JUnit tests in parallel using Java 7 [Fork/Join](http://www.oracle.com/technetwork/articles/java/fork-join-422606.html).

This complements [Maven Surefire Plugin](http://maven.apache.org/surefire/maven-surefire-plugin/test-mojo.html#threadCount). Maven Surefire Plugin parallelizes at the class level and does not work in Eclipse or dynamically generated tests. This parallelizes tests in a class and works in Eclipse.

When you have lots of test classes where each runs quickly you want to use Maven Surefire Plugin. When you have few test classes that take a lot of time and have more than one test you want to use this.

Usage
-----

```xml
<dependency>
    <groupId>com.github.marschall</groupId>
    <artifactId>fork-join-junit-extensions</artifactId>
    <version>0.1.0</version>
    <scope>test</scope>
</dependency>
```

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
