package com.github.marschall.forkjoinjunit;

import org.junit.Test;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.RunWith;

import com.github.marschall.forkjoinjunit.ForkJoinParameters;
import com.github.marschall.forkjoinjunit.ForkJoinSuite;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(ForkJoinSuite.class)
@ForkJoinParameters(runnerBuilder = JUnit4Builder.class, parallelism = 2)
public class ForkJoinSuiteTest {

  @Test
  public void trueTest() {
    assertTrue(true);
  }
  
  @Test
  public void falseTest() {
    assertFalse(false);
  }

}
