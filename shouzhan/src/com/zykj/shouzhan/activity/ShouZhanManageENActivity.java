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

public class ShouZhanManageENActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.ll_language_manage)
	LinearLayout ll_language_manage;
	@Bind(R.id.ll_shangchuan)
	LinearLayout ll_shangchuan;
	@Bind(R.id.ll_guanlichanpin)
	LinearLayout ll_guanlichanpin;
	@Bind(R.id.ll_guanlifenlei)
	LinearLayout ll_guanlifenlei;
	@Bind(R.id.ll_gongsijieshao)
	LinearLayout ll_gongsijieshao;
	@Bind(R.id.ll_lianxifangshi)
	LinearLayout ll_lianxifangshi;
	@Bind(R.id.ll_cn_manage)
	LinearLayout ll_cn_manage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_shouzhan_manage_en);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.shouzhan_manage));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * 语种管理
	 */
	@OnClick(R.id.ll_language_manage)
	public void languageManage() {
		startActivity(new Intent(ShouZhanManageENActivity.this, WaiWenNetActivity.class));
	}

	/**
	 * 上传产品
	 */
	@OnClick(R.id.ll_shangchuan)
	public void shangChuanChanPin() {

	}

	/**
	 * 管理产品
	 */
	@OnClick(R.id.ll_guanlichanpin)
	public void guanLiChanPin() {

	}

	/**
	 * 管理分类
	 */
	@OnClick(R.id.ll_guanlifenlei)
	public void guanLiFenLei() {

	}

	/**
	 * 公司介绍
	 */
	@OnClick(R.id.ll_gongsijieshao)
	public void gongSiJieShao() {

	}

	/**
	 * 联系方式
	 */
	@OnClick(R.id.ll_lianxifangshi)
	public void lianXiFangShi() {

	}

	/**
	 * 中文管理
	 */
	@OnClick(R.id.ll_cn_manage)
	public void chineseGuanLi() {
		startActivity(new Intent(ShouZhanManageENActivity.this, ShouDianManageActivity.class));
		finish();
	}

}
