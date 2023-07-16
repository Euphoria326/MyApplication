package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class password  extends AppCompatActivity {

        private EditText edt_UserName, edt_OldPassWord, edt_NewPassWord, edt_NewPassWord2;
        private Button btn_Confirm;
        private boolean isFlag = false;
        private SQLite sqlite;



        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.change);
            sqlite = new SQLite(this);
            //获取实例对象
            ImageView draft=(ImageView) findViewById(R.id.exit3);
            edt_UserName = this.findViewById(R.id.edt_UserName);
            edt_OldPassWord = this.findViewById(R.id.edt_OldPassWord);
            edt_NewPassWord = this.findViewById(R.id.edt_NewPassWord);
            edt_NewPassWord2 = this.findViewById(R.id.edt_NewPassWord2);
            btn_Confirm = this.findViewById(R.id.btn_Confirm);

            //用户名
            edt_UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {

                    } else {
                        String username = edt_UserName.getText().toString();
                        if (username.length() > 40) {
                            Toast.makeText(password.this, "用户名长度必须小于,请重新输入", Toast.LENGTH_SHORT).show();
                            edt_UserName.setText("");
                        }
                    }
                }
            });
           draft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent exitmain=new Intent(password.this,MainActivity.class);
                    startActivity(exitmain);
                }
            });
            //旧密码，用作用户修改密码验证条件
            edt_OldPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {


                    } else {
                        String old = edt_OldPassWord.getText().toString();
                        if (old.length() > 12) {
                            Toast.makeText(password.this, "密码长度不超过12位,请重新输入", Toast.LENGTH_SHORT).show();
                            edt_OldPassWord.setText("");
                        }


                    }
                }
            });

            //新密码
            edt_NewPassWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {

                    } else {
                        String newPassword = edt_NewPassWord.getText().toString();
                        if ( newPassword.length() > 12) {
                            Toast.makeText(password.this, "密码长度请勿超过12位,请重新输入", Toast.LENGTH_LONG).show();
                            edt_NewPassWord.setText("");
                        }
                    }
                }
            });
            //确认新密码
            edt_NewPassWord2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {

                    } else {
                        String newPassword = edt_NewPassWord.getText().toString();
                        String newPassword2 = edt_NewPassWord2.getText().toString();

                        if (!(newPassword.equals(newPassword2))) {
                            Toast.makeText(password.this, "两次密码不一致,请重新输入", Toast.LENGTH_SHORT).show();
                            edt_NewPassWord2.setText("");

                        } else if ( newPassword2.length() > 12) {
                            Toast.makeText(password.this, "密码长度请勿超过12位", Toast.LENGTH_LONG).show();
                            edt_NewPassWord2.setText("");
                        }

                    }
                }
            });
//匹配
//确认修改密码，若用户名与此手机号匹配，则可以修改密码，否则不允许修改密码

            btn_Confirm.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int id = view.getId();

                    if (id == R.id.btn_Confirm) {
                        String username = edt_UserName.getText().toString();
                        String oldpassword = edt_OldPassWord.getText().toString();
                        String password1=edt_NewPassWord.getText().toString();
                        String password2=edt_NewPassWord2.getText().toString();
                        boolean login = sqlite.login(username, oldpassword);
                        if (login) {
                            boolean a = sqlite.equals(password1, password2);
                            if (a) {
                                long r = sqlite.update(username, password1);
                                if (r != -1) {
                                    Toast.makeText(com.example.myapplication.password.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(com.example.myapplication.password.this, com.example.myapplication.login.class);
                                    startActivity(i);
                                }
                            }else {
                                Toast.makeText(com.example.myapplication.password.this, "两次输入密码不相同", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(com.example.myapplication.password.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
}
//匹配

