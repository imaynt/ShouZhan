package com.zykj.shouzhan.activity;

import java.util.Locale;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.CommonUtils;
import com.zykj.shouzhan.utils.Tools;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginOrRegisterActivity  extends BaseActivity {
	
	String lan="zh";

	@Bind(R.id.register_shouzhan)   Button    register_shouzhan;		//��ͨ��վbutton
	@Bind(R.id.login_shouzhan)	    Button    login_shouzhan;			//��½��վbutton
	@Bind(R.id.iv_guest_background) ImageView iv_guest_background;		//�л�����
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login_or_register);
		ButterKnife.bind(this);// �󶨵�����
		
	}
	
	/**
	 * ��ͨ��վbutton����¼�
	 */
	@OnClick(R.id.register_shouzhan)
	public void registerShouzhanOnClick(){
		startActivity(new Intent(LoginOrRegisterActivity.this,RegisterForPhoneActivity.class));
	}
	
	/**
	 * ��½��վbutton����¼�
	 */
	@OnClick(R.id.login_shouzhan)
	public void loginShouzhanOnClick(){
		
	}

	@OnClick(R.id.iv_guest_background)
	public void guestBackgroudOnClick(){
		if(lan=="zh"){
			switchLanguage("en");
			lan="en";
		}else {
			switchLanguage("zh");
			lan="zh";
		}
		this.finish();
		startActivity(new Intent(LoginOrRegisterActivity.this,LoginOrRegisterActivity.class));
	}
	protected void switchLanguage(String language){
		//������������
		Resources resources = getResources();
		Configuration config = resources.getConfiguration();
		DisplayMetrics dm = resources.getDisplayMetrics();
		if(language.equals("en")){
			config.locale = Locale.ENGLISH;
		} else {
			config.locale = Locale.SIMPLIFIED_CHINESE;
		}
		resources.updateConfiguration(config, dm);
		
		//�����������Ե�����
	}

}
