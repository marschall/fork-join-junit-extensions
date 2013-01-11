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

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.runners.model.RunnerScheduler;


public final class ForkJoinRunnerScheduler implements RunnerScheduler {
  
  private final ForkJoinPool forkJoinPool;
  private List<ForkJoinTask<?>> tasks;
  
  public ForkJoinRunnerScheduler(ForkJoinPool forkJoinPool) {
    this.forkJoinPool = forkJoinPool;
  }

  @Override
  public void schedule(Runnable childStatement) {
    this.tasks.add(this.forkJoinPool.submit(childStatement));
  }

  @Override
  public void finished() {
    for (ForkJoinTask<?> task : this.tasks) {
      task.join();
    }
  }
  
}
