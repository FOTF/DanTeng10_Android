package fotf.space.danteng10.views;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import fotf.space.danteng10.R;
import fotf.space.danteng10.entity.ContentInfor;
import fotf.space.danteng10.util.DanTengInfo;

public class HomeFragment extends Fragment implements OnClickListener{
	
	private String name = "Home_Fragment";
	
	private Button mButton;
	private Button prexButton;
	private Button nextButton;
	
	private View view;
	
	private List<DetailFragment> dfs = new ArrayList<DetailFragment>();
	
	/*public HomeFragment(){
		mTextView = (TextView) view.findViewById(R.id.content_title);
	}*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("", "HomeFragment is on create ...");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.homefrag, container, false);
		mButton= (Button) view.findViewById(R.id.button_click);
		prexButton= (Button) view.findViewById(R.id.button_prex);
		nextButton= (Button) view.findViewById(R.id.button_next);
		mButton.setOnClickListener(this);
		prexButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	public String getName() {
		return name;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.button_click :
				//加载内容
				loadContext();
				break;
			case R.id.button_next : loadContextNext();break;
			case R.id.button_prex : loadContextPrex();break;
		}
	}
	
	public void loadContextNext(){
		clear();
		List<ContentInfor> lists = DanTengInfo.getNextDayWebContent();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		for(int i = 0, j = lists.size(); i < j; i++){
			DetailFragment df = new DetailFragment(lists.get(i).getTitle(), lists.get(i).getImgSrc());
			tx.add(R.id.content_scoll , df);
			dfs.add(df);
		}
        tx.addToBackStack(null);
        tx.commit();
	}
	
	public void loadContextPrex(){
		clear();
		List<ContentInfor> lists = DanTengInfo.getPrexDayWebContent();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		for(int i = 0, j = lists.size(); i < j; i++){
			DetailFragment df = new DetailFragment(lists.get(i).getTitle(), lists.get(i).getImgSrc());
			tx.add(R.id.content_scoll , df);
			dfs.add(df);
		}
        tx.addToBackStack(null);  
        tx.commit();
	}
	
	public void clear(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		for(int i = 0, j = dfs.size(); i < j; i++){
			tx.remove(dfs.get(i));
		}
		tx.commit();
		dfs.clear();
	}
	
	public void loadContext(){
		List<ContentInfor> lists = DanTengInfo.getWebContent();
		Toast.makeText(this.getView().getContext(), "加载内容" + lists.size(), Toast.LENGTH_SHORT).show();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction tx = fm.beginTransaction();
		for(int i = 0, j = lists.size(); i < j; i++){
			DetailFragment df = new DetailFragment(lists.get(i).getTitle(), lists.get(i).getImgSrc());
			Toast.makeText(this.getView().getContext(), lists.get(i).getTitle(), Toast.LENGTH_SHORT).show();
			tx.add(R.id.content_scoll , df);
			tx.show(df);
			dfs.add(df);
		}
        tx.addToBackStack(null);
        tx.commit();
        Toast.makeText(this.getView().getContext(), "内容加载完成", Toast.LENGTH_SHORT).show();
	}
}
