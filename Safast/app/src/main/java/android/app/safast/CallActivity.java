package android.app.safast;

import android.app.safast.adapters.TodoCursorAdapter;
import android.app.safast.controller.DatabaseController;
import android.app.safast.domain.Phone;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import me.drakeet.materialdialog.MaterialDialog;


public class CallActivity extends MainActivity {

    private Toolbar mToolbar;


    private MaterialDialog mMaterialDialog;
    private Context context;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);
        mToolbar.setTitle("CHAMADAS");
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final DatabaseController crud = new DatabaseController(CallActivity.this);// TALVEZ TENHA Q MUDAR
        final Cursor todoCursor = crud.getData();
        // Find ListView to populate
        final ListView lvItems = (ListView) findViewById(R.id.mobile_list);
        // Setup cursor adapter using cursor from last step
        final TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor, lvItems);
        // Attach cursor adapter to the ListView

        lvItems.setAdapter(todoAdapter);



        context = CallActivity.this;
        fab.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){

                final AlertDialog.Builder alert = new AlertDialog.Builder(context);

                final LinearLayout ll = new LinearLayout(context);
                ll.setOrientation(LinearLayout.VERTICAL);

                final EditText etName = new EditText(context);
                etName.setHint("Nome");
                final EditText etPhone = new EditText(context);
                etPhone.setHint("Telefone");
                etPhone.setInputType(InputType.TYPE_CLASS_NUMBER);

                alert.setTitle("Novo Número Emêrgencial");

                ll.addView(etName);
                ll.setPadding(30, 10, 30, 0);
                ll.addView(etPhone);

                alert.setView(ll);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        if (!etName.getText().toString().matches("") && !etPhone.getText().toString().matches("")) {
                            try {
                                final DatabaseController crud = new DatabaseController(context.getApplicationContext());// TALVEZ TENHA Q MUDAR
                                final String name = etName.getText().toString();
                                final String phone = etPhone.getText().toString();
                                ColorPickerDialogBuilder
                                        .with(context)
                                        .setTitle("Escolha uma cor: ")
                                        .initialColor(R.color.colorAccent)
                                        .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                                        .density(12)
                                        .lightnessSliderOnly()
                                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                                            @Override
                                            public void onColorSelected(int selectedColor) {
                                                Toast.makeText(context.getApplicationContext(), "" + Integer.toHexString(selectedColor).toUpperCase(),
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })

                                        .setPositiveButton("OK", new ColorPickerClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                                String color = "#" + Integer.toHexString(selectedColor);

                                                if (name != null && phone != null && color != null) {
                                                    try {
                                                        crud.insertData(name, phone, color);
                                                        DatabaseController crud = new DatabaseController(context);
                                                        Cursor newCursor = crud.getData();

                                                        TodoCursorAdapter adapter = new TodoCursorAdapter(context, newCursor, lvItems);
                                                        //my adapter holds an internal list of DataItems
                                                        lvItems.setAdapter(adapter);
                                                        adapter.notifyDataSetChanged();
                                                    } catch (SQLiteException e) {
                                                        Toast.makeText(context.getApplicationContext(),
                                                                "Unable to execute sql query " + e.getMessage(),
                                                                Toast.LENGTH_SHORT).show();
                                                    }


                                                }
                                            }
                                        })
                                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .build()
                                        .show();
                            } catch (SQLiteException e) {
                            }

                        } else {
                            AlertDialog.Builder alert2 = new AlertDialog.Builder(context);
                            alert2.setTitle("ERRO!");
                            alert2.setMessage("Ambos compos precisam estar preenchidos!");

                            alert2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }
                            });

                            alert2.show();
                        }

                    }
                });

                alert.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();

            }


        });

        super.setDrawer(savedInstanceState);
/*

        // LONG CLICKERS <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        BtFF.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Phone ic_phone_white_48dp=db.getPhoneById(1);
                longClick(ic_phone_white_48dp,1);
                return true;
            }

        });

*/
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
