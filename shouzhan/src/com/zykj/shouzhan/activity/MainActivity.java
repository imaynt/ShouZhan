package com.zykj.shouzhan.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zykj.shouzhan.BaseTabActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;
import com.zykj.shouzhan.view.MyRadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends BaseTabActivity {

	private MyCommonTitle myCommonTitle;
	private TabHost m_tab;
	private Intent intent_1;
	private Intent intent_2;
	private Intent intent_3;
	private Intent intent_4;
	// 单选按钮组
	private RadioGroup m_rgroup;
	// 4个单选按钮
	private MyRadioButton m_radio_shouzhan;
	private MyRadioButton m_radio_shangchuan;
	private MyRadioButton m_radio_guanli;
	private MyRadioButton m_radio_erweima;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_layout);
		
		m_tab=getTabHost();
		initView();
	}
	private void initView() {
//		myCommonTitle=(MyCommonTitle) findViewById(R.id.aci_mytitle);
//		myCommonTitle.setTitle(getString(R.string.my_shouzhan));
		intent_1 = new Intent(this, IndexOneActivity.class);
		intent_2 = new Intent(this, IndexTwoActivity.class);
		intent_3 = new Intent(this, IndexThreeActivity.class);
		intent_4 = new Intent(this, IndexFourActivity.class);

		m_tab.addTab(buildTagSpec("test1", 0, intent_1));
		m_tab.addTab(buildTagSpec("test2", 1, intent_2));
		m_tab.addTab(buildTagSpec("test3", 2, intent_3));
		m_tab.addTab(buildTagSpec("test4", 3, intent_4));

		m_rgroup = (RadioGroup) findViewById(R.id.tab_rgroup);
		m_radio_shouzhan = (MyRadioButton) findViewById(R.id.tab_radio1);
		m_radio_shouzhan.getLayoutParams().width = Tools.M_SCREEN_WIDTH/4;
		m_radio_shangchuan = (MyRadioButton) findViewById(R.id.tab_radio2);
		m_radio_shangchuan.getLayoutParams().width = Tools.M_SCREEN_WIDTH/4;
		m_radio_guanli = (MyRadioButton) findViewById(R.id.tab_radio3);
		m_radio_guanli.getLayoutParams().width = Tools.M_SCREEN_WIDTH/4;
		m_radio_erweima = (MyRadioButton) findViewById(R.id.tab_radio4);
		m_radio_erweima.getLayoutParams().width = Tools.M_SCREEN_WIDTH/4;
		
		m_rgroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == m_radio_shouzhan.getId()) {
					m_tab.setCurrentTabByTag("test1");
				} else if (checkedId == m_radio_shangchuan.getId()) {
					m_tab.setCurrentTabByTag("test2");
				} else if (checkedId == m_radio_guanli.getId()) {
					m_tab.setCurrentTabByTag("test3");
				}else if (checkedId == m_radio_erweima.getId()) {
					m_tab.setCurrentTabByTag("test4");
				}
			}
		});
		m_tab.setCurrentTab(0);
	}

	private TabHost.TabSpec buildTagSpec(String tagName, int tagLable,
			Intent content) {
		return m_tab.newTabSpec(tagName).setIndicator(tagLable + "")
				.setContent(content);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onDestroy() {
		Tools.Log("当前tabActivity退出");
		super.onDestroy();
	}
}

