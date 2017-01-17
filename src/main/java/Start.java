import com.jakub.ajamarks.config.BeanConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by ja on 11.01.17.
 */
public class Start {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {

        new AnnotationConfigApplicationContext(BeanConfiguration.class);

    }
}
