package com.example.IDEAAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Button addBtn;
    private Button queryBtn;
    private EditText addText;
    private EditText searchText;
    private TextView textView;
    private Socket server;
    private LinearLayout frameSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        addBtn = (Button) findViewById(R.id.addButton);
        queryBtn = (Button) findViewById(R.id.searchButton);
        addText = (EditText) findViewById(R.id.addText);
        searchText = (EditText) findViewById(R.id.searchText);
        textView = (TextView) findViewById(R.id.blankView);
        frameSwitch = (LinearLayout) findViewById(R.id.queryPanel);
        try {
            server = new Socket("192.168.1.102", 8888);
        } catch (Exception e) {
        }

        String str = "<font color=\"#0099FF\">我的<br>标签</font>";
        TextView tv = (TextView) findViewById(R.id.myLabelView);
        tv.setText(Html.fromHtml(str));
        addText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    addText.setText("");
                } else {
                    String hint = "<font><em>新增标签，逗号分隔，点击输入…</em></font>";
                    addText.setText(Html.fromHtml(hint));
                }
            }
        });
        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    searchText.setText("");
                } else {
                    String hint = "请输入要查询的内容，点击查询…";
                    searchText.setText(Html.fromHtml(hint));
                }
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((addText.getText().toString()).trim().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("提示");
                    alertDialog.setMessage("请输入要新增的标签内容哦");
                    alertDialog.show();
                } else {
                    textView.setText("新增成功");
                    Thread t = new Thread(new Runnable() {
                        public void run() {
                            try {
                                if (server == null) server = new Socket("192.168.1.102", 8888);
                                BufferedReader in = new BufferedReader(new InputStreamReader(
                                        server.getInputStream()));
//                                textView.setText("helloTest");
//                                Log.i("ExceptionLHT 3 ", "");
                                PrintWriter out = new PrintWriter(server.getOutputStream());
//                                Log.i("ExceptionLHT 4 ", "");
                                String str = "client send";

                                while (true) {
                                    int count = 0;
                                    out.println(str + count++);
                                    out.flush();
                                    Log.i("ExceptionLHT ", "TEST111 ");
                                    if (in != null) {
                                        Log.i("ExceptionLHT ", "in is not null ");
                                    }
                                    int t = in.read();
                                    if (t > 0) {
                                        Log.i("ExceptionLHT 555 ", in.toString());
                                    }
                                    if (in.read() > 0) {
                                        Log.i("ExceptionLHT ", "TEST32223 ");
                                        Log.i("ExceptionLHT ", in.readLine());

                                    }
                                    Log.i("ExceptionLHT 666", in.readLine());
                                    Log.i("ExceptionLHT ", "TEST3333 ");
                                }

//                                server.close();
                            } catch (Exception e) {
                                Log.i("ExceptionLHT ", "");
                            } finally {
                                Log.i("ExceptionLHT gggg", "TTTT");
                            }
                        }
                    });
                    t.start();


                }
            }
        });

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((searchText.getText().toString()).trim().equals("")) {
//                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                    alertDialog.setTitle("提示");
//                    alertDialog.setMessage("请输入要搜索的内容哦");
//                    alertDialog.show();
                    getViewSecond();
                } else {
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this, TestActivity.class);
//                        Bundle mBundle = new Bundle();
//                        mBundle.putString("Data", "data from TestBundle");//压入数据
//                        intent.putExtras(mBundle);
//                        startActivity(intent);

//                    TestActivity activity = new TestActivity();
//                     MainActivity.this.setContentView(activity);
                    getViewOne();

                }
            }
        });
    }

    public void getViewOne() {
        View viewOne = getLayoutInflater().inflate(R.layout.one, null);
        frameSwitch.removeAllViews();
        frameSwitch.addView(viewOne, LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
    }

    public void getViewSecond() {
        View viewSecond = getLayoutInflater().inflate(R.layout.two, null);

//        queryBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "hello world",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
        frameSwitch.removeAllViews();
        frameSwitch.addView(viewSecond, LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
    }
}
