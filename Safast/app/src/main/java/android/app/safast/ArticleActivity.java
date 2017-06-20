package android.app.safast;

import android.app.safast.controller.DatabaseController;
import android.app.safast.domain.Wiki;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class ArticleActivity extends ActionBarActivity {

    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_layout);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("SAFAST");
        mToolbar.setNavigationIcon(R.drawable.ic_navigator_36dp);
        mToolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(mToolbar);


        ImageView ivPhoto = (ImageView) findViewById(R.id.iv_photo);
        TextView tvCategory = (TextView) findViewById(R.id.tv_category);
        TextView tvDescription = (TextView) findViewById(R.id.tv_description);
        TextView tvFonte = (TextView) findViewById(R.id.tv_fonte);


        DatabaseController db = new DatabaseController(ArticleActivity.this);

        Intent myIntent = getIntent();
        String category = myIntent.getStringExtra("key");

        if(category.equals("Alagamento")){

            Wiki wiki = db.getWikiById(1);
            wiki.setDescription(getDescription(1));
            ivPhoto.setImageResource(wiki.getPhoto());
            tvCategory.setText(wiki.getCategory());
            tvDescription.setText(wiki.getDescription());
            tvFonte.setText(getFonte(1));
        }else if(category.equals("Incêndios")){


            Wiki wiki = db.getWikiById(2);
            wiki.setDescription(getDescription(2));
            ivPhoto.setImageResource(wiki.getPhoto());
            tvCategory.setText(wiki.getCategory());
            tvDescription.setText(wiki.getDescription());
            tvFonte.setText(getFonte(2));
        }else if(category.equals("Terremotos")){


            Wiki wiki = db.getWikiById(3);
            wiki.setDescription(getDescription(3));
            ivPhoto.setImageResource(wiki.getPhoto());
            tvCategory.setText(wiki.getCategory());
            tvDescription.setText(wiki.getDescription());
            tvFonte.setText(getFonte(3));
        }else if(category.equals("Trânsito")){


            Wiki wiki = db.getWikiById(4);
            wiki.setDescription(getDescription(4));
            ivPhoto.setImageResource(wiki.getPhoto());
            tvCategory.setText(wiki.getCategory());
            tvDescription.setText(wiki.getDescription());
            tvFonte.setText(getFonte(4));
        }


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

    public  String getDescription(int i) {
        String d = "Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(";
        switch (i) {
            case 1:
                d = "        Inundações repentinas, bruscas ou enxurradas, que ocorrem em regiões de relevo acentuado, montanhoso, como na região Sul do País. Acontecem pela presença de grande quantidade de água num curto espaço de tempo. " +
                        "\n" +
                        "        São frequentes em rios de zonas montanhosas com bastante inclinação, vales profundos e muitas vezes as águas de chuva arrastam terra sem vegetação devido aos deslizamentos nas margens dos rios. A grande quantidade de água e materiais arrastados representam, à medida que escoam, grande poder destruidor.\n" +
                        "\n" +
                        "\n" +
                        "SE PREVININDO:\n" +
                        "\n" +
                        "        • Não deixe crianças trancadas em casa sozinhas;\n" +
                        "\n" +
                        "        • Mantenha sempre pronta água potável, roupa e remédios, caso tenha que sair rápido da sua casa;\n" +
                        "\n" +
                        "        • Conheça o Centro de Saúde mais próximo da sua casa, pode ser necessário;\n" +
                        "\n" +
                        "        • Avise aos seus vizinhos sobre o perigo, no caso de casas construídas em áreas de risco de deslizamento. Avise, também, imediatamente ao Corpo de Bombeiros e à Defesa Civil;\n" +
                        "\n" +
                        "        • Convença as pessoas que moram nas áreas de risco a saírem de casa durante as chuvas;\n" +
                        "\n" +
                        "        • Avise imediatamente ao Corpo de Bombeiros ou Defesa Civil sobre áreas afetadas pela inundação;\n" +
                        "\n" +
                        "        • Jogue o lixo no lixo. Não jogue lixo em terrenos baldios ou na rua. Não jogue papel e lixo na rua;\n" +
                        "\n" +
                        "        • Não jogue sedimentos, troncos, móveis, materiais e lixo que impedem o curso do rio, provocando transbordamentos;\n" +
                        "\n" +
                        "        • Não jogue lixo nos bueiros (boca de lobo), para não obstruir o escoamento da água;\n" +
                        "\n" +
                        "        • Limpe o telhado e canaletas de águas para evitar entupimentos;\n" +
                        "\n" +
                        "\n" +
                        "ENFRENTANDO A INUNDAÇÃO:\n" +
                        "\n" +
                        "        • Tenha um lugar previsto, seguro, onde você e sua família possam se alojar no caso de uma inundação;\n" +
                        "\n" +
                        "        • Desconecte os aparelhos elétricos da corrente elétrica para evitar curtos circuitos nas tomadas;\n" +
                        "\n" +
                        "        • Não construa próximo a córregos que possam inundar;\n" +
                        "\n" +
                        "        • Não construa em cima de barrancos que possam deslizar, carregando sua casa;\n" +
                        "\n" +
                        "        • Não construa embaixo de barrancos que possam deslizar, soterrando sua casa;\n" +
                        "\n" +
                        "        • Feche o registro de entrada d`água;\n" +
                        "\n" +
                        "        • Não use equipamentos elétricos que tenham sido molhados ou em locais inundados, pois há risco de choque elétrico e curto-circuito\n" +
                        "\n" +
                        "        • Retire todo o lixo e leve para áreas não sujeitas a inundações;\n" +
                        "\n" +
                        "        • Feche bem as portas e janelas.\n" +
                        "\n" +
                        "\n" +
                        "APÓS A INUNDAÇÃO:\n" +
                        "\n" +
                        "        • Enterre animais mortos e limpe os escombros e lama deixados pela inundação;\n" +
                        "\n" +
                        "        • Lave e desinfete os objetos que tiveram contato com as águas da enchente;\n" +
                        "\n" +
                        "        • Retire todo o lixo da casa e do quintal e o coloque para a limpeza pública;\n" +
                        "\n" +
                        "        • Veja se sua casa não corre o risco de desabar;\n" +
                        "\n" +
                        "        • Raspe toda a lama e o lixo do chão, das paredes, dos móveis e utensílios;\n" +
                        "\n" +
                        "        • Cuidado com aranhas, cobras e ratos, ao movimentar objetos, móveis e utensílios.\n" +
                        "\n" +
                        "        • Tenha cuidado com cobras e outros animais venenosos, pois eles procuram refúgio em lugares secos.";
                break;
            case 2:
                d = "        Prevenir incêndios é tão importante quanto saber apagá-los ou mesmo saber como agir corretamente no momento em que eles ocorrem. " +
                        "\n" +
                        "        Inícios de incêndios e outros sinistros de menor vulto podem deixar de transformar-se em tragédia, se forem evitados e controlados com segurança e tranquilidade por pessoas devidamente treinadas. Na maioria das vezes, o pânico dos que tentam se salvar faz mais vítimas que o próprio acidente." +
                        "\n" +
                        "\n" +
                        "\n" +
                        "SE PREVININDO:\n" +
                        "\n" +
                        "        • Mantenha sempre à vista o telefone de emergência do Corpo de Bombeiros( encontrado neste aplicativo na aba de chamadas);\n" +
                        "\n" +
                        "        • Conserve sempre as caixas de incêndios em perfeita condições de uso e somente as utilize em caso de incêndio;\n" +
                        "\n" +
                        "        • Os extintores devem estar fixados sempre em locais de fácil acesso, devidamente carregados e revisados (periodicamente);\n" +
                        "\n" +
                        "        • Revisar periodicamente toda a instalação elétrica do prédio, procurando inclusive constatar também a existência de possíveis vazamentos de gases;\n" +
                        "\n" +
                        "        • Evitar o vazamento de líquidos inflamáveis;\n" +
                        "\n" +
                        "        •  Evitar a falta de ventilação;\n" +
                        "\n" +
                        "        • Não colocar trancas nas portas de halls, elevadores, porta corta-fogo ou outras saídas para áreas livres. Nem obstruí-las com materiais ou equipamentos;\n" +
                        "\n" +
                        "        • Tomar cuidado com cera, utilizada nos piso,s quando dissolvida. Não deixar estopas ou flanelas embebidas em óleos ou graxas em locais inadequados;\n" +
                        "\n" +
                        "        • Alertar sobre o ato de fumar em locais proibidos (como elevadores) e sobre o cuidado de atirar fósforos e pontas de cigarros acessos em qualquer lugar;\n" +
                        "\n" +
                        "        • Aconselhar os trabalhadores para que verifiquem antes de sair de seus locais de trabalho, ao término da lornada de trabalho, se desligaram todos os aparelhos elétricos, como estufas, ar condicionado, exaustores, dentre outros;\n" +
                        "\n" +
                        "        • Em caso de incêndio, informar o Corpo de Bombeiros o mais rápido possível: a ocorrência, o acesso mais fácil para a chegada ao local e o número de pessoas acidentadas, inclusive nas proximidades;\n" +
                        "\n" +
                        "        • Evitar aglomerações para não dificultar a ação do socorro e manter a área junto aos hidrantes livre para manobras e estacionamento de viaturas;\n" +
                        "\n" +
                        "        • Nunca utilizar os elevadores no momento do incêndio;" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "DURANTE O INCÊNDIO:\n" +
                        "\n" +
                        "        • Haja rapidamente. Procure identificar a saída mais próxima a você e dirija-se a ela. Tenha certeza de que é uma saída, verificando a sinalização acima da porta;\n" +
                        "\n" +
                        "        • Acione o alarme de incêndio(se houver);\n" +
                        "\n" +
                        "        • Comunique a todos o que esta acontecendo, especialmente a brigada de incêndio;\n" +
                        "\n" +
                        "        • Haverá fumaça tóxica no ar, que pode fazê-lo desmaiar rapidamente. Procure proteger o nariz com uma camisa, pano, ou máscara – se tiver – amarrados firmemente ao rosto. Você ganhará instantes que poderão ser valiosos para a fuga.\n" +
                        "\n" +
                        "        • Caso esteja no banheiro e escute o alarme de incêndio ou uma gritaria de pânico, pare imediatamente o que estiver fazendo e deixe o local. Mesmo que tenha que se afastar para ninguém vê-lo (ou sentir o cheiro), o mais importante é sair com vida;\n" +
                        "\n" +
                        "        • Se for mulher e estiver de salto, tire-o e jogue num canto para não atrapalhar a passagem dos outros em fuga;\n" +
                        "\n" +
                        "        • Se não conseguir sair rapidamente, procure se molhar o máximo que puder. A camada de água que se formar oferece uma pequena proteção contra o fogo, mas que pode ajudá-lo a aguentar o calor;\n" +
                        "\n" +
                        "        • Tome cuidado para não cair ou causar a queda dos outros, pois pessoas que caem em passagens com grande fluxo de pessoas correm o risco de serem pisoteadas;\n" +
                        "\n" +
                        "        • Mantenha-se o mais próximo do chão. Os gases tóxicos tendem a ocupar a parte superior do ambiente;\n" +
                        "\n" +
                        "        • Não siga as outras pessoas. Siga a sinalização e sua observação do local. Muitas pessoas correm para a primeira porta que veem, outras as seguem achando que estão indo para a saída e impedem as primeiras que se enganaram de voltar pelo mesmo caminho. Este tempo perdido pode fazer diferença entre a vida e a morte;\n" +
                        "\n" +
                        "        • Se for abrir portas usando maçanetas, utilize um pano ou camisa molhados para não queimar a mão. Certifique-se que não há fogo do outro lado, sentindo a irradiação de calor. Se abrir a porta e o outro lado estiver com fogo, ele pode ir em sua direção;\n" +
                        "\n" +
                        "        • No caso de festas, bares e baladas, procure consumir bebidas alcoólicas até um nível em que você esteja em condições de se mover e deixar o local rapidamente." +
                        "\n" +
                        "\n" +
                        "\n" +
                        "APÓS O INCÊNDIO:\n" +
                        "\n" +
                        "        • Certificar-se de que o fogo foi extinto;\n" +
                        "\n" +
                        "        • Reconhecer as áreas que as autoridades de incêndio estabeleceram como zonas de segurança;\n" +
                        "\n" +
                        "        • Saiba que, se uma casa ou edifício foi muito danificada, você pode não ter permissão para entrar em seu interior.\n" +
                        "\n" +
                        "        • Se você for um inquilino, entre em contato com o proprietário ou locador e/ou sua companhia de seguros.\n" +
                        "\n" +
                        "        • Você deve gastar um pouco de tempo para avaliar se você acha que pode executar a limpeza, ou se você vai precisar de produtos ou auxílio profissional. Pode ser útil discutir este assunto com sua companhia de seguros.\n";
                break;

            case 3:
            d =     "        Terremotos, também chamados de abalos sísmicos, são tremores passageiros que ocorrem na superfície terrestre. Esse fenômeno natural pode ser desencadeado por fatores como atividade vulcânica, falhas geológicas e, principalmente, pelo encontro de diferentes placas tectônicas." +
                    "\n" +
                    "        Não possuirmos abalos sísmicos relevantes em solo brasileiro, o quê por um lado nos livra de tais catastrofes, também pode gerar uma ignorância indesejada. Ao viajar para países como Indonésia, Chile, Estados Unidos, Japão, entre outros, um brasileiro pode ter a infelicidade de se depara com um terremoto. Habitantes deste locais aprendem seja na escola ou na TV a como se portar durante um abalo sísmico, e por isso é necessário que nós brasileiros também aprendermos, antes que seja tarde demais." +
                    "\n" +
                    "\n" +
                    "\n" +
                    "SE PREVININDO:\n" +
                    "\n" +
                    "        • Prender os móveis como o armário, estantes de livros e gabinetes da cozinha fixando-os na parede ou no teto;\n" +
                    "\n" +
                    "        • De 30% a 50% dos ferimentos causados durante os terremotos são por causa de objetos que caem. Procure dispor os móveis e utensílios domésticos de forma segura dentro da sua casa. Evite colocar coisas pesadas como televisor e microondas em lugares muito altos, pois correm o risco de ferir as cabeças;\n" +
                    "\n" +
                    "        • Desobstruir o caminho da saída, evitar colocar utensílios e móveis no corredor e limpar a terra que esteja bloqueando a porta para poder sair rápido em caso de emergência;\n" +
                    "\n" +
                    "        • Colar filmes protetores nos vidros, para não estilhaçar;\n" +
                    "\n" +
                    "        • Deixar preparado o kit de emergência com objetos essenciais como: Dinheiro, documentos, caderneta bancária, carimbo, lanterna, pilhas, água, luvas, velas, isqueiro, estojo de primeiros socorros, rádio, alimentos não perecíveis;\n" +
                    "\n" +
                    "        • Procurar se informar antecipadamente do local de refúgio, que geralmente são  escolas e Associaçãos do Bairro;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "DURANTE O TERREMOTO:\n" +
                    "\n" +
                    "        • Ficar calmo e protejer-se debaixo de uma mesa ou escrivaninha resistente;\n" +
                    "\n" +
                    "        • Abrir uma porta ou janela, para garantir sua saída. Não correr desenfreadamente para a rua, aguardar o final dos tremores do terremoto, calçar os sapatos e sair cautelosamente;\n" +
                    "\n" +
                    "        • Sem precipitações, eliminar qualquer risco de incêndio, fechando todas as válvulas de gás e desligando os aparelhos a gás, aquecedores, todas as tomadas dos aparelhos elétrico e também a chave geral da rede elétrica;\n" +
                    "\n" +
                    "        • Evite passar por becos, ruas com muros, rochedos ou margens de rio e mar. Há o perigo dos blocos dos muros se partirem com o tremor, podendo ceder, e causar desmoramento;\n" +
                    "\n" +
                    "        • Não saia de carro, para evitar engarrafamento; que retardam a chegada de viaturas de emergência;\n" +
                    "\n" +
                    "        • Quem mora perto da praia deve buscar refúgio em terreno elevado, pois pode ocorrer marremoto;\n" +
                    "\n" +
                    "        • Não dê ouvido a boatos e procure sempre as informações corretas de rádio ou TV. Evite ligações desnecessárias e sem emergência, especialmente evite ficar ligando para o corpo de bombeiro para obter informações;\n" +
                    "\n" +
                    "        • Se tiver dirigindo, estacione imediatamente em local desimpedido, na margem da rua desligue o motor. Busque refúgio à pé.;\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "APÓS O TERREMOTO:\n" +
                    "\n" +
                    "        • Verificar a segurança da família e ficar atento para os abalos sísmicos secundários;\n" +
                    "\n" +
                    "        • Colaborar com as atividades de extinção de incêndio, regaste e salvamento de pessoas machucadas;\n" +
                    "\n" +
                    "        • Usar a água e comida estocada para emergência. Tomar cuidado com boatos e assegurar informações corretas;\n" +
                    "\n";
                    break;
            case 4:
            d =     "        Acidentes no trânsito, infelizmente, são muito comuns, seja uma batida leve ou uma em alta velocidade, ambas causam risco de morte. Tais acidentes ocorrem com ainda mais frequência em grandes cidades como São paulo, Belo Horizonte e Porto Alegre devido ao grande fluco de automóveis. Ainda mais importante do que saber o que fazer nesta situação, é saber o que você NÃO DEVE fazer." +
                    "\n" +
                    "        É necessário esclarecer que, se existir serviço de socorro especializado que possa chegar ao local, o leigo NÃO ESTÁ autorizado a executar certos precedimentos na vítima, pois a prestação inadequada de um socorro pode agravar as lesões ou até mesmo provacar outras lesões." +
                    "\n" +
                    "\n" +
                    "\n" +
                    "SE PREVININDO:\n" +
                    "\n" +
                    "        • Faça revisões regulares em seu automóvel;\n" +
                    "\n" +
                    "        • Mantenha a atenção no trânsito, evite quaisquer distrações como rádio, celular, TV, jogos, etc;\n" +
                    "\n" +
                    "        • O condutor deve estar apto a prever o que poderá acontecer, melhorando seu tempo de reação;\n" +
                    "\n" +
                    "        • Conheça ou estude previamente o local onde irá trafegar;\n" +
                    "\n" +
                    "        • SEMPRE utilize e peça para os passageiros utilizarem o cinto de segurança;\n" +
                    "\n" +
                    "        • Dirija sempre dentro da velocidade permitida, e não se preocupe em diminuir ainda mais a velocidade caso ache necessrio, todavia, cuide para não diminuir ao ponto de atrapalhar o tráfego de uma estrada movimentada;\n" +
                    "\n" +
                    "        • Ajuste os retrovisores e evite entrar no ponto cego do outro motorista;\n" +
                    "\n" +
                    "        • Mantenha uma distância segura dos outros automóveis;\n" +
                    "\n" +
                    "        • Evite ultrapassagens arriscadas;\n" +
                    "\n" +
                    "        • Ao pilotar uma motocicleta SEMPRE utilize capacete, assim como o passageiro;\n" +
                    "\n" +
                    "        • Entorpecentes como álcool e remédios podem tirar sua atenção e diminuir seus reflexos, jamais utilize-os junto a direção;\n" +
                    "\n" +
                    "        • Evite dirigir com sono, caso haja a necessidade, redobre a atenção e a distância para com os outros automóveis;\n" +
                    "\n" +
                    "        • SEMPRE de prioridade aos pedestres, caso estes já tenham começado a travessia;\n"+
                    "\n" +
                    "\n" +
                    "\n" +
                    "APÓS O ACIDENTE:\n" +
                    "\n" +
                    "        • Manter a calma é fundamental;\n" +
                    "\n" +
                    "        • Sinalizar o acidente, utilize o triângulo de segurançã, o pisca-alerta do veículo, galhos de árvore, pedaços de tecido, tudo que for usado deve ser FACILMENTE visualizado;\n" +
                    "\n" +
                    "        • Solicitar socorro;\n" +
                    "\n"+
                    "        • Controlar a situação, alguém precisará tomar a inciativa, indificar o tipo de acidente e os danos provocados. Um trabalho organizado irá facilitar o trabalho das autoridades;\n" +
                    "\n"+
                    "        • Caso a vítma esteja dentro do carro, NÃO a retire, apenas converse com ela e apenas retire o sinto de segurança caso esteja dificultando a respiração da vítima;\n" +
                    "\n"+
                    "        • Informar a vítima sobre o que esta acontecendo, ser solidário ao ouvir e aceitar as queixas da mesma;\n" +
                    "\n"+
                    "        • ANTES da chegada do socorro é necessário uma avaliação primária do leigo, observando possíveis fraturas, a respiração e o estado de consciência da vítima;\n" +
                    "\n"+
                    "        • Dependendo do tipo de acidente, é pode haver uma fratura na coluna, por isso a cabeça e o pescoço devem permanecer imobilizados;\n" +
                    "\n"+
                    "        • Em caso de fogo nas roupas, a vítima deve rolar no chão para abafar as chamas;\n" +
                    "\n"+
                    "        • Em casos de queimaduras químicas, é recomendáve NÃO lavar a região, por causa da reação da água com esses produtos;\n" +
                    "\n"+
                    "        • NÃO remover à vítima do local;\n" +
                    "\n"+
                    "        • NÃO dar líquidos à vítima;\n" +
                    "\n"+
                    "        • NÃO fazer torniquete para parar sangramentos;\n" +
                    "\n"+
                    "        • NÃO tentar colocar ossos quebrados no lugar;\n" +
                    "\n"+
                    "        • NÃO remova o capacete da vítima após cair de uma motocicleta, há perigo de piorar uma fratura na coluna;\n" +
                    "\n"+
                    "        • NÃO tente ser um super-herói, caso haja perigo na pista, incêndio, ou óleo despejado, não se aproxime do acidente;\n" +
                    "\n" ;
                    break;
        }

        return d;
    }

    public  String getFonte(int i) {
        String d = "Ops! Ocorreu algum erro! Desculpe pelo emprevisto :(";
        switch (i) {
            case 1:
                d =     "Defesa Civíl - Dicas que salvam\n"+
                        " \n" +
                        "Globo.com - Dicas de como agir";
                break;
            case 2:
                d =     "Papo de Homem - Como se comportar em situações de risco\n"+
                        "\n" +
                        "Bombeiros Emergência - Como Agir\n"+
                        " \n" +
                        "Valney Andrade - Treinamento, Concientização e Prevenção";
                break;

            case 3:
                d =     "Toyohashi City - Medidas De Prevenção Contra Desastres Naturais\n" +
                        "\n" +
                        "Aichi City - Prevenção contra terremotos";
                break;
            case 4:
                d =     "Detran - Consciência sobre rodas\n" +
                        "\n" +
                        "Agência de notícias do Paraná - Omissão de socorro\n" +
                        "\n" +
                        "FlatOut - Acidente de Trânsito: passo-a-passo";
                break;
        }
        return d;
    }

}
