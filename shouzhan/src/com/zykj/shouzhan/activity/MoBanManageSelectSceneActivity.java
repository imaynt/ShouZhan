package com.zykj.shouzhan.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.zykj.shouzhan.BaseActivity;
import com.zykj.shouzhan.R;
import com.zykj.shouzhan.view.MyCommonTitle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoBanManageSelectSceneActivity extends BaseActivity {
	@Bind(R.id.aci_mytitle)
	MyCommonTitle myCommonTitle;
	@Bind(R.id.ll_from_photo)
	LinearLayout ll_from_photo;
	@Bind(R.id.ll_from_camera)
	LinearLayout ll_from_camera;
	@Bind(R.id.ll_from_teacher)
	LinearLayout ll_from_teacher;
	private String timeString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_moban_manage_select_scene);
		ButterKnife.bind(this);

		initView();
	}

	private void initView() {
		myCommonTitle.setTitle(getString(R.string.moban_manage));
		myCommonTitle.setListener(this, null, null);
		myCommonTitle.setBackTitle(getString(R.string.back));
	}

	/**
	 * �����ѡȡ
	 */
	@OnClick(R.id.ll_from_photo)
	public void selectFromPhoto() {
		Intent photoIntent = new Intent(Intent.ACTION_PICK,null);
		photoIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(photoIntent, 11);
	}

	/**
	 * ��һ��
	 */
	@OnClick(R.id.ll_from_camera)
	public void selectFromCamera() {
		/* ���� */
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMddHHmmss", new Locale("zh", "CN"));
		timeString = dateFormat.format(date);
		createSDCardDir();
		Intent shootIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		shootIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
				new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera", timeString + ".jpg")));
		startActivityForResult(shootIntent, 2);
	}

	/**
	 * ����Ӱʦ��Ʒ��ѡȡ
	 */
	@OnClick(R.id.ll_from_teacher)
	public void selectFromTeacher() {

	}
	
	
	public void createSDCardDir() {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			// ����һ���ļ��ж��󣬸�ֵΪ�ⲿ�洢����Ŀ¼
			File sdcardDir = Environment.getExternalStorageDirectory();
			// �õ�һ��·����������sdcard���ļ���·��������
			String path = sdcardDir.getPath() + "/DCIM/Camera";
			File path1 = new File(path);
			if (!path1.exists()) {
				// �������ڣ�����Ŀ¼��������Ӧ��������ʱ�򴴽�
				path1.mkdirs();

			}
		}
	}
}
