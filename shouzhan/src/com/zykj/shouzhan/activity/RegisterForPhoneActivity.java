package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.TextUtil;

import android.content.Intent;
import android.graphics.Color;
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

public class RegisterForPhoneActivity extends BaseActivity {
	//常量
	private static final String APPKEY = "df1b7b43b308";
	private static final String APPSECRET = "9a8379ed3817518e83a1fd7b7db8738e";
	private static final int 	TIME_INIT=60;		//初始化时间用
	
	private String phone_num="";			//手机号
	
	private String code="";
	
	private boolean isPhone=false;			//是否为手机号
	private boolean isCode=false;			//是否为验证码
	
	
	Handler handler = new Handler(); 
	
	private int time=TIME_INIT;
	
	@Bind(R.id.tv_register_time) 	TextView 	   tv_register_time;		//时间显示
	@Bind(R.id.tv_register_again) 	TextView 	   tv_register_again;		//再次发送
	@Bind(R.id.aci_title_textview) 	TextView 	   aci_title_textview;		//顶部标题栏显示
	@Bind(R.id.ll_back_btn) 	   	LinearLayout   ll_back_btn;				//顶部标题栏返回按钮
	@Bind(R.id.et_register_input)  	EditText       et_register_input;		//手机号输入框
	@Bind(R.id.et_register_code)  	EditText       et_register_code;		//验证码输入框
	@Bind(R.id.rl_register_click)	RelativeLayout rl_register_click;		//开通手站-手机验证-发送验证按钮
	@Bind(R.id.rl_register_confirm)	RelativeLayout rl_register_confirm;		//开通手站-手机验证-验证确定按钮
	@Bind(R.id.ll_edit_clear)		LinearLayout   ll_edit_clear;			//手机文本清除按钮
	@Bind(R.id.ll_register_clickhide) 	   	LinearLayout   ll_register_clickhide;				//点击隐藏
	@Bind(R.id.ll_register_clickshow) 	   	LinearLayout   ll_register_clickshow;				//点击显示
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_phone);
		ButterKnife.bind(this);// 绑定第三方
		aci_title_textview.setText(R.string.register_shouzhan);
		
		
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
						Toast.makeText(RegisterForPhoneActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(RegisterForPhoneActivity.this,RegisterForPwdActivity.class));
						overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// 获取验证码成功
						Toast.makeText(RegisterForPhoneActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// 返回支持发送验证码的国家列表
					}
				
				
			} else {
				((Throwable) data).printStackTrace();
				Toast.makeText(RegisterForPhoneActivity.this,
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
	 * 手机输入框输入文本变化时执行的事件
	 */
	@OnTextChanged(R.id.et_register_input)
	public void etRegisterInputOnTextChanged(CharSequence s, int start, int before, int count) {
		
		 if (s == null || s.length() == 0) return;
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < s.length(); i++) {
	            if (i != 3 && i != 8 && s.charAt(i) == ' ') {
	                continue;
	            } else {
	                sb.append(s.charAt(i));
	                if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
	                    sb.insert(sb.length() - 1, ' ');
	                }
	            }
	        }
	        if (!sb.toString().equals(s.toString())) {
	            int index = start + 1;
	            if (sb.charAt(start) == ' ') {
	                if (before == 0) {
	                    index++;
	                } else {
	                    index--;
	                }
	            } else {
	                if (before == 1) {
	                    index--;
	                }
	            }
	            et_register_input.setText(sb.toString());
	            et_register_input.setSelection(index);
	        }
	        phone_num = et_register_input.getText().toString().replace(" ", "");
			if(TextUtil.isMobile(phone_num)){
				rl_register_click.setBackgroundResource(R.drawable.button_register_circle_red);
				isPhone = true;
			}else{
				rl_register_click.setBackgroundResource(R.drawable.button_register_circle_gray);
				isPhone = false;
			}
	}
	
	/**
	 * 验证码输入框输入文本变化时执行的事件
	 */
	@OnTextChanged(R.id.et_register_code)
	public void etRegisterCodeOnTextChanged() {
		code = et_register_code.getText().toString();
		if (code.length() == 4) {
			rl_register_confirm.setBackgroundResource(R.drawable.button_register_circle_red);
			isCode = true;
		} else {
			rl_register_confirm.setBackgroundResource(R.drawable.button_register_circle_gray);
			isCode = false;
		}
	}
	
	/**
	 * 开通手站-手机验证-发送验证按钮点击事件
	 */
	@OnClick(R.id.rl_register_click)
	public void rlRegisterOnClick() {
		if(!isPhone)
			return;
		CommDialog myDialog = new CommDialog(RegisterForPhoneActivity.this, getWindowManager(),
				getString(R.string.comm_dialog_message));
		myDialog.show();
		
		//发送验证
		sendMessageCode();
		
		ll_register_clickhide.setVisibility(View.GONE);
		ll_register_clickshow.setVisibility(View.VISIBLE);
		tv_register_time = (TextView) findViewById(R.id.tv_register_time);
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
//		startActivity(new Intent(RegisterForPhoneActivity.this,RegisterForPwdActivity.class));
//		overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
	}
	
	/**
	 * 验证码时间倒计时
	 */
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			time--;
			if (time >= 0) {
				tv_register_time.setText(time + "s");
				handler.postDelayed(this, 1000);
			} else {
				tv_register_time.setVisibility(View.GONE);
				tv_register_again.setVisibility(View.VISIBLE);
			}
		}
	};

	
	/**
	 *手机文本清除按钮点击事件
	 */
	@OnClick(R.id.ll_edit_clear)
	public void llEditClear() {
		et_register_input.setText("");
		rl_register_click.setBackgroundResource(R.drawable.button_register_circle_gray);
		isPhone = false;
	}
	
	/**
	 *再次发送手机验证码
	 */
	@OnClick(R.id.tv_register_again)
	public void tvRegisterAgainClick() {
		tv_register_time.setText(TIME_INIT + "s");
		tv_register_time.setVisibility(View.VISIBLE);
		tv_register_again.setVisibility(View.GONE);
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
