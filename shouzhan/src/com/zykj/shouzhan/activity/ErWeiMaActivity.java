package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.CommonUtils;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ErWeiMaActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle) MyCommonTitle  myCommonTitle;
	@Bind(R.id.btn_share) TextView  btn_share;
	@Bind(R.id.btn_copy) TextView  btn_copy;
	@Bind(R.id.btn_download) TextView  btn_download;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_erweima);
		ButterKnife.bind(this);
		initView();
	}


	private void initView() {
		myCommonTitle.setTitle(getString(R.string.erweima));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}
	/**
	 * 分享
	 */
	@OnClick(R.id.btn_share)
	public void shareOnClick(){
		CommonUtils.showShare(this, "", "", "");
	}
	/**
	 * 复制
	 */
	@OnClick(R.id.btn_copy)
	public void copyOnClick(){
		Tools.toast(ErWeiMaActivity.this, "复制成功");
		
	}
	/**
	 * 下载
	 */
	@OnClick(R.id.btn_download)
	public void downLoadOnClick(){
		AlertDialog.Builder builder =new Builder(ErWeiMaActivity.this);
		builder.setMessage("确定要下载二维码图片？");
		builder.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.create().show();
	}
}
