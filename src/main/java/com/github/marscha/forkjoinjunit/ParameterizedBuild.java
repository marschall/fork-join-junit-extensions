/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.github.marscha.forkjoinjunit;

import org.junit.runner.Runner;
import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerBuilder;


public final class ParameterizedBuild extends RunnerBuilder {

  @Override
  public Runner runnerForClass(Class<?> testClass) throws Throwable {
    return new Parameterized(testClass);
  }

}