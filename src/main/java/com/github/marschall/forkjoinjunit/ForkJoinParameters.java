package com.github.marschall.forkjoinjunit;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.runners.model.RunnerBuilder;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Inherited
public @interface ForkJoinParameters {
  
  Class<? extends RunnerBuilder> runnerBuilder();
  
  /**
   * The parallelism level. Leaving this out will default to the number
   * of avaialble processor.
   * 
   * @see java.util.concurrent.ForkJoinPool#ForkJoinPool(int)
   * @return the parallelism level
   */
  int parallelism() default -1;

}
