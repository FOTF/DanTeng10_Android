package fotf.space.danteng10.views;



import java.util.ArrayList;
import java.util.List;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import fotf.space.danteng10.R;
import fotf.space.danteng10.entity.ButtonInfor;
import fotf.space.danteng10.util.MyASyncTask;

public class HomeFragment extends Fragment implements OnClickListener{
	
	private String name = "Home_Fragment";
	
	private Button mButton;
	private Button prexButton;
	private Button nextButton;
	
	private View view;
	
	private List<DetailFragment> dfs = new ArrayList<DetailFragment>();
	
	private Context context;
	private MyASyncTask yncTask;
	private FragmentManager fm;
	
	private ProgressDialog pd;
	private ButtonInfor bi = new ButtonInfor();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("HomeFragment is on create ...");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("HomeFragment is onCreateView ...");
		view = inflater.inflate(R.layout.homefrag, container, false);
		mButton= (Button) view.findViewById(R.id.button_click);
		prexButton= (Button) view.findViewById(R.id.button_prex);
		nextButton= (Button) view.findViewById(R.id.button_next);
		mButton.setOnClickListener(this);
		prexButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);
		
		context = view.getContext();
		
		pd = ProgressDialog.show(context, "", "内容加载中", true, true);
		fm = getFragmentManager();
		yncTask = new MyASyncTask();
		yncTask.execute(fm, dfs, pd, bi, 0);
		
		return view;
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

	public String getName() {
		return name;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()){
			case R.id.button_click : 
				pd = ProgressDialog.show(context, "", "内容加载中", true, true);
				yncTask = new MyASyncTask();
				yncTask.execute(fm, dfs, pd, bi, 10);
				break;
			case R.id.button_next : 
				pd = ProgressDialog.show(context, "", "内容加载中", true, true);
				yncTask = new MyASyncTask();
				yncTask.execute(fm, dfs, pd, bi, 1);
				break;
			case R.id.button_prex : 
				pd = ProgressDialog.show(context, "", "内容加载中", true, true);
				yncTask = new MyASyncTask();
				yncTask.execute(fm, dfs, pd, bi, -1);
				break;
		}
	}
}
