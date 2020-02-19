package com.example;

public class SqlInterceptorRegistry {
  public static SqlInterceptorRegistry DEFAULT = new SqlInterceptorRegistry();

  private SqlInterceptor instance;

  static {
    System.out.println(
        "Sql interceptor registry loaded from:" + SqlInterceptorRegistry.class.getClassLoader());
  }

  public void register(SqlInterceptor interceptor) {
    this.instance = interceptor;
  }

  public SqlInterceptor get() {
    return this.instance;
  }

  public static SqlInterceptorRegistry getInstance() {
    return DEFAULT;
  }
}
