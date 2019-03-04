import java.sql.*;

public class SQLHandler {
    private static final String url = "jdbc:mysql://localhost:3306/flags?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "shutkobot";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    protected void connect(){
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void disconnect(){
        try {
            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void registerUser(String userName,String firstName){
        String query = "REPLACE INTO flags.user_score (id,FirstName) VALUES ('"+userName+"','"+firstName+"')";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void plusPoint(String userName, String firstName){
        String query = "UPDATE flags.user_score SET score = score + 1 WHERE id='"+userName+"'";
        registerUser(userName,firstName);
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void minusPoint(String userName, String firstName){
        String query = "UPDATE flags.user_score SET score = score - 1 WHERE id='"+userName+"'";
        registerUser(userName,firstName);
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void info() {
        String query = "select id,FirstName,score from user_score";

        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                int score = rs.getInt(3);
                System.out.printf("id: %s, name: %s, score: %d %n", id, firstName, score);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
