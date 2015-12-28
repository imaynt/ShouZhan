package com.zykj.shouzhan.view;

import com.zykj.shouzhan.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyShareAndStoreTitle extends RelativeLayout {
	private LinearLayout ll_back_btn;
	private TextView backEdit, titleText, subtitleText;
	private ImageView share_btn, store_btn;

	public MyShareAndStoreTitle(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.ui_myshareandstoretitle,
				null);
		ll_back_btn = (LinearLayout) findViewById(R.id.ll_back_btn);// 返回
		backEdit = (TextView) findViewById(R.id.aci_back_textview);
		share_btn = (ImageView) findViewById(R.id.aci_share_btn);// 分享
		store_btn = (ImageView) findViewById(R.id.aci_store_btn);// 收藏

		ll_back_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((Activity) getContext()).finish();
			}
		});
	}

	/**
	 * 当监听事件不为空时候,该控件可见
	 * 
	 * @param backEditListener
	 * @param shareListener
	 * @param storeClickListener
	 */
	public void setListener(OnClickListener backEditListener,
			OnClickListener shareListener, OnClickListener storeClickListener) {
		if (backEditListener != null) {
			backEdit.setVisibility(View.VISIBLE);
			backEdit.setOnClickListener(backEditListener);
		}
		if (shareListener != null) {
			share_btn.setVisibility(View.VISIBLE);
			share_btn.setOnClickListener(backEditListener);
		}
		if (storeClickListener != null) {
			store_btn.setVisibility(View.VISIBLE);
			store_btn.setOnClickListener(backEditListener);
		}
	}

	public void setBackEdit(String backedit) {
		backEdit.setText(backedit);
	}

	public void setTitle(String title) {
		titleText = (TextView) findViewById(R.id.aci_title_textview);
		titleText.setText(title);
	}

	public void setSubTitle(String title) {
		subtitleText = (TextView) findViewById(R.id.aci_subtitle_textview);
		subtitleText.setText(title);
	}

	public void setBackBtnVisible(Boolean flag) {
		if (flag) {
			ll_back_btn.setVisibility(View.VISIBLE);
		} else {
			ll_back_btn.setVisibility(View.INVISIBLE);
		}
	}

}
