module ruc.portfolio2.application {
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens ruc.portfolio2.application;
    exports ruc.portfolio2.application;
}