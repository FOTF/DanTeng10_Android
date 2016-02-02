package fotf.space.danteng10.util;

import java.util.ArrayList;
import java.util.List;

import fotf.space.danteng10.entity.ContentInfor;

/**
 * 获取app所需要的image路径和标题
 * 
 * @author fotf
 * 
 */
public class DanTengInfo {


	private static final String mainURL = "http://www.10danteng.com";
	
	private static String loadUrl = "http://www.10danteng.com";
	
	private static String prexDayUrl = "";
	private static String nextDayUrl = "";

	
	public static List<ContentInfor> getWebContent() {
		List<ContentInfor> details = new ArrayList<ContentInfor>();
		ContentInfor info = new ContentInfor();
		info.setTitle("tes1");
		info.setImgSrc("http://ww2.sinaimg.cn/mw690/006ds4gKgw1f00a3cod3lg306o06mu0y.gif");
		details.add(info);
		return details;
	}

	public static List<ContentInfor> getNextDayWebContent(){
		return null;
	}

	public static List<ContentInfor> getPrexDayWebContent(){
		return null;
	}
	

}
