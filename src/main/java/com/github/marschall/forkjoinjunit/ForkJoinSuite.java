package com.github.marschall.forkjoinjunit;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.junit.runner.Runner;
import org.junit.runners.ParentRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;


public class ForkJoinSuite extends Suite {

  private static final Method METHOD_GET_CHILDREN;
  private final Runner runner;

  static {
    String methodName = "getChildren";
    Class<?> clazz = ParentRunner.class;
    try {
      METHOD_GET_CHILDREN = clazz.getDeclaredMethod(methodName);
    } catch (NoSuchMethodException e) {
      throw new IllegalStateException("no " + methodName + "() method on " + clazz, e);
    }
    METHOD_GET_CHILDREN.setAccessible(true);
  }

  public ForkJoinSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
    super(klass, Collections.<Runner>emptyList());
    ForkJoinParameters forkJoinParameters = getForkJoinParameters(klass);
    ForkJoinPool forkJoinPool = this.buildForkJoinPool(forkJoinParameters);
    RunnerBuilder runnerBuilder = this.getRunnerBuilder(forkJoinParameters);
    try {
      runner = runnerBuilder.runnerForClass(klass);
      recursivelySetScheduler(runner, forkJoinPool);
    } catch (Throwable e) {
      throw new InitializationError(e);
    }
  }

  @Override
  protected List<Runner> getChildren() {
    return Collections.singletonList(this.runner);
  }

  private static void recursivelySetScheduler(Runner runner, ForkJoinPool forkJoinPool) {
    if (runner instanceof ParentRunner) {
      ParentRunner<?> parentRunner = (ParentRunner<?>) runner;
      parentRunner.setScheduler(new ForkJoinRunnerScheduler(forkJoinPool));
      for (Object each : getChildren(parentRunner)) {
        if (each instanceof Runner) {
          Runner child = (Runner) each;
          recursivelySetScheduler(child, forkJoinPool);
        }
      }
    }
  }

  private static List<?> getChildren(Runner runner) {
    try {
      return (List<?>) METHOD_GET_CHILDREN.invoke(runner);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException("could not get children", e);
    }
  }

  private ForkJoinParameters getForkJoinParameters(Class<?> klass) throws InitializationError {
    ForkJoinParameters annotation = klass.getAnnotation(ForkJoinParameters.class);
    if (annotation == null) {
      throw new InitializationError("@" + ForkJoinParameters.class.getSimpleName() + " missing on " + klass);
    }
    return annotation;
  }

  private RunnerBuilder getRunnerBuilder(ForkJoinParameters parameter) throws InitializationError {
    try {
      return parameter.runnerBuilder().newInstance();
    } catch (ReflectiveOperationException e) {
      throw new InitializationError(e);
    }
  }

  private ForkJoinPool buildForkJoinPool(ForkJoinParameters parameter) {
    int parallelism = parameter.parallelism();
    if (parallelism > 0) {
      return new ForkJoinPool(parallelism);
    } else {
      return new ForkJoinPool();
    }
  }

}
