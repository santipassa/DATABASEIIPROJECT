package proyectobdii;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author santiago
 */
public class DBConnection {

    private Connection connection;
    private String host;
    private String user;
    private String pwd;
    private String bd;
    public DBConnection(String host, String bd, String user, String pwd) {
        try {
            this.host = host;
            this.user = user;
            this.pwd = pwd;
            this.bd=bd;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+bd,user,pwd);
            //this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RioCuartoCamping", "root", "root");

        } catch (Exception e) {

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConection(Connection conection) {
        this.connection = conection;
    }

    public boolean closeConnection() {
        try {
            this.connection.close();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

}
