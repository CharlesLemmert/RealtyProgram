/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import za.ac.cput.main.Main;
import za.ac.cput.server.Server;

/**
 *
 * @author Joshua Julies
 */
public class AdminGUIPostLogin implements ActionListener{
    
    //MAIN
    private JFrame mainFrame;
    private JFrame houseFrame;
    private JFrame agentFrame;
    private JPanel menuPanel;
    private JPanel menuPanel2;
    private JPanel holder;
    private JLabel menuLbl;
    private JPanel houseHolder;
    private JPanel housePanel;
    private JPanel housePanel2;
    private JPanel housePanel3;
    private JPanel houseHoldingPanel;
    private JPanel agentHolder;
    private JPanel agentPanel;
    private JPanel agentPanel2;
    private JPanel agentPanel3;
    private JPanel agentHoldingPanel;
    private JButton addhouseBtn;
    private JButton agentBtn;
    private JButton returnBtn;
    private Dimension prefSize = new Dimension(150, 30);
    
    //IMAGES
    private JLabel imgLbl;
    private JLabel imgLbl2;
    private JLabel imgLbl3;
    private JLabel imgLbl4;
    
    //HOUSE
    private JLabel houseLbl;
    private JLabel numRoomsLbl;
    private JTextField numRoomsText;
    private JLabel addressLbl;
    private JTextField addressText;
    private JLabel rentLbl;
    private JTextField rentText;
    private JLabel depositLbl;
    private JTextField depositText;
    private JLabel propertyLbl;
    private JComboBox propertyType; // the type of house: condo, apartment, house, townhouse?
    private JButton houseConfirmBtn;
    private JButton houseUpdateBtn;
    private JButton houseSearchBtn;
    private JButton houseReturnBtn;    
    private JLabel specificHouseLbl;
    private JTextField specificHouse;
    private boolean housePanelVisible = false;

    
    //AGENT 
    private JLabel agentLbl;
    private JLabel nameLbl;
    private JTextField nameText;
    private JLabel phoneNumLbl;
    private JTextField phoneNumText;
    private JLabel emailLbl;
    private JTextField emailText;
    private JLabel activeLbl;
    private JRadioButton active;
    private JButton agentConfirmBtn;
    private JButton agentReturnBtn;
    private boolean agentPanelVisible = false;
    
    //OBJECTS
    private static Main main = new Main();
    private static AdminGUIPostLogin admin = new AdminGUIPostLogin();
    private ClientAdmin client = new ClientAdmin();
    private Server serv = new Server();
    
    private boolean normalUpdate;
    private String isActive = "false";
    
    public int flag;
    public String numRooms;
    public String address;
    public String rent;
    public String property;
    private String updateHouseBtnPressed;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        admin.AdminMenu();
        
