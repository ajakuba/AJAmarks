import com.jakub.ajamarks.config.BeanConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by ja on 11.01.17.
 */
public class Start {

    public static void main(String[] args) {

        new AnnotationConfigApplicationContext(BeanConfiguration.class);


    }
}
