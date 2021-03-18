package top.iqqcode.moredialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * @Author: iqqcode
 * @Date: 2021/3/14
 * @Description:AlertDialog更多使用
 */
public class MainActivity extends Activity {

    private static final int NUM = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 显示一般AlertDialog
     *
     * @param v
     */
    public void showAD(View v) {
        // new AlertDialog.Builder(this).create().show();
        // 链式调用
        new AlertDialog.Builder(this)
                .setTitle("消息提示") //设置标题
                .setMessage("你确定删除数据吗")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "确认删除数据", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消删除数据", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    /**
     * 单选item列表
     *
     * @param v
     */
    public void showLB(View v) {
        // final的变量在方法执行完后还存在(拷贝一封放到常量池中) [作用域]
        // 对话框点击完消失之后该对象被回收，但是Toast才显示
        final String[] items = {"Java", "Kotlin", "RxJava", "Flutter", "React Native"};
        new AlertDialog.Builder(this)
                .setTitle("Android开发")
                .setIcon(R.mipmap.ic_launcher_round)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 提示颜色
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        //移除dilaog
                        dialog.dismiss();
                    }
                })
                .show();
    }


<<<<<<< HEAD
    /**
     * 多选列表
     *
     * @param v
     */

    List<Integer> list = new ArrayList<Integer>();

    public void showML(View v) {
        final String[] items = {"Java", "Kotlin", "RxJava", "Flutter", "React Native"};
        boolean[] choice = new boolean[]{false, false, false, false, false, false};
        new AlertDialog.Builder(this)
                .setTitle("Android开发")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMultiChoiceItems(items, choice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            list.add(which);
                        } else {
                            list.remove(which);
                        }
                        // 提示颜色
                        Toast.makeText(MainActivity.this, "选择是:" + list.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
=======
    /**
     * 多选列表
     *
     * @param v
     */

    List<Integer> list = new ArrayList<Integer>();

    public void showML(View v) {
        final String[] items = {"Java", "Kotlin", "RxJava", "Flutter", "React Native"};
        boolean[] choice = new boolean[]{false, false, false, false, false, false};
        new AlertDialog.Builder(this)
                .setTitle("Android开发")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMultiChoiceItems(items, choice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            list.add(which);
                        } else {
                            list.remove(which);
                        }
                        // 提示颜色
                        Toast.makeText(MainActivity.this, "选择是:" + list.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
>>>>>>> 7020f95802514c3818c52478095a30454a5b6a60
     * 显示单选RadioButton列表
     *
     * @param v
     */
    public void showLD(View v) {
        // final的变量在方法执行完后还存在(拷贝一封放到常量池中)
        final String[] items = {"红", "蓝", "绿", "灰"};
        new AlertDialog.Builder(this)
                .setTitle("指定背景颜色")
                .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 提示颜色
                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        //移除dilaog
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
<<<<<<< HEAD
     * 显示自定义AlertDialog-01
=======
     * 显示自定义Al ertDialog
>>>>>>> 7020f95802514c3818c52478095a30454a5b6a60
     *
     * @param v
     */
    public void showCD(View v) {
        // LayoutInflater inflater = LayoutInflater.from(this);
        // 动态加载布局文件, 得到对应的View对象
        View view = View.inflate(this, R.layout.dialog_view, null); // 此处与该Activity的布局无关，故为null
        // 问题1:　view的真实类型? --是布局文件根标签的类型(如LinearLayout), 包含了子View对象
        /**
         * 问题2:　如何得到一个独立View的子View?  view.findViewById(id)
         *  findViewById(id)是在setContentView()中的View中找
         */
        final EditText nameET = view.findViewById(R.id.et_dialog_name);
        final EditText pwdET = view.findViewById(R.id.et_dialog_pwd);

        new AlertDialog.Builder(this)
                .setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消操作", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //读取用户名和密码
                        String username = nameET.getText().toString();
                        String password = pwdET.getText().toString();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("password", password);
                        startActivity(intent);
                        //提示
                        Toast.makeText(MainActivity.this, username + " : " + password, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    /**
     * 自定义AlertDialog-02
     *
     * @param v
     */

    Dialog customDialog = null;

    public void showCD02(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog02_view, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        view.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button was Clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                customDialog.dismiss();
            }
        });

        customDialog = builder.create();
        customDialog.show();
    }

    /**
     * 显示圆形进度ProgressDialog
     * 回调方法: 主线程执行
     *
     * @param v
     * @throws InterruptedException
     */
    public void showPD(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "数据加载", "数据加载中");
        // 模拟耗时操作，但是一定要在子线程中执行
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //移除dialog
                progressDialog.dismiss(); //方法在分线程执行, 但内部使用Handler实现主线程移除dialog，所以不需要放到UI线程

                // Toast.makeText(MainActivity.this, "加载完成了!!!", Toast.LENGTH_SHORT).show();

                // 不能在分线程直接更新UI(Toast)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载完成了!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }


    /**
     * 显示水平进度ProgressDialog
     *
     * @param v
     */
    public void showPD2(View v) {
        // 1. 创建Dialog对象
        final ProgressDialog dialog = new ProgressDialog(this);
        // 2. 设置样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置可取消
        dialog.setCancelable(true);
        // 3. 显示
        dialog.show();
        // 4. 启动分线程, 加载数据, 并显示进度, 当加载完成移除dialog
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 20;
                // 设置最大进度
                dialog.setMax(count);
                for (int i = 0; i < count; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dialog.setProgress(dialog.getProgress() + 1);
                }
                // 移除dialog
                dialog.dismiss();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载完成了!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 日期Dialog
     *
     * @param v
     */
    public void showDateAD(View v) {
        //创建日历对象
        Calendar calendar = Calendar.getInstance();
        //得到当前的年月日
        int year = calendar.get(Calendar.YEAR); //得到年份
        int month = calendar.get(Calendar.MONTH); // 得到月份
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 得到天
        Log.e("TAG", year + "-" + month + "-" + dayOfMonth);

        new DatePickerDialog(this, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.e("TAG", year + "--" + (monthOfYear + 1) + "--" + dayOfMonth);
            }
        }, year, month, dayOfMonth).show();
    }

    /**
     * 分秒
     *
     * @param v
     */
    public void showTimeAD(View v) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Log.e("TAG", hour + " : " + minute);
        new TimePickerDialog(this, new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e("TAG", hour + " : " + minute);
            }
        }, hour, minute, true).show();
    }


    /**
     * 案例：实现程序退出确认框
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("🎨确认退出吗，亲亲~")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("不走了", null)
                .show();
    }
<<<<<<< HEAD

    /**
     * 自定义AlertDialog-02
     *
     * @param v
     */

    PopupWindow popWindow = null;

    public void popupWindowBtn(View v) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog02_view, null);

        popWindow = new PopupWindow(view, 800, 800);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        view.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button was Clicked!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                popWindow.dismiss();
            }
        });

        // 按钮点击位置 -- 下拉式
        //popWindow.showAsDropDown(v);
        // 锚点偏移式
        popWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }

    public void activityBtn(View view) {
        startActivity(new Intent(MainActivity.this, DialogStyleActivity.class));
    }
=======
>>>>>>> 7020f95802514c3818c52478095a30454a5b6a60
}