package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaiWenNetActivity extends BaseActivity {
	
	@Bind(R.id.aci_mytitle) MyCommonTitle myCommonTitle;
	@Bind(R.id.tv_positive) TextView tv_positive;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_select_language);
		ButterKnife.bind(this);
		
		initView();
	}
	private void initView() {
		myCommonTitle.setTitle(getString(R.string.waiwen_net));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));		
	}

	@OnClick(R.id.tv_positive)
	public void submitLanguage(){
		
		
		startActivity(new Intent(WaiWenNetActivity.this, OpenShouZhanActivity.class));
	}
}
