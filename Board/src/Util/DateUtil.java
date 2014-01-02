package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * <p>����ð��� ���ڿ��� ��ȯ.</p>
	 * 
	 * <pre>
	 * Util.DateUtil.getDate(1)
	 * Util.DateUtil.getDate(2)
	 * </pre>
	 * 
	 * @param type "1,2"
	 * @param 1 -> yyyy-MM-dd  , 2 -> yyyy-MM-dd hh:mm:ss
	 * @return 
	 */
	public String getDate(int type){
		String formatType =null;
		
		if(type==1)
		{
			formatType ="yyyy-MM-dd";
		}else if(type ==2){
			formatType ="yyyy년 MM월 dd일 a hh:mm";
		}else if(type ==3){
			formatType ="yyyy-MM-dd hh:mm:ss";
		}else{
			return null;
		}
		
		long currentTime =System.currentTimeMillis();
		SimpleDateFormat dataFormat = new SimpleDateFormat(formatType);
		
		String time =dataFormat.format(new Date(currentTime));
		
		return time;
	}
	
}
