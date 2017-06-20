package android.app.safast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SoonActivity extends MainActivity {

    private Toolbar mToolbar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soon);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);
        mToolbar.setTitle("EM BREVE");
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        super.setDrawer(savedInstanceState);


        LinearLayout llSoon1 = (LinearLayout) findViewById(R.id.ll_soon1);
        LinearLayout llSoon2 = (LinearLayout) findViewById(R.id.ll_soon2);
        LinearLayout llSoon3= (LinearLayout) findViewById(R.id.ll_soon3);
        final TextView llFeature1= (TextView) findViewById(R.id.tv_feature1);
        final TextView llFeature2= (TextView) findViewById(R.id.tv_feature2);
        final TextView llFeature3= (TextView) findViewById(R.id.tv_feature3);






        llSoon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Em Breve: "+llFeature1.getText(),
                        Toast.LENGTH_SHORT).show();


            }
        });

        llSoon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Em Breve: "+llFeature2.getText(),
                        Toast.LENGTH_SHORT).show();


            }
        });

        llSoon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Em Breve: "+llFeature3.getText(),
                        Toast.LENGTH_SHORT).show();



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
        }else if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
