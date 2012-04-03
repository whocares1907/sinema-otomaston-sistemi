package sinemaotomasyon;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class filmekle  extends JFrame {
	private  Statement stmt;
	private JTextField film= new JTextField(null);
	private JTextField seans1= new JTextField();
	private JTextField seans2= new JTextField();
	private JTextField seans3= new JTextField();
	private JButton btn=new JButton("Kaydet");
	private JLabel Label1=new JLabel("Film Ýsmi");
	private JLabel Label2=new JLabel("Seans");
	private JLabel Label3=new JLabel("Seans");
	private JLabel Label4=new JLabel("Seans");
	private JButton git=new JButton("Geri");
	static int onceki;
	static String salon;
	private JComboBox gos=new JComboBox();

	public filmekle(){
		
        initComponents();
			
	}
	private void initComponents(){
	
		final Container con = getContentPane();
		con.setLayout(null);
		this.setSize(500,400);
		gos.addItem("Salonlar");
	    con.add(film);
	    con.add(seans1);
	    con.add(seans2);
	    con.add(seans3);
	    con.add(btn);gos.setBounds(240,35,80 ,25);
	    con.add(git);con.add(gos);
	    con.add(Label1); con.add(Label2); con.add(Label3); con.add(Label4);
	    film.setBounds(20,30,140,32);Label1.setBounds(170, 30, 100,30);seans2.setBounds(20,110,140,32);
	    Label2.setBounds(170, 70, 100,30); Label3.setBounds(170,110, 100,30); 
	    seans1.setBounds(20,70,140,32);seans3.setBounds(20,150,140,32);
	    btn.setBounds(370,320,100,30);Label4.setBounds(170, 150, 100,30);
	     git.setBounds(20, 320,80, 30);
	   
	    final String URL="jdbc:mysql://localhost:3306/sinema";
       
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		   
    		
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Integer> seanslar = new ArrayList<Integer>();
		
		 String sql="";
		  try {
			 
				sql="SELECT `salonno` FROM `salon` WHERE `bos`= '"+0+"';";
				ResultSet rs=sorgu(URL,sql);
				
				while(rs.next()){
					 System.out.println("ff");
					seanslar.add(rs.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("ff");
			for(int i:seanslar){				
				gos.addItem(i);				
			}
			     
	    
	  btn.addActionListener(new ActionListener(){
     	   
       public void actionPerformed(ActionEvent item){
    	   
        try {
        	Connection conn;
        	conn=DriverManager.getConnection(URL,"root","");
    		stmt=conn.createStatement();
    		String a=film.getText();
       	 if(!a.isEmpty()){	
        	// String a=film.getText();
         	 System.out.println(a);
       	     String	sql="INSERT INTO viz_filmler(`filmismi`) " +
       	 		       "VALUES ('"+film.getText()+"')";
	           stmt.executeUpdate(sql);

       		if(seans1.getText()!=null){	
       		   sql="INSERT INTO `seanslar` (`seans`,`f_adi`)" +
       	              "VALUES ('"+seans1.getText()+"','"+film.getText()+"')";
       		  stmt.executeUpdate(sql);
       		}
       		if(seans2.getText()!=null){
       			sql="INSERT INTO `seanslar` (`seans`,`f_adi`)" +
 	              "VALUES ('"+seans2.getText()+"','"+film.getText()+"')";
 		      stmt.executeUpdate(sql);
       		}
       		if(seans3.getText()!=null){
       			sql="INSERT INTO `seanslar` (`seans`,`f_adi`)" +
 	              "VALUES ('"+seans3.getText()+"','"+film.getText()+"')";
 		      stmt.executeUpdate(sql);
       		}
       		   
       		JOptionPane.showMessageDialog(null, "Kaydedildi");
       	 }else{
       	JOptionPane.showMessageDialog(null, "Film Adý Giriniz!!");}
       }
       		catch(Exception exp)
       		{
       		    exp.printStackTrace();
       		}
       		
  
       	    }});
        
	  git.addActionListener(new ActionListener(){
    	   
	       public void actionPerformed(ActionEvent item){
	    	   Anapencere ana=new Anapencere();
	    	   ana.setVisible(true); 
	    	   ana.repaint();   	
	    	   dispose();
	    	   
	       }});

	  gos.addItemListener(new ItemListener() {

			 public void itemStateChanged(ItemEvent item){
				 if(item.getItem().toString()=="Salonlar"){
	         		return ;
	         	}
			   salon=item.getItem().toString();
			    String sql ="SELECT `bos` FROM `salon` WHERE `salonno` = '"+ item.getItem()+"';";
			    try {
			       sql="INSERT INTO `gosterimler` (`seans`,`filmadi`,`salon`)" +
		              "VALUES ('"+seans1.getText()+"','"+film.getText()+"','"+item.getItem()+"')";
			    	 stmt.executeUpdate(sql);
	     		   sql="INSERT INTO `gosterimler` (`seans`,`filmadi`,`salon`)" +
	                   "VALUES ('"+seans2.getText()+"','"+film.getText()+"','"+item.getItem()+"')";  
	     		     stmt.executeUpdate(sql);
	     	       sql="INSERT INTO `gosterimler` (`seans`,`filmadi`,`salon`)" +
	                   "VALUES ('"+seans3.getText()+"','"+film.getText()+"','"+item.getItem()+"')";
	     	         stmt.executeUpdate(sql);
	     	      sql="DELETE FROM `salon` WHERE `salonno` = '" +item.getItem() + "';";
	     	         stmt.executeUpdate(sql);
	     	      sql="INSERT INTO `salon` ( `salonno`,`bos`)" +
                       "VALUES ('"+item.getItem() +"','"+1+"')";
    	       stmt.executeUpdate(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			  }});
   
	 
	 
      }
	 private ResultSet sorgu(String URL, String sql) throws SQLException {
			Connection conn;
			conn=DriverManager.getConnection(URL,"root","");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
}	
	
	

