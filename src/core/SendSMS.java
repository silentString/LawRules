package core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendSMS {
	
    public synchronized static String BASE64Encoder(String str) throws Exception {
	    return new sun.misc.BASE64Encoder().encode(str.getBytes());
    }
	
	public String sendMessage(String phoneNumber, String content){

		HttpURLConnection conn = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("englishtest3");
			String s = "http://61.147.98.117:9015/servlet/UserServiceAPI?method=sendSMS"
					+ "&username=18668770201&password="+SendSMS.BASE64Encoder("150ggk")+"&smstype=1"
					+ "&mobile=" + phoneNumber + "&content=" + content;
			URL url = new URL(s);
			conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Close");
			conn.setRequestProperty("Content-length", String.valueOf(stringBuffer.length()));
			conn.setDoInput(true);
			conn.connect();
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "GBK");
			out.write(stringBuffer.toString());
			out.flush();
			out.close();
			InputStream in = conn.getInputStream();
			InputStreamReader r = new InputStreamReader(in);
			LineNumberReader din = new LineNumberReader(r);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while((line=din.readLine())!=null)
			{
				sb.append(line+"\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
