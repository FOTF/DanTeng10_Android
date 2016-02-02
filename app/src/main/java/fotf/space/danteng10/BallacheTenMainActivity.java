package fotf.space.danteng10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import fotf.space.danteng10.views.DetailFragment;
import fotf.space.danteng10.views.HomeFragment;
import fotf.space.danteng10.views.PostFragment;
import fotf.space.danteng10.views.RankFragment;
import fotf.space.danteng10.views.SubmitFragment;

public class BallacheTenMainActivity extends FragmentActivity implements OnCheckedChangeListener{
	
	private int mMainContainerId;
	private RadioGroup mainGroup;
	
	private HomeFragment mHomeFragment;
	private PostFragment mPostFragment;
	private RankFragment mRankFragment;
	private SubmitFragment mSubmitFragment;

	private DetailFragment df;
	
	private Fragment thizFragment;
	
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;


	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		
		initFragment();
		mHomeFragment = (HomeFragment) showFrag(mHomeFragment, HomeFragment.class);
		Log.d("debug", "create fragment");
	}
	
	public void initFragment(){
		mainGroup = (RadioGroup) this.findViewById(R.id.main_group);
		mainGroup.setOnCheckedChangeListener(this);
		mMainContainerId = R.id.main_container;
		fragmentManager = getSupportFragmentManager();
		Log.d("debug", "init fragment");
	}
	
	public Fragment showFrag(Fragment frag, Class<?> cla){
		fragmentTransaction = fragmentManager.beginTransaction();
		if(thizFragment == null || thizFragment != frag){
			if(frag == null){
				try {
					frag = (Fragment) cla.newInstance();
				} catch (InstantiationException e) {
					return null;
				} catch (IllegalAccessException e) {
					return null;
				}
				fragmentTransaction.add(R.id.main_container, frag);
			}
			if(thizFragment != null){
				fragmentTransaction.hide(thizFragment);
			}
			fragmentTransaction.show(frag);
			thizFragment = frag;
		}
		fragmentTransaction.commit();
		
		return frag;
	}
	
	
	/**
	 * 显示主页
	 */
//	public void showHomeFrag(){
//		fragmentTransaction = fragmentManager.beginTransaction();
//		if(thizFragment == null || thizFragment != mHomeFragment){
//			if(mHomeFragment == null){
//				mHomeFragment = new HomeFragment();
//				fragmentTransaction.add(mMainContainerId, mHomeFragment);
//			}
//			if(thizFragment != null){
//				fragmentTransaction.hide(thizFragment);
//			}
//			fragmentTransaction.show(mHomeFragment);
//			thizFragment = mHomeFragment;
//		}
//		fragmentTransaction.commit();
//	}
//	
//	public void showPostFrag(){
//		if(thizFragment == null || thizFragment != mPostFragment){
//			if(mPostFragment == null){
//				mPostFragment = new PostFragment();
//				fragmentTransaction.add(mMainContainerId, mPostFragment);
//			}
//			if(thizFragment != null){
//				fragmentTransaction.hide(thizFragment);
//			}
//			fragmentTransaction.show(mPostFragment);
//			thizFragment = mPostFragment;
//		}
//	}
//	
//	public void showRankFrag(){
//		if(thizFragment == null || thizFragment != mRankFragment){
//			if(mRankFragment == null){
//				mRankFragment = new RankFragment();
//				fragmentTransaction.add(mMainContainerId, mRankFragment);
//			}
//			if(thizFragment != null){
//				fragmentTransaction.hide(thizFragment);
//			}
//			fragmentTransaction.show(mRankFragment);
//			thizFragment = mRankFragment;
//		}
//	}
//	
//	public void showSubmitFrag(){
//		if(thizFragment == null || thizFragment != mSubmitFragment){
//			if(mSubmitFragment == null){
//				mSubmitFragment = new SubmitFragment();
//				fragmentTransaction.add(mMainContainerId, mSubmitFragment);
//			}
//			if(thizFragment != null){
//				fragmentTransaction.hide(thizFragment);
//			}
//			fragmentTransaction.show(mSubmitFragment);
//			thizFragment = mSubmitFragment;
//		}
//	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId){
			case R.id.tab_main_home :
//				showFrag(df, DetailFragment.class);
				mHomeFragment = (HomeFragment) showFrag(mHomeFragment, HomeFragment.class);
				break;
			case R.id.tab_main_post :
				mPostFragment = (PostFragment) showFrag(mPostFragment, PostFragment.class);
				break;
			case R.id.tab_mian_rank :
				mRankFragment = (RankFragment) showFrag(mRankFragment, RankFragment.class);
				break;
			case R.id.tab_main_submit :
				mSubmitFragment = (SubmitFragment) showFrag(mSubmitFragment, SubmitFragment.class);
				break;
			case R.id.tab_main_more :
				break;
		}
	}
}
