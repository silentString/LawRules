package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Common;
import util.DBUtil;

public class CodeDao {
	DBUtil dbUtil;
	
	public CodeDao() {
		dbUtil = new DBUtil();
	}
	
	private boolean codeExist(String phoneNumber) {
		boolean exist = false;
		String sql = "select * from identify_code where phone_num = ?";
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
	
	private boolean addCode(String phoneNumber, int code) {
		Connection conn = dbUtil.getConnection();
		String sql = "INSERT INTO identify_code values(?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, phoneNumber, code);
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
	
	public int queryCode(String phoneNumber) {
		Connection conn = dbUtil.getConnection();
		String sql = "select * from identify_code where phone_num = ?";
		PreparedStatement ps;
		int code = 0;
		try {
			ps = conn.prepareStatement(sql);
			boolean setResult = Common.setSqlParam(ps, phoneNumber);
			if (!setResult) {
				return 0;
			}
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				code = resultSet.getInt("code");
			}
			dbUtil.close(resultSet);
			dbUtil.close(ps);
			dbUtil.close(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return code;
	}
	
	public boolean updateCode(String phoneNum, int code){
		String sql = "";
		Connection conn = dbUtil.getConnection();
		PreparedStatement ps;
		if (codeExist(phoneNum)) {
			sql = "update identify_code set code = ? where phone_num = ?";
			try {
				ps = conn.prepareStatement(sql);
				boolean setResult = Common.setSqlParam(ps, code, phoneNum);
				if (!setResult) {
					return false;
				}
				ps.executeUpdate();
				dbUtil.close(ps);
				dbUtil.close(conn);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return addCode(phoneNum, code);
		}
		
	}
	

	public static void main(String[] args) {
		CodeDao dao = new CodeDao();
		System.out.println(dao.updateCode("18710961111", 49856));
	}
	
}
