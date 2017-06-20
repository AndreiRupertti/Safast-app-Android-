package android.app.safast.adapters;

import android.app.safast.CallActivity;
import android.app.safast.R;
import android.app.safast.controller.DatabaseController;
import android.app.safast.domain.Feature;
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

public class SoonAdapter extends CursorAdapter {

    public SoonAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_buttons, parent, false);

    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        /*// Find fields to populate in inflated template
        int[] photos = new int[]{R.drawable.soon_geolocalization, R.drawable.soon_notification, R.drawable.soon_robery};
        LinearLayout llSoon = (LinearLayout) view.findViewById(R.id.ll_soon);
        ImageView ivSoon = (ImageView) view.findViewById(R.id.iv_soon);
        final TextView tvFeature = (TextView) view.findViewById(R.id.tv_feature);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        // Extract properties from cursor
        DatabaseController crud = new DatabaseController(context.getApplicationContext());
        Cursor c = crud.getDataSoonById(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));

        if(c!=null){
            Feature f = new Feature();
            f.setId(c.getInt(0));
            f.setFeature(c.getString(1));
            f.setDate(c.getString(2));

            if (f.getId() <= 2) {
                //ivSoon.setImageResource(R.drawable.soon_geolocalization);
                tvFeature.setText(f.getFeature());
                tvDate.setText(f.getDate());
            }
            llSoon.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Toast.makeText(context.getApplicationContext(),"Em Breve :"+tvFeature.getText(),
                            Toast.LENGTH_SHORT).show();
                }

            });
        }




    }


        rlItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

            }

        });


    }
*/
    }
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

