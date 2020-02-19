package com.example;

// import io.jaegertracing.Configuration;
// import io.jaegertracing.internal.samplers.ConstSampler;
// import io.opentracing.Tracer;
// import io.opentracing.contrib.web.servlet.filter.TracingFilter;
// import io.opentracing.util.GlobalTracer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TracingServletContextListener implements ServletContextListener {
  //private Tracer tracer;

  // public Tracer tracer() {
  //   Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
  //       .withType(ConstSampler.TYPE)
  //       .withParam(1);

  //   Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
  //       .withLogSpans(true);

  //   Configuration config = new Configuration("MyConfig")
  //       .withSampler(samplerConfig)
  //       .withReporter(reporterConfig);

  //   return config.getTracer();
  // }

  // private void ensureTracerLoaded() {
  //   //    tracer = tracer();
  //   //    GlobalTracer.registerIfAbsent(tracer);
  //   Class exampleClass = null;
  //   try {
  //     exampleClass = Class.forName("app.TracerInitializer");
  //   } catch (ClassNotFoundException e) {
  //     e.printStackTrace();
  //   }
  //   try {
  //     exampleClass.newInstance(); // force instantiation of class in tomcat lib
  //   } catch (InstantiationException e) {
  //     e.printStackTrace();
  //   } catch (IllegalAccessException e) {
  //     e.printStackTrace();
  //   }
  // }

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    // ensureTracerLoaded();
    // TracingFilter filter = new TracingFilter(GlobalTracer.get());
    // FilterRegistration.Dynamic reg = servletContextEvent.getServletContext().addFilter("tracingFilter", filter);
    // reg.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
    /*
      webapp registers the actual SqlInterceptor with the registry due to classpath
      reasons (this allows us to keep all the Prometheus/Tracing jars in webapp
      instead of having to stuff in tomcat/lib).
    */
    SqlInterceptorRegistry.getInstance().register(new SqlInterceptorImpl());
    System.out.println("TracingServletContextListener contextInitialized");
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {
    System.out.println("Shutting down!");
  }
}
