package fotf.space.danteng10.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import com.ant.liao.GifView;

import org.apache.commons.io.IOUtils;

import fotf.space.danteng10.BallacheTenMainActivity;
import fotf.space.danteng10.R;

public class ImageASyncTask extends AsyncTask<Object, Integer, byte[]> {

	private GifView mGifView = null;

	private int _displaywidth;
	private int _displayheight = 400;
	private int _displaypixels;

	private View view;

	@Override
	protected void onPreExecute(){
	}
	
	@Override
	protected byte[] doInBackground(Object... params) {
		String url = (String) params[0];
		mGifView = (GifView) params[1];
		view = (View) params[2];
		byte[] bitmap = null;
		try {
			bitmap = getBitmap(url, _displaypixels, true);
		} catch (Exception e) {
			return null;
		}
		return bitmap;
	}

	/**
	 * 通过URL获得网上图片。如:http://www.xxxxxx.com/xx.jpg
	 * */
	public byte[] getBitmap(String url, int displaypixels, Boolean isBig)
			throws MalformedURLException, IOException {
//		Bitmap bmp = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		InputStream stream = new URL(url).openStream();
		byte[] bytes = getBytes(stream);
		//if(getImageExtendName(url).equals("jpg")){
			/*BitmapFactory.Options opts = new BitmapFactory.Options();
			InputStream stream = new URL(url).openStream();
			bytes = getBytes(stream);
			// 这3句是处理图片溢出的begin( 如果不需要处理溢出直接 opts.inSampleSize=1;)
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			opts.inSampleSize = computeSampleSize(opts, -1, displaypixels);
			// end
			opts.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);*/
		//}
		return bytes;
	}

	@Override
	protected void onPostExecute(byte[] result) {
		if (mGifView != null && result != null) {
			mGifView.setGifImage(R.drawable.gif1);
//			if (null != result)
//				System.gc();
		}
	}

	/**
	 * 数据流转成btyle[]数组
	 * */
	private byte[] getBytes(InputStream is) {
		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/****
	 * 处理图片bitmap size exceeds VM budget （Out Of Memory 内存溢出）
	 */
	private int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	/**
	 * 描述： 获取图片的格式 <br/>
	 * 作者： fotf
	 * 
	 * @param url
	 *            文件路径
	 * @return String <br/>
	 */
	public String getImageExtendName(String url) {
		return url.substring(url.lastIndexOf(".") + 1, url.length());
	}
}
