package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.utils.TextUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class RegisterForPwdActivity extends BaseActivity {
	
	private boolean isUsed = false;

	@Bind(R.id.rl_register_pwd_click) 	RelativeLayout    rl_register_pwd_click;		//��������-ȷ����ť
	@Bind(R.id.et_register_pwd_first)	EditText	      et_register_pwd_first;		//��һ����������
	@Bind(R.id.et_register_pwd_two)		EditText		  et_register_pwd_two;			//�ڶ�����������
	@Bind(R.id.aci_title_textview) 		TextView 	   	  aci_title_textview;			//������������ʾ
	@Bind(R.id.ll_edit_pwd_t_clear)		LinearLayout	  ll_edit_pwd_t_clear;			//�ڶ����������
	@Bind(R.id.ll_edit_pwd_o_clear)		LinearLayout	  ll_edit_pwd_o_clear;			//��һ���������
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_pwd);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_pwd);
	}
	
	/**
	 * ��������-ȷ����ť����¼�
	 */
	@OnClick(R.id.rl_register_pwd_click)
	public void pwdOnClick(){
		if(!isUsed)
			return;
		if(et_register_pwd_first.getText().toString().equals(et_register_pwd_two.getText().toString())){
			Toast.makeText(RegisterForPwdActivity.this, "�������óɹ�", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(RegisterForPwdActivity.this,RegisterForPayActivity.class));
			overridePendingTransition(R.anim.default_fromright_in, R.anim.default_toleft_out);
		}
		else
			Toast.makeText(RegisterForPwdActivity.this, "�����������벻һ�£�����������", Toast.LENGTH_SHORT).show();
	}

	/**
	 * ����һ�������ʧȥ����ʱ���ж������Ƿ�Ϸ�
	 * @param b falseΪʧȥ���㣬trueΪ��ý���
	 */
	@OnFocusChange(R.id.et_register_pwd_first)
	public void registerPwdFFocusChange(boolean b){
		if(!b)
			Toast.makeText(RegisterForPwdActivity.this, TextUtil.isPasswordLengthLegal(et_register_pwd_first.getText().toString())?"����Ϸ�":"���벻�Ϸ�������������", Toast.LENGTH_SHORT).show();
		
	}
	
	/**
	 * ���ڶ����������������ʱ������ȷ����ť
	 */
	@OnTextChanged(R.id.et_register_pwd_two)
	public void registerPwdTChanged(){
		if(et_register_pwd_two.getText().toString().equals("")){
			isUsed = false;
			rl_register_pwd_click.setBackgroundResource(R.drawable.button_register_circle_gray);
			return;
		}
			
		isUsed = true;
		rl_register_pwd_click.setBackgroundResource(R.drawable.button_register_circle_red);
	}
	

	/**
	 *�ֻ��ı������ť����¼�����һ�������
	 */
	@OnClick(R.id.ll_edit_pwd_o_clear)
	public void editPwdOClear() {
		et_register_pwd_first.setText("");
	}
	
	/**
	 *�ֻ��ı������ť����¼����ڶ��������
	 */
	@OnClick(R.id.ll_edit_pwd_t_clear)
	public void editPwdTClear() {
		et_register_pwd_two.setText("");
	}

}
