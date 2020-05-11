module xam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;
    requires org.mybatis;

    opens at.htl.xam to javafx.fxml;
    exports at.htl.xam;
}