package DELETE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HAPUS_DATA {
    public static void deleteData(int id) {
        String url = "jdbc:mysql://localhost/umkm";
        String user = "myusername";
        String password = "mypassword";
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
