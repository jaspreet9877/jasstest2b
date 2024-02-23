package com.example.test2b_jaspreet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//
//
//    }
    public TextField ename;
    public TextField ephone;
    public TextField eaddress;
  //  public TextField eage;
    public TextField eid;
    public Label user_name;
    @FXML
    private TableView<Contact_Information> tableView;
    @FXML
    private TableColumn<Contact_Information,Integer > id;
    @FXML
    private TableColumn<Contact_Information, String> contact_name;
    @FXML
    private TableColumn<Contact_Information,Integer> phone;
    @FXML
    private TableColumn<Contact_Information,String> address;
    //@FXML
    //private TableColumn<Contact_Information,Integer> age;
    ObservableList<Contact_Information> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Contact_Information,Integer>("id"));
        contact_name.setCellValueFactory(new
                PropertyValueFactory<Contact_Information,String>("contact_name"));
        phone.setCellValueFactory(new
                PropertyValueFactory<Contact_Information,Integer>("phone"));
        address.setCellValueFactory(new
                PropertyValueFactory<Contact_Information,String>("Address"));
      //  age.setCellValueFactory(new
        //        PropertyValueFactory<Contact_Information,Integer>("Age"));
        tableView.setItems(list);
    }
    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }
    public void populateTable() {

        list.clear();


// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_contacts`";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String contact_name = resultSet.getString("contact_name");
                int phone = resultSet.getInt("phone");
                String address = resultSet.getString("address");
              //  int age = resultSet.getInt("age");
                tableView.getItems().add(new Contact_Information(id, contact_name, phone,
                        address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertData(ActionEvent actionEvent) {

        String Ename =ename.getText();
        String Ephone =ephone.getText();
        String Eaddress =eaddress.getText();
      //  String Eage =eage.getText();

        InserTable(Ename,Ephone,Eaddress);
    }


    public void InserTable(String ename,String esalary,String eaddress) {
// Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `tbl_contacts`( `contact_name`, `phone`, `address`) VALUES ('"+ename+"','"+ephone+"','"+eaddress+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void DeleteData(ActionEvent actionEvent) {


        String Eid=eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `tbl_contacts` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateData(ActionEvent actionEvent) {

        String Eid =eid.getText();
        String Ename =ename.getText();
        String Ephone =ephone.getText();
        String Eaddress =eaddress.getText();
      //  String Eage =eage.getText();



        String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `tbl_contacts` SET `contact_name`='"+Ename+"',`phone`='"+Ephone+"',`address`='"+Eaddress+"' WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void LoadData(ActionEvent actionEvent) {


        String Eid =eid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/test2b_jas";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `tbl_contacts` WHERE id='"+Eid+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String contact_name = resultSet.getString("contact_name");
                int phone = resultSet.getInt("phone");
                String address = resultSet.getString("address");
               // int age = resultSet.getInt("age");


                ename.setText(contact_name);
                ephone.setText(String.valueOf(phone));
                eaddress.setText(address);
               // eage.setText(String.valueOf(age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void set_username(String messge){
        user_name.setText(messge);
    }
}