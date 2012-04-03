package sinemaotomasyon;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
  
public class Anapencere  extends JFrame{
	public static String secilen="";
	private  JComboBox filmler;
	private  JButton ekle;
	private  JButton sil;
	private  JButton guncelle;
	static   String onceki="";
	private  JButton sec;
	static List<Time> filmSeanslar = new ArrayList<Time>();
	
	public Anapencere(){
	
	      initComponents();	
	}

	private void initComponents(){
		JPanel anapanel=new JPanel();
		anapanel.setLayout(new FlowLayout());
		this.setSize(500,400);
    	final String URL="jdbc:mysql://localhost:3306/sinema";
        String sql="";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	List<String> gelen = new ArrayList<String>();

    	try {
    		sql="SELECT `filmismi` FROM `viz_filmler`";
    		ResultSet rs = sorgu(URL, sql);
        	
        	while (rs.next()){
        		System.out.println("don");
        		gelen.add(rs.getString(1));
        	}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	System.out.println("gelen "+ gelen);
		filmler=new JComboBox();
		filmler.addItem("Gosterimdeki Filmler");
		for (String i:gelen)
			filmler.addItem(i);
		ekle = new JButton("Ekle");
		sil = new JButton("Sil");
		sil.addActionListener(new filmsil());
		guncelle = new JButton("Güncelle");
		anapanel.add(filmler);
		anapanel.add(ekle);
		anapanel.add(sil);
		anapanel.add(guncelle);
		
		 final List<JRadioButton> seansButonlari = new ArrayList<JRadioButton>();
		 final ButtonGroup seansButonGrubu = new ButtonGroup();
		 final JPanel altpanel=new JPanel();
		 sec=new JButton("Git");
		
		 
		 filmler.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent item){
	            	if(item.getItem().toString()=="Gosterimdeki Filmler"){
	            		return ;
	            	}
	            	if(item.getItem().toString()==onceki){
	            		return ;
	            	}
	            	onceki=item.getItem().toString();
	            	String sqlQuery ="SELECT `seans` FROM `seanslar` WHERE `f_adi` = '"+ item.getItem().toString()+"';";
	            	System.out.println(sqlQuery);                                                                   
	            	for (JRadioButton i: seansButonlari){
	            		
						altpanel.remove(i);
	            	}
	            	secilen= item.getItem().toString();
					seansButonlari.clear();
					 altpanel.setVisible(false);
					 altpanel.setVisible(true);
	            	try {
						ResultSet seanslar = sorgu(URL, sqlQuery);
						filmSeanslar.clear();
						while(seanslar.next()){
							filmSeanslar.add((Time) seanslar.getTime(1));
						}
						for(Time a:filmSeanslar)
							System.out.println("seans : "+a);
					
						for(Time i:filmSeanslar){
							seansButonlari.add(new JRadioButton((String) i.toString()));
							seansButonGrubu.add(seansButonlari.get(seansButonlari.size()-1));
						}
						for(JRadioButton i:seansButonlari)
							altpanel.add(i);
						altpanel.setVisible(false);
						altpanel.setVisible(true);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					

					
	            }});
	    
          ekle.addActionListener(new ActionListener(){
        	   
        	 public void actionPerformed(ActionEvent item){
        	   Anapencere ana=new Anapencere();
          	  // ana.hide(); 
        	   filmekle flm=new filmekle();
        	   flm.setVisible(true);
        	   dispose();
        	 
        	   }});

        
          guncelle.addActionListener(new ActionListener(){
       	   
         	 public void actionPerformed(ActionEvent item){
         		 
         		filmara flm=new filmara();
         	    flm.setVisible(true);
         	     dispose();
         	 
         	 
         	   }});
          getContentPane().add(anapanel, BorderLayout.NORTH);
		  
		  JLabel seanslarLabel = new JLabel("Seanslar");
		  altpanel.add(seanslarLabel);
	     
	      
	      altpanel.addAncestorListener(new AncestorListener() {
			
			@Override
			public void ancestorRemoved(AncestorEvent event) {
				System.out.println("hede");
				
			}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {
				System.out.println("1hede");
				
			}
			
			@Override
			public void ancestorAdded(AncestorEvent event) {
				System.out.println("2hede");
	
			}
		}); 
	      getContentPane().add(altpanel, BorderLayout.CENTER);
	      anapanel.add(sec,BorderLayout.PAGE_END);
	    
	
	      sec.addActionListener(new ActionListener(){
	       	   
	         	 public void actionPerformed(ActionEvent item){	
	
	         		JRadioButton a = getSelection(seansButonGrubu);
	         		if(a.isSelected()){
	         		salon salon=new salon();
	         		salon.setVisible(true);
	         		dispose();
	         		}
	     }});	
	
	}
	public static ResultSet sorgu(String URL, String sql) throws SQLException {
		Connection conn;
	    Statement stmt;
		conn=DriverManager.getConnection(URL,"root","");
		stmt=conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	// This method returns the selected radio button in a button group
	public static JRadioButton getSelection(ButtonGroup group) {
	    for (Enumeration e=group.getElements(); e.hasMoreElements(); ) {
	        JRadioButton b = (JRadioButton)e.nextElement();
	        if (b.getModel() == group.getSelection()) {
	            return b;
	        }
	     String name=b.getName();
	    }
	    return null;
	}

	}
