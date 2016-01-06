package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginForNumberActivity  extends BaseActivity {
	//常量
	private static final String APPKEY = "e42a07504680";
	private static final String APPSECRET = "520ef34cacedf3702f17a9c22cf5602c";
	private static final int 	TIME_INIT=60;		//初始化时间用
	
	private String phone_num="";			//手机号
	
	private String code="";
	
	private boolean isCode=false;			//是否为验证码
	
	Handler handler = new Handler(); 
	
	private int time=TIME_INIT;
	
	@Bind(R.id.tv_login_time) 		TextView 	   tv_login_time;			//时间显示
	@Bind(R.id.tv_login_again) 		TextView 	   tv_login_again;			//再次发送
	@Bind(R.id.aci_title_textview) 	TextView 	   aci_title_textview;		//顶部标题栏显示
	@Bind(R.id.ll_back_btn) 	   	LinearLayout   ll_back_btn;				//顶部标题栏返回按钮
	@Bind(R.id.et_login_code)  		EditText       et_login_code;			//验证码输入框
	@Bind(R.id.rl_login_confirm)	RelativeLayout rl_login_confirm;		//开通手站-手机验证-验证确定按钮
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login_number);
		ButterKnife.bind(this);// 绑定第三方
		aci_title_textview.setText(R.string.register_shouzhan);
		
		 /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        
        /*获取Bundle中的数据，注意类型和key*/
        phone_num = bundle.getString("phone");
		
		// 短信验证
		// 初始化短信验证
		SMSSDK.initSDK(this, APPKEY, APPSECRET);
		EventHandler eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handlersms.sendMessage(msg);
			}
		};
		SMSSDK.registerEventHandler(eh); // 注册短信回调
	}
	
	/**
	 * 创建可调用ui的handler
	 */
	Handler handlersms = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("result", "result=" + result);
			Log.e("event", "event=" + event);
			
				if (result == SMSSDK.RESULT_COMPLETE) {
					// 回调完成
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						// 提交验证码成功
						Toast.makeText(LoginForNumberActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LoginForNumberActivity.this,RegisterForPwdActivity.class));
						overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// 获取验证码成功
						Toast.makeText(LoginForNumberActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// 返回支持发送验证码的国家列表
					}
				
				
			} else {
				((Throwable) data).printStackTrace();
				Toast.makeText(LoginForNumberActivity.this,
						event == SMSSDK.EVENT_GET_VERIFICATION_CODE ? "验证码频繁，请稍后再试！" : "验证码错误",Toast.LENGTH_SHORT).show();
			}
		}
	};
	

	/**
	 * 顶部标题栏返回按钮点击事件
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}
	
	
	/**
	 * 验证码输入框输入文本变化时执行的事件
	 */
	@OnTextChanged(R.id.et_register_code)
	public void etRegisterCodeOnTextChanged() {
		code = et_login_code.getText().toString();
		if (code.length() == 4) {
			rl_login_confirm.setBackgroundResource(R.drawable.button_register_circle_red);
			isCode = true;
		} else {
			rl_login_confirm.setBackgroundResource(R.drawable.button_register_circle_gray);
			isCode = false;
		}
	}
	
	/**
	 * 开通手站-手机验证-发送验证按钮点击事件
	 */
	@OnClick(R.id.rl_register_click)
	public void rlRegisterOnClick() {
		
		//发送验证
		sendMessageCode();
		
		tv_login_time = (TextView) findViewById(R.id.tv_register_time);
		handler.postDelayed(runnable, 1000);
	}
	
	/**
	 * 开通手站-手机验证-校验验证按钮点击事件
	 */
	@OnClick(R.id.rl_register_confirm)
	public void rlRegisterConfirmOnClick() {
		if(!isCode)
			return;
		SMSSDK.submitVerificationCode("86", phone_num, code);
		startActivity(new Intent(LoginForNumberActivity.this,RegisterForPwdActivity.class));
		overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
	}
	
	/**
	 * 验证码时间倒计时
	 */
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			time--;
			if (time >= 0) {
				tv_login_time.setText(time + "s");
				handler.postDelayed(this, 1000);
			} else {
				tv_login_time.setVisibility(View.GONE);
				tv_login_again.setVisibility(View.VISIBLE);
			}
		}
	};

	
	/**
	 *再次发送手机验证码
	 */
	@OnClick(R.id.tv_register_again)
	public void tvRegisterAgainClick() {
		tv_login_time.setText(TIME_INIT + "s");
		tv_login_time.setVisibility(View.VISIBLE);
		tv_login_again.setVisibility(View.GONE);
		time=TIME_INIT;
		handler.postDelayed(runnable, 1000);
		sendMessageCode();
	}
	
	
	/**
	 *手机发送验证码
	 */
	public void sendMessageCode() {
		SMSSDK.getVerificationCode("86", phone_num);
	}
}