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


public class InstructionsActivity extends MainActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("INSTRUÇÕES");
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

        if(id == R.id.action_about_activity){
            startActivity(new Intent(this, AboutUsActivity.class));
        }else if(id == R.id.action_instructions_activity){
            startActivity(new Intent(this, InstructionsActivity.class));
        }else if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String getInstructionsText(){
        String instructionText;

        instructionText = "\tOlá, este é um aplicativo para lhe auxiliar em situações de risco, tais como acidentes de trânsito, incêndios, entre outros. No menu principal há quatro opções: Chamadas; Artigos; Alarme e a que você está agora, Instruções. Vamos entende-las por partes."+
                          "\n\n"+
                          "\tChamadas: Ao clicar nesta opção você será redirecionado para outra aba, onde tera atalhos de ligações para números emergênciais, como Bombeiros, Poilícia civil, Delegacia da Mulher e etc. Também é possivel editar estes atalhos, colocando outros números de telefone neles, além de adicionar novos e, caso queira, excluí-los."+
                          "\n\n"+
                          "\tArtigos: Nesta aba irão aparecer quatro cards: Alagamentos, Incêndios, Terremotos e Trânsito. Ao clicar em cada você sera direcionado para uma nova aba, nela haverão dicas de como se previnir, agir e se recuperar de acidentes nos respectivos conteúdos."+
                          "\n\n"+
                          "\tAlarme: Esta é uma aba muito simples, mas tão útil quanto, nela você verá um botão com o dizer 'ALARME', ao aperta-lo um alarme sonoro irá disparar, no máximo volume e o brilho da sua tela também ira aumentar, mas não se preocupe, ao apertar o botao novamente, o som para e tudo volta a como estava antes."+
                          "\n\n"+
                          "\tNovidades: Nesta aba por sua vez, é possivel visualizar novos conteúdos em desenvolvimento para a aplicação, assim como sua respectivas datas de lançamento";
        return instructionText;
    }
}
