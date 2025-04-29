/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UPDATE;

/**
 *
 * @author alvon
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UPDATE{
    public static void updateData(int id, String newName, double newPrice) {
        String url = "jdbc:mysql://localhost/umkm";
        String user = "myusername";
        String password = "mypassword";
        String sql = "UPDATE items SET name = ?, price = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setDouble(2, newPrice);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
