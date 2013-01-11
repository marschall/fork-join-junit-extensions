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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertNotNull;

@RunWith(ForkJoinSuite.class)
@ForkJoinParameters(runnerBuilder = ParameterizedBuild.class, parallelism = 2)
public class ForkJoinSuiteParameterizedTest {
  
  private final Integer parameter;

  public ForkJoinSuiteParameterizedTest(Integer parameter) {
    this.parameter = parameter;
  }
  
  @Test
  public void testNotNull() {
    assertNotNull(this.parameter);
  }
  
  @Parameters
  public static Collection<Object[]> parameters() {
    int parameterCount = 10000;
    List<Object[]> parameters = new ArrayList<>(parameterCount);
    for (int i = 0; i < parameterCount; i++) {
      parameters.add(new Object[]{i});
    }
    return parameters;
  }

}
