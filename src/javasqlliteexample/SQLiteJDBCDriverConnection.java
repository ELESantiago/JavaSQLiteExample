package javasqlliteexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author santiago
 */
public class SQLiteJDBCDriverConnection 
{
    private final String connectionString = "jdbc:sqlite:";
    private final String schemaName = "lotes.db";
    
    public SQLiteJDBCDriverConnection() 
    {
        Connection conn = null;
        try
        {
            conn = connect();
            checkClients(conn);
            
            System.out.println("La conexion a SQLite ha sido establecida");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally 
        {
            closeConnection(conn);
        }
    }
    
    /**
     * Revisa si existe la tabla de Clientes
     * si no existe la crea
     * @param conn
     * @throws SQLException 
     */
    private void checkClients(Connection conn) throws SQLException
    {
        String sql = "CREATE TABLE IF NOT EXISTS Clientes(\n"
                + "id integer PRIMARY KEY, \n"
                + "nombrePrimario text NOT NULL,\n"
                + "apellidos text NOT NULL\n"
                + ");";
        Statement stmnt = conn.createStatement();
        stmnt.execute(sql);
    }
    
    public boolean insertClient(String firstName, String lastName)
    {
        Connection conn = null;
        String sql = "INSERT INTO Clientes(nombrePrimario, apellidos) VALUES(?, ?)";
        try 
        {
            conn = connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        finally 
        {
            closeConnection(conn);
        }
        
        return true;
    }
    
    public void selectAllClients()
    {
        String sql = "SELECT id, nombrePrimario, apellidos FROM CLIENTES";
        Connection conn = null;
        try 
        {
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " : " + rs.getString("nombrePrimario") + " : " + rs.getString("apellidos"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally 
        {
            closeConnection(conn);
        }
    }
    
    private Connection connect() throws SQLException
    {
        return DriverManager.getConnection(connectionString + schemaName);
    }
    
    private void closeConnection(Connection conn)
    {
        try 
        {
            if(conn != null)
                conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
