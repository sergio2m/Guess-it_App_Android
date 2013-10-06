package es.mentor.ProyectoFinal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityJuego extends Activity implements OnClickListener
{
	//variables
	//array de animales
	private ArrayList<String> animales = new ArrayList<String>();
	private ArrayList<String> animals = new ArrayList<String>();
	//array de numeros
	private ArrayList<String> numeros = new ArrayList<String>();
	private ArrayList<String> numbers = new ArrayList<String>();
	//array de colores
	private ArrayList<String> colores = new ArrayList<String>();
	private ArrayList<String> colors = new ArrayList<String>();
	//array de naturaleza
	private ArrayList<String> naturaleza = new ArrayList<String>();
	private ArrayList<String> nature = new ArrayList<String>();
	//array de frutas
	private ArrayList<String> frutas = new ArrayList<String>();
	private ArrayList<String> fruits = new ArrayList<String>();
	//array de cuerpo humano
	private ArrayList<String> cuerpoHumano = new ArrayList<String>();
	private ArrayList<String> body = new ArrayList<String>();
	//array de vestuario
	private ArrayList<String> vestuario = new ArrayList<String>();
	private ArrayList<String> wardrobe = new ArrayList<String>();
	//array de 
	private ArrayList<String> hogar = new ArrayList<String>();
	private ArrayList<String> home = new ArrayList<String>();
	//array de escuela
	private ArrayList<String> escuela = new ArrayList<String>();
	private ArrayList<String> school = new ArrayList<String>();
	//contador de errores
	int contadorErrores;
	//palabra oculta en ingles
	//controla si gana la partida
	int controlGanar;
	//variable donde se almacena el numero del tema elegido. El primero corresponde al 0
	int tema;
	int aleatorio;
	int numeroAleatorio;
	int img1;
	int img2;
	int img3;
	int img4;
	int img5;
	int img6;
	int img7;
	Random rnd;
	String palabraSpainRandon = "";
	String palabraEnglishRandonVisible = "";
	String palabraEnglishRandonOculta = "";
	String palabraEnglishAuxiliar = "";
	//control del resultado final del juego
	String resultadoJuego;
	//controla si existe alguna letra en la palabra o no
	boolean acierto = false;
	char letra;
	//variables de sonido
	SoundManager sound;
	int sonidoAcertar;
	int sonidoErrar;
	int ganar;
	int perder;

	//Base de datos
	PalabrasSQLiteHelper palabrasBD;
	SQLiteDatabase db;

	//view
	TextView tvTematica;
	TextView tvPalabraSpain;
	TextView tvPalabraEnglish;
	TextView tvAutor;
	LinearLayout layoutPantallaJuego;
	ImageView imgTema;
	Button btA;
	Button btB;
	Button btC;
	Button btD;
	Button btE;
	Button btF;
	Button btG;
	Button btH;
	Button btI;
	Button btJ;
	Button btK;
	Button btL;
	Button btM;
	Button btN;
	Button btNN;
	Button btO;
	Button btP;
	Button btQ;
	Button btR;
	Button btS;
	Button btT;
	Button btU;
	Button btV;
	Button btW;
	Button btX;
	Button btY;
	Button btZ;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_juego);

		//Recuperamos la información pasada en el intent
		Bundle bundle = this.getIntent().getExtras();
		//Construimos el mensaje a mostrar
		tema = bundle.getInt("TEMA");

		// Creamos una instancia de SoundManager
		sound = new SoundManager(getApplicationContext());
		// Set volume rocker mode to media volume
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Lee los sonidos que figuran en res/raw
		sonidoAcertar = sound.load(R.raw.acierto);
		sonidoErrar = sound.load(R.raw.error);
		ganar = sound.load(R.raw.ganar);
		perder = sound.load(R.raw.perder);

		//referencia a view
		tvPalabraSpain = (TextView) findViewById(R.id.tvPalabraSpain);
		tvPalabraEnglish = (TextView) findViewById(R.id.tvPalabraEnglish);
		tvTematica = (TextView) findViewById(R.id.tvTematica);
		tvAutor = (TextView)findViewById(R.id.tvAutor);
		layoutPantallaJuego = (LinearLayout)findViewById(R.id.layoutPantallaJuego);
		imgTema = (ImageView)findViewById(R.id.imgTema);
		btA = (Button)findViewById(R.id.btA);
		btB = (Button)findViewById(R.id.btB);
		btC = (Button)findViewById(R.id.btC);
		btD = (Button)findViewById(R.id.btD);
		btE = (Button)findViewById(R.id.btE);
		btF = (Button)findViewById(R.id.btF);
		btG = (Button)findViewById(R.id.btG);
		btH = (Button)findViewById(R.id.btH);
		btI = (Button)findViewById(R.id.btI);
		btJ = (Button)findViewById(R.id.btJ);
		btK = (Button)findViewById(R.id.btK);
		btL = (Button)findViewById(R.id.btL);
		btM = (Button)findViewById(R.id.btM);
		btN = (Button)findViewById(R.id.btN);
		btNN = (Button)findViewById(R.id.btNN);
		btO = (Button)findViewById(R.id.btO);
		btP = (Button)findViewById(R.id.btP);
		btQ = (Button)findViewById(R.id.btQ);
		btR = (Button)findViewById(R.id.btR);
		btS = (Button)findViewById(R.id.btS);
		btT = (Button)findViewById(R.id.btT);
		btU = (Button)findViewById(R.id.btU);
		btV = (Button)findViewById(R.id.btV);
		btW = (Button)findViewById(R.id.btW);
		btX = (Button)findViewById(R.id.btX);
		btY = (Button)findViewById(R.id.btY);
		btZ = (Button)findViewById(R.id.btZ);
		
		//img 
		img1 = R.drawable.uno;
		img2 = R.drawable.dos;
		img3 = R.drawable.tres;
		img4 = R.drawable.cuatro;
		img5 = R.drawable.cinco;
		img6 = R.drawable.seis;
		img7 = R.drawable.siete;

		//escuchadores
		btA.setOnClickListener(this);
		btB.setOnClickListener(this);
		btC.setOnClickListener(this);
		btD.setOnClickListener(this);
		btE.setOnClickListener(this);
		btF.setOnClickListener(this);
		btG.setOnClickListener(this);
		btH.setOnClickListener(this);
		btI.setOnClickListener(this);
		btJ.setOnClickListener(this);
		btK.setOnClickListener(this);
		btL.setOnClickListener(this);
		btM.setOnClickListener(this);
		btN.setOnClickListener(this);
		btNN.setOnClickListener(this);
		btO.setOnClickListener(this);
		btP.setOnClickListener(this);
		btQ.setOnClickListener(this);
		btR.setOnClickListener(this);
		btS.setOnClickListener(this);
		btT.setOnClickListener(this);
		btU.setOnClickListener(this);
		btV.setOnClickListener(this);
		btW.setOnClickListener(this);
		btX.setOnClickListener(this);
		btY.setOnClickListener(this);
		btZ.setOnClickListener(this);

		//Abrimos la base de datos 'DBPalabras.db'
		palabrasBD = new PalabrasSQLiteHelper(this, "BDPalabras.db", null, 1);
		// Modo escritura
		db = palabrasBD.getWritableDatabase();

		//Si se ha abierto correctamente la base de datos entonces cargamos algunos registros...
		if(db != null)
		{
			String [] tablasSpainEnglish = new String[]{"animales","animals","numeros","numbers","colores","colors","naturaleza","nature","frutas","fruits"
					,"cuerpoHumano","body","vestuario","wardrobe","hogar","home","escuela","school"};
			ArrayList[] tablas = new ArrayList[]{animales,animals,numeros,numbers,colores,colors,naturaleza,nature,frutas,fruits,cuerpoHumano,body,vestuario,wardrobe,hogar,home,escuela,school};
			for(int i = 0; i <tablasSpainEnglish.length;i++)
			{
				Cursor c = db.rawQuery("SELECT nombre FROM "+ tablasSpainEnglish[i] +"",null); 		
				if (c.moveToFirst()) 
				{
					int contador = 0;
					//Recorremos el cursor hasta que no haya más registros
					tablas[i].clear();
					do {
						tablas[i].add(c.getString(0));
						contador++;
					} while(c.moveToNext());
				}
			}
		}

		//inicializacion del contador
		contadorErrores = 0;

		//cambio de fuente
		Typeface fuente = Typeface.createFromAsset(getAssets(),"orange juice 2.0.ttf");
		tvTematica.setTypeface(fuente);
		fuente = Typeface.createFromAsset(getAssets(),"Miss Smarty Pants.ttf");
		tvPalabraSpain.setTypeface(fuente);
		tvPalabraEnglish.setTypeface(fuente);		
		btA.setTypeface(fuente);
		btB.setTypeface(fuente);
		btC.setTypeface(fuente);
		btD.setTypeface(fuente);
		btE.setTypeface(fuente);
		btF.setTypeface(fuente);
		btG.setTypeface(fuente);
		btH.setTypeface(fuente);
		btI.setTypeface(fuente);
		btJ.setTypeface(fuente);
		btK.setTypeface(fuente);
		btL.setTypeface(fuente);
		btM.setTypeface(fuente);
		btN.setTypeface(fuente);
		btNN.setTypeface(fuente);
		btO.setTypeface(fuente);
		btP.setTypeface(fuente);
		btQ.setTypeface(fuente);
		btR.setTypeface(fuente);
		btS.setTypeface(fuente);
		btT.setTypeface(fuente);
		btU.setTypeface(fuente);
		btV.setTypeface(fuente);
		btW.setTypeface(fuente);
		btX.setTypeface(fuente);
		btY.setTypeface(fuente);
		btZ.setTypeface(fuente);
		tvAutor.setTypeface(fuente);

		switch (tema)
		{
		case 0:
			numeroAleatorio = numeroAleatorio(animales.size());
			componerPantalla(animales.get(numeroAleatorio).toString(),animals.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.animales);
			break;
		case 1:
			numeroAleatorio = numeroAleatorio(colores.size());
			componerPantalla(colores.get(numeroAleatorio).toString(),colors.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.colores);
			break;
		case 2:
			numeroAleatorio = numeroAleatorio(numeros.size());
			componerPantalla(numeros.get(numeroAleatorio).toString(),numbers.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.numeros);
			break;
		case 3:
			numeroAleatorio = numeroAleatorio(naturaleza.size());
			componerPantalla(naturaleza.get(numeroAleatorio).toString(),nature.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.naturaleza);
			break;
		case 4:
			numeroAleatorio = numeroAleatorio(frutas.size());
			componerPantalla(frutas.get(numeroAleatorio).toString(),fruits.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.frutas);
			break;
		case 5:
			numeroAleatorio = numeroAleatorio(cuerpoHumano.size());
			componerPantalla(cuerpoHumano.get(numeroAleatorio).toString(),body.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.cuerpoHumano);
			break;
		case 6:
			numeroAleatorio = numeroAleatorio(vestuario.size());
			componerPantalla(vestuario.get(numeroAleatorio).toString(),wardrobe.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.vestuario);
			break;
		case 7:
			numeroAleatorio = numeroAleatorio(hogar.size());
			componerPantalla(hogar.get(numeroAleatorio).toString(),home.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.hogar);
			break;
		case 8:
			numeroAleatorio = numeroAleatorio(escuela.size());
			componerPantalla(escuela.get(numeroAleatorio).toString(),school.get(numeroAleatorio).toString(),R.drawable.fondo,R.string.escolar);
			break;
		default:
			break;
		}
	}

	//metodo que compone la pantalla de juego en funcion del tema seleccionado
	private void componerPantalla(String spain,String english,int fondoPantalla,int imagenTema)
	{		
		palabraSpainRandon = spain;
		palabraEnglishRandonOculta = english;
		//control del resultado final del juego
		controlGanar = palabraEnglishRandonOculta.length();
		//en tvpalabraSpain se pone el nombre del animal elegido
		tvPalabraSpain.setText(palabraSpainRandon);
		//en tvpalabraEnglish tanta rayitas como letras tenga la palabra en ingles
		for (int i = 0; i < english.length(); i++) 
		{
			palabraEnglishRandonVisible+="-";
		}
		tvPalabraEnglish.setText(palabraEnglishRandonVisible);
		//se pone el fondo de pantalla de animales
		layoutPantallaJuego.setBackgroundResource(fondoPantalla);
		//se pone el nombre del tema
		tvTematica.setText(imagenTema);
	}

	//metodo que genera un numero aleatorio del 0 al 9
	public int numeroAleatorio(int numero)
	{
		rnd = new Random();
		aleatorio = (int)((rnd.nextDouble())*numero);
		return aleatorio;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId()) 
		{
		case (R.id.btA):
			comprobarLetra(btA);
		break;
		case (R.id.btB):
			comprobarLetra(btB);
		break;
		case (R.id.btC):
			comprobarLetra(btC);
		break;
		case (R.id.btD):
			comprobarLetra(btD);
		break;
		case (R.id.btE):
			comprobarLetra(btE);
		break;
		case (R.id.btF):
			comprobarLetra(btF);
		break;
		case (R.id.btG):
			comprobarLetra(btG);
		break;
		case (R.id.btH):
			comprobarLetra(btH);
		break;
		case (R.id.btI):
			comprobarLetra(btI);
		break;
		case (R.id.btJ):
			comprobarLetra(btJ);
		break;
		case (R.id.btK):
			comprobarLetra(btK);
		break;
		case (R.id.btL):
			comprobarLetra(btL);
		break;
		case (R.id.btM):
			comprobarLetra(btM);
		break;
		case (R.id.btN):
			comprobarLetra(btN);
		break;
		case (R.id.btNN):
			comprobarLetra(btNN);
		break;
		case (R.id.btO):
			comprobarLetra(btO);
		break;
		case (R.id.btP):
			comprobarLetra(btP);
		break;
		case (R.id.btQ):
			comprobarLetra(btQ);
		break;
		case (R.id.btR):
			comprobarLetra(btR);
		break;
		case (R.id.btS):
			comprobarLetra(btS);
		break;
		case (R.id.btT):
			comprobarLetra(btT);
		break;
		case (R.id.btU):
			comprobarLetra(btU);
		break;
		case (R.id.btV):
			comprobarLetra(btV);
		break;
		case (R.id.btW):
			comprobarLetra(btW);
		break;
		case (R.id.btX):
			comprobarLetra(btX);
		break;
		case (R.id.btY):
			comprobarLetra(btY);
		break;
		case (R.id.btZ):
			comprobarLetra(btZ);
		break;
		default:
			break;
		}
	}

	//metodo que comprueba si la letra existe en la palabra
	public void comprobarLetra(Button boton)
	{
		letra = boton.getText().toString().charAt(0);
		palabraEnglishAuxiliar="";
		acierto = false;
		for (int i = 0; i < tvPalabraEnglish.length(); i++)
		{
			if (((palabraEnglishRandonOculta.charAt(i))==letra)&&(contadorErrores<8))
			{	
				//sonido de acierto
				sound.play(sonidoAcertar);
				palabraEnglishAuxiliar+=letra;
				boton.setEnabled(false);
				boton.setTextColor(Color.BLUE);
				acierto = true;
				controlGanar--;
			}
			else
			{
				palabraEnglishAuxiliar+=palabraEnglishRandonVisible.charAt(i);
			}
		}
		palabraEnglishRandonVisible=palabraEnglishAuxiliar;
		tvPalabraEnglish.setText(palabraEnglishRandonVisible);

		if(!acierto)
		{
			//se introduce una imagen en funcion del error
			contadorErrores++;
			insertarImagen();
			sound.play(sonidoErrar);
		}
		if(controlGanar==0)
		{
			resultadoJuego = "gana";
		}	
		if(controlGanar==0 || contadorErrores==8)
		{
			//Creamos el Intent
			Intent intent = new Intent(ActivityJuego.this, ActivityResultado.class);
			//Creamos la información a pasar entre actividades
			Bundle resultado = new Bundle();
			resultado.putString("RESULTADO", resultadoJuego);
			resultado.putString("PALABRA", palabraEnglishRandonOculta);
			//Añadimos la información al intent
			intent.putExtras(resultado);
			//Iniciamos la nueva actividad
			startActivity(intent);
			this.finish();
		}
	}

	//metodo interno del inserta imagen2
	private void insertarImagen()
	{
		switch (contadorErrores) 
		{
		case 1:
			imgTema.setImageResource(img7);
			break;
		case 2:
			imgTema.setImageResource(img6);
			break;
		case 3:
			imgTema.setImageResource(img5);
			break;
		case 4:
			imgTema.setImageResource(img4);
			break;
		case 5:
			imgTema.setImageResource(img3);
			break;
		case 6:
			imgTema.setImageResource(img2);
			break;
		case 7:
			imgTema.setImageResource(img1);
			break;
		case 8:
			resultadoJuego = "pierde";
			break;
		default:
			break;
		}	
	}

	//gestion boton atras
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) 
		{
			//Creamos el Intent
			Intent intent = new Intent(ActivityJuego.this, ActivityInicial.class);
			//Creamos la información a pasar entre actividades
			Bundle resultado = new Bundle();
			startActivity(intent);
			this.finish();
		}
		return false;
	}
}
