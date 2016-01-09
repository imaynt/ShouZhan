package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoBanManageApplicationBtnStyleActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle) MyCommonTitle myCommonTitle;
	@Bind(R.id.img_btn_style) ImageView img_btn_style;
	@Bind(R.id.tv_cancle) TextView tv_cancle;
	@Bind(R.id.tv_select) TextView tv_select;
	private int btnstylepic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btnstylepic=getIntent().getIntExtra("btnstylepic",0);
		setContentView(R.layout.ui_moban_manage_application_btnstyle);
		ButterKnife.bind(this);
		
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.select_button_style));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
		img_btn_style.setImageResource(btnstylepic);
	}

	/**
	 * 取消
	 */
	@OnClick(R.id.tv_cancle)
	
	public void quxiaoSelectBtnStyle(){
		startActivity(new Intent(MoBanManageApplicationBtnStyleActivity.this, MoBanManageSelectBtnStyleActivity.class));
		finish();
	}
	/**
	 * 选用
	 */
	@OnClick(R.id.tv_select)
	
	public void selectBtnStyle(){
		startActivity(new Intent(MoBanManageApplicationBtnStyleActivity.this, MainActivity.class));
		finish();
	}

}
