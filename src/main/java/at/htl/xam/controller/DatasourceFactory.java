package at.htl.xam.controller;

import org.apache.derby.jdbc.ClientDataSource;
import javax.sql.DataSource;

public class DatasourceFactory {

    static final String db = "db";
    static final String username = "app";
    static final String password = "app";

    public static DataSource getDataSource() {
        ClientDataSource dataSource = new ClientDataSource();
        dataSource.setDatabaseName(db);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}