package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterForPayActivity extends BaseActivity {

	@Bind(R.id.aci_title_textview) 		TextView 	   	  aci_title_textview;			//������������ʾ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_pay);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_shouzhan);
	}

}
