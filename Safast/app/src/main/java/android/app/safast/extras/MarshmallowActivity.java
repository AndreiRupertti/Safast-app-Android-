package android.app.safast.extras;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.app.safast.extras.MarshmallowPermission;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Andre Figas on 21/12/2015.
 */
public abstract class MarshmallowActivity  extends AppCompatActivity {

    MarshmallowPermission p;



    public void setCallbackPermission(MarshmallowPermission p){
        this.p = p;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            p.getPermission();
        }
        else{


            for(String r : p.getPermissions()){

                p.granted(r);
            }

        }
    }
    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults){

        p.onRequestPermissionsResult(requestCode, permissions, grantResults);

        HashMap<String, Boolean> results = p.getResults();

        for(String r : results.keySet()){

            if(results.get(r)){
                if(requestCode ==  p.getRequestCode()){

                    p.granted(r);
                }
            }
            else{
                if(requestCode ==  p.getRequestCode()){

                    p.denied(r);
                }
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        p.onActivityResult(requestCode, resultCode, data);
    }
    private void configDialog(String text, String positive, String negative){
        p.configDialog(text, positive, negative);
    }
    //public boolean result(String permission){
    //    return p.result(permission);
    //}
}