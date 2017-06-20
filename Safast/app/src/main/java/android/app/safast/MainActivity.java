package android.app.safast;

import android.Manifest;
import android.app.safast.controller.DatabaseController;
import android.app.safast.extras.MarshmallowActivity;
import android.app.safast.extras.MarshmallowPermission;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;



import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends MarshmallowActivity {


    private Toolbar mToolbar;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    //private FloatingActionMenu fab;
    private int mItemDrawerSelected;
    private int mProfileDrawerSelected;




    public static final int REQUEST_PERMISSIONS_CODE = 128;

    int CODE_CALL_PHONE = 3;

    boolean call_permission;

    private List<PrimaryDrawerItem> listPanel;


    private MaterialDialog mMaterialDialog;


    private OnCheckedChangeListener mCheckedChange = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(MainActivity.this, "MUDO O ROLE", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawable d = drawable.ic_info_outline_white_36dp;
        // TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("SAFAST");
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        //>>>>>>>>>>>>>>>TESTE

        final DatabaseController db = new DatabaseController(this);

        // NAVIGATION DRAWER
        setDrawer(savedInstanceState);




        ImageButton btCall= (ImageButton) findViewById(R.id.bt_call);
        ImageButton btWiki= (ImageButton) findViewById(R.id.bt_wiki);
        ImageButton btAlarm= (ImageButton) findViewById(R.id.bt_alarm);
        ImageButton btIntruc= (ImageButton) findViewById(R.id.bt_instructions);


        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  PERMISSOES ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                if(call_permission){

                    createIntent(CallActivity.class);

                }else{

                    permission();
                    if(call_permission){
                        createIntent(CallActivity.class);
                    }
                }



                // ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PERMISSOES FIM


            }
        });





        btWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIntent(WikiActivity.class);
            }
        });


        btAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createIntent(AlarmActivity.class);
            }
        });
        btIntruc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createIntent(SoonActivity.class);
            }
        });
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





    public void setDrawer(Bundle savedInstanceState){
        // HEADER
        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .build();

        headerNavigationLeft.setBackgroundRes(R.drawable.img_emergency);




        // BODY
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationLeft)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        createIntent(i);
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                        return false;
                    }
                })
                .build();
        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Abas:"));

        listPanel =getSetPanelList();
        if(listPanel != null && listPanel.size() > 0){
            for( int i = 0; i < listPanel.size(); i++ ){
                navigationDrawerLeft.addItem( listPanel.get(i));
            }
            navigationDrawerLeft.setSelection(mItemDrawerSelected);
        }
        navigationDrawerLeft.addItem(new SectionDrawerItem().withName("Outros:"));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Instruções"));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Sobre nós"));
        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Avalie o apliativo"));


    }
    private List<PrimaryDrawerItem> getSetPanelList(){
        String[] names = new String[]{"Chamadas", "Artigos", "Alarme", "Novidades"};
        int[] icons = new int[]{R.drawable.ic_call_black_48dp, R.drawable.ic_wiki_black_48dp, R.drawable.ic_flare_black_48dp, R.drawable.ic_instructions_black_48dp};
        int[] iconsSelected = new int[]{R.drawable.ic_call_red_48dp, R.drawable.ic_wiki_red_48dp, R.drawable.ic_flare_red_48dp, R.drawable.ic_instructions_red_48dp};

        List<PrimaryDrawerItem> list = new ArrayList<>();

        for(int i = 0; i < names.length; i++){
            PrimaryDrawerItem aux = new PrimaryDrawerItem();
            aux.setName( names[i] );
            aux.setIcon(getResources().getDrawable(icons[i]));
            aux.setTextColor(getResources().getColor(R.color.colorPrimarytext));
            aux.setSelectedIcon(getResources().getDrawable(iconsSelected[i]));
            aux.setSelectedTextColor(getResources().getColor(R.color.colorPrimary));


            list.add( aux );
        }
        return(list);
    }


    public void createIntent(Class c){
        Intent intent = new Intent(MainActivity.this, c);
        MainActivity.this.startActivity(intent);;
        overridePendingTransition(R.anim.new_activity_anim, R.anim.old_activity_anim);
    }

    public void createIntent(int i){
        switch (i){
            case 1:
                if(call_permission){

                    createIntent(CallActivity.class);

                }else{

                    permission();
                        if(call_permission){
                        createIntent(CallActivity.class);
                    }
                }
                    break;
            case 2: createIntent(WikiActivity.class);
                    break;
            case 3: createIntent(AlarmActivity.class);
                    break;
            case 4: createIntent(SoonActivity.class);
                    break;
            case 5: createIntent(InstructionsActivity.class);
                    break;
            case 6: createIntent(InstructionsActivity.class);
                    break;
            case 7: createIntent(AboutUsActivity.class);
                    break;
        }
    }





    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("mItemDrawerSelected", mItemDrawerSelected);
        outState.putInt("mProfileDrawerSelected", mProfileDrawerSelected);
        // outState.putParcelableArrayList("listPhones", (ArrayList<Phone>) listPhones);
        outState = navigationDrawerLeft.saveInstanceState(outState);
        outState = headerNavigationLeft.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if(navigationDrawerLeft.isDrawerOpen()){
            navigationDrawerLeft.closeDrawer();
        }
        /*else if(fab.isOpened()){
            fab.close(true);
        }*/
        else{
            super.onBackPressed();
        }
    }



    public void permission(){
        MarshmallowPermission permission = new MarshmallowPermission(this, Manifest.permission.CALL_PHONE,
                CODE_CALL_PHONE,false,"Está permissão é importante pois...") {
            @Override
            public void granted(String permission) {
                call_permission=true;
            }
            @Override
            public void denied(String permission){
                call_permission=false;
            }
        };
        permission.configDialog("Irá permitir você fazer chamadas emergências com rapidez.","PERMITIR","CANCELAR");
        setCallbackPermission(permission);

    }




}
