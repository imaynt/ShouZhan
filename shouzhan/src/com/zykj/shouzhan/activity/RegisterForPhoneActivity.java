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

public class RegisterForPhoneActivity extends BaseActivity {
	//����
	private static final String APPKEY = "e42a07504680";
	private static final String APPSECRET = "520ef34cacedf3702f17a9c22cf5602c";
	private static final int 	TIME_INIT=60;		//��ʼ��ʱ����
	
	private String phone_num="";			//�ֻ���
	
	private String code="";
	
	private boolean isPhone=false;			//�Ƿ�Ϊ�ֻ���
	private boolean isCode=false;			//�Ƿ�Ϊ��֤��
	private boolean isSelect=false;
	
	
	Handler handler = new Handler(); 
	
	private int time=TIME_INIT;
	
	@Bind(R.id.tv_register_time) 	TextView 	   tv_register_time;		//ʱ����ʾ
	@Bind(R.id.tv_register_again) 	TextView 	   tv_register_again;		//�ٴη���
	@Bind(R.id.aci_title_textview) 	TextView 	   aci_title_textview;		//������������ʾ
	@Bind(R.id.ll_back_btn) 	   	LinearLayout   ll_back_btn;				//�������������ذ�ť
	@Bind(R.id.et_register_input)  	EditText       et_register_input;		//�ֻ��������
	@Bind(R.id.et_register_code)  	EditText       et_register_code;		//��֤�������
	@Bind(R.id.rl_register_click)	RelativeLayout rl_register_click;		//��ͨ��վ-�ֻ���֤-������֤��ť
	@Bind(R.id.rl_register_confirm)	RelativeLayout rl_register_confirm;		//��ͨ��վ-�ֻ���֤-��֤ȷ����ť
	@Bind(R.id.ll_edit_clear)		ImageView   ll_edit_clear;			//�ֻ��ı������ť
	@Bind(R.id.lv_select)			ImageView   lv_select;				//�û�Э��ѡ��״̬
	@Bind(R.id.ll_register_clickhide) 	   	LinearLayout   ll_register_clickhide;		//�������
	@Bind(R.id.ll_register_clickshow) 	   	LinearLayout   ll_register_clickshow;		//�����ʾ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_phone);
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
						Toast.makeText(RegisterForPhoneActivity.this, getString(R.string.submit_code_success), Toast.LENGTH_SHORT).show();
						startActivity(new Intent(RegisterForPhoneActivity.this,RegisterForPwdActivity.class));
						overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						// ��ȡ��֤��ɹ�
						Toast.makeText(RegisterForPhoneActivity.this, getString(R.string.get_code_success), Toast.LENGTH_SHORT).show();
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
						// ����֧�ַ�����֤��Ĺ����б�
					}
				
				
			} else {
				((Throwable) data).printStackTrace();
				Toast.makeText(RegisterForPhoneActivity.this,
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
	 * �ֻ�����������ı��仯ʱִ�е��¼�
	 */
	@OnTextChanged(R.id.et_register_input)
	public void etRegisterInputOnTextChanged(CharSequence s, int start, int before, int count) {
		
		 if(!et_register_input.getText().toString().equals(""))
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
	 * ��֤������������ı��仯ʱִ�е��¼�
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
	 * ��ͨ��վ-�ֻ���֤-������֤��ť����¼�
	 */
	@OnClick(R.id.rl_register_click)
	public void rlRegisterOnClick() {
		if(!isPhone)
			return;
		if(!isSelect){
			Toast.makeText(RegisterForPhoneActivity.this, getString(R.string.please_read), Toast.LENGTH_SHORT).show();
			return;
		}
		CommDialog myDialog = new CommDialog(RegisterForPhoneActivity.this, getWindowManager(),
				getString(R.string.comm_dialog_message));
		myDialog.show();
		
		//������֤
//		sendMessageCode();
		
		ll_register_clickhide.setVisibility(View.GONE);
		ll_register_clickshow.setVisibility(View.VISIBLE);
		tv_register_time = (TextView) findViewById(R.id.tv_register_time);
		handler.postDelayed(runnable, 1000);
	}
	
	/**
	 * ��ͨ��վ-�ֻ���֤-У����֤��ť����¼�
	 */
	@OnClick(R.id.rl_register_confirm)
	public void rlRegisterConfirmOnClick() {
		if(!isCode)
			return;
//		SMSSDK.submitVerificationCode("86", phone_num, code);
		startActivity(new Intent(RegisterForPhoneActivity.this,RegisterForPwdActivity.class));
		overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
	}
	
	/**
	 * ��֤��ʱ�䵹��ʱ
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
	 *�ֻ��ı������ť����¼�
	 */
	@OnClick(R.id.ll_edit_clear)
	public void llEditClear() {
		et_register_input.setText("");
		rl_register_click.setBackgroundResource(R.drawable.button_register_circle_gray);
		isPhone = false;
	}
	
	/**
	 *�ٴη����ֻ���֤��
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
	 * �û�Э��ѡ��״̬�¼�
	 */
	@OnClick(R.id.lv_select)
	public void selectOnClick(){
		if(isSelect){
			lv_select.setImageResource(R.drawable.icon_gouxuan_normal);
			isSelect = false;
		}
		else{
			lv_select.setImageResource(R.drawable.icon_gouxuan_click);
			isSelect = true;
		}
	}
	
	
	/**
	 *�ֻ�������֤��
	 */
	public void sendMessageCode() {
		SMSSDK.getVerificationCode("86", phone_num);
	}
}
