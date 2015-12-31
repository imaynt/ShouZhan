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
	EditText ed_mobile;// 手机
	@Bind(R.id.ed_telephone)
	EditText ed_telephone;// 座机
	@Bind(R.id.ed_contacts)
	EditText ed_contacts;// 联系人
	@Bind(R.id.ed_qq)
	EditText ed_qq;// QQ
	@Bind(R.id.ed_weixin)
	EditText ed_weixin;// 微信
	@Bind(R.id.ed_other_url)
	EditText ed_other_url;// 其他网址
	@Bind(R.id.ed_in_area)
	EditText ed_in_area;// 所在地区
	@Bind(R.id.ed_details_address)
	EditText ed_details_address;// 详细地址
	@Bind(R.id.tv_submit)
	TextView tv_submit;// 上传

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
	 * 选择所在的地区
	 */
	@OnClick(R.id.ed_in_area)
	public void selectInArea() {

	}

	/**
	 * 提交数据
	 */
	@OnClick(R.id.tv_submit)
	public void submitData() {
		if (!TextUtil.isMobile(ed_mobile.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "手机号码格式不正确，请重新输入!");
			return;
		}
		if (!TextUtil.isPhone(ed_telephone.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "电话号码格式不正确，请按照 区号-号码格式重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_contacts.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "联系人不能为空，请重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_qq.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "QQ号码不能为空，请重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_weixin.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "微信号码不能为空，请重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_other_url.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "网站不能为空，请重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_in_area.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "网站不能为空，请重新输入!");
			return;
		}
		if (StringUtil.isEmpty(ed_details_address.getText().toString().trim())) {
			Tools.toast(ManageContactsActivity.this, "网站不能为空，请重新输入!");
			return;
		}
	}

}
