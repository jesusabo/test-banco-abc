package pe.com.testquarkus.tools;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.h2.tools.Server;

@Startup
@ApplicationScoped
public class H2Console {

    @PostConstruct
    public void init() {
        try {
            Server.createWebServer("-web", "-webAllowOthers", "-ifNotExists", "-webPort", "8082").start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
