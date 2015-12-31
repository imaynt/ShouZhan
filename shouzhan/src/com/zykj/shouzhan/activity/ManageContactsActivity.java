package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.StringUtil;
import com.zykj.shouzhan.utils.TextUtil;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageContactsActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.ed_mobile)
	EditText ed_mobile;// �ֻ�
	@Bind(R.id.ed_telephone)
	EditText ed_telephone;// ����
	@Bind(R.id.ed_contacts)
	EditText ed_contacts;// ��ϵ��
	@Bind(R.id.ed_qq)
	EditText ed_qq;// QQ
	@Bind(R.id.ed_weixin)
	EditText ed_weixin;// ΢��
	@Bind(R.id.ed_other_url)
	EditText ed_other_url;// ������ַ
	@Bind(R.id.ed_in_area)
	EditText ed_in_area;// ���ڵ���
	@Bind(R.id.ed_details_address)
	EditText ed_details_address;// ��ϸ��ַ
	@Bind(R.id.tv_submit)
	TextView tv_submit;// �ϴ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_manage_contacts);

		ButterKnife.bind(this);
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.my_shouzhan));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));

	}

	/**
	 * ѡ�����ڵĵ���
	 */
	@OnClick(R.id.ed_in_area)
	public void selectInArea() {

	}

	/**
	 * �ύ����
	 */
	@OnClick(R.id.tv_submit)
	public void submitData() {
		if (!TextUtil.isMobile(ed_mobile.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "�ֻ������ʽ����ȷ������������!");
			return;
		}
		if (!TextUtil.isPhone(ed_telephone.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "�绰�����ʽ����ȷ���밴�� ����-�����ʽ��������!");
			return;
		}
		if (StringUtil.isEmpty(ed_contacts.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "��ϵ�˲���Ϊ�գ�����������!");
			return;
		}
		if (StringUtil.isEmpty(ed_qq.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "QQ���벻��Ϊ�գ�����������!");
			return;
		}
		if (StringUtil.isEmpty(ed_weixin.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "΢�ź��벻��Ϊ�գ�����������!");
			return;
		}
		if (StringUtil.isEmpty(ed_other_url.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "��վ����Ϊ�գ�����������!");
			return;
		}
		if (StringUtil.isEmpty(ed_in_area.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "��վ����Ϊ�գ�����������!");
			return;
		}
		if (StringUtil.isEmpty(ed_details_address.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "��վ����Ϊ�գ�����������!");
			return;
		}
	}

}
