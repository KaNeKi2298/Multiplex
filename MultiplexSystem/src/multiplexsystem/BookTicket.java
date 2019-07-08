/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplexsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SpinnerNumberModel;
import static multiplexsystem.MovieData.date2;
import static multiplexsystem.ShowTime.disc;
import static multiplexsystem.ShowTime.offer;
import static multiplexsystem.ShowTime.screeno;
import static multiplexsystem.ShowTime.showNo;
import static multiplexsystem.ShowTime.startsAt;

/**
 *
 * @author Smit
 */
public class BookTicket extends javax.swing.JFrame {

    /**
     * Creates new form BookTicket
     */
    public int rbt;
    public int gbt;
    public int sbt;
    public int cap;
    ShowTime ST;
    public BookTicket() {
        initComponents();
        
        labelMovie.setText(MovieData.multiplexName);
        
        labelMul.setText(MovieData.selectedName);
        
        labelDate.setText(MovieData.date2);
        
        labelScreen.setText(""+ShowTime.scno);
        
        labelTime.setText(ShowTime.time);
        getCap();
        rbt=getRecliner();
        int r=20-rbt;
        gbt=getGold();
        sbt=getSilver();
        int s=cap-sbt;
        int g=40-gbt;
        leftRecliner.setText(""+r);
        leftGold.setText(""+g);
        leftSilver.setText(""+s);
        
        SpinnerNumberModel model1=new SpinnerNumberModel(0,0,r,1);
        SpinnerNumberModel model2=new SpinnerNumberModel(0,0,g,1);
        SpinnerNumberModel model3=new SpinnerNumberModel(0,0,s,1);
        
        cntRecliner.setModel(model1);
        cntGold.setModel(model2);
        cntSilver.setModel(model3);
        
        totalseats.setText("0");
        totalprice.setText("0");
        
        Status.setText("");
        
        if(r+s+g==0){
               buttonBook.setVisible(false);
               
               Status.setText("Show Already Full");
               
        }
    }
    public BookTicket(ShowTime st) {
        initComponents();
        
        labelMovie.setText(MovieData.multiplexName);
        
        labelMul.setText(MovieData.selectedName);
        
        labelDate.setText(MovieData.date2);
        
        labelScreen.setText(""+ShowTime.scno);
        
        labelTime.setText(ShowTime.time);
        getCap();
        rbt=getRecliner();
        int r=20-rbt;
        gbt=getGold();
        sbt=getSilver();
        int s=cap-sbt;
        int g=40-gbt;
        leftRecliner.setText(""+r);
        leftGold.setText(""+g);
        leftSilver.setText(""+s);
        
        SpinnerNumberModel model1=new SpinnerNumberModel(0,0,r,1);
        SpinnerNumberModel model2=new SpinnerNumberModel(0,0,g,1);
        SpinnerNumberModel model3=new SpinnerNumberModel(0,0,s,1);
        
        cntRecliner.setModel(model1);
        cntGold.setModel(model2);
        cntSilver.setModel(model3);
        
        totalseats.setText("0");
        totalprice.setText("0");
        ST=st;
    }
    public void displayseats(){
       
          int r,g,s;
          
          r=Integer.parseInt(cntRecliner.getValue().toString());
          g=Integer.parseInt(cntGold.getValue().toString());
          s=Integer.parseInt(cntSilver.getValue().toString());
          
          int x=r+g+s;
          totalseats.setText(""+x);
          
          double price;
          
          price=r*350+s*175+g*225;
          price=price*((double)(100-ShowTime.discount)/100);
          
          price=Math.ceil(price);
          
          totalprice.setText("Rs. "+price);
          
    
    }
    public int getTicId(){
        int x=0;
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("call getticid();");  
        
           while(rs.next()){
             x=rs.getInt(1);
             System.out.println(x);
     }  
 
             con.close();
           
          
          
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }
        return x+1;
    }
    public int getRecliner(){
        int x=0;
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("select count(*) from (select t.ticket_id,t.seatno,s.seat_type from ticketseat t inner join seat_nt s on t.seatno=s.seatno) ss  where ss.seat_type=\'"+"Recliner"+"\' and ss.ticket_id in (select ticket_id from ticket where show_id="+ShowTime.showno+");");  
        
           while(rs.next()){
             x=rs.getInt(1);
             System.out.println(x);
     }  
 
             con.close();
           
          
          
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }
        return x;
    }
    public int getGold(){
        int x=0;
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("select count(*) from (select t.ticket_id,t.seatno,s.seat_type from ticketseat t inner join seat_nt s on t.seatno=s.seatno) ss  where ss.seat_type=\'"+"Gold"+"\' and ss.ticket_id in (select ticket_id from ticket where show_id="+ShowTime.showno+");");  
        
           while(rs.next()){
             x=rs.getInt(1);
             System.out.println(x);
     }  
 
             con.close();
           
          
          
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }
        return x;
    }
    public int getSilver(){
        int x=0;
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("select count(*) from (select t.ticket_id,t.seatno,s.seat_type from ticketseat t inner join seat_nt s on t.seatno=s.seatno) ss  where ss.seat_type=\'"+"Silver"+"\' and ss.ticket_id in (select ticket_id from ticket where show_id="+ShowTime.showno+");");  
        
           while(rs.next()){
             x=rs.getInt(1);
             System.out.println(x);
     }  
 
             con.close();
           
          
          
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }
        return x;
    }
    public void getCap(){
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("select capacity from screen where screeno="+ShowTime.scno+" and mname=\'"+MovieData.multiplexName+"\';");  
        
           while(rs.next()){
             cap=rs.getInt(1);
     }  
 
             con.close();
           
          
          
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }
    }
    public void bookTicket(int id){
       Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
           
            String dd=java.time.LocalTime.now().toString();
          dd= dd.substring(0,5);
              
           System.out.println(dd);
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("insert into ticket values("+id+","+ShowTime.showno+",\'"+Welcome.UserEmail+"\',\'"+dd+"\');");
             con.close();
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }   
    }
    public void bookseat(int id,int seat){
        Connection con=Authentication.CustomerConn();
           try{  
            int p=0;
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("insert into ticketseat values("+id+","+seat+");");
             con.close();
      }
           catch(Exception e){
               System.out.println("jajbdckanc");
          System.out.println(e);
      }    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMovie = new javax.swing.JLabel();
        labelMul = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelTime = new javax.swing.JLabel();
        labelScreen = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelDate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        leftRecliner = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cntRecliner = new javax.swing.JSpinner();
        cntGold = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        leftGold = new javax.swing.JLabel();
        cntSilver = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        leftSilver = new javax.swing.JLabel();
        buttonBook = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        totalseats = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totalprice = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelMovie.setFont(new java.awt.Font("Sitka Small", 1, 30)); // NOI18N
        labelMovie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMovie.setText("MovieName");

        labelMul.setFont(new java.awt.Font("Arial Rounded MT Bold", 2, 20)); // NOI18N
        labelMul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMul.setText("Multiplex Name");

        jLabel2.setFont(new java.awt.Font("Sitka Small", 2, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Date");

        labelTime.setFont(new java.awt.Font("Sitka Small", 3, 20)); // NOI18N
        labelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTime.setText("3:00 PM");

        labelScreen.setFont(new java.awt.Font("Sitka Small", 3, 30)); // NOI18N
        labelScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Sitka Small", 2, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Time");

        labelDate.setFont(new java.awt.Font("Sitka Small", 3, 20)); // NOI18N
        labelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDate.setText("25-Apr-2019");

        jLabel7.setFont(new java.awt.Font("Sitka Small", 2, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Screen No.");

        jLabel3.setFont(labelDate.getFont());
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("RECLINER");

        leftRecliner.setFont(labelScreen.getFont());
        leftRecliner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftRecliner.setText("20");

        jLabel6.setFont(jLabel5.getFont());
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Seat Type");

        jLabel8.setFont(jLabel5.getFont());
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Seats Left");

        jLabel9.setFont(jLabel5.getFont());
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Seat Quantity");

        cntRecliner.setFont(jLabel5.getFont());
        cntRecliner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cntReclinerStateChanged(evt);
            }
        });

        cntGold.setFont(jLabel5.getFont());
        cntGold.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cntGoldStateChanged(evt);
            }
        });

        jLabel10.setFont(labelDate.getFont());
        jLabel10.setForeground(new java.awt.Color(255, 204, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("GOLD");

        leftGold.setFont(labelScreen.getFont());
        leftGold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftGold.setText("40");

        cntSilver.setFont(jLabel5.getFont());
        cntSilver.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cntSilverStateChanged(evt);
            }
        });

        jLabel12.setFont(labelDate.getFont());
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SILVER");

        leftSilver.setFont(labelScreen.getFont());
        leftSilver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftSilver.setText("60");

        buttonBook.setFont(new java.awt.Font("Sitka Small", 1, 24)); // NOI18N
        buttonBook.setText("BOOK NOW");
        buttonBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("No of Seats Selected:");

        totalseats.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalseats.setText("jLabel4");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Total Price:");

        totalprice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalprice.setText("jLabel13");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(labelMul, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftSilver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leftGold, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cntSilver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cntGold, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(leftRecliner, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(cntRecliner, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(185, 185, 185))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel7)
                        .addGap(29, 29, 29)
                        .addComponent(labelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(totalseats, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(totalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonBook, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMul, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftRecliner, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cntRecliner, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftGold, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cntGold, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leftSilver, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cntSilver, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalseats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(buttonBook, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookActionPerformed
        // TODO add your handling code here:
        
        int r1,g1,s1;
          
          r1=Integer.parseInt(cntRecliner.getValue().toString());
          g1=Integer.parseInt(cntGold.getValue().toString());
          s1=Integer.parseInt(cntSilver.getValue().toString());
          
          int x1=r1+g1+s1;
          
          if(x1==0){
            
                Status.setText("No tickets Selected");
                return;
            
          }
        System.out.println("gffdhvd");
        System.out.println(getTicId());
        int x=getTicId();
        bookTicket(x);
        int r,g,s;
          
          r=Integer.parseInt(cntRecliner.getValue().toString());
          g=Integer.parseInt(cntGold.getValue().toString());
          s=Integer.parseInt(cntSilver.getValue().toString());
        for(int i=1;i<=r;i++)
            bookseat(x,rbt+i);
        for(int i=1;i<=g;i++)
            bookseat(x,20+gbt+i);
        for(int i=1;i<=s;i++)
            bookseat(x,60+sbt+i);
        
        this.setVisible(false);
        
        Confirmed con=new Confirmed(this);
        con.setVisible(true);
        
        
    }//GEN-LAST:event_buttonBookActionPerformed

    private void cntReclinerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cntReclinerStateChanged
        // TODO add your handling code here:
        
        displayseats();
        
    }//GEN-LAST:event_cntReclinerStateChanged

    private void cntGoldStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cntGoldStateChanged
                     // TODO add your handling code here:
                     
         displayseats();            
    }//GEN-LAST:event_cntGoldStateChanged

    private void cntSilverStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cntSilverStateChanged
                       // TODO add your handling code here:
          displayseats();             
    }//GEN-LAST:event_cntSilverStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ST.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookTicket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Status;
    private javax.swing.JButton buttonBook;
    private javax.swing.JSpinner cntGold;
    private javax.swing.JSpinner cntRecliner;
    private javax.swing.JSpinner cntSilver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelDate;
    private javax.swing.JLabel labelMovie;
    private javax.swing.JLabel labelMul;
    private javax.swing.JLabel labelScreen;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel leftGold;
    private javax.swing.JLabel leftRecliner;
    private javax.swing.JLabel leftSilver;
    private javax.swing.JLabel totalprice;
    private javax.swing.JLabel totalseats;
    // End of variables declaration//GEN-END:variables
}