        //tried to implement a way for the server to run without having to manually run it.
        /**
        try{
            new Server().startServer();
        }catch(IOException io){
            io.printStackTrace();
        }
        **/
    }
    
    
    public void AdminMenu(){
        mainFrame = new JFrame("Admin Client");
        houseFrame = new JFrame("Housing Details");
        agentFrame = new JFrame("Agent Details");
        holder = new JPanel();
        
        ImageIcon userimage = new ImageIcon("Images\\realestate.png");
        Image img = userimage.getImage();
        Image newImg = img.getScaledInstance(450, 270, Image.SCALE_SMOOTH);
        imgLbl = new JLabel(new ImageIcon(newImg));
        imgLbl2 = new JLabel(new ImageIcon(newImg));
        imgLbl3 = new JLabel(new ImageIcon(newImg));
        imgLbl4 = new JLabel(new ImageIcon(newImg));
        
        menuLbl = new JLabel("Admin Menu");
        menuLbl.setFont(new Font("Sans", Font.BOLD, 30));
        menuPanel = new JPanel();
        menuPanel2 = new JPanel();
        addhouseBtn = new JButton("Enter house details");
        agentBtn = new JButton("Enter agent details");
        returnBtn = new JButton("Return");
        addhouseBtn.addActionListener(this);
        agentBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        addhouseBtn.setBackground(Color.white);
        agentBtn.setBackground(Color.white);
        returnBtn.setBackground(Color.white);
        addhouseBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        returnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        addhouseBtn.setPreferredSize(prefSize);
        agentBtn.setPreferredSize(prefSize);
        returnBtn.setPreferredSize(prefSize);
        
        //FRAME that holds all components
        //mainFrame.setLayout(new GridLayout(1, 1, 2, 2));
        mainFrame.setSize(850, 480);
        mainFrame.setEnabled(true);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(350, 230);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //HOUSE
        houseFrame = new JFrame("Housing Details");
        housePanel = new JPanel();
        housePanel2 = new JPanel();
        housePanel3 = new JPanel();
        houseHoldingPanel = new JPanel();
        houseHolder = new JPanel();
        houseLbl = new JLabel("Housing Details");
        houseLbl.setFont(new Font("Sans", Font.BOLD, 25));
        numRoomsLbl = new JLabel("Number of Rooms: ");
        numRoomsText = new JTextField();
        addressLbl = new JLabel("Address: ");
        addressText = new JTextField();
        rentLbl = new JLabel("Rent: ");
        rentText = new JTextField();
        propertyLbl = new JLabel("Property type: ");
        propertyType = new JComboBox();
        propertyType.addItem("Family/Single-Family Home");
        propertyType.addItem("Town House");
        propertyType.addItem("Condominium");
        propertyType.addItem("Apartment");
        specificHouseLbl = new JLabel("Specific house");
        specificHouse = new JTextField();
        numRoomsText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        addressText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        rentText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        
        houseConfirmBtn = new JButton("Confirm");
        houseReturnBtn = new JButton("Return");
        houseUpdateBtn = new JButton("Update");
        houseSearchBtn = new JButton("Search");
        houseConfirmBtn.addActionListener(this);
        houseReturnBtn.addActionListener(this);
        houseUpdateBtn.addActionListener(this);
        houseSearchBtn.addActionListener(this);
        houseConfirmBtn.setBackground(Color.white);
        houseReturnBtn.setBackground(Color.white);
        houseUpdateBtn.setBackground(Color.white);
        houseSearchBtn.setBackground(Color.white);
        houseConfirmBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseReturnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseUpdateBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseSearchBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        houseConfirmBtn.setPreferredSize(prefSize);
        houseReturnBtn.setPreferredSize(prefSize);
        houseUpdateBtn.setPreferredSize(prefSize);
        houseSearchBtn.setPreferredSize(prefSize);

        housePanel2.add(houseLbl);
        housePanel2.setLayout(new GridLayout(1,1));
        housePanel.add(specificHouseLbl);
        housePanel.add(specificHouse);
        housePanel.add(numRoomsLbl);
        housePanel.add(numRoomsText);
        housePanel.add(addressLbl);
        housePanel.add(addressText);
        housePanel.add(rentLbl);
        housePanel.add(rentText);
        housePanel.add(propertyLbl);
        housePanel.add(propertyType);
        housePanel3.add(houseSearchBtn);
        housePanel3.add(houseUpdateBtn);
        housePanel3.add(houseConfirmBtn);
        housePanel3.add(houseReturnBtn);
        housePanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        housePanel.setLayout(new GridLayout(5, 2, 30, 30));        

        houseHoldingPanel.setBackground(Color.decode("#007bb4"));
        houseHoldingPanel.setLayout(new BorderLayout(60, 60));
        houseHoldingPanel.add(housePanel2, BorderLayout.NORTH);
        houseHoldingPanel.add(housePanel, BorderLayout.CENTER);
        houseHoldingPanel.add(housePanel3, BorderLayout.SOUTH); 
        houseHoldingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(40, 200, 40, 200)));    
        housePanel2.setBackground(Color.decode("#007bb4"));
        housePanel.setBackground(Color.decode("#007bb4"));
        housePanel3.setBackground(Color.decode("#007bb4"));
        houseHolder.add(houseHoldingPanel);
        houseHolder.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        houseHolder.setBackground(Color.decode("#36d23b"));
        houseFrame.add(houseHolder);
        houseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        houseFrame.setResizable(false);
        houseFrame.pack();
        houseFrame.setLocationRelativeTo(null);
         
        //AGENT
        agentFrame = new JFrame("Agent Details");
        agentHolder = new JPanel();
        agentPanel = new JPanel();
        agentPanel2 = new JPanel();
        agentPanel3 = new JPanel();
        agentHoldingPanel = new JPanel();
        agentLbl = new JLabel("Agent Details");
        agentLbl.setFont(new Font("Sans", Font.BOLD, 25));
        nameLbl = new JLabel("Name: ");
        nameText = new JTextField();
        phoneNumLbl = new JLabel("Contact number: ");
        phoneNumText = new JTextField();
        emailLbl = new JLabel("E-mail address: ");
        emailText = new JTextField();
        activeLbl = new JLabel("Active");
        active = new JRadioButton();
        nameText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        phoneNumText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        emailText.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        
        agentConfirmBtn = new JButton("Confirm");
        agentReturnBtn = new JButton("Return");
        agentConfirmBtn.addActionListener(this);
        agentReturnBtn.addActionListener(this);
        agentConfirmBtn.setBackground(Color.white);
        agentReturnBtn.setBackground(Color.white);
        agentConfirmBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentReturnBtn.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        agentConfirmBtn.setPreferredSize(prefSize);
        agentReturnBtn.setPreferredSize(prefSize);
        active.addActionListener(this);
        
        agentPanel2.add(agentLbl);
        agentPanel2.setBackground(Color.decode("#007bb4"));
        agentPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        agentPanel.add(nameLbl);
        agentPanel.add(nameText);
        agentPanel.add(phoneNumLbl);
        agentPanel.add(phoneNumText);
        agentPanel.add(emailLbl);
        agentPanel.add(emailText);
        agentPanel.add(activeLbl);
        agentPanel.add(active);
        active.setBackground(Color.decode("#007bb4"));
        agentPanel3.add(agentConfirmBtn);
        agentPanel3.add(agentReturnBtn);
        agentPanel3.setBackground(Color.decode("#007bb4"));
        agentPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        agentPanel.setLayout(new GridLayout(4, 2, 90, 30));
        agentPanel.setBackground(Color.decode("#007bb4"));
        agentHoldingPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(20, 50, 20, 50)));
        agentHoldingPanel.setBackground(Color.decode("#007bb4"));
        agentHoldingPanel.setLayout(new BorderLayout(60, 60));
        agentHoldingPanel.add(agentPanel2, BorderLayout.NORTH);
        agentHoldingPanel.add(agentPanel, BorderLayout.CENTER);
        agentHoldingPanel.add(agentPanel3, BorderLayout.SOUTH);
        agentHolder.add(agentHoldingPanel);
        agentHolder.setBackground(Color.decode("#36d23b"));
        agentHolder.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        agentFrame.add(agentHolder);
        agentFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        agentFrame.setEnabled(true);
        agentFrame.setResizable(false);
        agentFrame.pack();
        agentFrame.setLocationRelativeTo(null);
        
        //PANEL that hold the housing details and agent details buttons 
        menuPanel2.add(menuLbl);
        menuPanel2.setBackground(Color.decode("#007bb4"));
        menuPanel.add(menuPanel2);
        menuPanel.add(addhouseBtn);
        menuPanel.add(agentBtn);
        menuPanel.add(returnBtn);
        menuPanel.setLayout(new GridLayout(4, 1, 30, 30));
        menuPanel.setEnabled(true);
        menuPanel.setSize(800, 400);
        menuPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 4), BorderFactory.createEmptyBorder(80, 50, 80, 50)));
        menuPanel.setBackground(Color.decode("#007bb4"));
        
        holder.add(imgLbl);
        holder.add(menuPanel);
        holder.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 40));
        holder.setBackground(Color.decode("#36d23b"));
        mainFrame.setResizable(false);
        mainFrame.add(holder);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
    }
    
    private void AddHouseDetails(){
        propertyType.setSelectedIndex(1);
        housePanel.setEnabled(true);
        housePanel.setVisible(true);
        houseFrame.setEnabled(true);
        houseFrame.setVisible(true);
        houseHolder.setVisible(true);
        houseHolder.setEnabled(true);
    }
    
    private void AgentDetails(){
        agentPanel.setEnabled(true);
        agentPanel.setVisible(true);
        agentFrame.setEnabled(true);
        agentFrame.setVisible(true);
    }
    
    private void Close(){
        mainFrame.setVisible(false);
        houseFrame.setVisible(false);
        agentFrame.setVisible(false);
        mainFrame.dispose();
        houseFrame.dispose();
        agentFrame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == houseReturnBtn){
            mainFrame.setVisible(true);
            agentFrame.setVisible(false);
            houseFrame.setVisible(false);
        } 
        if(e.getSource() == agentReturnBtn){
            mainFrame.setVisible(true);
            agentFrame.setVisible(false);
            houseFrame.setVisible(false);
        }
        if(e.getSource() == houseConfirmBtn){       
            normalUpdate = true; //true since its normal
            
            //send house information to the server
            if(numRoomsText.getText() != null || addressText.getText() != null || rentText.getText() != null || propertyType.getSelectedItem() != null){
                String rooms = numRoomsText.getText(); 
                String addr = addressText.getText();
                String rent = rentText.getText();
                String prop = (String) propertyType.getSelectedItem();
                client.AddHouse(rooms, addr, rent, prop);
            } else {
                JOptionPane.showMessageDialog(null, "The values you have entered are not complete");
            }
            
            numRoomsText.setText(""); 
            addressText.setText("");
            rentText.setText("");
            propertyType.setSelectedIndex(1);
        } 
        if(e.getSource() == houseSearchBtn){
            normalUpdate = false; //false since it is not normal
            
            String spec = specificHouse.getText();
            client.GetHouse(spec);
            
            if(client.num != null || client.adr != null || client.rnt != null || client.prop != null){
                numRoomsText.setText(client.num);
                addressText.setText(client.adr);
                rentText.setText(client.rnt);
                propertyType.setSelectedItem(client.prop);
            } 
        }
        if(e.getSource() == houseUpdateBtn){
            //update a house in the database
            if(normalUpdate == false){
                if(numRoomsText.getText() != null || addressText.getText() != null || rentText.getText() != null){
                    String rooms = numRoomsText.getText(); 
                    String addr = addressText.getText();
                    String rent = rentText.getText();
                    String prop = (String) propertyType.getSelectedItem();
                    client.UpdateHouse(rooms, addr, rent, prop);
                } else {
                    JOptionPane.showMessageDialog(null, "There is no data to be updated.");
                }
            }  
        }
        if(e.getSource() == agentConfirmBtn){  
            //send agent information over to the server
            if(nameText.getText() != null || phoneNumText.getText() != null || emailText.getText() != null){
                String name = nameText.getText(); 
                String num = phoneNumText.getText();
                String email = emailText.getText();
                String act = isActive;
                client.AddAgent(name, num, email, act);
            }
        }
        if(e.getSource() == active){
            if(active.isSelected() == true){
                isActive = "true";
            }
        }
        if(e.getSource() == addhouseBtn){ 
            //reset house
            specificHouse.setText(null);
            numRoomsText.setText(null); 
            addressText.setText(null);
            rentText.setText(null);
            propertyType.setSelectedIndex(1);
            
            mainFrame.setVisible(false);
            agentFrame.setVisible(false);
            AddHouseDetails();
        } 
        if(e.getSource() == agentBtn){
            //reset agent
            nameText.setText(null); 
            phoneNumText.setText(null);
            emailText.setText(null);
            
            mainFrame.setVisible(false);
            houseFrame.setVisible(false);
            AgentDetails();
        } 
        if(e.getSource() == returnBtn){
            Close();
            main.startProgram();
        }
    }
    
}
