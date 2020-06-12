package at.htl.xam.database;


import at.htl.xam.controller.DatasourceFactory;
import org.apache.ibatis.jdbc.ScriptRunner;


import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SqlRunner {

    private static final String SCRIPT_PROPERTIES_PATH = "sql/script-files.properties";

    public static void main(String[] args) {

        run();
    }

    public static void run() {
        try {
            Properties scriptProperties = new Properties();
            scriptProperties.load(new FileInputStream(SCRIPT_PROPERTIES_PATH));


            DataSource dataSource = DatasourceFactory.getDataSource();
            Connection conn = dataSource.getConnection();
            System.out.println("Connection established......");
            ScriptRunner sr = new ScriptRunner(conn);
            sr.setLogWriter(null);

            for (String file : scriptProperties.stringPropertyNames()) {
                Reader reader = new BufferedReader(new FileReader(scriptProperties.getProperty(file)));
                sr.runScript(reader);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}