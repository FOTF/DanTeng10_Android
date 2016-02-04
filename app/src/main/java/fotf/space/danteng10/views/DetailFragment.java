package fotf.space.danteng10.views;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fotf.space.danteng10.util.ImageASyncTask;
import fotf.space.danteng10.util.MyASyncTask;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
	private GifView mgifView;

	private String title;
	private String imgSrc;

	private final static int DEFAULT_IMAGE_WIDTH = 64;
	private final static int DEFAULT_IMAGE_HEIGHT = 64;

	private int CHECKED_COLOR = Color.rgb(29, 118, 199); //选中蓝色
	private int UNCHECKED_COLOR = Color.GRAY;   //自然灰色
	
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
		View view = inflater.inflate(R.layout.detail, container, false);
		mTextView = (TextView) view.findViewById(R.id.content_title_id);
		mTextView.setTextColor(UNCHECKED_COLOR);
		mgifView = (GifView)view.findViewById(R.id.contont_gif_image_id);
		setTitle(title);
		ImageASyncTask ias = new ImageASyncTask();
		ias.execute(imgSrc, mgifView, view);

//		mgifView.setGifImage(R.drawable.gif1);
		return view;
	}
	
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("DetailFragment is before onCreateView ...");
		View view = inflater.inflate(R.layout.detail, container, false);
		mTextView = (TextView) view.findViewById(R.id.content_title_id);
		mTextView.setTextColor(UNCHECKED_COLOR);
		gifView = (GifView)view.findViewById(R.id.contont_gif_image_id);
		setTitle(title);
		try {
			InputStream is = getHttpIS("http://mpic.spriteapp.cn/ugc/2016/02/01/56aea47633e5b.gif");
			gifView.setGifImage(is);
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			String errorresult = " you = " + writer.toString();
			new  AlertDialog.Builder(view.getContext())
					.setTitle("标题" )
					.setMessage("fuck=" + errorresult)
					.setPositiveButton("确定" ,  null )
					.show();
			gifView.setGifImage(R.drawable.gif1);
		}
		return view;
	}*/

	/**
	 * 设置图片大小
	 */
	/*private void setImageSize(){
		ViewGroup.LayoutParams params = gifView.getLayoutParams();
		params.width = DEFAULT_IMAGE_WIDTH;
		params.height = DEFAULT_IMAGE_HEIGHT;
		gifView.setLayoutParams(params);

	}*/
	
	public void setTitle(String title){
		this.mTextView.setText(title);
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
