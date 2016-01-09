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
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MyShouZhanActivity extends FragmentActivity {
	private MyCommonTitle myCommonTitle;
	private RadioGroup tab_shouzhan;
	private MSZShouYeFragment shouyeFragment;//首页
	private MSZChanPinFragment chanpinFragment;//产品
	private MSZGongSiFragment gongsiFragment;//公司
	private MSZLianXiFragment lianxiFragment;//联系
	private MSZEnglishFragment englishFragment;//英文
	private int checkedId=1 ;// 当前Fragment

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
		RadioButton radio1 = (RadioButton) findViewById(R.id.rb_shouye);
		radio1.setText(getString(R.string.shouye));
//		radio1.setId(1);
		
		RadioButton radio2 = (RadioButton) findViewById(R.id.rb_chanpin);
		radio2.setText(getString(R.string.chanpin));
//		radio2.setId(2);
		
		RadioButton radio3 = (RadioButton) findViewById(R.id.rb_gongsi);
		radio3.setText(getString(R.string.gongsi));
//		radio3.setId(3);
		
		RadioButton radio4 = (RadioButton) findViewById(R.id.rb_lianxi);
		radio4.setText(getString(R.string.lianxi));
//		radio4.setId(4);
		
		RadioButton radio5 = (RadioButton) findViewById(R.id.rb_english);
		radio5.setText(getString(R.string.yingwen));
//		radio5.setId(5);
		
		shouyeFragment = MSZShouYeFragment.getInstance(1);//首页
		chanpinFragment = MSZChanPinFragment.getInstance(2);//产品
		gongsiFragment = MSZGongSiFragment.getInstance(3);//公司
		lianxiFragment = MSZLianXiFragment.getInstance(4);//联系
		englishFragment = MSZEnglishFragment.getInstance(5);//英文

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
