package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexTwoActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;// 顶部标题
	@Bind(R.id.ll_shouye)
	LinearLayout ll_shouye;// 首页
	@Bind(R.id.ll_chanpin)
	LinearLayout ll_chanpin;// 产品
	@Bind(R.id.ll_gongsi)
	LinearLayout ll_gongsi;// 公司
	@Bind(R.id.ll_lianxi)
	LinearLayout ll_lianxi;// 联系
	@Bind(R.id.ll_yingwen)
	LinearLayout ll_yingwen;// 英文
	@Bind(R.id.img_change_model)
	ImageView img_change_model;// 更换模板

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_index_two_activity);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.my_shouzhan));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * 进入首页
	 */
	@OnClick(R.id.ll_shouye)
	public void gotoShouYe() {

	}

	/**
	 * 进入产品
	 */
	@OnClick(R.id.ll_chanpin)
	public void gotoChanPin() {

	}

	/**
	 * 进入公司
	 */
	@OnClick(R.id.ll_gongsi)
	public void gotoGongSi() {

	}

	/**
	 * 进入联系
	 */
	@OnClick(R.id.ll_lianxi)
	public void gotoLianXi() {

	}

	/**
	 * 进入英文版本
	 */
	@OnClick(R.id.ll_yingwen)
	public void gotoYingWen() {

	}

	/**
	 * 进入切换模板
	 */
	@OnClick(R.id.img_change_model)
	public void gotoIndexModel() {

	}

}
