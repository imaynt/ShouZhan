package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterForPayActivity extends BaseActivity {
	
	private final int PRICE = 280;
	
	private String whoSelected="card";	//֧����ʽcard:���п�  wechat:΢��  ali:֧����
	private String payName="";			//֧������
	
	int num = 1;

	@Bind(R.id.aci_title_textview) 	  TextView aci_title_textview;	//������������ʾ
	@Bind(R.id.ll_back_btn) 	   	  LinearLayout   ll_back_btn;	//�������������ذ�ť
	@Bind(R.id.tv_num) 				  TextView tv_num;				//��������
	@Bind(R.id.tv_num_sub) 			  TextView tv_num_sub;			//���Ű�ť
	@Bind(R.id.tv_num_add) 			  TextView tv_num_add;			//�ӺŰ�ť
	@Bind(R.id.tv_num_pay) 			  TextView tv_num_pay;			//Ǯ���ܺ�
	@Bind(R.id.tv_pay_years) 		  TextView tv_pay_years;		//֧��������ʾ
	@Bind(R.id.tv_pay_price) 		  TextView tv_pay_price;		//Ǯ���ܺ���ʾ
	@Bind(R.id.tv_pay_success) 		  TextView tv_pay_success;		//֧����ʽ��ʾ
	@Bind(R.id.rl_register_pay_yuyue)   RelativeLayout rl_register_pay_yuyue;	//ԤԼ��ť
	@Bind(R.id.rl_register_pay_queding) RelativeLayout rl_register_pay_queding;	//ȷ��֧����ť
	@Bind(R.id.rl_reset_pay) 		  RelativeLayout rl_reset_pay;				//��ѡ֧����ʽ
	@Bind(R.id.ll_register_clickhide) LinearLayout   ll_register_clickhide;		//֧����Ϣ
	@Bind(R.id.rl_register_pay_zhifu) RelativeLayout rl_register_pay_zhifu;		//����֧����ť
	@Bind(R.id.ll_yuyue) 		  	  LinearLayout   ll_yuyue;					//ԤԼ
	@Bind(R.id.ll_pay_success) 		  LinearLayout   ll_pay_success;			//֧���ɹ�
	@Bind(R.id.ll_pay_online) 		  LinearLayout   ll_pay_online;				//����֧��
	@Bind(R.id.rb_ali_pay)			  RadioButton 	 rb_ali_pay;				//֧����֧��
	@Bind(R.id.rb_card_pay)			  RadioButton 	 rb_card_pay;				//���п�֧��
	@Bind(R.id.rb_wechat_pay)		  RadioButton 	 rb_wechat_pay;				//΢��֧��
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_register_pay);
		ButterKnife.bind(this);// �󶨵�����
		aci_title_textview.setText(R.string.register_shouzhan);
		
		num = Integer.parseInt(tv_num.getText().toString());
	}
	
	/**
	 * �������������ذ�ť����¼�
	 */
	@OnClick(R.id.ll_back_btn)
	public void llBackBtnOnClick() {
		this.finish();
	}
	
	/**
	 * ԤԼרԱ
	 */
	@OnClick(R.id.rl_register_pay_zhifu)
	public void payZhifuClick() {
		ll_pay_online.setVisibility(View.VISIBLE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * ԤԼרԱ
	 */
	@OnClick(R.id.rl_register_pay_yuyue)
	public void payYuyueClick() {
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.VISIBLE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * ��ѡ֧����ʽ��ť
	 */
	@OnClick(R.id.rl_reset_pay)
	public void resetPayClick() {
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.VISIBLE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.GONE);
	}
	
	/**
	 * ��ȥ��������
	 */
	@OnClick(R.id.tv_num_sub)
	public void numSubClick() {
		if (num < 1 && num > 3)
			num = 1;
		if (num == 1)
			return;
		num = num - 1;
		tv_num.setText(num+"");
		tv_num_pay.setText("��"+num*PRICE);
		tv_pay_years.setText(num+"��");
		tv_pay_price.setText("��"+num*PRICE);
	}

	/**
	 * ���Ϲ�������
	 */
	@OnClick(R.id.tv_num_add)
	public void numAddClick() {
		if (num < 1 && num > 3)
			num = 1;
		if (num == 3)
			return;
		num = num + 1;
		tv_num.setText(num+"");
		tv_num_pay.setText("��"+num*PRICE+"");
		tv_pay_years.setText(num+"��");
		tv_pay_price.setText("��"+num*PRICE);
	}
	
	
	/**
	 * ֧������ť��ѡ��
	 */
	@OnClick(R.id.rb_ali_pay)
	public void aliPayClick() {
		rb_ali_pay.setChecked(true);
		rb_card_pay.setChecked(false);
		rb_wechat_pay.setChecked(false);
		whoSelected="ali";
	}
	
	/**
	 * ���п���ť��ѡ��
	 */
	@OnClick(R.id.rb_card_pay)
	public void cardPayClick() {
		rb_ali_pay.setChecked(false);
		rb_card_pay.setChecked(true);
		rb_wechat_pay.setChecked(false);
		whoSelected="card";
	}
	
	/**
	 * ΢�Ű�ť��ѡ��
	 */
	@OnClick(R.id.rb_wechat_pay)
	public void wechatPayClick() {
		rb_ali_pay.setChecked(false);
		rb_card_pay.setChecked(false);
		rb_wechat_pay.setChecked(true);
		whoSelected="wechat";
	}
	
	/**
	 * ȷ��֧����ť
	 */
	@OnClick(R.id.rl_register_pay_queding)
	public void registerPayOnClick(){
		payName = whoSelected.equals("ali")?"֧����":(whoSelected.equals("card")?"���п�":"΢��");
		tv_pay_success.setText(payName+"֧���ɹ�");
		
		ll_pay_online.setVisibility(View.GONE);
		ll_register_clickhide.setVisibility(View.GONE);
		ll_yuyue.setVisibility(View.GONE);
		ll_pay_success.setVisibility(View.VISIBLE);
		
	}

}
