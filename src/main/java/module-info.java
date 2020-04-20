module at.htl.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.tools;

    opens at.htl.project.view to javafx.fxml;
    exports at.htl.project.view;
    exports at.htl.project.controller;
    exports at.htl.project.model;
}