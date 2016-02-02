package fotf.space.danteng10.views;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.liao.GifView;

import fotf.space.danteng10.R;

@SuppressLint("ValidFragment")
public class DetailFragment extends Fragment{
	
	private TextView mTextView;
	private GifView gifView;
//	private ImageView mImageView;

	private String title;
	private String imgSrc;
	
	public DetailFragment() {
		super();
	}

	public DetailFragment(String title, String imgSrc) {
		super();
		this.title = title;
		this.imgSrc = imgSrc;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("DetailFragment is on create ...");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("DetailFragment is before onCreateView ...");
		View view = inflater.inflate(R.layout.detail, container, false);
		mTextView = (TextView) view.findViewById(R.id.content_title_id);
//		mImageView = (ImageView) view.findViewById(R.id.contont_image_id);
		gifView = (GifView)view.findViewById(R.id.contont_gif_image_id);
		setTitle(title);
//		mImageView.setImageBitmap(getHttpBitmap(imgSrc));
		gifView.setGifImage(R.drawable.gif1);
		return view;
	}
	
	public void setTitle(String title){
		this.mTextView.setText(title);
	}
	
	/** 
     * 获取网落图片资源  
     * @param url 
     * @return 
     */  
    public static Bitmap getHttpBitmap(String url){  
        URL myFileURL;  
        Bitmap bitmap = null;
        try{  
            myFileURL = new URL(url);  
            //获得连接  
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();  
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制  
            conn.setConnectTimeout(6000);  
            //连接设置获得数据流  
            conn.setDoInput(true);  
            //不使用缓存  
            conn.setUseCaches(false);  
            //这句可有可无，没有影响  
            //conn.connect();  
            //得到数据流  
            InputStream is = conn.getInputStream();
			Log.d("image", is.toString());
            //解析得到图片  
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }

		return bitmap;
          
    }

	/**
	 * 获取网落图片资源
	 * @param url
	 * @return
	 */
	public static InputStream getHttpIS(String url){
		URL myFileURL;
		InputStream  is = null;
		try{
			Log.d("image", "get connection");
			myFileURL = new URL(url);
			//获得连接
			HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
			//设置超时时间为6000毫秒，
			 conn.setConnectTimeout(0);//表示没有时间限制
//			conn.setConnectTimeout(6000);
			//连接设置获得数据流
			conn.setDoInput(true);
			//不使用缓存
			conn.setUseCaches(false);
			//这句可有可无，没有影响
			//conn.connect();
			//得到数据流
			is = conn.getInputStream();
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return is;
	}


	
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("HomeFragment is onResume ...");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("HomeFragment is onStop ...");
	}

}
