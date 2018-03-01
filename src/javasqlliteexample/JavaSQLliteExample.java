/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasqlliteexample;

/**
 *
 * @author santiago
 */
public class JavaSQLliteExample 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        SQLiteJDBCDriverConnection conn = new SQLiteJDBCDriverConnection();
        conn.insertClient("Andy", "Cruz");
        conn.selectAllClients();
    }
    
}
