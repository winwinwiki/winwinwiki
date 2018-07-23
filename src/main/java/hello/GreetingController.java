package hello;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s! at %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public GreetingResponse greeting(@RequestParam(value="name", defaultValue="World") String name) {
        String timeFromDB = testPostgresConnection();
        return new GreetingResponse(counter.incrementAndGet(),
                            String.format(template, name, timeFromDB));
    }

    private String testPostgresConnection() {
        String url = "jdbc:postgresql://localhost/winwindb";
        Properties props = new Properties();
        props.setProperty("user","winwindbuser");
        props.setProperty("password","winwindbpassword");
        //props.setProperty("ssl","true");
        try(Connection conn = DriverManager.getConnection(url, props);
            Statement stmt = conn.createStatement();
        ) {
            ResultSet rs = stmt.executeQuery("select now() as current_time");
            if (rs.next()) {
                return rs.getTime("current_time").toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}