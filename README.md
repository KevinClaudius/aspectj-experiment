./build.sh

See glowroot:
http://localhost:4000/

Hit sample echo servlet:
http://localhost:8080/ServletFilterExample/echo


See prometheus metric
http://localhost:8080/ServletFilterExample/metrics

Hitting this will also insert a test value into RDBMS.  Database: Test, Table: TEST

Tomcat archive is checked in for convenience for this sample project and was downloaded from:
https://www.apache.org/dist/tomcat/tomcat-$TOMCAT_MAJOR/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz


Biggest gotcha was the aop.xml needed a file:// at the front to be searched for

We could not get the aop.xml to be registered via the classpath


Four components

aopjar
- Aspectj instumentation, as an example see https://github.com/dsyer/spring-boot-aspectj/blob/master/ctw/src/main/java/com/example/Interceptor.java#L28
- In our aspect, we need the static reference of a class to happen before webapp is loaded

contracts
- Interface and a hook for allowing webapp code to run when jdbc statements are executed (based off the @Aspect)
- Also can be used to see thread pool activity/ request times
- Allows us to put tracing/ etc. code in webapp and not have to be inside of Tomcat Lib, due to classpath/loading issues

webapp (src)
- Code that becomes a WAR deployed on tomcat

weaver javaagent
- Set in the setenv.sh file as part of CATALINA_OPTS
- Also required is specifing the path to the aop.xml
    - Sets up what will be woven, see https://github.com/dsyer/spring-boot-aspectj/blob/master/ctw/src/main/resources/org/aspectj/aop.xml as an example


Next steps:

Have to see the impact on: 
* startup time
** Possibly could cause weird bugs? Weaving methods might be better than proxying, but it could also still be done
* actual runtime calls
** Need end to end tests? Maybe could add aspectjweaver in the junit tests?
* Be careful on what to instrument (need to be careful about java.sql.X methods we instrument), over instrumenting may cause problems
* Need further refinement of contract, worry about threadsafety
** Could make sql interceptor more of a factory, use start and stop on that instance, instead of calling on a singleton instance
