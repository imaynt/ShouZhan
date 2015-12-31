package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.R;
import com.zykj.shouzhan.fragment.MyShouZhanFragment;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MyShouZhanActivity extends FragmentActivity {
	private MyCommonTitle myCommonTitle;
	private RadioGroup tab_shouzhan;
	private MyShouZhanFragment shouyeFragment, chanpinFragment, gongsiFragment,lianxiFragment,englishFragment;
	private int checkedId = R.id.rb_shouye;// 当前Fragment

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
		
		shouyeFragment = MyShouZhanFragment.getInstance(1);//首页
		chanpinFragment = MyShouZhanFragment.getInstance(2);//产品
		gongsiFragment = MyShouZhanFragment.getInstance(3);//公司
		lianxiFragment = MyShouZhanFragment.getInstance(4);//联系
		englishFragment = MyShouZhanFragment.getInstance(5);//英文

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
	 * 加载数据
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
