package com.github.marschall.forkjoinjunit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.junit.runners.model.RunnerScheduler;


public final class ForkJoinRunnerScheduler implements RunnerScheduler {
  
  private final ForkJoinPool forkJoinPool;
  private List<ForkJoinTask<?>> tasks;
  
  public ForkJoinRunnerScheduler(ForkJoinPool forkJoinPool) {
    this.forkJoinPool = forkJoinPool;
    this.tasks = new ArrayList<>();
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
