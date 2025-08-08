
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.altar.jseproject.controllers.ProductController;
import io.altar.jseproject.controllers.ShelfController;

@ApplicationPath("api")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ProductController.class);
        classes.add(ShelfController.class);
        return classes;
    }
}
