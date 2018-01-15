/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.utils;
import java.sql.*;
/**
 *
 */
public interface BaseUtils {
    
    public boolean getConnection() throws SQLException;
    /**
     *
     * @return
     * @throws SQLException
     */
    public boolean closeConnection() throws SQLException;
}
