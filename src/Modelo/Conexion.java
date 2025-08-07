
package Modelo;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String base ="tienda";
    private final String user ="root";
    private final String password ="Panconqueso12";
    private final String url ="jdbc:mysql://localhost:3306";
    private Connection conexion = null;
    public Connection getConexion() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(this.url,this.user,this.password);
        } catch (SQLException e ) {
            System.err.print(e);
        }
        return conexion;
    }
}
