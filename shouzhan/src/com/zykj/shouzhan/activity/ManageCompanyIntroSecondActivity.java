package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.StringUtil;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageCompanyIntroSecondActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.img_1)
	ImageView img_person;// Í·Ïñ
	@Bind(R.id.img_2)
	ImageView img_zhizhao;// Ö´ÕÕ
	@Bind(R.id.tv_company_intro)
	TextView tv_company_intro;// ¹«Ë¾½éÉÜ
	@Bind(R.id.btn_delete)
	TextView btn_delete;// É¾³ý
	@Bind(R.id.btn_edit)
	TextView btn_edit;// ±à¼­
	@Bind(R.id.btn_preview)
	TextView btn_preview;// Ô¤ÀÀÐ§¹û

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_manage_company_intro_second);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.manage_company_intro));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * É¾³ý
	 */
	@OnClick(R.id.btn_delete)
	public void deleteCompanyIntro() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage(getString(R.string.delete_message));
		builder.setNegativeButton(getString(R.string.quxiao), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).setPositiveButton(getString(R.string.queding), new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				Tools.toast(ManageCompanyIntroSecondActivity.this, (getString(R.string.delete_sucess)));
			}
		});
		builder.create().show();
	}

	/**
	 * ±à¼­
	 */
	@OnClick(R.id.btn_edit)
	public void editCompanyIntro() {
		startActivity(new Intent(ManageCompanyIntroSecondActivity.this, ManageCompanyIntroFirstActivity.class));
		finish();
	}

	/**
	 * Ô¤ÀÀÐ§¹û
	 */
	@OnClick(R.id.btn_preview)
	public void previewCompanyIntro() {
		startActivity(new Intent(ManageCompanyIntroSecondActivity.this, MyShouZhanActivity.class));
	}
}
