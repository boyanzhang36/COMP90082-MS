//STEP 1. Import required packages
import java.sql.*;

public class JavaSQL {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:8889";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
//      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "USE medsec;";
      stmt.executeQuery(sql);
      sql = "SELECT * FROM Doctor";
//      sql = "SELECT id, first, last, age FROM Employees";
      ResultSet rs = stmt.executeQuery(sql);

      int id = 0;
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         id  = rs.getInt("id");
         int phone = rs.getInt("phone");
         String name = rs.getString("name");
         String add = rs.getString("address");
         String bio = rs.getString("bio");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Phone: " + phone);
         System.out.print(", Name: " + name);
         System.out.print(", Address: " + add);
         System.out.println(", Bio: " + bio);

      }
      
      // STEP 901: Insert Prepared Statement
//      sql = " insert into Doctor (name, bio, address, phone, email, id)"
//    	        + " values (?, ?, ?, ?, ?, ?)";
      PreparedStatement pStmt = conn.prepareStatement(sql);
//      pStmt.setString(1, "Kogan");
//      pStmt.setString(2, "");
//      pStmt.setString(3, "Eliz St");
//      pStmt.setInt(4, 0421556);
//      pStmt.setString(5, "Nil@123.com");
//      pStmt.setInt(6, id+1);
//      pStmt.execute();
      
      // ADD()
      addDoctor(conn, "Alan", "", "", 996, "", id+1);
      
      // STEP 902: Update SQL
      sql = "UPDATE Doctor set phone = ? where id = ?";
      pStmt = conn.prepareStatement(sql);
      pStmt.setInt(1, 996);
      pStmt.setInt(2, 1);
      pStmt.executeUpdate();
      
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
      
     
      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
   }//end try
   System.out.println("Goodbye!");
   
   }//end main		
   
   public static void addDoctor (Connection conn, String name, String bio, String add, Integer phone, String email, Integer id) throws SQLException {
	 String sql = " insert into Doctor (name, bio, address, phone, email, id)"
   	        + " values (?, ?, ?, ?, ?, ?)";
     PreparedStatement pStmt = conn.prepareStatement(sql);
     pStmt.setString(1, name);
     pStmt.setString(2, bio);
     pStmt.setString(3, add);
     pStmt.setInt(4, phone);
     pStmt.setString(5, email);
     pStmt.setInt(6, id);
     pStmt.execute();
   }
   
}//end FirstExample










