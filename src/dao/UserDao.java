package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import bean.UserInfo;
import bean.UserInfo.StatusEnum;
import util.Common;
import util.DBUtil;

public class UserDao {
	
	DBUtil dbUtil;
	
	public UserDao(){
		dbUtil = new DBUtil();
	}
	
	public boolean userExist(String phoneNumber) {
		boolean exist = false;
		String sql = "select * from user_info where phone_num = ?";
		Connection conn = dbUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Common.setSqlParam(ps, phoneNumber);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				exist = true;
			}
			dbUtil.close(resultSet);
			dbUtil.close(ps);
			dbUtil.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
				
	}
	
	public boolean addUser(UserInfo info) {
		Connection conn = dbUtil.getConnection();
		String sql = "INSERT INTO user_info values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, info.getPhoneNumber(), info.getPassword(),
					info.getEndDate(), info.getPoints(), info.getRegisterDate(), info.getNickName(),
					info.getStatus());
			if (!setResult) {
				return false;
			}
			ps.executeUpdate();
			dbUtil.close(ps);
			dbUtil.close(conn);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
		
	}
	
	public UserInfo queryUser(String phoneNumber, String password) {
		Connection conn = dbUtil.getConnection();
		String sql = "select * from user_info where(phone_num = ? and password = ?)";
		PreparedStatement ps;
		UserInfo userInfo = new UserInfo("0", "0");
		try {
			ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, phoneNumber, password);
			if (!setResult) {//server error
				return null;
			}
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				Date endDate = resultSet.getTimestamp("end_date");
				double points = resultSet.getDouble("points");
				Date registerDate = resultSet.getTimestamp("register_date");
				String nickName = resultSet.getString("nick_name");
				StatusEnum status = Enum.valueOf(StatusEnum.class, resultSet.getString("status"));
				userInfo = new UserInfo(phoneNumber, password, endDate, points, registerDate, nickName, status);
			}
			dbUtil.close(resultSet);
			dbUtil.close(ps);
			dbUtil.close(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userInfo;
		
	}
	
	public UserInfo queryUser(String phoneNumber) {
		Connection conn = dbUtil.getConnection();
		String sql = "select * from user_info where phone_num = ?)";
		PreparedStatement ps;
		UserInfo userInfo = new UserInfo("0", "0");
		try {
			ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, phoneNumber);
			if (!setResult) {
				return null;
			}
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				Date endDate = resultSet.getTimestamp("end_date");
				double points = resultSet.getDouble("points");
				Date registerDate = resultSet.getTimestamp("register_date");
				String nickName = resultSet.getString("nick_name");
				StatusEnum status = Enum.valueOf(StatusEnum.class, resultSet.getString("status"));
				userInfo = new UserInfo(phoneNumber, "******", endDate, points, registerDate, nickName, status);
			}
			dbUtil.close(resultSet);
			dbUtil.close(ps);
			dbUtil.close(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userInfo;
	}
	
	public String changePwd(String phoneNumber, String newPwd) {
		UserDao dao = new UserDao();
		if (!dao.userExist(phoneNumber)) {//user did not exist
			return "";
		}
		Connection conn = dbUtil.getConnection();
		String sql = "update user_info set password = ? where phone_num = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, newPwd, phoneNumber);
			if (!setResult) {
				return null;
			}
			if (1 == ps.executeUpdate()) {//change the password success
				UserInfo info = queryUser(phoneNumber);
				if (null == info || info.getPhoneNumber().equals("0")) {
					return "unfound";
				} else {
					return info.getNickName();
				}
			} else {//server error
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {

	}

}
