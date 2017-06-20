package android.app.safast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class WikiActivity extends MainActivity {

    private Toolbar mToolbar;

    private int art;
    private float scale;
    private int width;
    private int height;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);
        mToolbar.setTitle("ARTIGOS");
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        super.setDrawer(savedInstanceState);

        scale = WikiActivity.this.getResources().getDisplayMetrics().density;
        width = WikiActivity.this.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;

        LinearLayout llAlag = (LinearLayout) findViewById(R.id.ll_alag);
        LinearLayout llTerre = (LinearLayout) findViewById(R.id.ll_terre);
        LinearLayout llIncen = (LinearLayout) findViewById(R.id.ll_incen);
        LinearLayout llTrans = (LinearLayout) findViewById(R.id.ll_trans);



        llAlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(WikiActivity.this, ArticleActivity.class);
                    intent.putExtra("key","Alagamento");
                    WikiActivity.this.startActivity(intent);
                    overridePendingTransition(R.anim.new_activity_anim, R.anim.old_activity_anim);


            }
        });

        llIncen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(WikiActivity.this, ArticleActivity.class);
                    intent.putExtra("key","Incêndios");
                    WikiActivity.this.startActivity(intent);
                    overridePendingTransition(R.anim.new_activity_anim, R.anim.old_activity_anim);

            }
        });

        llTerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(WikiActivity.this, ArticleActivity.class);
                    intent.putExtra("key","Terremotos");
                    WikiActivity.this.startActivity(intent);
                    overridePendingTransition(R.anim.new_activity_anim, R.anim.old_activity_anim);



            }
        });

        llTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(WikiActivity.this, ArticleActivity.class);
                    intent.putExtra("key","Trânsito");
                    WikiActivity.this.startActivity(intent);
                    overridePendingTransition(R.anim.new_activity_anim, R.anim.old_activity_anim);


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
