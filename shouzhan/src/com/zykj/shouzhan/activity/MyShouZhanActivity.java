package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.R;
import com.zykj.shouzhan.fragment.MSZChanPinFragment;
import com.zykj.shouzhan.fragment.MSZEnglishFragment;
import com.zykj.shouzhan.fragment.MSZGongSiFragment;
import com.zykj.shouzhan.fragment.MSZLianXiFragment;
import com.zykj.shouzhan.fragment.MSZShouYeFragment;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyShouZhanActivity extends FragmentActivity {
	private MyCommonTitle myCommonTitle;
	private RadioGroup tab_shouzhan;
	private MSZShouYeFragment shouyeFragment;//��ҳ
	private MSZChanPinFragment chanpinFragment;//��Ʒ
	private MSZGongSiFragment gongsiFragment;//��˾
	private MSZLianXiFragment lianxiFragment;//��ϵ
	private MSZEnglishFragment englishFragment;//Ӣ��
	private int checkedId = R.id.rb_shouye;// ��ǰFragment
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_my_shouzhan_activity);
		
		initView();
		requestData();
		
	}

	private void initView() {
		myCommonTitle = (MyCommonTitle) findViewById(R.id.aci_mytitle);
		myCommonTitle.setTitle(getString(R.string.my_shouzhan));
		
		myCommonTitle.setBackTitle(getString(R.string.back));
		
		tab_shouzhan = (RadioGroup) findViewById(R.id.tab_my_shouzhan);
		((RadioButton) findViewById(R.id.rb_shouye)).setText(getString(R.string.shouye));
		((RadioButton) findViewById(R.id.rb_chanpin)).setText(getString(R.string.chanpin));
		((RadioButton) findViewById(R.id.rb_gongsi)).setText(getString(R.string.gongsi));
		((RadioButton) findViewById(R.id.rb_lianxi)).setText(getString(R.string.lianxi));
		((RadioButton) findViewById(R.id.rb_english)).setText(getString(R.string.yingwen));
		
		shouyeFragment = MSZShouYeFragment.getInstance(1);//��ҳ
		chanpinFragment = MSZChanPinFragment.getInstance(2);//��Ʒ
		gongsiFragment = MSZGongSiFragment.getInstance(3);//��˾
		lianxiFragment = MSZLianXiFragment.getInstance(4);//��ϵ
		englishFragment = MSZEnglishFragment.getInstance(5);//Ӣ��

		tab_shouzhan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				MyShouZhanActivity.this.checkedId = checkedId;
				if (checkedId == R.id.rb_shouye) {
					getSupportFragmentManager().beginTransaction()
							.show(shouyeFragment).hide(chanpinFragment)
							.hide(gongsiFragment).hide(lianxiFragment).hide(englishFragment).commit();
				} else if (checkedId == R.id.rb_chanpin) {
					getSupportFragmentManager().beginTransaction()
							.show(chanpinFragment).hide(shouyeFragment)
							.hide(gongsiFragment).hide(lianxiFragment).hide(englishFragment).commit();
				} else if (checkedId == R.id.rb_gongsi) {
					getSupportFragmentManager().beginTransaction()
							.show(gongsiFragment).hide(chanpinFragment)
							.hide(shouyeFragment).hide(lianxiFragment).hide(englishFragment).commit();
				} else if (checkedId == R.id.rb_lianxi) {
					getSupportFragmentManager().beginTransaction()
							.show(lianxiFragment).hide(chanpinFragment)
							.hide(gongsiFragment).hide(shouyeFragment).hide(englishFragment).commit();
				}else if (checkedId == R.id.rb_english) {
					getSupportFragmentManager().beginTransaction()
					.show(englishFragment).hide(shouyeFragment).hide(chanpinFragment)
					.hide(gongsiFragment).hide(lianxiFragment).commit();
		}
			}
		});
	}

	/**
	 * ��������
	 */
	private void requestData() {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.my_shouzhan_framelayout, shouyeFragment)
				.add(R.id.my_shouzhan_framelayout, chanpinFragment)
				.add(R.id.my_shouzhan_framelayout, gongsiFragment)
				.add(R.id.my_shouzhan_framelayout, lianxiFragment)
				.add(R.id.my_shouzhan_framelayout, englishFragment)
				.show(shouyeFragment).hide(chanpinFragment).hide(gongsiFragment)
				.hide(lianxiFragment).hide(englishFragment).commit();
	}
	
}
