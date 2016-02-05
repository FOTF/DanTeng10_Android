package fotf.space.danteng10.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import fotf.space.danteng10.R;
import fotf.space.danteng10.entity.ButtonInfor;
import fotf.space.danteng10.entity.ContentInfor;
import fotf.space.danteng10.views.DetailFragment;


public class MyASyncTask extends AsyncTask<Object, Integer, List<ContentInfor>> {


	List<ContentInfor> details = new ArrayList<ContentInfor>();

	private FragmentManager fm;
	private List<DetailFragment> dfs;
	private ProgressDialog pd;
	private ButtonInfor bi;
	private int state;

	@Override
	protected void onPreExecute(){
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<ContentInfor> doInBackground(Object... params) {
		StringBuffer sb = new StringBuffer("");
		fm = (FragmentManager) params[0];
		dfs = (List<DetailFragment>) params[1];
		pd = (ProgressDialog) params[2];
		bi = (ButtonInfor) params[3];
		state = (Integer) params[4];

//		ContentInfor ci = new ContentInfor("test1", "http://mpic.spriteapp.cn/ugc/2016/02/01/56aea47633e5b.gif");
//		details.add(ci);
		ContentInfor ci2 = new ContentInfor("test2", "http://ww2.sinaimg.cn/mw600/6dae210fgw1f0jxzfm3zuj20c80e7abe.jpg");
		details.add(ci2);
//		ContentInfor ci3 = new ContentInfor("test3", "http://mpic.spriteapp.cn/ugc/2016/02/01/56aea47633e5b.gif");
//		details.add(ci3);
		return details;
	}

	@Override
	protected void onPostExecute(List<ContentInfor> result) {
		clear();
		FragmentTransaction tx = fm.beginTransaction();
		for (int i = 0, j = result.size(); i < j; i++) {
			DetailFragment df = new DetailFragment(result.get(i).getTitle(),
					result.get(i).getImgSrc());
			tx.add(R.id.content_scoll, df);
			tx.show(df);
			dfs.add(df);
		}
		tx.commit();
		pd.dismiss();
	}

	public void clear() {
		if (dfs != null && dfs.size() > 0) {
			FragmentTransaction tx = fm.beginTransaction();
			for (int i = 0, j = dfs.size(); i < j; i++) {
				tx.remove(dfs.get(i));
			}
			tx.commit();
			dfs.clear();
		}
	}
}
