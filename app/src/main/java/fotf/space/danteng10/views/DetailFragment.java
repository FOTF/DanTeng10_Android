package fotf.space.danteng10.views;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fotf.space.danteng10.util.ImageASyncTask;
import fotf.space.danteng10.util.MyASyncTask;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import fotf.space.danteng10.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

@SuppressLint("ValidFragment")
public class DetailFragment extends Fragment{
	
	private TextView mTextView;

	private GifImageView mgifView;
	private GifDrawable gifDrawable;
	private String title;
	private String imgSrc;

	private final static int DEFAULT_IMAGE_WIDTH = 64;
	private final static int DEFAULT_IMAGE_HEIGHT = 64;

	private int CHECKED_COLOR = Color.rgb(29, 118, 199); //选中蓝色
	private int UNCHECKED_COLOR = Color.GRAY;   //自然灰色

	private AsyncHttpClient asyncHttpClient;
	private Dialog dialog;
	
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

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.detail, container, false);
		mTextView = (TextView) view.findViewById(R.id.content_title_id);
		mTextView.setTextColor(UNCHECKED_COLOR);
		mgifView = (GifImageView)view.findViewById(R.id.contont_gif_image_id);
		setTitle(title);

		this.loadImage(view);

		/*ImageASyncTask ias = new ImageASyncTask();
		ias.execute(imgSrc, mgifView, view);*/
		return view;
	}

	private void loadImage(final View view){
		dialog = ProgressDialog.show(view.getContext(), "加载中", "加载网络图片中");
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient
				.get(imgSrc,
						new AsyncHttpResponseHandler() {
							@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
							@Override
							public void onSuccess(int arg0, Header[] arg1,
												  byte[] arg2) {
								// TODO Auto-generated method stub

								GifDrawable drawable = null;
								try {
									drawable = new GifDrawable(arg2);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								mgifView.setBackground(drawable);
								dialog.dismiss();
							}

							@Override
							public void onFailure(int arg0, Header[] arg1,
												  byte[] arg2, Throwable arg3) {
								// TODO Auto-generated method stub
								Toast.makeText(view.getContext(),
										"加载网络图片出错", Toast.LENGTH_SHORT).show();
								dialog.dismiss();
							}
						});
	}
	
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
