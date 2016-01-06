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

public class OpenShouZhanActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.tv_manage_en_net)
	TextView tv_manage_en_net;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_open_shouzhan);
		ButterKnife.bind(this);
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.open_shouzhan));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}
	
	@OnClick(R.id.tv_manage_en_net)
	public void manageEnNet(){
		startActivity(new Intent(OpenShouZhanActivity.this, ShouZhanManageENActivity.class));
		finish();
	}
}
