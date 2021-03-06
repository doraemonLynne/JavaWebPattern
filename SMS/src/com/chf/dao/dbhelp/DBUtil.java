package com.chf.dao.dbhelp;
import java.sql.*;

/**
 * ��������������ݿ�֮������ӣ�ͬʱִ��Sql���
 * @author shuai
 *
 */
public class DBUtil {
	private static Connection conn=null;
	private static Statement st=null;
	
	//�÷���������ȡ�����֮�������
	public static Connection getConnection(){
		if(conn==null){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				String username="root";
				String password="123456";
				String url="jdbc:mysql://localhost:3306/northwnd";
				conn=DriverManager.getConnection(url,username,password);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return conn;
	}
	
	//�÷���������ȡִ��Sql������
	public static  Statement getStatement(){
		if(st==null){
			try{
				Connection c=getConnection();
				st=c.createStatement();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return st;
	}
	
	//���Sql�����Select���,�������Ե��ø÷�����ִ��
	public static ResultSet executeQuery(String sql){
		ResultSet set=null;
		try{
			Statement state=getStatement();
			set=state.executeQuery(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return set;
	}
	
	//���Sql�����insert,update,delete���,�������Ե��ø÷�����ִ��
	public static int executeUpdateInsertDelete(String sql){
		int i=0;
		try{
			Statement state=getStatement();
			i=state.executeUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return i;
	}
	
	//�÷���������ȡ���������id�е�ǰ���ֵ
	public static int getMaxId(String tableName){
		StringBuffer sql=new StringBuffer();
		sql.append("select id from "+tableName+" order by id desc;");
		int i=0;
		ResultSet set=executeQuery(sql.toString());
		try{
			while(set.next()){
				i=set.getInt("id");
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return i;
	} 
}
