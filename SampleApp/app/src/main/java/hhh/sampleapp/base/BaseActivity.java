package hhh.sampleapp.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import hhh.sampleapp.MainActivity;
import hhh.sampleapp.Manager.ActivityManager;
import hhh.sampleapp.R;
import hhh.sampleapp.utils.T;


/**
 * Created by hhh on 2016/6/12.
 */
public class BaseActivity extends AppCompatActivity {
    private boolean isExit;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().put(getClass().getName(),this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(getClass().getName().equals(MainActivity.class.getName())){
                if(isExit){
                    ActivityManager.getInstance().closeAll();
                }else{
                    isExit=true;
                    T.show(this, R.string.exit);
                    handler.postDelayed(r,2000);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private Runnable r=new Runnable() {
        @Override
        public void run() {
            isExit=false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(getClass().getName());
    }
}
