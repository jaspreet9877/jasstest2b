package com.example.test2b_jaspreet;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    public TextField email;
    public PasswordField pswd;
    public Label msg;

    public void LoginClick(ActionEvent actionEvent) {
        msg.setText("");

        String Email_add = email.getText();
        String Given_Password = pswd.getText();

        if(Email_add.equals("")||Given_Password.equals("")){

            msg.setText("Please Give Email or Password");

        }else{



            // Establish a database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
                // Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM `tbl_user` WHERE email='"+Email_add+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                // Populate the table with data from the database

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");

                    if(password.equals(Given_Password)){

                        msg.setText("Success");



                        try {
                            // Load the FXML file for the second scene
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                            Parent secondScene = loader.load();

                            // Access the controller of the second scene
                            HelloController userController = loader.getController();

                            // Set the data in the controller of the second scene
                            userController.set_username("Welcome"+name);

                            // Create a new stage for the second scene
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Employee Scene");
                            secondStage.setScene(new Scene(secondScene));

                            Stage firstSceneStage = (Stage) pswd.getScene().getWindow();
                            firstSceneStage.close();

                            // Show the second stage
                            secondStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else{
                        msg.setText("Invalid email or password");
                    }

                }else{
                    msg.setText("Invalid email or password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
