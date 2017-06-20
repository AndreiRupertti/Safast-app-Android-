package android.app.safast;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutUsActivity extends MainActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("SOBRE NÓS");
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);

        super.setDrawer(savedInstanceState);

        TextView insText = (TextView) findViewById(R.id.ins_text);

        insText.setText(getInstructionsText());
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
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String getInstructionsText(){
        String instructionText;

        instructionText = "\tOlá, muito obrigado por estar utilizando o Safast!\n\n\tEste aplicativo foi feito com o intuito de ajudar seus usuários em situações de risco, mas também como forma de TCC(Tabalho de Conclusão de Curso) do aluno Andrei Oliveira, na escola Solon Tavares, em guaíba.\n\nAutor: Andrei oliveira\n\nOrientador: Francisco Herique da Silva\n\nCurso: Técnico em Informática\n\nAno: 2017";
        return instructionText;
    }
}
