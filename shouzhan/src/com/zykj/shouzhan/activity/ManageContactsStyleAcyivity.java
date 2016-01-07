package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.CommonUtils;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageContactsStyleAcyivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.btn_delete) // ɾ��
	TextView btn_copy;
	@Bind(R.id.btn_edit) // �༭
	TextView btn_share;
	@Bind(R.id.btn_preview) // Ԥ��Ч��
	TextView btn_preview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_manage_contact_style);
		ButterKnife.bind(this);
		iniView();
	}

	private void iniView() {
		myCommonTitle.setTitle(getString(R.string.manage_contacts_style));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * ɾ��
	 */
	@OnClick(R.id.btn_delete)
	public void deletebtn_preview() {
		Tools.toast(ManageContactsStyleAcyivity.this, "ɾ���ɹ�");
	}

	/**
	 * �༭
	 */
	@OnClick(R.id.btn_edit)
	public void edit() {
		startActivity(new Intent(ManageContactsStyleAcyivity.this, ManageContactsActivity.class));
	}

	/**
	 * Ԥ��Ч��
	 */
	@OnClick(R.id.btn_preview)
	public void previewEffect() {
		startActivity(new Intent(ManageContactsStyleAcyivity.this, MyShouZhanActivity.class));
	}
}
