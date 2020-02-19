package com.example;

import io.prometheus.client.Counter;

// TODO: Not threadsafe
public class SqlInterceptorImpl implements SqlInterceptor {

  private static final Counter sqlCount = Counter.build().name("sql_count").help("help me").register();

  @Override
  public void start() {
    System.out.println("In start");
  }

  @Override
  public void end() {
    System.out.println("In end");
    sqlCount.inc();
  }
}
