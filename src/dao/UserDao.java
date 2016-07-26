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
		UserInfo userInfo = null;
		try {
			ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, phoneNumber, password);
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
				userInfo = new UserInfo(phoneNumber, password, endDate, points, registerDate, nickName, status);
			}
			dbUtil.close(resultSet);
			dbUtil.close(ps);
			dbUtil.close(conn);
			return userInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static void main(String[] args) {

	}

}
