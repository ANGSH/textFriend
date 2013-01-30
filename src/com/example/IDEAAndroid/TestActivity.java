package com.example.IDEAAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: MT-ZJG
 * Date: 13-1-29
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */
public class TestActivity extends Activity {

    private Button addBtn;
    private EditText addText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        addBtn = (Button) findViewById(R.id.addButton2);
        addText = (EditText) findViewById(R.id.addText2);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((addText.getText().toString()).trim().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(TestActivity.this).create();
                    alertDialog.setTitle("提示");
                    alertDialog.setMessage("请输入要新增的标签内容哦");
                    alertDialog.show();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(TestActivity.this, MainActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putString("Data", "data from TestBundle");//压入数据
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
            }
        });


    }

}
