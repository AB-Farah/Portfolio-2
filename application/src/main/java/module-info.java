module ruc.portfolio2.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens ruc.portfolio2.application to javafx.fxml;
    exports ruc.portfolio2.application;
}