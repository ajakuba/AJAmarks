import com.jakub.ajamarks.config.DataBaseConfiguration;
import com.jakub.ajamarks.config.StartConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by ja on 11.01.17.
 */

public class Start {

    public static void main(String[] args) {
       new AnnotationConfigApplicationContext(StartConfiguration.class, DataBaseConfiguration.class);
    }
}
