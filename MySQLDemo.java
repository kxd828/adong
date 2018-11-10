package servlet;

import java.sql.*;
 
public class MySQLDemo {
 
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 
    //url说明：
    //(1)jdbc:mysql: 是指JDBC连接方式；
    //(2)localhost: 是指你的本机地址；
    //(3)3306: SQL数据库的端口号；
    //(4)RUNOOB: 本地mysql中数据库的名称
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
 
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //（1）加载 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");   
            
            System.out.println("连接数据库中：");
            
            //（2）建立连接：输入URL、用户名、密码
            conn = DriverManager.getConnection(DB_URL,USER,PASS);  
            
            System.out.println(" 实例化Statement对象...");
            
            //（3）创造数据库对象
            stmt = conn.createStatement();
            
            //（4）建立数据库语句
            String sql;          
            sql = "SELECT id, name, url FROM websites";
            
            //（5）执行语句
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // （6）关闭连接
            rs.close();
            stmt.close();
            conn.close();
            
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
