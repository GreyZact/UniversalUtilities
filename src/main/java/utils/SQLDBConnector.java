/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class SQLDBConnector {

    private Debug debug = new Debug("SQLDBConnector: ");
    private String JDBC_DRIVER;
    private String DB_URL;
    private String DB_LOGIN;
    private String DB_PASSWORD;
    private Connection connection;

    public SQLDBConnector() {
        this.JDBC_DRIVER = Config.getKey("JDBC_DRIVER");
        this.DB_URL = Config.getKey("DB_URL");
        this.DB_LOGIN = Config.getKey("DB_LOGIN");
        this.DB_PASSWORD = Config.getKey("DB_PASSWORD");
    }

    public SQLDBConnector(String jdbc_driver, String db_url, String db_login, String db_password) {
        this.JDBC_DRIVER = jdbc_driver;
        this.DB_URL = db_url;
        this.DB_LOGIN = db_login;
        this.DB_PASSWORD = db_password;
    }

    /**
     * Getter for Debug
     *
     * @return debug message prefix
     */
    public Debug getDebug() {
        return debug;
    }

    /**
     * Setter for toggling debugging and writing log to file ON/OFF.
     *
     * @param isOn parameter which decides if class writes messages
     * @param writeToFile parameter which decides if class writes messages to
     * additional file
     */
    public void setDebugging(Boolean isOn, Boolean writeToFile) {
        this.debug.setOn(isOn);
        this.debug.setWriteToFile(writeToFile);
    }

    public SQLDBConnector connect() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        return this;
    }

    public ResultSet execSQL(String sqlQuery) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public void updateSQL(String sqlQuery) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            try {
                connection.rollback();
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(SQLDBConnector.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SQLDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
