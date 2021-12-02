package za.ac.cput.server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import za.ac.cput.dbconnection.DbConnection;
import za.ac.cput.doa.AgentWorker;

/**
 *
 * @author Charles
 */
public class Server {

    Connection conn;
    PreparedStatement psm = null;
    ResultSet rs = null;
    String acceptanceMessage;
    private String houseAddress;
    private List<AgentWorker> listCategory = new ArrayList();
    private String[] list;
    
    private String num;
    private String adr;
    private String rnt;
    private String prop;
    private JFrame parentComponent = new JFrame();
    
    private ArrayList cust = new ArrayList();
    private Statement s;
    
    public Server() {
        try {
            this.conn = DbConnection.DbConnect();
            System.out.println("connection made");
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e);

        }
    }

    public void startServer() throws IOException {
        System.out.println("Server is running: Login");
        // creating the socket 
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = null;

        //port connection
        serverSocket = new ServerSocket(1234);

        while (true) {
            try {

                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {

                    //Reads the message from the client side and prints(Login info)
                    String input0 = bufferedReader.readLine();
                    
                    String ARTG = "Start";
                    String Login = "Login";
                    String AdminLogin = "AdminLogin";
                    String CreateUser = "createAccount";
                    String SaveHouse = "SaveHouse";
                    String UpdateHouse = "UpdateHouse";
                    String SaveAgent = "SaveAgent";
                    String RetrieveHouse = "RetrieveHouse";
                    String RetrieveCust = "RetrieveCustomer";

                    if (input0.equals(Login)) {
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        verifyLogin(input1, input2);

                    } else if (input0.equals(CreateUser)) {
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        String input3 = bufferedReader.readLine();
                        String input4 = bufferedReader.readLine();
                        saveCustomer(input1, input2, input3, input4);

                    } //add admin if statements here
                    else if (input0.equals(AdminLogin)) {
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        AdminVerification(input1, input2);

                    }
                    else if(input0.equals(ARTG)){
                        
                        //updateComboBox();
                        //list();
                        //for (int i = 0; i < listCategory.size(); i++) {
                        //    bufferedWriter.write(listCategory.get(0).getCustomerName());
                        //}

                    
                    }else if (input0.equals(AdminLogin)) {
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        AdminVerification(input1, input2);
                    }
                    else if(input0.equals(SaveHouse)){
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        String input3 = bufferedReader.readLine();
                        String input4 = bufferedReader.readLine();
                        SaveHouse(input1, input2, input3, input4);
                    }
                    else if(input0.equals(UpdateHouse)){
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        String input3 = bufferedReader.readLine();
                        String input4 = bufferedReader.readLine();
                        UpdateHouse(input1, input2, input3, input4);
                    }
                    else if(input0.equals(SaveAgent)){
                        String input1 = bufferedReader.readLine();
                        String input2 = bufferedReader.readLine();
                        String input3 = bufferedReader.readLine();
                        String input4 = bufferedReader.readLine();
                        SaveAgent(input1, input2, input3, input4);
                    }
                    else if(input0.equals(RetrieveHouse)){
                        houseAddress = bufferedReader.readLine();
                        RetrieveHouse(houseAddress);
                        bufferedWriter.write(num+"\n");
                        bufferedWriter.write(adr+"\n");
                        bufferedWriter.write(rnt+"\n");
                        bufferedWriter.write(prop+"\n");
                    } else if(input0.equals(RetrieveCust)){
                        RetrieveCustomer();
                        
                        for (int i = 0; i < cust.size(); i++) {
                            String name = cust.get(i).toString();
                            bufferedWriter.write(name);
                            bufferedWriter.newLine();
                        }
                    }
                    
                    //sends a message from the server side back to the client side
                    bufferedWriter.write(acceptanceMessage);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    break;
                    //try to save the infomation entered by a client into a database through the server
                    /*if(msgFromClient.equalsIgnoreCase("BYE"))
                        break;*/
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {

                e.printStackTrace();

            }
        }

    }

    //--------------------------------------------------------------------------CRUD Opperations
    //User Verification
    public void verifyLogin(String Username, String Password) {
        String username = Username;
        String password = Password;

        String sqlQuery = "SELECT * FROM AGENT WHERE agentID = ? AND  agentPassword = ?";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, username);
            psm.setString(2, password);

            rs = psm.executeQuery();
            if (rs.next()) {

                // if the values are found send  a message back to the client saying accepted then open new form
                acceptanceMessage = "accepted";

            } else {
                // if the values are not found send the client a message saying notAccepted then open JOption
                //message to re-enter the values
                acceptanceMessage = "denied";

            }

        } catch (Exception e) {

            System.out.println("Error occured in CRUD Login method: " + e);

        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }

    //Save new Customer code
    public void saveCustomer(String idNumber, String Name, String Surname, String mobileNumber) {
        String name = Name;
        String surname = Surname;
        String id = idNumber;
        String mobile = mobileNumber;

        String query = "INSERT INTO CUSTOMERS(customerID,customerName,customerSurname,customerMobile)"
                + "VALUES(?,?,?,?)";

        try {
            psm = conn.prepareStatement(query);
            psm.setString(1, id);
            psm.setString(2, name);
            psm.setString(3, surname);
            psm.setString(4, mobile);

            int x = psm.executeUpdate();
            if(x > 0){
                acceptanceMessage = "record created";
                System.out.println("Customer Record Saved.");
            }
        } catch (Exception e) {

            System.out.println("There is an error in the 'saveCustomer' method: " + e);

        }

    }

    //Retail Tranaction Agent Code
    //Populate comboBoxes
   /* public void updateComboBox(){
        
        String query = "SELECT * FROM CUSTOMERS";
        
        try{
            psm = conn.prepareStatement(query);
            
            rs = psm.executeQuery();
            
            while(rs.next()){
                list.add(rs.getString("customerName"));
                //acceptanceMessage = rs.getString("customerName");
                
            
            }
        
        }catch(Exception e){
        
            System.out.println("There is and error in the updateCombo Method: " + e);
        
        }
        
        
    }*/
    
    public List<AgentWorker> list(){
        //List<AgentWorker> listCategory = new ArrayList<>();
        
        try{
            String query ="SELECT * FROM CUSTOMERS ORDER BY customerName";
            psm = conn.prepareStatement(query);
            
            rs = psm.executeQuery();
            
            while(rs.next()){
                acceptanceMessage = "From list method";
                String name = rs.getString("customerName");
                AgentWorker agentworker = new AgentWorker(name);
                listCategory.add(agentworker);
        }
        
        }catch(SQLException ex){
        
        
        }
        
       System.out.println("Values: "+ listCategory.toString());
       return listCategory;
    
    }
    
    public void RetrieveCustomer() {
        String nameClient;

        String sqlQuery = "select * from CUSTOMERS";
        try {
            //psm = conn.prepareStatement(sqlQuery);

            s = conn.createStatement();
            
            //rs = psm.executeQuery();
            rs = s.executeQuery(sqlQuery);
            
            while(rs.next()){
                //if (rs.next() == true) {
                    nameClient = rs.getString(2);
                    cust.add(nameClient);
                    
                    for (int i = 0; i < cust.size(); i++) {
                        System.out.println(cust.get(i));
                    }
                    
                
                    parentComponent.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(parentComponent, "Data has been successfully loaded.");
                    acceptanceMessage = "accepted";
                /**} else {
                    parentComponent.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(parentComponent, "Data could not be loaded.");
                    acceptanceMessage = "denied";
                }**/
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {
                    
                    //psm.close();
                    System.out.println("Psm Connection is closed.");

                } else if (s == null) {
                    
                    s.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }
    
    //Admin methods below
        //Admin methods below
    public void AdminVerification(String name, String Password) {
        String username = name;
        String password = Password;
        //checks the database if the value exists
        String sqlQuery = "SELECT * FROM ADMIN WHERE ADMINID = ? AND  ADMINPASSWORD = ?";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, username);
            psm.setString(2, password);

            rs = psm.executeQuery();
            if (rs.next() == true) {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "You have successfully been logged in, administrator.");
                acceptanceMessage = "accepted";
            } else {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Your login has failed.");
                acceptanceMessage = "denied";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }
    public void SaveHouse(String NumberClients, String Address, String Rent, String Property) {
        String numClient = NumberClients;
        String addressClient = Address;
        String rentClient = Rent;
        String propertyClient = Property;

        String sqlQuery = "insert into ADMIN_HOUSE (NUMROOMS, ADDRESS, RENT, PROPERTYTYPE) values (?, ?, ?, ?)";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, numClient);
            psm.setString(2, addressClient);
            psm.setString(3, rentClient);
            psm.setString(4, propertyClient);

            rs = psm.executeQuery();
            if (rs.next() == true) {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Data has been successfully saved.");
                acceptanceMessage = "accepted";
            } else {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Error saving data.");
                acceptanceMessage = "denied";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }
    
    public void UpdateHouse(String NumberClients, String Address, String Rent, String Property) {
        String numClient = NumberClients;
        String addressClient = Address;
        String rentClient = Rent;
        String propertyClient = Property;

        String sqlQuery = "update ADMIN_HOUSE set NUMROOMS = ?, ADDRESS = ?, RENT = ?, PROPERTYTYPE = ? where ADDRESS = ?";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, numClient);
            psm.setString(2, addressClient);
            psm.setString(3, rentClient);
            psm.setString(4, propertyClient);
            psm.setString(5, addressClient);

            //psm.executeUpdate();
            if (psm.executeUpdate() > 0) {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Data from the database has been updated.");
                acceptanceMessage = "accepted";
            } else {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(null, "Data could not be updated.");
                acceptanceMessage = "denied";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }
    
    public void RetrieveHouse(String houseAddress) {
        String numClient;
        String addressClient;
        String rentClient;
        String propertyType;
        
        String adrClient = houseAddress;

        String sqlQuery = "select * from ADMIN_HOUSE where ADDRESS = ?";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, adrClient);
            
            rs = psm.executeQuery();
            if (rs.next() == true) {
                numClient = rs.getString(1);
                addressClient = rs.getString(2);
                rentClient = rs.getString(3);
                propertyType = rs.getString(4);
                
                num = numClient;
                adr = addressClient;
                rnt = rentClient;
                prop = propertyType;
                
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Data has been successfully loaded.");
                acceptanceMessage = "accepted";
            } else {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Data could not be loaded.");
                acceptanceMessage = "denied";
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }
    
    public void SaveAgent(String Name, String Phone, String Email, String active) {
        String nameClient = Name;
        String phoneClient = Phone;
        String emailClient = Email;
        String activeClient = active;
        
        String sqlQuery = "insert into ADMIN_AGENT (NAME, PHONENUMBER, EMAIL, ISACTIVE) values (?, ?, ?, ?)";
        try {
            psm = conn.prepareStatement(sqlQuery);
            psm.setString(1, nameClient);
            psm.setString(2, phoneClient);
            psm.setString(3, emailClient);
            psm.setString(4, activeClient);

            //psm.executeUpdate();
            if (psm.executeUpdate() > 0) {
                parentComponent.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(parentComponent, "Data has been successfully saved.");
                acceptanceMessage = "accepted";
            } else {
                JOptionPane.showMessageDialog(parentComponent, "Data could not be saved successfully.");
                acceptanceMessage = "denied";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (psm == null) {

                    psm.close();
                    System.out.println("Psm Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
            try {
                if (conn == null) {

                    conn.close();
                    System.out.println("Conn Connection is closed.");

                }
            } catch (Exception e) {

                System.out.println("Error in closure: " + e);

            }
        }
    }


    public static void main(String[] args) throws IOException {
        new Server().startServer();
    }
}
