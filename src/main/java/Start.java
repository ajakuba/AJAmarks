import com.jakub.ajamarks.config.BeanConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * Created by ja on 11.01.17.
 */
@Slf4j

public class Start {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {

        log.error("");

        new AnnotationConfigApplicationContext(BeanConfiguration.class);

    }
}
