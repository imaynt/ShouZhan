package com.zykj.shouzhan.activity;

import com.zykj.shouzhan.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CommDialogPwdErr  implements OnClickListener{
	Context context;
	Dialog dialog;
	WindowManager windowManager; 
	
	String phone;
	
	Button	 comm_dialog_cancel;		//ȡ����ť
	Button	 comm_dialog_confirm;		//ȷ����ť
	TextView comm_dialog_message;		//dialog��ʾ��Ϣ

	/**
	 * init the dialog
	 * 
	 * @return
	 */
	public CommDialogPwdErr(Context con,WindowManager wm,String message,String phone) {
		this.context = con; 
		windowManager = wm;
		dialog = new Dialog(context, R.style.dialog); 
		dialog.setContentView(R.layout.comm_dialog_pwderr); 
		comm_dialog_message=(TextView) dialog.findViewById(R.id.comm_dialog_message);
		comm_dialog_cancel =(Button) dialog.findViewById(R.id.comm_dialog_cancel);
		comm_dialog_confirm =(Button) dialog.findViewById(R.id.comm_dialog_confirm);
		comm_dialog_message.setText(message);
		comm_dialog_cancel.setOnClickListener(this);
		comm_dialog_confirm.setOnClickListener(this);
		this.phone=phone;
	}

	public void show() {
		dialog.show();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int)(display.getWidth()-50); //���ÿ��
		dialog.getWindow().setAttributes(lp);
	}

	public void hide() {
		dialog.hide();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.comm_dialog_confirm:
			context.startActivity(new Intent(context,LoginForNumberActivity.class).putExtra("phone", phone));
			break;
		case R.id.comm_dialog_cancel:
			this.dismiss();
			break;
		default:
			break;
		}
	}


}