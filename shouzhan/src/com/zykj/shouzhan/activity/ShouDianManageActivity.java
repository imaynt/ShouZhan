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

public class ShouDianManageActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
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
	@Bind(R.id.ll_mobanguanli)
	LinearLayout ll_mobanguanli;
	@Bind(R.id.ll_waiwenwangzhan)
	LinearLayout ll_waiwenwangzhan;
	@Bind(R.id.ll_yaoqinghaoyou)
	LinearLayout ll_yaoqinghaoyou;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_shoudian_manage);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.shoudianguanli));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * \ 上传产品
	 */
	@OnClick(R.id.ll_shangchuan)
	public void shangChuanChanPin() {

	}

	/**
	 * \ 管理产品
	 */
	@OnClick(R.id.ll_guanlichanpin)
	public void guanLiChanPin() {

	}

	/**
	 * \ 管理分类
	 */
	@OnClick(R.id.ll_guanlifenlei)
	public void guanLiFenLei() {

	}

	/**
	 * \ 公司介绍
	 */
	@OnClick(R.id.ll_gongsijieshao)
	public void gongSiJieShao() {

	}

	/**
	 * \ 联系方式
	 */
	@OnClick(R.id.ll_lianxifangshi)
	public void lianXiFangShi() {
		startActivity(new Intent(ShouDianManageActivity.this, ManageContactsStyleAcyivity.class));
	}

	/**
	 * \ 模板管理
	 */
	@OnClick(R.id.ll_mobanguanli)
	public void moBanGuanLi() {
		startActivity(new Intent(ShouDianManageActivity.this, MoBanManageActivity.class));
		finish();
	}

	/**
	 * \ 外文网站
	 */
	@OnClick(R.id.ll_waiwenwangzhan)
	public void waiWenWangZhan() {
		startActivity(new Intent(ShouDianManageActivity.this, WaiWenNetActivity.class));
		finish();
	}

	/**
	 * \ 邀请好友
	 */
	@OnClick(R.id.ll_yaoqinghaoyou)
	public void yaoQingHaoYou() {
		startActivity(new Intent(ShouDianManageActivity.this, InviteFriendActivity.class));
	}

}
