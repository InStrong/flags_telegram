import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLHandler {
    private static final String url = "jdbc:mysql://localhost:3306/flags?ftimeCode=false&serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    public void connect(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserRegistered(String userId){
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

    public void registerUser(String userId,String firstName){
        String query = "REPLACE INTO flags.user_score (id,FirstName,score) VALUES ('"+userId+"','"+firstName+"','0')";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void plusPoint(String userName){
        String query = "UPDATE flags.user_score SET score = score + 1 WHERE id='"+userName+"'";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void minusPoint(String userName){
        String query = "UPDATE flags.user_score SET score = score - 1 WHERE id='"+userName+"'";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String upTo20Spaces(String s){
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (str[i]!='_') {
                sb.append(str[i]);
            }
            else sb.append("_");
        }
        do{
            sb.append(" ");
        } while (sb.toString().length()==25);
        return sb.toString();
    }

    public String top10(){
        StringBuilder sb= new StringBuilder();
        int i=1;
        String query = "SELECT * FROM flags.user_score order by score DESC LIMIT 10";

        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                sb.append(i++);
                sb.append(". ");
                sb.append("@");
                sb.append(upTo20Spaces(rs.getString(1)));
                sb.append(" : ").append(rs.getInt(3)).append(" монет");
                sb.append(System.lineSeparator());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    public void addFoundedCountry(String userId,String countryName){
        String query = "INSERT INTO flags.founded_countries (userId,country) VALUES ('"+userId+"','"+countryName+"')";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCountryAlreadyFound(String userId,String countryName){
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

    public int currentScore(String userId){
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

    public void info() {
        String query = "select id,FirstName,score from user_score";

        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                int score = rs.getInt(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
