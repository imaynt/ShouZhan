package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.CommonUtils;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteFriendActivity extends BaseActivity {

	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.btn_copy) // 复制
	TextView btn_copy;
	@Bind(R.id.btn_share) // 分享
	TextView btn_share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_invite_friend);
		ButterKnife.bind(this);// 绑定第三方
		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.invite_friend));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	@OnClick(R.id.btn_copy)
	public void copy() {
		Tools.toast(InviteFriendActivity.this, "复制成功");
	}

	@OnClick(R.id.btn_share)
	public void share() {
		CommonUtils.showShare(InviteFriendActivity.this, "手站", "手站是一款...软件", "http://fir.im");
	}
}
