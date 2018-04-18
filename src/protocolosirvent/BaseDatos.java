package protocolosirvent;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Miguel Angel Flores Sirvent
 */
public class BaseDatos {
    //Atributos
    String url;
    String usuarioBD;
    String password;

    /**
     * Constructor parametrizado.
     * @param url
     * @param usuarioBD
     * @param password 
     */
    public BaseDatos(String url, String usuarioBD, String password) {
        this.url = url;
        this.usuarioBD = usuarioBD;
        this.password = password;
    }

    public BaseDatos() {}
    
    public void actualizarBD(){
        Connection conexion = null;
        PreparedStatement ps = null;
        
        try{
            System.out.println("Obteniendo IP...");
            IpPublica ip = new IpPublica();
           
            conexion = (Connection) DriverManager.getConnection(getUrl(), getUsuarioBD(), getPassword());
            
            ps = (PreparedStatement) conexion.prepareStatement("CREATE TABLE IF NOT EXISTS ip");
            ps.executeUpdate();
            
            ps = (PreparedStatement) conexion.prepareStatement("DELETE FROM ip WHERE 1");
            ps.executeUpdate();
            
            ps = (PreparedStatement) conexion.prepareStatement("INSERT INTO ip VALUES (?)");
            ps.setString(1, ip.getIp());
            ps.executeUpdate();

            conexion.close();
            System.out.println("IP actualizada correctamente, proxima actualizacion en 5 minutos...");
            
        }catch (IOException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void configurarBD(String servidor, String nombreBD, String usuario, String password){
        setUrl("jdbc:mysql://"+servidor+"/"+nombreBD);
        setUsuarioBD(usuario);
        setPassword(password);
    }
    
    public void iniciar(){
        while(true){
            try {
                
                actualizarBD();
                Thread.sleep(300000);
                
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
