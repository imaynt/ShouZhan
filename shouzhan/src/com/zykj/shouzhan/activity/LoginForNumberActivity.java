package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.TextUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
	//����
	private static final String APPKEY = "e42a07504680";
	private static final String APPSECRET = "520ef34cacedf3702f17a9c22cf5602c";
	private static final int 	TIME_INIT=60;		//��ʼ��ʱ����
	
	private String phone_num="";			//�ֻ���
	
	private String code="";
	
	private boolean isPhone=false;			//�Ƿ�Ϊ�ֻ���
	private boolean isCode=false;			//�Ƿ�Ϊ��֤��
	
	Handler handler = new Handler(); 
	
	private int time=TIME_INIT;
	
	@Bind(R.id.tv_login_time) 		TextView 	   tv_login_time;			//ʱ����ʾ
	@Bind(R.id.tv_login_again) 		TextView 	   tv_login_again;			//�ٴη���
	@Bind(R.id.aci_title_textview) 	TextView 	   aci_title_textview;		//������������ʾ
	@Bind(R.id.ll_back_btn) 	   	LinearLayout   ll_back_btn;				//�������������ذ�ť
	@Bind(R.id.et_login_code)  		EditText       et_login_code;			//��֤�������
	@Bind(R.id.rl_login_confirm)	RelativeLayout rl_login_confirm;		//��ͨ��վ-�ֻ���֤-��֤ȷ����ť
	@Bind(R.id.et_login_input)  	EditText       et_login_input;		//�ֻ��������
	@Bind(R.id.rl_login_click)		RelativeLayout rl_login_click;		//��ͨ��վ-�ֻ���֤-������֤��ť
	@Bind(R.id.ll_edit_clear)		ImageView      ll_edit_clear;			//�ֻ��ı������ť
	@Bind(R.id.ll_login_clickhide) 	   	LinearLayout   ll_login_clickhide;		//�������
	@Bind(R.id.ll_login_clickshow) 	   	LinearLayout   ll_login_clickshow;		//�����ʾ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login_number);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_shouzhan);
		
		// ������֤
		// ��ʼ��������֤
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
		SMSSDK.registerEventHandler(eh); // ע����Żص�
		
		//������֤��
//		sendMessageCode();
//		time=TIME_INIT;
//		handler.postDelayed(runnabletime, 1000);
	}
	
	/**
	 * �ֻ�����������ı��仯ʱִ�е��¼�
	 */
	@OnTextChanged(R.id.et_login_input)
	public void etLoginInputOnTextChanged(CharSequence s, int start, int before, int count) {
		
		 if(!et_login_input.getText().toString().equals(""))
			 ll_edit_clear.setVisibility(View.VISIBLE);
		 else
			 ll_edit_clear.setVisibility(View.GONE);
		
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
	            et_login_input.setText(sb.toString());
	            et_login_input.setSelection(index);
	        }
	        phone_num = et_login_input.getText().toString().replace(" ", "");
			if(TextUtil.isMobile(phone_num)){
				rl_login_click.setBackgroundResource(R.drawable.button_register_circle_red);
				isPhone = true;
			}else{
				rl_login_click.setBackgroundResource(R.drawable.button_register_circle_gray);
				isPhone = false;
			}
	}
	
	/**
	 *�ֻ��ı������ť����¼�
	 */
	@OnClick(R.id.ll_edit_clear)
	public void llEditClear() {
		et_login_input.setText("");
		rl_login_click.setBackgroundResource(R.drawable.button_register_circle_gray);
		isPhone = false;
	}
	
	/**
	 * ��ͨ��վ-�ֻ���֤-������֤��ť����¼�
	 */
	@OnClick(R.id.rl_login_click)
	public void rlLoginOnClick() {
		if(!isPhone)
			return;
		CommDialogLoginErr myDialog = new CommDialogLoginErr(LoginForNumberActivity.this, getWindowManager(),
				getString(R.string.login_err_msg));
		myDialog.show();
		
		//������֤
		sendMessageCode();
		
		ll_login_clickhide.setVisibility(View.GONE);
		ll_login_clickshow.setVisibility(View.VISIBLE);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		handler.postDelayed(runnabletime, 1000);
	}
	
	/**
	 * �����ɵ���ui��handler
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
					// �ص����
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						// �ύ��֤��ɹ�
						Toast.makeText(LoginForNumberActivity.this, getString(R.string.submit_code_success), Toast.LENGTH_SHORT).show();
						startActivity(new Intent(LoginForNumberActivity.this,RegisterForPwdActivity.class));
						overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// ��ȡ��֤��ɹ�
						Toast.makeText(LoginForNumberActivity.this, getString(R.string.get_code_success), Toast.LENGTH_SHORT).show();
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// ����֧�ַ�����֤��Ĺ����б�
					}
				
				
			} else {
				((Throwable) data).printStackTrace();
				Toast.makeText(LoginForNumberActivity.this,
						event == SMSSDK.EVENT_GET_VERIFICATION_CODE ? getString(R.string.code_busy) : getString(R.string.code_error),Toast.LENGTH_SHORT).show();
			}
		}
	};
	

	/**
	 * �������������ذ�ť����¼�
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}
	
	
	/**
	 * ��֤������������ı��仯ʱִ�е��¼�
	 */
	@OnTextChanged(R.id.et_login_code)
	public void etLoginCodeOnTextChanged() {
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
	 * ��ͨ��վ-�ֻ���֤-У����֤��ť����¼�
	 */
	@OnClick(R.id.rl_login_confirm)
	public void rlLoginConfirmOnClick() {
		if(!isCode)
			return;
		SMSSDK.submitVerificationCode("86", phone_num, code);
		startActivity(new Intent(LoginForNumberActivity.this,LoginForPwdActivity.class));
		overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
	}
	
	/**
	 * ��֤��ʱ�䵹��ʱ
	 */
	Runnable runnabletime = new Runnable() {
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
	 *�ٴη����ֻ���֤��
	 */
	@OnClick(R.id.tv_login_again)
	public void tvLoginAgainClick() {
		tv_login_time.setText(TIME_INIT + "s");
		tv_login_time.setVisibility(View.VISIBLE);
		tv_login_again.setVisibility(View.GONE);
		time=TIME_INIT;
		handler.postDelayed(runnabletime, 1000);
		sendMessageCode();
	}
	
	
	/**
	 *�ֻ�������֤��
	 */
	public void sendMessageCode() {
		SMSSDK.getVerificationCode("86", phone_num);
	}
}