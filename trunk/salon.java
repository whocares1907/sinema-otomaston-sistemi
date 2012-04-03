package sinemaotomasyon;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class salon extends JFrame{
	private  Statement stmt;
	
	 public salon() {
	        initComponents();
	    }

	private void initComponents() {
	
		JPanel panel=new JPanel();
		getContentPane().add(panel);
	    this.setSize(400,250);
	    final List<JCheckBox> koltuklar = new ArrayList<JCheckBox>();
	    final List<JCheckBox> secilenkoltuk = new ArrayList<JCheckBox>();
	    final List<Integer> secilmiskoltuklar = new ArrayList<Integer>();
	    final List<Integer> salon=new ArrayList<Integer>();
	    final List<String> knumaralari=new ArrayList<String>();
		JButton btn=new JButton("Satýn Al");
		final String URL="jdbc:mysql://localhost:3306/sinema";
        String sql="";
        
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 String sql1 ="SELECT `koltukno` FROM `koltuklar` WHERE `filmi` = '"+Anapencere.secilen+"';";
         Connection conn;
        try {
			conn=DriverManager.getConnection(URL,"root","");
			stmt=conn.createStatement();	
		    ResultSet rs1=stmt.executeQuery(sql1);
		   if(!rs1.wasNull()){
		    while(rs1.next()){
	   			secilmiskoltuklar.add(rs1.getInt(1));	
	   			System.out.println("as");
	   		}
		    
		   }  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 if(secilmiskoltuklar.isEmpty()){
		  System.out.println("bs");
		  for(Integer i=1;i<35;i++){ 
			  koltuklar.add(new JCheckBox(i.toString()));	  
		  }
		  System.out.println(koltuklar.size());
	  }
			
	  else{ for(Integer i=1;i<35;i++){
		  System.out.println("cs");
			  for(int j=0;j<secilmiskoltuklar.size();j++){
			     if(i==secilmiskoltuklar.get(j)){			 
				  JCheckBox disabledCheck = new JCheckBox(i.toString());
				    disabledCheck.setEnabled(false);
				    koltuklar.add(disabledCheck);  
				    
			     }else{
				 
			     koltuklar.add(new JCheckBox(i.toString()));
			     setEnabled(true);
			    }
			 }   
		 }
	  }


		
	   btn.addActionListener(new ActionListener(){
		   String sql="";
      	 public void actionPerformed(ActionEvent item){
      		String sqlQuery ="SELECT `salon` FROM `gosterimler` WHERE `filmadi` = '"+ Anapencere.secilen+"';";
    
    		    	try {
    		    		Connection conn;
        	        	conn=DriverManager.getConnection(URL,"root","");
        	    		stmt=conn.createStatement();
        	    		ResultSet rs=stmt.executeQuery(sqlQuery);
        	    		while(rs.next()){
        	    			salon.add(rs.getInt(1));
        	    		}
        	    		 for(JCheckBox i:koltuklar){ 
        	    		       if(i.isSelected()){
        	    		    	secilenkoltuk.add(i);  
        	    		    	knumaralari.add(i.toString());
        	    		   sql="INSERT INTO `koltuklar` (`salon`,`koltukno`,`filmi`)" +
          	                    "VALUES ('"+salon.get(1)+"','"+i.getLabel().toString()+"','"+Anapencere.secilen+"')";
        	    		       }
        	    		 }   
						stmt.executeUpdate(sql);
					
						System.out.println("aa");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					  
    		    	System.out.println("dd");
    		    int toplam=	knumaralari.size()*8;
    		    JOptionPane.showInputDialog( "Toplam Tutar",toplam);
    		  
      	 }});
	 
		
	   for(int i=0;i<koltuklar.size();i++){
		   panel.add(koltuklar.get(i));
	   }
	   

	   System.out.println(koltuklar.size());
	   panel.add(btn); 
	}
	
	
}
