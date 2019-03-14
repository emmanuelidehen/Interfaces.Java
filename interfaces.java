/*   Emmanuel idehen
       Java Programming  
       3/20/2018

Program:  This programs simulate a basic registration/login
 process for a standard application or website.


***Design (Pseudo/Algorithm)***
create jframe and panel 
Create UserLogin, createprofile, duplicate and the main function
UserLogin gives the user a platform to login if his username and password already exists
createprofile allows user register
The username and password are encrypted and decrypted accordingly
The duplicate function checks if the username already exists

*******************************

*/             

import java.io.*;
import java.util.*;
import java.lang.StringBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class interfaces
{
      private static JFrame a;   //Used to create interface
      private static JPanel g, e;	
      private JButton p, login;
      private static JLabel l;
      private static JTextField text, id, userpass; 
      static int state=0, shifting=0, asciinum, outcome;  
      static long phonenum=0; 
      static String name=null, major = null, usename=null, password=null, gender=null, uni=null, line=null, check=null,
                    dash=null, temp=null;   //Creation of user profile
      static StringBuilder sb = new StringBuilder(); //Spacing 

   //
   public static void main(String[] args)
   {
	   
	   //calling interface function
	   new interfaces();  
   }
   
   //Write data to file
    public static void WriteToFile()
    {
         File outFile = new File("accounts.txt");   //Write to file accounts.txt
      
         try
         {
               if(!outFile.exists())   //Check if the file exists
               {
                     outFile.createNewFile();
                     JOptionPane.showMessageDialog(null, "File was created.", "Information", JOptionPane.INFORMATION_MESSAGE);
               }
         
               FileWriter fw = new FileWriter(outFile, true);   //Write into file
               BufferedWriter bw = new BufferedWriter(fw);  	//Use buffered WriteToFiler and printer
               PrintWriter pw = new PrintWriter(bw);
         
               pw.println("-----");   //Write into file
               pw.println(state);
               pw.println(shifting);
               pw.println(name);
               pw.println(gender);
               pw.println(phonenum);
               pw.println(uni);
               pw.println(password);
               pw.println(usename);
               pw.println(major);
               pw.close();
               
               state =0;     //Assign zero to each value of the variables being written into the file 
               shifting =0;
               name = null;
               gender =null;
               phonenum=0;
               uni =null;
               password=null;
               usename=null;
               
               JOptionPane.showMessageDialog(null, "Your profile was created.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
         }  
      
         catch (IOException e)    //Catch an exception
         {JOptionPane.showMessageDialog(null, "File not found.", "error", JOptionPane.ERROR_MESSAGE);}
    }

    //Main Frame
    public interfaces()  //Graphic user interface
    {
 	   	// creates the jframe box 
          a = new JFrame("Welcome to Menu");           
          a.setVisible(true);
          a.setSize(100, 75);
          a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //....................................................//
          // ...?? ....?? ........
          g = new JPanel();
          g.setBackground(Color.WHITE); //sets jframe color 
          
          //............Jframe button .......//
          p = new JButton("Creating Profile");
          // calls newaction 
          p.addActionListener(new ActionListener()
          {
                public void actionPerformed(ActionEvent e){create();}
          });

          login = new JButton("Login");
          login.addActionListener(new ActionListener()
          {
                public void actionPerformed(ActionEvent e){UserLogin();}
          });
         
          l = new JLabel("Select an option.");
       
          g.add(l);
          g.add(p);  
          g.add(login);   
          a.add(g);
    }  
//Create Profile Panel
   public static void create()    //Create profile
   {
         id = new JTextField();
         
         userpass = new JTextField();
         //........................................//
         JTextField yourName = new JTextField();
         JTextField phone = new JTextField();
         JTextField university = new JTextField();
         //......................................//
         e = new JPanel(new GridLayout(6,2));   
         e.add( new JLabel( "Name:") );  
         e.add( yourName );		      
         e.add( new JLabel( "Userid:") );
         e.add( id );
         e.add( new JLabel( "Password:") );
         e.add( userpass );
         e.add( new JLabel( "Phone number:") );
         e.add( phone );
         e.add( new JLabel( "University:") );
         e.add( university );
         //.....................................//
         JRadioButton male = new JRadioButton("Male"); 
         JRadioButton female = new JRadioButton("Female");
         ButtonGroup group = new ButtonGroup();
         //..................................//
         e.add(male);
         e.add(female);
         group.add(male);
         group.add(female);
         
         outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);    
         name = yourName.getText();
         usename = id.getText();
        
         while((usename.length() < 8 || !Character.isAlphabetic(usename.charAt(0))) && outcome != JOptionPane.NO_OPTION)    //Username validation
         {
               JOptionPane.showMessageDialog(null, "Username must at least be 8 characters and can only start with an alphabet.", "Information", JOptionPane.INFORMATION_MESSAGE);
               outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);
               usename = id.getText();     
         }
      
         if(outcome != JOptionPane.NO_OPTION)
         {  
               File outFile = new File("accounts.txt");
               if(outFile.exists())
               {duplicate();}
         }
        
         sb.setLength(0);
      
         Random rnum = new Random();
         state = rnum.nextInt(14)+2;
         
         for(int n=0; n<usename.length(); n++)     //Mod username
         {
               char character = usename.charAt(n);
               asciinum = (int) character;
            
               asciinum = asciinum - state;
           
               character = (char) asciinum;
         
               sb.append(character);
         }
 
         usename = sb.toString();   //Adds spaces 

         password = userpass.getText();  //Gets text

         while((password.length() < 7 || Character.isDigit(password.charAt(0))) && outcome != JOptionPane.NO_OPTION)
         {
               JOptionPane.showMessageDialog(null, "Password must at least be 7 characters and can not start with a number.", "Information", JOptionPane.INFORMATION_MESSAGE);
               outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);
               password = userpass.getText();
         }
         
         Random num2 = new Random();   //Generate random number 
         shifting = num2.nextInt(14)+4;    //Shift the password first
      
         sb.setLength(0); //Set length with string builder at zero
         
         for(int n=0; n<password.length(); n++)   //Encrypt every character of the password using Ceaser cipher
         {
               char character = password.charAt(n);
               asciinum = (int) character;
         
               asciinum = asciinum - shifting;
         
               character = (char) asciinum;
         
               sb.append(character);
         }
      
         password = sb.toString();
      
         if(male.isSelected())   //Validate gender inputted
               gender = "Male";        
       
         else if(female.isSelected())
               gender = "Female";
       else
       {
            while((!male.isSelected() && !female.isSelected()) && outcome != JOptionPane.NO_OPTION)
            {
                  JOptionPane.showMessageDialog(e, "A gender was not selected.", "Inane error",JOptionPane.ERROR_MESSAGE); 
                  outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);
                              
                  if(male.isSelected())
                        gender = "Male";        
   
                  else if(female.isSelected())
                        gender = "Female";
             }
        }
           
        temp = phone.getText();   //Gets phone number
      
         if(outcome != JOptionPane.NO_OPTION)
         {       
               try 
               {phonenum = Long.parseLong(temp);}
               
               catch(NumberFormatException e) 
               {JOptionPane.showMessageDialog(null, "Invalid entry.", "error", JOptionPane.ERROR_MESSAGE);}
         } 

         if(outcome != JOptionPane.NO_OPTION) 
         {
               phonenum = Long.parseLong(temp);
               
               while(Long.toString(phonenum).length() != 10 && outcome != JOptionPane.NO_OPTION)
               {
                     outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);
                     temp = phone.getText();   
                     
                     try {phonenum = Long.parseLong(temp);}
                     
                     catch(NumberFormatException e) 
                     {JOptionPane.showMessageDialog(null, "Invalid entry.", "error", JOptionPane.ERROR_MESSAGE);}  
                }
          } 
   
          uni = university.getText();
   
          if(outcome != JOptionPane.NO_OPTION)
               WriteToFile();
    }
    
    

    //Login Panel
    public static void UserLogin()    //Login function verifies if username previously exists in the file accounts.txt
    {
         String userName2, passWord2;  //Username and password as stored in the data file
         boolean validI = false;   //Boolean variable to validate
         id = new JTextField();  //Space in which you input your username or user ID
         JTextField password = new JTextField();   //Space in whuch you input your password
         e = new JPanel(new GridLayout(4,4));   
         e.add( new JLabel( "Username:") );
         e.add( id );
         e.add( new JLabel( "Password:") );
         e.add( password );

         outcome = JOptionPane.showConfirmDialog(null, e, "Login", JOptionPane.YES_NO_OPTION);
         userName2 = id.getText();
         passWord2 = password.getText();
         //;......................................................................................................//
         // creates account.txt 
         try
         {  
               File file = new File("accounts.txt");

               FileReader fr = new FileReader(file);      
               BufferedReader br = new BufferedReader(fr);
               // readd input from file 
            
               while(((line = br.readLine()) != null) && ((!userName2.equals(usename)) || (!passWord2.equals(password))))
               {
                     if(dash == null)
                           dash = line;
                           
                     else if(state == 0)
                           state = Integer.parseInt(line.trim());
                           
                     else if(shifting == 0)
                           shifting = Integer.parseInt(line.trim());
            
                     else if(name == null)
                           name = line;
             
                     else if(gender == null)
                           gender = line;
             
                     else if(phonenum == 0)
                           phonenum = Long.parseLong(line.trim());
             
                     else if(uni==null)
                           uni = line;
             
                     else if(password == null)
                     {
                         
                     }
             
                     else if(usename == null)
                     { 
                           usename = line;
                           decodeUserN();
                   
                           if(userName2.equals(usename) && passWord2.equals(password))
                           {
                                 JOptionPane.showMessageDialog(null,"                Name:    " + name + "\n       Username:    " 
                                                      +usename+ "\n        Password:    " +password+
                                                      "\n              Gender:   " +gender+
                                                      "\nPhone Number:   " +phonenum+
                                                      "\n          University:   " +uni, "Results", JOptionPane.PLAIN_MESSAGE);                        
                                 validI = true;
                                 dash = null;
                                 name = null; 
                                 usename = null;
                                 password = null;
                                 gender = null;
                                 phonenum = 0;
                                 uni = null;
                                 state = 0;
                                 shifting = 0;
                           }
                            
                           else
                           {
                                 dash = null;
                                 name = null; 
                                 usename = null;
                                 password = null;
                                 gender = null;
                                 phonenum = 0;
                                 uni = null;
                                 state = 0;
                                 shifting = 0;
                            }
                      }
               
                           
               }
               
               if(validI == false && outcome != JOptionPane.NO_OPTION)
                     JOptionPane.showMessageDialog(null, "Your profile could not be found or doesn't exist.", "error", JOptionPane.ERROR_MESSAGE);
         }
         
               
      
         catch (IOException e)
         {JOptionPane.showMessageDialog(null, "File not found", "error", JOptionPane.ERROR_MESSAGE);}
            
         dash = null;
         name = null; 
         usename = null;
         password = null;
         gender = null;
         phonenum = 0;
         uni = null;
         state = 0;
         shifting = 0;
    }
    
    //Decipher Password  
    public static void decodePassW()
    {
         sb.setLength(0);
                
         for(int n=0; n<password.length(); n++)
         {
               char character = password.charAt(n);
               asciinum = (int) character;
         
               asciinum = asciinum + shifting;
        
               character = (char) asciinum;
         
               sb.append(character);
          }
      
          password = sb.toString();
     }
     
     //Deciphers the username
     public static void decodeUserN()
     {
         sb.setLength(0);    
                
         for(int n=0; n<usename.length(); n++)
         {
               char character = usename.charAt(n);
               asciinum = (int) character;
         
               asciinum = asciinum + state;
         
               character = (char) asciinum;
         
               sb.append(character);
          }
          usename = sb.toString();
     }

     //Checks to see if a username already exist in the file
     public static void duplicate()
     {
         String name2 = null;
         try
         {  
               File file = new File("accounts.txt");

               FileReader fr = new FileReader(file);
               BufferedReader br = new BufferedReader(fr);
         
               while((line = br.readLine()) != null)
               {
                     if(dash == null)
                           dash = line;
                 
                     else if(state == 0)
                           state = Integer.parseInt(line.trim());
              
                     else if(shifting == 0)
                           shifting = Integer.parseInt(line.trim());
             
                     else if(name2 == null)
                           name2 = line;
              
                     else if(gender == null)
                           gender = line;
             
                     else if(phonenum == 0)
                           phonenum = Long.parseLong(line.trim());
             
                     else if(uni==null)
                           uni = line;
             
                     else if(password == null)
                           password = line;
             
                     else if(check == null)
                     { 
                           sb.setLength(0);    
                           check = line;
               
                           for(int n=0; n<check.length(); n++)
                           {
                                 char character = check.charAt(n);
                                 asciinum = (int) character;
         
                                 asciinum = asciinum + state;
         
                                 character = (char) asciinum;
         
                                 sb.append(character);
                           }
                           check = sb.toString();  
             
                           if(usename.equals(check))
                           {
                                 JOptionPane.showMessageDialog(null, "The username entered already exists", "error", JOptionPane.ERROR_MESSAGE);
                                 while((usename.equals(check)|| usename.length() < 8 || !Character.isAlphabetic(usename.charAt(0))) && outcome != JOptionPane.NO_OPTION)
                                 {
                                       outcome = JOptionPane.showConfirmDialog(null, e, "Choose", JOptionPane.YES_NO_OPTION);
                                       usename = id.getText();
                        
                                 } 
                            }
               
                            else
                            {
                                 dash = null; name2 = null; check = null;
                                 password = null;
                                 gender = null;
                                 phonenum = 0;
                                 uni = null;
                                 state = 0;
                                 shifting = 0;
                             }  
                     }
               }
         }
      
         catch (IOException e)
         {JOptionPane.showMessageDialog(null, "File not found.", "error", JOptionPane.ERROR_MESSAGE);}
       }
}

/*
 * 
 * Test cases 
 * .............................................  
15
15
Emmanuel 
Male
3473231538
mvsu
computer scince 
a`a`ZdT`^Z_X]`]
@dRXf^hVc_e`
/.................................................
 * 
9
7
osas
Female
3453421426
mvsu
computer scince 
`fZbe'\hf
nXc^i\\ej
null
/................................................
 * 
7
4
Marcus 
Male
2345678906
MVSU
computer scince 
lnkbaookn
gZbfgZflr^`^
null
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
