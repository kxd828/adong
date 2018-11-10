package servlet;

import java.sql.*;
 
public class MySQLDemo {
 
    // JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
 
    // ���ݿ���û��������룬��Ҫ�����Լ�������
    static final String USER = "root";
    static final String PASS = "12346";
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //��1������ JDBC ����
            Class.forName("com.mysql.jdbc.Driver");   
            
            System.out.println("�������ݿ��У�");
            
            //��2���������ӣ�����URL���û���������
            conn = DriverManager.getConnection(DB_URL,USER,PASS);  
            
            System.out.println(" ʵ����Statement����...");
            
            //��3���������ݿ����
            stmt = conn.createStatement();
            
            //��4���������ݿ����
            String sql;          
            sql = "SELECT id, name, url FROM websites";
            
            //��5��ִ�����
            ResultSet rs = stmt.executeQuery(sql);
        
            // չ����������ݿ�
            while(rs.next()){
                // ͨ���ֶμ���
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // �������
                System.out.print("ID: " + id);
                System.out.print(", վ������: " + name);
                System.out.print(", վ�� URL: " + url);
                System.out.print("\n");
            }
            // ��6���ر�����
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(SQLException se){
            // ���� JDBC ����
            se.printStackTrace();
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }finally{
            // �ر���Դ
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// ʲô������
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}