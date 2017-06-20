package android.app.safast.extras;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Andre Figas on 21/12/2015.
 */
public abstract class MarshmallowPermission {

    public abstract void granted(String permission);
    public void denied(String permission){};

    public MarshmallowPermission(AppCompatActivity activity,String[] permission,int requestCode, boolean[] required, String detail){

        if(permission.length== required.length) {
            this.activity = activity;
            this.required = required;
            this.requestCode = requestCode;
            this.permission = permission;
            this.deitail = detail;
        }
        else Log.e("Exception", "Number of parameters required must be equal to the number of permissions");

    }
    public MarshmallowPermission(AppCompatActivity activity,String[] permission,int requestCode, boolean required, String detail){



        this.activity = activity;
        this.requestCode = requestCode;
        this.permission = permission;
        this.required = new boolean[permission.length];
        for(int i=0;i<permission.length;i++){
            this.required[i]=required;
        }

        this.deitail = detail;

    }
    public MarshmallowPermission(AppCompatActivity activity,String permission,int requestCode, boolean required, String detail){

        this.activity=activity;
        this.required =new boolean[]{required};
        this.requestCode = requestCode;
        this.permission=new String[]{permission};
        this.deitail=detail;


        //getPermission(activity,permission,requestCode,required, detail);
    }


    private boolean[] required;
    private AppCompatActivity activity = null;

    private String[] permission;
    public String[] getPermissions(){
        return permission;
    }

    private String deitail;

    private int requestCode =0;
    public int getRequestCode(){
        return this.requestCode;
    }

    protected boolean response = false;

    private HashMap<String, Boolean> results = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> getResults(){
        return this.results;
    }


    private String dlgText="Click Permissions and allow to continue", dlgPositive="Continue", dlgNegative="Exit";
    public void configDialog(String text, String positive, String negative){
        this.dlgText=text;
        this.dlgPositive=positive;
        this.dlgNegative=negative;
    }


    protected void getPermission(AppCompatActivity activity, String[] permission,int requestCode, String detail){





        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            boolean granted = true;

            for(String p :permission){
                if(activity.checkSelfPermission(p)!= PackageManager.PERMISSION_GRANTED){
                    granted = false;
                }
            }

            if(granted){
                for (String r : getPermissions()) {

                    granted(r);
                }
            }
            else{
                boolean rationale = false;

                for(String p :permission){
                    if(activity.shouldShowRequestPermissionRationale(p)){
                        rationale = true;
                    }
                }

                if (rationale){
                    //se ja foi negado anteriormente
                    if(detail!=null && !detail.equals(""))
                        Toast.makeText(activity, detail, Toast.LENGTH_LONG).show();

                }


                activity.requestPermissions(permission, requestCode);


            }
        }

    }


    public void getPermission(){
        getPermission(this.activity,this.permission,this.requestCode,this.deitail);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean exit = false;
        int i=0;
        for (int result : grantResults) {
            results.put(permissions[i],(result==PackageManager.PERMISSION_DENIED)?false:true);


            if (result == PackageManager.PERMISSION_DENIED && this.required[i] && !exit) {
                dialog();
                exit = true;
            }
            if (exit==false) response = true;

            i++;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode== this.requestCode){

            getPermission(activity,permission, this.requestCode,deitail);
        }
    }

    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(deitail);
        builder.setMessage(dlgText);
        builder.setPositiveButton(dlgPositive, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(i, requestCode);
            }
        });
        builder.setNegativeButton(dlgNegative, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });

        builder.setCancelable(false);

        AlertDialog alerta = builder.create();
        alerta.show();


    }
}