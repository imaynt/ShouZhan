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

	@Bind(R.id.register_shouzhan)   Button    register_shouzhan;		//开通手站button
	@Bind(R.id.login_shouzhan)	    Button    login_shouzhan;			//登陆手站button
	@Bind(R.id.iv_guest_background) ImageView iv_guest_background;		//切换语种
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login_or_register);
		ButterKnife.bind(this);// 绑定第三方
		
	}
	
	/**
	 * 开通手站button点击事件
	 */
	@OnClick(R.id.register_shouzhan)
	public void registerShouzhanOnClick(){
		startActivity(new Intent(LoginOrRegisterActivity.this,RegisterForPhoneActivity.class));
	}
	
	/**
	 * 登陆手站button点击事件
	 */
	@OnClick(R.id.login_shouzhan)
	public void loginShouzhanOnClick(){
		startActivity(new Intent(LoginOrRegisterActivity.this,MainActivity.class));
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
		//设置语言类型
		Resources resources = getResources();
		Configuration config = resources.getConfiguration();
		DisplayMetrics dm = resources.getDisplayMetrics();
		if(language.equals("en")){
			config.locale = Locale.ENGLISH;
		} else {
			config.locale = Locale.SIMPLIFIED_CHINESE;
		}
		resources.updateConfiguration(config, dm);
		
		//保存设置语言的类型
	}
	
	
	

}
