package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoBanManageActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.ll_button_style)
	LinearLayout ll_button_style;
	@Bind(R.id.ll_background_pic)
	LinearLayout ll_background_pic;

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

	/**
	 * °´Å¥ÑùÊ½
	 */
	@OnClick(R.id.ll_button_style)

	public void selectButtonStyle() {
		startActivity(new Intent(MoBanManageActivity.this, MoBanManageSelectBtnStyleActivity.class));
	}

	/**
	 * ±³¾°Í¼Æ¬
	 */
	@OnClick(R.id.ll_background_pic)

	public void selectBackgroundPicture() {
		startActivity(new Intent(MoBanManageActivity.this, MoBanManageSelectSceneActivity.class));
	}
}
