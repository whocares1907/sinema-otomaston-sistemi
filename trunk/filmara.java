package sinemaotomasyon;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class filmara  extends JFrame{
    private String flmara;
	private JTextField film= new JTextField();
	private JTextField seans1= new JTextField();
	private JTextField seans2= new JTextField();
	private JTextField seans3= new JTextField();
	private JButton btn=new JButton("Güncelle");
	private JButton ara=new JButton("Ara");
    private  Statement stmt;
    static List<Time> seanslar = new ArrayList<Time>();
    private String DuzenlenecekFilm ="";
	public filmara(){
		  initComponents();		
	}

	private void   initComponents(){
		final Container con = getContentPane();
		con.setLayout(null);
		this.setSize(500,400);
		con.add(film);
	    con.add(btn);
	    con.add(ara);
	    ara.setBounds(170, 30, 60, 32);
	    film.setBounds(20,30,140,32);seans2.setBounds(20,110,140,32);
	    seans1.setBounds(20,70,140,32);seans3.setBounds(20,150,140,32);
	    btn.setBounds(370,320,100,30);
		
	    final String URL="jdbc:mysql://localhost:3306/sinema";
        String sql="";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	   ara.addActionListener(new ActionListener(){
		   String sql="";
     	   public void actionPerformed(ActionEvent item){
     		    
     		   	DuzenlenecekFilm = film.getText();
     			try {
     	    		 sql="SELECT `seans` FROM `seanslar` WHERE  `f_adi`='"+film.getText()+"';";
     	    	 	 ResultSet rs = sorgu(URL,sql);
     	  
     	        	while (rs.next()){
     	        		//System.out.println(rs.getTime(1));
     	        		seanslar.add(rs.getTime(1));
	
     	        	}
     	        	seans1.setText(seanslar.get(0).toString());
     	        	seans2.setText(seanslar.get(1).toString());
     	       		seans3.setText(seanslar.get(2).toString());	
     			} catch (SQLException e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     			}
     			con.add(seans1);
     		    con.add(seans2);
     		    con.add(seans3);
     		    con.repaint();
     	   }});
	   
	   btn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Connection conn;
				conn=DriverManager.getConnection(URL,"root","");
				stmt=conn.createStatement();
               
				String filminismi = DuzenlenecekFilm;
				if(!filminismi.isEmpty()){
				String sql = "DELETE FROM viz_filmler WHERE filmismi = '" +filminismi + "';";	
				filmsil.sorgu(URL, sql);
				sql="DELETE FROM `seanslar` WHERE `f_adi` = '" +filminismi + "';";
				filmsil.sorgu(URL, sql);
			
      	     	sql="INSERT INTO viz_filmler(`filmismi`) " +
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
				}
				else{
			       	JOptionPane.showMessageDialog(null, "Film Adý Giriniz!!");}
			       
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Anapencere ana=new Anapencere();
		  	   ana.repaint();
		  	   ana.setVisible(true);    	
		  	   dispose();
		}
		
	});
	
	}	
	 
	 private ResultSet sorgu(String URL, String sql) throws SQLException {
			Connection conn;
			conn=DriverManager.getConnection(URL,"root","");
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
}
