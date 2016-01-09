package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoBanManageSelectBtnStyleActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.rl_index_1)
	RelativeLayout rl_index_1;
	@Bind(R.id.rl_index_2)
	RelativeLayout rl_index_2;
	@Bind(R.id.rl_index_3)
	RelativeLayout rl_index_3;
	@Bind(R.id.rl_index_4)
	RelativeLayout rl_index_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_moban_manage_select_btnstyle);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.select_button_style));
		myCommonTitle.setListener(this, this, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
		myCommonTitle.setEditTitle(getString(R.string.check));
	}

	/**
	 * 选择按钮样式1
	 */
	@OnClick(R.id.rl_index_1)

	public void selectButtonStyleOne() {
		startActivity(new Intent(MoBanManageSelectBtnStyleActivity.this, MoBanManageApplicationBtnStyleActivity.class)
				.putExtra("btnstylepic", R.drawable.icon_pic_1));
	}

	/**
	 * 选择按钮样式2
	 */
	@OnClick(R.id.rl_index_2)

	public void selectButtonStyleTwo() {
		startActivity(new Intent(MoBanManageSelectBtnStyleActivity.this, MoBanManageApplicationBtnStyleActivity.class)
				.putExtra("btnstylepic", R.drawable.icon_pic_2));
	}

	/**
	 * 选择按钮样式3
	 */
	@OnClick(R.id.rl_index_3)

	public void selectButtonStyleThree() {
		startActivity(new Intent(MoBanManageSelectBtnStyleActivity.this, MoBanManageApplicationBtnStyleActivity.class)
				.putExtra("btnstylepic", R.drawable.icon_pic_3));
	}

	/**
	 * 选择按钮样式4
	 */
	@OnClick(R.id.rl_index_4)

	public void selectButtonStyleFour() {
		startActivity(new Intent(MoBanManageSelectBtnStyleActivity.this, MoBanManageApplicationBtnStyleActivity.class)
				.putExtra("btnstylepic", R.drawable.icon_pic_4));
	}
}
