# 注意

- 若要在此处新建文件夹 templates，需要将 spring-cloud-netflix-eureka-server-*.*.*.*.jar 中的页面拷贝进来。否则项目原有页面不能正常访问

- 如果异常
    ~~~
    java.io.FileNotFoundException: class path resource [templates/] cannot be resolved to absolute file path because it does not reside in the file system: jar:file:/D:/Apache/apache-maven-repo/org/springframework/cloud/spring-cloud-netflix-eureka-server/2.2.6.RELEASE/spring-cloud-netflix-eureka-server-2.2.6.RELEASE.jar!/templates/
    ~~~
    - 仅在日志等级为 <kbd>web: debug</kbd> 时，才会显示，可直接忽略。
    - 若不想看到此异常，需要将 spring-cloud-netflix-eureka-server-*.*.*.*.jar 中的 FreeMarker 页面拷贝到此项目中。
