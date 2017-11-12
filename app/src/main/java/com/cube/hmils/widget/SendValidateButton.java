package com.cube.hmils.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.cube.hmils.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @作者 cjh
 * @日期 2016/4/23 13:26
 * @描述 发送验证码的button，带有倒计时，以及在发送的过程中不可点击； 调用方式 view.startTickWork()方法即可；
 */

public class SendValidateButton extends android.support.v7.widget.AppCompatButton {
    private static final int DISABLE_TIME = 60;
    private static final int MSG_TICK = 0;
    private Timer mTimer = null;
    private TimerTask mTask = null;
    private int mDisableTime = DISABLE_TIME; // 倒计时时间，默认60秒
    private int mEnableColor = getResources().getColor(R.color.colorPrimary);
    private int mDisableColor = getResources().getColor(R.color.textSecondary);
    private int mEnableBgColor = R.color.white;
    private String mEnableString = "重发";
    private String mDisableString = "剩余";
    private String Second = "秒";
    private boolean mClickBle = true;
    private SendValidateButtonListener mListener = null;

    public int getmDisableTime() {
        return mDisableTime;
    }

    public int getmEnableColor() {
        return mEnableColor;
    }

    public void setEnableBgColor(int enableBgColor) {
        mEnableBgColor = enableBgColor;
        this.setBackgroundResource(enableBgColor);
    }

    public void setmEnableColor(int mEnableColor) {

        this.mEnableColor = mEnableColor;

        this.setTextColor(mEnableColor);

    }

    public int getmDisableColor() {
        return mDisableColor;
    }

    public void setmDisableColor(int mDisableColor) {

        this.mDisableColor = mDisableColor;
        this.setTextColor(mDisableColor);

    }

    public String getmEnableString() {
        return mEnableString;
    }

    public boolean isDisable() {
        return mDisableTime < DISABLE_TIME;
    }

    public void setmEnableString(String mEnableString) {

        this.mEnableString = mEnableString;

        if (this.mEnableString != null) {

            this.setText(mEnableString);
        }
    }

    public String getmDisableString() {

        return mDisableString;

    }

    public void setmDisableString(String mDisableString) {

        this.mDisableString = mDisableString;

    }

    public void setListener(SendValidateButtonListener mListener) {

        this.mListener = mListener;

    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case MSG_TICK:

                    tickWork();

                    break;

                default:

                    break;
            }
            super.handleMessage(msg);
        }
    };

    public SendValidateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        this.setText(mEnableString);
        this.setGravity(Gravity.CENTER);
        this.setTextColor(mEnableColor);
//        setEnableBgColor(mEnableBgColor);
        initTimer();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null && mClickBle) {
                    // startTickWork();
                    mListener.onClickSendValidateButton();
                }
            }
        });
    }

    private void initTimer() {
        mTimer = new Timer();
    }

    private void initTimerTask() {
        mTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_TICK);
            }
        };
    }

    public void startTickWork() {
        if (mClickBle) {
            mClickBle = false;

            SendValidateButton.this.setText(mDisableString + mDisableTime
                    + Second);
            this.setEnabled(false);
//            setmDisableColor(mDisableColor);
            SendValidateButton.this.setTextColor(mDisableColor);
//            setEnableBgColor(mEnableBgColor);
//            SendValidateButton.this.setBackgroundResource();
            initTimerTask();
            mTimer.schedule(mTask, 0, 1000);

        }

    }

    /**
     * 每秒钟调用一次
     */
    private void tickWork() {

        mDisableTime--;

        this.setText(mDisableString + mDisableTime + Second);

        if (mListener != null) {
            mListener.onTick();
        }
        if (mDisableTime <= 0) {
            stopTickWork();
        }
    }

    public void stopTickWork() {
        mTask.cancel();
        mTask = null;
        mDisableTime = DISABLE_TIME;
        this.setText(mEnableString);
        this.setTextColor(mEnableColor);
//        setEnableBgColor(mEnableBgColor);
//        SendValidateButton.this.setBackgroundResource(R.mipmap.ic_get_code_ok);
        this.setEnabled(true);
        mClickBle = true;

        if (mListener != null) {
            mListener.onTickStop();
        }
    }

    public interface SendValidateButtonListener {
        void onClickSendValidateButton();

        void onTick();

        void onTickStop();
    }
}
