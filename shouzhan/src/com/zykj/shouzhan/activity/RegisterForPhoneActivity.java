package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterForPhoneActivity extends BaseActivity {
	
	@Bind(R.id.aci_title_textview) TextView 	aci_title_textview;		//������������ʾ����
	@Bind(R.id.ll_back_btn) 	   LinearLayout ll_back_btn;			//�������������ذ�ť
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_phone);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_shouzhan);
	}
	
	/**
	 * �������������ذ�ť����¼�
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick(){
		this.finish();
	}
	
}
