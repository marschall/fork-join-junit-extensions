package com.github.marschall.forkjoinjunit;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(ForkJoinSuite.class)
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
