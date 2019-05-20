package wang.tyrael.android.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import wang.tyrael.android.ToastUtil;
import wang.tyrael.log.LogHelper;
import wang.tyrael.presenter.UIInterface;

/**
 * Created by 王超 on 2017/11/2.
 */

public abstract class LibBaseActivity extends AppCompatActivity implements UIInterface {
    private static final String TAG = "LibBaseActivity";
    protected String state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogHelper.i(TAG, "onCreate:" + this);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogHelper.i(TAG, "onNewIntent:" + this);
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogHelper.i(TAG, "onSaveInstanceState:" + this);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        state = "resumed";
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = "paused";
    }

    @Override
    protected void onDestroy() {
        LogHelper.i(TAG, "onDestroy:" + this);

        super.onDestroy();
    }

    protected void requestPermission(int requestCode, String permission, String explanation){
        if (ContextCompat.checkSelfPermission(LibBaseActivity.this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LibBaseActivity.this,
                    permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ToastUtil.show("shouldShowRequestPermissionRationale");


            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(LibBaseActivity.this,
                        new String[]{permission},
                        requestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            onPermissionGranted(requestCode);
        }
    }

    protected void onPermissionGranted(int requestCode){}
    protected void onPermissionDenied(int requestCode){}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    onPermissionGranted(requestCode);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    onPermissionDenied(requestCode);
                }


            // other 'case' lines to check for other
            // permissions this app might request

    }


}
