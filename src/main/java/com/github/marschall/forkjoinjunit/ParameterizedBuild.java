package com.github.marschall.forkjoinjunit;

import org.junit.runner.Runner;
import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerBuilder;


public final class ParameterizedBuild extends RunnerBuilder {

  @Override
  public Runner runnerForClass(Class<?> testClass) throws Throwable {
    return new Parameterized(testClass);
  }

}
