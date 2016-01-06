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
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginForPwdActivity extends BaseActivity {
	
	private boolean isUsed = false;

	@Bind(R.id.rl_login_pwd_click) 	RelativeLayout rl_login_pwd_click;		//��������-ȷ����ť
	@Bind(R.id.et_login_pwd_first)	EditText	   et_login_pwd_first;		//��һ����������
	@Bind(R.id.et_login_pwd_two)		EditText	   et_login_pwd_two;			//�ڶ�����������
	@Bind(R.id.aci_title_textview) 		TextView 	   aci_title_textview;			//������������ʾ
	@Bind(R.id.ll_back_btn) 	   		LinearLayout   ll_back_btn;					//�������������ذ�ť
	@Bind(R.id.ll_edit_pwd_t_clear)		ImageView	   ll_edit_pwd_t_clear;			//�ڶ����������
	@Bind(R.id.ll_edit_pwd_o_clear)		ImageView	   ll_edit_pwd_o_clear;			//��һ���������
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_login_pwd);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_pwd);
	}
	
	/**
	 * ��������-ȷ����ť����¼�
	 */
	@OnClick(R.id.rl_login_pwd_click)
	public void pwdOnClick(){
		if(!isUsed)
			return;
		if(et_login_pwd_first.getText().toString().equals(et_login_pwd_two.getText().toString())){
			Toast.makeText(LoginForPwdActivity.this, getString(R.string.pwd_reset_success), Toast.LENGTH_SHORT).show();
			startActivity(new Intent(LoginForPwdActivity.this,LoginActivity.class));
			overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
		}
		else
			Toast.makeText(LoginForPwdActivity.this, getString(R.string.pwd_set_notsame), Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * �������������ذ�ť����¼�
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}

	/**
	 * ����һ�������ʧȥ����ʱ���ж������Ƿ�Ϸ�
	 * @param b falseΪʧȥ���㣬trueΪ��ý���
	 */
	@OnFocusChange(R.id.et_login_pwd_first)
	public void loginPwdFFocusChange(boolean b){
		if(!b)
			Toast.makeText(LoginForPwdActivity.this, TextUtil.isPasswordLengthLegal(et_login_pwd_first.getText().toString())?getString(R.string.pwd_set_ispwd):getString(R.string.pwd_set_notpwd), Toast.LENGTH_SHORT).show();
		
	}
	
	/**
	 * ����һ���������������ʱ������ȷ����ť
	 */
	@OnTextChanged(R.id.et_login_pwd_first)
	public void loginPwdOChanged(){
		
		if(!et_login_pwd_first.getText().toString().equals(""))
			ll_edit_pwd_o_clear.setVisibility(View.VISIBLE);
		 else
			 ll_edit_pwd_o_clear.setVisibility(View.GONE);
	}
	
	/**
	 * ���ڶ����������������ʱ������ȷ����ť
	 */
	@OnTextChanged(R.id.et_login_pwd_two)
	public void loginPwdTChanged(){
		
		if(!et_login_pwd_two.getText().toString().equals(""))
			ll_edit_pwd_t_clear.setVisibility(View.VISIBLE);
		 else
			 ll_edit_pwd_t_clear.setVisibility(View.GONE);
		
		if(et_login_pwd_two.getText().toString().equals("")){
			isUsed = false;
			rl_login_pwd_click.setBackgroundResource(R.drawable.button_register_circle_gray);
			return;
		}
			
		isUsed = true;
		rl_login_pwd_click.setBackgroundResource(R.drawable.button_register_circle_red);
	}
	

	/**
	 *�ֻ��ı������ť����¼�����һ�������
	 */
	@OnClick(R.id.ll_edit_pwd_o_clear)
	public void editPwdOClear() {
		et_login_pwd_first.setText("");
	}
	
	/**
	 *�ֻ��ı������ť����¼����ڶ��������
	 */
	@OnClick(R.id.ll_edit_pwd_t_clear)
	public void editPwdTClear() {
		et_login_pwd_two.setText("");
	}

}
