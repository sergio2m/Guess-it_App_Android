package es.mentor.ProyectoFinal;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ActivityResultado extends Activity implements OnClickListener
{
	//variables
	String resultado;
	String palabra;
	//variables de sonido
	SoundManager sound;
	int ganar;
	int perder;
	int eleccion;
	//musica de fondo
	MediaPlayer mediaPlayer;

	//view
	TextView tvGanaPierde;
	TextView tvSolución;
	TextView tvAutor;
	ImageView imgGanaPierde;
	Button btJugar;
	Button btSalir;


	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_resultado);

		// Creamos una instancia de SoundManager
		sound = new SoundManager(getApplicationContext());
		// Set volume rocker mode to media volume
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Lee los sonidos que figuran en res/raw
		ganar = sound.load(R.raw.ganar);
		perder = sound.load(R.raw.perder);
		eleccion = sound.load(R.raw.eleccion);

		//referencias a view
		tvGanaPierde = (TextView)findViewById(R.id.tvGanaPierde);
		tvSolución = (TextView)findViewById(R.id.tvSolucion);
		imgGanaPierde = (ImageView)findViewById(R.id.imgGanaPierde);
		tvAutor = (TextView)findViewById(R.id.tvAutor);
		btJugar = (Button)findViewById(R.id.BtJugar);
		btSalir = (Button)findViewById(R.id.BtSalir);

		//escuchadores
		btJugar.setOnClickListener(this);
		btSalir.setOnClickListener(this);

		//Recuperamos la información pasada en el intent
		Bundle bundle = this.getIntent().getExtras();
		//Construimos el mensaje a mostrar
		resultado = bundle.getString("RESULTADO");
		tvGanaPierde.setText(resultado);
		palabra = bundle.getString("PALABRA");

		//conposision de pantalla en funcion del resultado
		if ("gana".equals(resultado))
		{ 
			tvGanaPierde.setText(R.string.gana);
			imgGanaPierde.setImageResource(R.drawable.gana);
			//musica de fondo
			mediaPlayer = MediaPlayer.create(this,R.raw.ganar);
			mediaPlayer.setLooping(true);
			mediaPlayer.setVolume(100,100);
			mediaPlayer.start();

		}
		if ("pierde".equals(resultado))
		{
			tvGanaPierde.setText(R.string.pierde);
			imgGanaPierde.setImageResource(R.drawable.pierde);
			tvSolución.setText(palabra);
			//musica de fondo
			mediaPlayer = MediaPlayer.create(this,R.raw.perder);
			mediaPlayer.setLooping(true);
			mediaPlayer.setVolume(100,100);
			mediaPlayer.start();
		}

		//cambio de fuente
		Typeface fuente = Typeface.createFromAsset(getAssets(),"orange juice 2.0.ttf");
		tvGanaPierde.setTypeface(fuente);
		fuente = Typeface.createFromAsset(getAssets(),"Miss Smarty Pants.ttf");
		tvSolución.setTypeface(fuente);
		tvAutor.setTypeface(fuente);
		btJugar.setTypeface(fuente);
		btSalir.setTypeface(fuente);
	}

	@Override
	public void onClick(View v) 
	{
		sound.play(eleccion);

		switch (v.getId()) {
		case R.id.BtJugar:
			//Creamos el Intent
			Intent intent = new Intent(ActivityResultado.this, ActivityInicial.class);
			//Creamos la información a pasar entre actividades
			Bundle resultado = new Bundle();
			startActivity(intent);
			mediaPlayer.stop();
			this.finish();
			break;

		case R.id.BtSalir:
			mediaPlayer.stop();
			this.finish();
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
			mediaPlayer.stop();
			this.finish();
		}
		return false;
	}
}
