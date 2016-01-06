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

public class CommDialogLoginErr implements OnClickListener{
	Context context;
	Dialog dialog;
	WindowManager windowManager; 
	
	String phone;
	
	Button	 comm_dialog_cancel;		//取消按钮
	Button	 comm_dialog_confirm;		//确定按钮
	TextView comm_dialog_message;		//dialog显示信息

	/**
	 * init the dialog
	 * 
	 * @return
	 */
	public CommDialogLoginErr(Context con,WindowManager wm,String message) {
		this.context = con; 
		windowManager = wm;
		dialog = new Dialog(context, R.style.dialog); 
		dialog.setContentView(R.layout.comm_dialog_loginerr); 
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
		lp.width = (int)(display.getWidth()-50); //设置宽度
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
			context.startActivity(new Intent(context,RegisterForPhoneActivity.class));
			break;
		case R.id.comm_dialog_cancel:
			this.dismiss();
			break;
		default:
			break;
		}
	}


}