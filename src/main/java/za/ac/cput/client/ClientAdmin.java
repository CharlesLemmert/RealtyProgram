/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import za.ac.cput.server.Server;

/**
 *
 * @author joshu
 */
public class ClientAdmin {
    
    public String num;
    public String adr;
    public String rnt;
    public String prop;
    
    public ArrayList cust = new ArrayList();
    public ArrayList custSur = new ArrayList();
    
    public void RetrieveCust() {
        System.out.println("Client is connecting to server now...");
        
        //String name;
        
        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is getting information from the server...\n");

                //message that will be sent to the server
                String save = "RetrieveCustomer";
                    
                bufferedWriter.write(save+"\n");
                
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();
                
                //while(bufferedReader.read() > 0){
                while(cust.size() < 2){
                    String name = bufferedReader.readLine();
                    
                    cust.add(name);
                    
                    String surname = bufferedReader.readLine();
                    
                    custSur.add(surname);
                }
                //}
                
                for (int i = 0; i < cust.size(); i++) {
                    System.out.println(cust.get(i).toString());
                    System.out.println(cust.get(i).toString());
                }
                
                
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void AddHouse(String numRooms, String address, String rent, String propertyType) {
        System.out.println("Client is connecting to server now...");
        String num = numRooms;
        String adr = address;
        String rnt = rent;
        String prop = propertyType;

        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            //socket.connect(addr);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //message that will be sent to the server
                String save = "SaveHouse";
                String nummsg = num;
                String addressmsg = adr;
                String rentmsg = rnt;
                String propertymsg = prop;

                bufferedWriter.write(save+"\n");
                bufferedWriter.write(nummsg + "\n");
                bufferedWriter.write(addressmsg + "\n");
                bufferedWriter.write(rentmsg + "\n");
                bufferedWriter.write(propertymsg + "\n");

                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void UpdateHouse(String numRooms, String address, String rent, String propertyType) {
        System.out.println("Client is connecting to server now...");
        String num = numRooms;
        String adr = address;
        String rnt = rent;
        String prop = propertyType;

        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            //socket.connect(addr);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //message that will be sent to the server
                String save = "UpdateHouse";
                String nummsg = num;
                String addressmsg = adr;
                String rentmsg = rnt;
                String propertymsg = prop;

                bufferedWriter.write(save+"\n");
                bufferedWriter.write(nummsg + "\n");
                bufferedWriter.write(addressmsg + "\n");
                bufferedWriter.write(rentmsg + "\n");
                bufferedWriter.write(propertymsg + "\n");

                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void GetHouse(String address) {
        System.out.println("Client is connecting to server now...");
        
        String addr = address;
        
        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //message that will be sent to the server
                String save = "RetrieveHouse";
                String addressClient = addr;
                    
                bufferedWriter.write(save+"\n");
                bufferedWriter.write(addressClient+"\n");
                
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();
                
                num = bufferedReader.readLine();
                adr = bufferedReader.readLine();
                rnt = bufferedReader.readLine();
                prop = bufferedReader.readLine();
                
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void AddAgent(String name, String number, String email, String isActive) {
        System.out.println("Client is connecting to server now...");
        String nm = name;
        String nmbr = number;
        String e = email;
        String active = isActive;

        //create a socket connection
        Socket socket = null;
        InputStream input;
        OutputStream output;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);
            //socket.connect(addr);

            input = socket.getInputStream();
            output = socket.getOutputStream();

            inputStreamReader = new InputStreamReader(input);
            outputStreamWriter = new OutputStreamWriter(output);

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (socket.isClosed() == false) {
                System.out.println("Client is sending information to the server...\n");

                //message that will be sent to the server
                String save = "SaveAgent";
                String namemsg = nm;
                String numbermsg = nmbr;
                String emailmsg = e;
                String activemsg = active;

                bufferedWriter.write(save+"\n");
                bufferedWriter.write(namemsg + "\n");
                bufferedWriter.write(numbermsg + "\n");
                bufferedWriter.write(emailmsg + "\n");
                bufferedWriter.write(activemsg+"\n"); 
                
                System.out.println("Information has been sent...\n");
                
                //this flushes the writer when the buffer is full
                bufferedWriter.flush();

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}
