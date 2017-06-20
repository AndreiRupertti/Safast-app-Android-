package android.app.safast;

import android.content.Intent;
import android.graphics.Color;

import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;



public class AlarmActivity extends MainActivity{

    private Toolbar mToolbar;

    private int art;
    private int cont=0;
    private int currentVolume;
    private MediaPlayer mp;
    private WindowManager.LayoutParams lp;
    private WindowManager.LayoutParams lpOriginal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        mp = MediaPlayer.create(AlarmActivity.this, R.raw.alarm_sound);


        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);

        mToolbar.setTitle("ALARME");
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(mp.isPlaying()==true){
                    try{
                        mp.setLooping(false);
                        mp.stop();
                    }catch (Exception e){
                        Log.wtf("DO THIS", " WHEN SAVE() FAILS");
                    }

                    AlarmActivity.super.onBackPressed();
                }else{
                    AlarmActivity.super.onBackPressed();
                }
            }
        });

        super.setDrawer(savedInstanceState);


        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll_main);
        final Button btAlarm= (Button) findViewById(R.id.bt_alarm);




        btAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cont==0) {

                    btAlarm.setText("PARAR");

                    lp = AlarmActivity.this.getWindow().getAttributes();
                    lpOriginal = AlarmActivity.this.getWindow().getAttributes();

                    lpOriginal.screenBrightness = lp.screenBrightness;
                    lp.screenBrightness =0.9000f;// i needed to dim the display


                    AlarmActivity.this.getWindow().setAttributes(lp);

                    AudioManager audio = (AudioManager) getSystemService(AlarmActivity.this.AUDIO_SERVICE);
                    currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);

                    for(int i=0; i<10; i++) {
                        audio.setStreamVolume(AudioManager.STREAM_MUSIC, 20, AudioManager.FLAG_SHOW_UI);

                    }
                    mp = MediaPlayer.create(AlarmActivity.this, R.raw.alarm_sound);

                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // TODO Auto-generated method stub

                        }

                    });
                    mp.setLooping(true);
                    mp.start();

                    cont++;



                }else if(cont==1) {


                    AudioManager audio = (AudioManager) getSystemService(AlarmActivity.this.AUDIO_SERVICE);
                    audio.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, AudioManager.FLAG_SHOW_UI );

                    mp.setLooping(false);
                    mp.stop();
                    btAlarm.setText("ALARME");

                    lp.screenBrightness = lpOriginal.screenBrightness;



                    cont--;
                }
            }

        });





    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_about_activity){
            startActivity(new Intent(this, AboutUsActivity.class));
        }else if(id == R.id.action_instructions_activity){
            startActivity(new Intent(this, InstructionsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
    }



}
