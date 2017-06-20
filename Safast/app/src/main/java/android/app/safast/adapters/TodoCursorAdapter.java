package android.app.safast.adapters;

import android.app.safast.CallActivity;
import android.app.safast.R;
import android.app.safast.controller.DatabaseController;
import android.app.safast.domain.Phone;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TodoCursorAdapter extends CursorAdapter {

    private ViewGroup par;
    private ListView lv;
    public TodoCursorAdapter(Context context, Cursor cursor, ListView listView) {

        super(context, cursor, 0);
        lv = listView;
    }
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        par=parent;
        return LayoutInflater.from(context).inflate(R.layout.list_buttons, parent, false);

    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        // Find fields to populate in inflated template
        final RelativeLayout rlItem = (RelativeLayout) view.findViewById(R.id.rl_item);
        final LinearLayout llItem = (LinearLayout) view.findViewById(R.id.ll_item);
        final ImageButton btnCall = (ImageButton) view.findViewById(R.id.btn_call);
        final TextView IvName = (TextView) view.findViewById(R.id.tv_name);
        TextView ivNumber = (TextView) view.findViewById(R.id.tv_number);
        ImageView iv1 = (ImageView) view.findViewById(R.id.iv_1);
        // Extract properties from cursor
        DatabaseController crud = new DatabaseController(context.getApplicationContext());
        Cursor c = crud.getDataById(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        if(c!=null) {
            final Phone p = new Phone();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPhone(c.getString(2));
            p.setColor(c.getString(3));

            String name = p.getName();
            final String number = p.getPhone();
            String color = p.getColor();

            int[] photos = new int[]{R.drawable.firefighters01l, R.drawable.civil_police01, R.drawable.state_police02l, R.drawable.harassment03l, R.drawable.hospitall};
            if (p.getId() <= 5) {
                iv1.setImageResource(photos[p.getId() - 1]);
                IvName.setText(name);
                ivNumber.setText(number);
                rlItem.setBackgroundColor(Color.parseColor(color));
            } else {
                iv1.setImageResource(R.drawable.extra_call);
                IvName.setText(name);
                ivNumber.setText(number);
                rlItem.setBackgroundColor(Color.parseColor(color));
            }


            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri call = Uri.parse("tel:" + number);
                    Intent surf = new Intent(Intent.ACTION_CALL, call);
                    context.startActivity(surf);
                }
            });
            rlItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    PopupMenu popup = new PopupMenu(context, btnCall);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater()
                            .inflate(R.menu.menu_call, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getTitle().toString().matches("Editar")) {
                                final AlertDialog.Builder alert = new AlertDialog.Builder(context);

                                final LinearLayout ll = new LinearLayout(context);
                                ll.setOrientation(LinearLayout.VERTICAL);

                                final TextView tvAlert = new TextView(context);
                                tvAlert.setText(" Editar:");
                                final EditText etName = new EditText(context);
                                etName.setHint("Nome");
                                final EditText etPhone = new EditText(context);
                                etPhone.setHint("Telefone");
                                etPhone.setInputType(InputType.TYPE_CLASS_NUMBER);


                                alert.setTitle(p.getName());

                                ll.addView(tvAlert);
                                ll.addView(etName);
                                ll.setPadding(30, 10, 30, 0);
                                ll.addView(etPhone);

                                alert.setView(ll);

                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //What ever you want to do with the value
                                        DatabaseController crud = new DatabaseController(context.getApplicationContext());// TALVEZ TENHA Q MUDAR

                                        String name = etName.getText().toString();
                                        String phone = etPhone.getText().toString();


                                        if (!name.matches("") && !phone.matches("")) {
                                            try {
                                                crud.alterData(p.getId(), name, phone);
                                                Cursor c = crud.getDataById(p.getId());
                                                bindView(view, context, c);
                                            } catch (SQLiteException e) {
                                                Toast.makeText(context.getApplicationContext(),
                                                        "Unable to execute sql query " + e.getMessage(),
                                                        Toast.LENGTH_SHORT).show();
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
                                return true;
                            } else if (item.getTitle().toString().matches("Excluir")) {
                                AlertDialog.Builder alert2 = new AlertDialog.Builder(context);
                                alert2.setTitle("Tem certeza?");
                                alert2.setMessage("Você não poderá recuperar o número emergencial após remove-lô");
                                final DatabaseController crud = new DatabaseController(context.getApplicationContext());
                                alert2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        crud.removePhone(p.getId());
                                        DatabaseController crud = new DatabaseController(context);
                                        Cursor c = crud.getData();
                                        //TodoCursorAdapter.super.notifyDataSetInvalidated();
                                        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(context, c, lv);
                                        lv.setAdapter(todoAdapter);
                                        todoAdapter.notifyDataSetChanged();
                                        //llItem.removeAllViews();
                                        //newView(context, c, par);
                                        //rlItem.setVisibility(View.INVISIBLE);
                                        //rlItem.setFocusable(false);
                                    }
                                });

                                alert2.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        // what ever you want to do with No option.
                                    }
                                });

                                alert2.show();
                            }


                            return true;
                        }
                    });

                    popup.show(); //showing

                }
            });
        }
    }

/*
        rlItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(context);

                final LinearLayout ll = new LinearLayout(context);
                ll.setOrientation(LinearLayout.VERTICAL);

                final TextView tvAlert = new TextView(context);
                tvAlert.setText(" Editar:");
                final EditText etName = new EditText(context);
                etName.setHint("Nome");
                final EditText etPhone = new EditText(context);
                etPhone.setHint("Telefone");

                alert.setTitle(p.getName());

                ll.addView(tvAlert);
                ll.addView(etName);
                ll.setPadding(30,10,30,0);
                ll.addView(etPhone);

                alert.setView(ll);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        DatabaseController crud = new DatabaseController(context.getApplicationContext());// TALVEZ TENHA Q MUDAR
                        String name = etName.getText().toString();
                        String phone = etPhone.getText().toString();


                        if(name!=null && phone!=null){
                            try{
                                crud.alterData(p.getId(), name, phone);
                                Cursor c = crud.getDataById(p.getId());
                                bindView(view, context, c);
                            }catch (SQLiteException e) {
                                Toast.makeText(context.getApplicationContext(),
                                        "Unable to execute sql query " + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }


                        }else{
                            AlertDialog.Builder alert2 = new AlertDialog.Builder(context.getApplicationContext());
                            alert2.setTitle("ERROR");
                            alert2.setMessage("Ambos compos precisam estar preenchidos!");
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
                return true;
            }

        });


    }
*/

    public Phone getPhone(Context context, Cursor cursor){

        DatabaseController crud = new DatabaseController(context.getApplicationContext());
        Cursor c = crud.getDataById(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
        final Phone p = new Phone();
        p.setId(c.getInt(0));
        p.setName(c.getString(1));
        p.setPhone(c.getString(2));
        p.setColor(c.getString(3));
        return p;
    }
    public ImageButton getButton(AdapterView v){
        final ImageButton btnCall = (ImageButton) v.findViewById(R.id.btn_call);
        return btnCall;
    }


}

