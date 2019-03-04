import java.sql.*;

public class SQLHandler {
    private static final String url = "jdbc:mysql://localhost:3306/flags?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "fhwheyrfhnjy94";

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

    protected boolean isUserRegistered(String userId){
        int temp=-1;
        String query = "SELECT EXISTS(SELECT * FROM flags.user_score WHERE id='"+userId+"')";

        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                temp=rs.getInt(1);
            }
            if (temp!=0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    protected void registerUser(String userId,String firstName){
        String query = "REPLACE INTO flags.user_score (id,FirstName,score) VALUES ('"+userId+"','"+firstName+"','0')";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void plusPoint(String userName){
        String query = "UPDATE flags.user_score SET score = score + 1 WHERE id='"+userName+"'";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void minusPoint(String userName){
        String query = "UPDATE flags.user_score SET score = score - 1 WHERE id='"+userName+"'";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    protected void addFoundedCountry(String userId,String countryName){
        String query = "INSERT INTO flags.founded_countries (userId,country) VALUES ('"+userId+"','"+countryName+"')";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean isCountryAlreadyFound(String userId,String countryName){
        String query = "select userid,country from flags.founded_countries where userid='"+userId+"'";
        String tempId = "";
        String tempCountryName = "";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                tempId= rs.getString(1);
                tempCountryName= rs.getString(2);
                if (tempId.equals(userId) && tempCountryName.equals(countryName)) return true;
            }


        }catch(SQLException e){
            e.printStackTrace();

        }
        return false;

    }

    protected int currentScore(String userId){
        String query = "select score from user_score where id='"+userId+"'";
        int currentScore=0;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                currentScore = rs.getInt(1);
            }
        }catch(SQLException e){
                e.printStackTrace();

        }
        return currentScore;

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
