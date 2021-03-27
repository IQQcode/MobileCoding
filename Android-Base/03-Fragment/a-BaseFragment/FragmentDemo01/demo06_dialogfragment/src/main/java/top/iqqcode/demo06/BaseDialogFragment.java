package top.iqqcode.demo06;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

/**
 * @Author: iqqcode
 * @Date: 2021-03-25 17:55
 * @Description:
 */
public class BaseDialogFragment extends DialogFragment {

    private TextView mTextView;

    public static BaseDialogFragment getInstance(int type) {
        BaseDialogFragment dialog = new BaseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("DIALOG_TYPE", type);
        dialog.setArguments(bundle);
        return dialog;
    }

    /**
     * 创建Dialog并返回
     *
     * @param savedInstanceState
     * @return
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        int dialogType = getArguments().getInt("DIALOG_TYPE");
        Calendar calendar = Calendar.getInstance();
        switch (dialogType) {
            case MainActivity.DIALOG_TYPE_ALERT:
                return new AlertDialog.Builder(getActivity())
                        .setIcon(R.mipmap.ic_launcher)
                        // getTag()传值时调用
                        .setTitle(getTag())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "点击确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "点击取消", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
            case MainActivity.DIALOG_TYPE_DATA:
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.e("TAG", year + "--" + (monthOfYear + 1) + "--" + dayOfMonth);
                    }
                }, year, month, dayOfMonth).show();
                break;
            case MainActivity.DIALOG_TYPE_TIME:
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                Log.e("TAG", hour + " : " + minute);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.e("TAG", hour + " : " + minute);
                    }
                }, hour, minute, true).show();
            default:
                break;
        }
        return super.onCreateDialog(savedInstanceState);
    }
}