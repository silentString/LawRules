package util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.UserInfo.StatusEnum;

public class Common {
	
	public static boolean setSqlParam(PreparedStatement ps, Object... params) {
		
		int index = 1;
		for(Object object : params) {
			
			try {
				if (object == null) {
					ps.setNull(index++, Types.NULL);
				} else if(object instanceof Double) {
					ps.setDouble(index++, (double)object);
				} else if(object instanceof Integer) {
					ps.setInt(index++, (int)object);
				} else if (object instanceof String) {
					ps.setString(index++, object.toString());
				} else if (object instanceof Long) {
					ps.setLong(index++, (long)object);
				} else if (object instanceof Date) {
					ps.setTimestamp(index++, castTime(object));
				} else if (object instanceof StatusEnum) {
					ps.setString(index++, object.toString());
				} else {
					System.out.println("unknown type " + object);
					return false;
				}
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
				return false;
			}	
		} 
		return true;
	}
	
	public static Timestamp castTime(Object obj) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = (Date)obj;
		String dateTime = dateFormat.format(date);
		Timestamp ts = Timestamp.valueOf(dateTime);
		return ts;
	}

	public static void main(String[] args) {
		Object object = new Date();
		System.out.print(castTime(object));
	}

}
