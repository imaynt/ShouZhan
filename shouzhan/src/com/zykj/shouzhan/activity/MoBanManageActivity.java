package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoBanManageActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle) MyCommonTitle myCommonTitle;
	@Bind(R.id.ll_button_style) LinearLayout ll_button_style;
	@Bind(R.id.ll_background_pic) LinearLayout ll_background_pic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_moban_manage);
		ButterKnife.bind(this);
		
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.moban_manage));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	@OnClick(R.id.ll_button_style)
	/**
	 * °´Å¥ÑùÊ½
	 */
	public void buttonStyle(){
		
	}
	@OnClick(R.id.ll_background_pic)
	/**
	 * ±³¾°Í¼Æ¬
	 */
	public void backgroundPicture(){
		
	}
}
