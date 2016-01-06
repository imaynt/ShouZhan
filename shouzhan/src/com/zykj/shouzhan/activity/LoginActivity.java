package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.TextUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseActivity {
	
	private boolean isUsed = false;
	private boolean isPhone=false;			//�ֻ���
	
	private String phone_num="";			//�ֻ���

	@Bind(R.id.rl_login_click) 		RelativeLayout rl_login_click;			//ȷ����ť
	@Bind(R.id.et_login_phone)		EditText	   et_login_phone;			//�����ֻ���
	@Bind(R.id.et_login_pwd)		EditText	   et_login_pwd;			//��������
	@Bind(R.id.aci_title_textview) 	TextView 	   aci_title_textview;		//������������ʾ
	@Bind(R.id.ll_back_btn) 	   	LinearLayout   ll_back_btn;				//�������������ذ�ť
	@Bind(R.id.ll_edit_pwd_t_clear)	ImageView	   ll_edit_pwd_t_clear;		//�ڶ����������
	@Bind(R.id.ll_edit_pwd_o_clear)	ImageView	   ll_edit_pwd_o_clear;		//��һ���������
	@Bind(R.id.tv_forget_pwd) 		TextView 	   tv_forget_pwd;			//��������
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.login);
	}
	
	/**
	 * �����������¼�
	 */
	@OnClick(R.id.tv_forget_pwd)
	public void forgetPwdOnClick(){
		startActivity(new Intent(LoginActivity.this,LoginForNumberActivity.class));
	}
	
	/**
	 * ��������-ȷ����ť����¼�
	 */
	@OnClick(R.id.rl_login_click)
	public void loginClick(){
		if (isPhone && isUsed) {
			CommDialogPwdErr myDialog = new CommDialogPwdErr(LoginActivity.this, getWindowManager(),
					getString(R.string.pwd_err_msg),phone_num);
			myDialog.show();
		}
	}
	
	/**
	 * �������������ذ�ť����¼�
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}

	
	/**
	 * �����ֻ���ʱ������ȷ����ť
	 */
	@OnTextChanged(R.id.et_login_phone)
	public void registerPwdOChanged(CharSequence s, int start, int before, int count){
		
		if(!et_login_phone.getText().toString().equals(""))
			ll_edit_pwd_o_clear.setVisibility(View.VISIBLE);
		 else
			ll_edit_pwd_o_clear.setVisibility(View.GONE);
		
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
            et_login_phone.setText(sb.toString());
            et_login_phone.setSelection(index);
        }
        phone_num = et_login_phone.getText().toString().replace(" ", "");
        
		if(!TextUtil.isMobile(phone_num)){
			isPhone = false;
			rl_login_click.setBackgroundResource(R.drawable.button_register_circle_gray);
			return;
		}
		
		isPhone = true;
		if(isUsed)
			rl_login_click.setBackgroundResource(R.drawable.button_register_circle_red);
	}
	
	/**
	 * ��������ʱ������ȷ����ť
	 */
	@OnTextChanged(R.id.et_login_pwd)
	public void registerPwdTChanged(){
		
		if(!et_login_pwd.getText().toString().equals(""))
			ll_edit_pwd_t_clear.setVisibility(View.VISIBLE);
		 else
			 ll_edit_pwd_t_clear.setVisibility(View.GONE);
		
		if(et_login_pwd.getText().toString().equals("")){
			isUsed = false;
			rl_login_click.setBackgroundResource(R.drawable.button_register_circle_gray);
			return;
		}
			
		isUsed = true;
		if(isPhone)
			rl_login_click.setBackgroundResource(R.drawable.button_register_circle_red);
	}
	

	/**
	 *�ֻ��ı������ť����¼�����һ�������
	 */
	@OnClick(R.id.ll_edit_pwd_o_clear)
	public void editPwdOClear() {
		et_login_phone.setText("");
		isPhone = false;
		rl_login_click.setBackgroundResource(R.drawable.button_register_circle_gray);
		return;
	}
	
	/**
	 *�ֻ��ı������ť����¼����ڶ��������
	 */
	@OnClick(R.id.ll_edit_pwd_t_clear)
	public void editPwdTClear() {
		et_login_pwd.setText("");
	}

}