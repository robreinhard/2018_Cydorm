package experimental_server.demo;
import org.springframework.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
 
import javax.sql.DataSource;
 
@Configuration
public class Java_sql {
 
    private static final String QUERY_FIND_RESIDENTS =
            "SELECT " +
                "name, " +
                "email_address, " +
                "street_address " +
            "FROM RESIDENTS " +
            "ORDER BY street_address ASC";
 
    @Bean
    ItemReader<Resident> databaseXmlItemReader(DataSource dataSource) {
        JdbcCursorItemReader<Residents> databaseReader = new JdbcCursorItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_RESIDENTS);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(Residents.class));
 
        return databaseReader;
    }
}