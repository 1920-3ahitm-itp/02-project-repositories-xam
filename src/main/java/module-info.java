module at.htl.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;
    requires org.mybatis;

    opens at.htl.xam.view to javafx.fxml;
    exports at.htl.xam.view;
    exports at.htl.xam.controller;
    exports at.htl.xam.model;
}