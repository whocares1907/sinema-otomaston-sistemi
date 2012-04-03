package sinemaotomasyon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;


import javax.swing.JOptionPane;

public class filmsil implements ActionListener {
	public static int sorgu(String URL, String sql) throws SQLException {
		Connection conn;
	    Statement stmt;
		conn=DriverManager.getConnection(URL,"root","");
		stmt=conn.createStatement();
		int result = stmt.executeUpdate(sql);
		return result;
	}
	private  Statement stmt1;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// TODO Auto-generated method stub
		 String  filminismi=JOptionPane.showInputDialog("Filmin Adýný Giriniz");
		String URL="jdbc:mysql://localhost:3306/sinema";
		try {
			Connection conn;
        	conn=DriverManager.getConnection(URL,"root","");
    		stmt1=conn.createStatement();
    		int i;
			String sql = "DELETE FROM viz_filmler WHERE filmismi = '" +filminismi + "';";	
			sorgu(URL, sql);
			sql="DELETE FROM `seanslar` WHERE `f_adi` = '" +filminismi + "';";
			sorgu(URL, sql);
			sql="SELECT `salon` FROM `gosterimler` WHERE  `filmadi`='"+filminismi+"';";
			ResultSet rs=stmt1.executeQuery(sql);
			while(rs.next()){
			  i=rs.getInt(1);
			  sql="DELETE FROM `salon` WHERE `salonno` = '" +i+ "';";
				sorgu(URL, sql);
				sql="INSERT INTO `salon` ( `salonno`,`bos`)" +
	             "VALUES ('"+i+"','"+0+"')";
				sorgu(URL, sql);
			}
			
			sql="DELETE FROM `gosterimler` WHERE `filmadi` = '" +filminismi + "';";
			sorgu(URL, sql);
			sql="DELETE FROM `koltuklar` WHERE `filmi` = '" +filminismi + "';";
			sorgu(URL, sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 JOptionPane.showMessageDialog(null, "Silindi");
		  Anapencere ana=new Anapencere();
		    ana.dispose();
   	        ana.repaint();   	
   	      
		
   	    
	}
	
	
}	
	


