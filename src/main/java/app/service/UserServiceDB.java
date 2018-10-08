package app.service;

import app.entity.Sex;
import app.entity.User;
import app.connection.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class UserServiceDB implements IUserServiceDB {
    private ConnectionDB conn;

    @Autowired
    public UserServiceDB(ConnectionDB conn){
        this.conn=conn;
    }


    public void save(User user){
        String sql = "begin; INSERT INTO users (name, surname, patronymic, birthdate, sex, organizationid) VALUES (?, ?, ?, ?,  cast(? as sex_type) , ?); commit;";

        try {
            PreparedStatement preparedStatement = this.conn.getConn().prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setDate(4, new java.sql.Date(user.getBirthDate().getTime()) );



            String sex=null;

            if (user.getSex() == Sex.MAN ){
                sex="MAN";
            }else if ((user.getSex() == Sex.WOMAN )){
                sex="WOMAN";
            }

            preparedStatement.setString(5,  sex);
//            preparedStatement.setString(5,  Sex.MAN.toString());
//            preparedStatement.setObject();
           // preparedStatement..setObject(5, Sex.MAN);

            preparedStatement.setInt(6, user.getOrganization());

            System.out.println("LINE 50");
            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }


    }

    public User getUser(String name){

        String sql = "select * from users where name=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;



        User user  = new User() ;

        try{
            preparedStatement = this.conn.getConn().prepareStatement(sql);
            preparedStatement.setString(1, name);


            resultSet = preparedStatement.executeQuery();



            resultSet.next();

             user.setName(resultSet.getString("name"));
             user.setSurname(resultSet.getString("surname"));
             user.setPatronymic(resultSet.getString("patronymic"));
             user.setBirthDate(new Date(resultSet.getTimestamp("birthdate").getTime()));

             System.out.println("sex is " + resultSet.getString("sex"));

//             user.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));

             if (resultSet.getString("sex").equals("MAN") ){
                 user.setSex(Sex.MAN);
             }else if(resultSet.getString("sex").equals("WOMAN")){
                user.setSex(Sex.WOMAN);
             }

             user.setOrganization(resultSet.getInt("organizationid"));



             return user;


        }catch (SQLException ex){
            System.out.println("LINE 106");
            System.out.println(ex.getMessage());
        }

        return null;

    }

    public Connection getConn(){
        return conn.getConn();
    }

    public Collection<User> getUsers(){
        Collection<User> colUsers = new ArrayList<User>();

        String sql = "select us.name, us.surname, us.patronymic, us.birthdate, us.organizationid, org.nameorganization  from users as us join organization as org on  org.id = us.organizationid;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;


        try {
            preparedStatement = conn.getConn().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setBirthDate(resultSet.getDate("birthdate"));
                user.setOrganization(resultSet.getInt("organizationid"));
                colUsers.add(user);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return colUsers;

    }

    public Collection<User> getUserOrganization(String organizationName){
        Collection<User> colUsers = new ArrayList<User>();

//        String sql = "select us.name, us.surname, us.patronymic, us.birthdate, us.organizationid, us.sex, org.nameorganization  from users as us join organization as org on  org.id = us.organizationid where Upper (org.nameorganization) like ? ;";

        String sql = "select us.name, us.surname, us.patronymic, us.birthdate, us.organizationid, us.sex, org.nameorganization  from users as us join organization as org on  org.id = us.organizationid where org.nameorganization ilike ? ;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;


        try {
            preparedStatement = conn.getConn().prepareStatement(sql);
            preparedStatement.setString(1,  ("%" + organizationName + "%").toUpperCase());
            preparedStatement.setString(1,  "%" + organizationName + "%");

            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPatronymic(resultSet.getString("patronymic"));
                user.setBirthDate(resultSet.getDate("birthdate"));
                user.setOrganization(resultSet.getInt("organizationid"));
                user.setSex(Sex.valueOf(resultSet.getString("sex").toUpperCase()));
                user.setOrganizationname(resultSet.getString("nameorganization"));

                colUsers.add(user);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        return colUsers;

    }


}
