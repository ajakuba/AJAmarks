import com.jakub.ajamarks.config.BeanConfiguration;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.services.showdataservices.MarkService;
import lombok.extern.slf4j.Slf4j;
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

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        MarkService markService = annotationConfigApplicationContext.getBean("markService", MarkService.class);

        Mark mark = new Mark();
        mark.setMarkName("sasas");
        mark.setMarkValue(7);

        markService.saveMark(mark);



    }
}
