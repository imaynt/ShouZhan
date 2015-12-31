package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexOneActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;// ��������
	@Bind(R.id.ll_shouye)
	LinearLayout ll_shouye;// ��ҳ
	@Bind(R.id.ll_chanpin)
	LinearLayout ll_chanpin;// ��Ʒ
	@Bind(R.id.ll_gongsi)
	LinearLayout ll_gongsi;// ��˾
	@Bind(R.id.ll_lianxi)
	LinearLayout ll_lianxi;// ��ϵ
	@Bind(R.id.ll_yingwen)
	LinearLayout ll_yingwen;// Ӣ��
	@Bind(R.id.img_change_model)
	ImageView img_change_model;// ����ģ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_index_one_activity);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.my_shouzhan));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * ������ҳ
	 */
	@OnClick(R.id.ll_shouye)
	public void gotoShouYe() {
		startActivity(new Intent(IndexOneActivity.this, MyShouZhanActivity.class));
	}

	/**
	 * �����Ʒ
	 */
	@OnClick(R.id.ll_chanpin)
	public void gotoChanPin() {

	}

	/**
	 * ���빫˾
	 */
	@OnClick(R.id.ll_gongsi)
	public void gotoGongSi() {

	}

	/**
	 * ������ϵ
	 */
	@OnClick(R.id.ll_lianxi)
	public void gotoLianXi() {

	}

	/**
	 * ����Ӣ�İ汾
	 */
	@OnClick(R.id.ll_yingwen)
	public void gotoYingWen() {

	}

	/**
	 * �����л�ģ��
	 */
	@OnClick(R.id.img_change_model)
	public void gotoIndexModel() {

	}

}
