package hello;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.StringUtils;
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

    class DBSettings {
        String host;
        String name;
        String user;
        String password;
    }

    private DBSettings getDBSettingsFromEnv() {
        DBSettings dbSettings = new DBSettings();
        dbSettings.host = getEnvOrDefault("WINWIN_DB_HOST", "localhost");
        dbSettings.name = getEnvOrDefault("WINWIN_DB_NAME", "winwindb");
        dbSettings.user = getEnvOrDefault("WINWIN_DB_USER", "winwindbuser");
        dbSettings.password = getEnvOrDefault("WINWIN_DB_PASSWORD", "winwindbpassword");
        return dbSettings;
    }

    private String getEnvOrDefault(String key, String defaultValue) {
        Map<String, String> env = System.getenv();
        String value = env.get(key);
        if(StringUtils.isEmpty(value)) {
            return defaultValue;
        } else {
            return value;
        }
    }

    private String testPostgresConnection() {
        DBSettings dbSettings = getDBSettingsFromEnv();
        String url = String.format("jdbc:postgresql://%s/%s", dbSettings.host, dbSettings.name);
        Properties props = new Properties();
        props.setProperty("user",dbSettings.user);
        props.setProperty("password",dbSettings.password);
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