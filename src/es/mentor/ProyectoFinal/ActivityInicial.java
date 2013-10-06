package es.mentor.ProyectoFinal;

import java.util.ArrayList;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityInicial extends Activity implements OnItemClickListener
{
	//variables
	String tabla = "";
	//array de animales
	private String animales[];
	private String animals[];
	//array de numeros
	private String numeros[];
	private String numbers[];
	//array de colores
	private String colores[];
	private String colors[];
	//array de naturaleza
	private String naturaleza[];
	private String nature[];
	//array de frutas
	private String frutas[];
	private String fruits[];
	//array de cuerpo humano
	private String cuerpoHumano[];
	private String body[];
	//array de vestuario
	private String vestuario[];
	private String wardrobe[];
	//array de 
	private String hogar[];
	private String home[];
	//array de escuela
	private String escuela[];
	private String school[];
	//array de temas
	String [] tablasSpain = new String[]{"animales","numeros","colores","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
	String [] tablasEnglish = new String[]{"animals","numbers","colors","nature","fruits","body","wardrobe","home","school"};

	//View
	private TextView tvTitulo;
	private TextView tvSubTitulo;
	private TextView tvAutor;
	private TextView tvInstrucciones;
	private TextView tvTablaSpainSeleccionada;
	private GridView gridOpciones;
	private EditText etTablaSpainInsertar;
	private EditText etTablaSpainEliminar;
	private EditText etEntradaPalabraSpain;
	private EditText etEntradaPalabraEnglish;
	private EditText etEliminarPalabraSpain;

	//Base de datos
	PalabrasSQLiteHelper palabrasBD;
	SQLiteDatabase db;
	Cursor c;

	//variables
	String tablaSpainInsertar;
	String tablaEnglishInsertar;
	String palabraSpainInsertar;
	String palabraEnglishInsertar;
	String tablaSpainEliminar;
	String tablaEnglishEliminar;
	String palabraSpainEliminar;
	String palabraEnglishEliminar;
	//variables de sonido
	SoundManager sound;
	int eleccion;
	int musicaInicio;
	//musica de fondo
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_inicial);
	

		//hacemos una primera carga de datos a las tablas
		//Abrimos la base de datos 'DBPalabras.db'
		palabrasBD = new PalabrasSQLiteHelper(this, "BDPalabras.db", null, 1);
		// Modo escritura
		db = palabrasBD.getWritableDatabase();
		String SQLSelect = "SELECT id FROM animales";
		c = db.rawQuery(SQLSelect, null);
		//comprobamos que no hay registros anteriores
		if (!c.moveToFirst())
		{
			// Hemos definido los datos en un fichero de recursos de la aplicación
			Resources res = getResources();
			int [] array = new int[]{R.array.animales,R.array.animals,R.array.numeros,R.array.numbers,R.array.colores,R.array.colors,R.array.naturaleza,R.array.nature
					,R.array.frutas,R.array.fruits,R.array.cuerpoHumano,R.array.body,R.array.vestuario,R.array.wardrobe,R.array.hogar,R.array.home,R.array.escuela,R.array.school};
			String[][] tablas = new String[][]{animales,animals,numeros,numbers,colores,colors,naturaleza,nature,frutas,fruits,cuerpoHumano,body,vestuario,wardrobe,hogar,home,escuela,school};
			for(int i = 0; i<tablas.length; i++)
			{
				tablas[i] = res.getStringArray(array[i]);
			}

			//Si se ha abierto correctamente la base de datos entonces cargamos algunos registros...
			if(db != null)
			{
				String SQLInsert;
				//Insertamos 10 palabras por tabla
				String [] tablasSpainEnglish = new String[]{"animales","animals","numeros","numbers","colores","colors","naturaleza","nature","frutas","fruits"
						,"cuerpoHumano","body","vestuario","wardrobe","hogar","home","escuela","school"};
				for(int i = 0; i < tablasSpainEnglish.length; i++)
				{
					for(int j = 0; j < tablas[i].length; j++)
					{
						SQLInsert="INSERT INTO "+ tablasSpainEnglish[i] +" (nombre) " + "VALUES ('" + tablas[i][j] +"')";
						//Insertamos los datos en la tabla animales
						db.execSQL(SQLInsert);
					}
				}
			}
		}

		// Creamos una instancia de SoundManager
		sound = new SoundManager(getApplicationContext());
		// Set volume rocker mode to media volume
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Lee los sonidos que figuran en res/raw
		eleccion = sound.load(R.raw.eleccion);
		//musica de fondo
		mediaPlayer = MediaPlayer.create(this,R.raw.musicainicio);
		mediaPlayer.setLooping(true);
		mediaPlayer.setVolume(100,100);
		mediaPlayer.start();

		//referencia a los view
		tvTitulo = (TextView)findViewById(R.id.tvTitulo);
		tvSubTitulo = (TextView)findViewById(R.id.tvSubtitulo);
		tvInstrucciones = (TextView)findViewById(R.id.tvInstrucciones);
		tvAutor = (TextView)findViewById(R.id.tvAutor);
		gridOpciones = (GridView) findViewById(R.id.gridOpciones);	

		//adaptador
		gridOpciones.setAdapter(new AdaptadorPersonalizado(this));

		//escuchador al gridView
		gridOpciones.setOnItemClickListener(this); 

		//cambio de fuente
		Typeface fuente = Typeface.createFromAsset(getAssets(),"orange juice 2.0.ttf");
		tvTitulo.setTypeface(fuente);
		fuente = Typeface.createFromAsset(getAssets(), "Miss Smarty Pants.ttf");
		tvSubTitulo.setTypeface(fuente);
		tvInstrucciones.setTypeface(fuente);
		tvAutor.setTypeface(fuente);
	}

	//evento al actuar sobre el gridLayout
	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	{
		sound.play(eleccion);
		mediaPlayer.stop();

		//Creamos el Intent
		Intent intent = new Intent(ActivityInicial.this, ActivityJuego.class);
		//Creamos la información a pasar entre actividades
		Bundle tema = new Bundle();
		tema.putInt("TEMA", position);
		//Añadimos la información al intent
		intent.putExtras(tema);
		//Iniciamos la nueva actividad
		startActivity(intent);
		//cerrar esta activity
		this.finish();   
	}

	//metodo de menu de administracion
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater(); 
		inflater.inflate(R.menu.main, menu); 
		return true; 
	}

	// Si el usuario selecciona una opción del menú mostramos la opción seleccionada en la etiqueta
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		final AlertDialog.Builder ventana;
		String infService; 
		LayoutInflater li;
		final View inflador;

		switch (item.getItemId())
		{
		case R.id.MenuInsertar:

			// Primero preparamos el interior de la ventana de diálogo inflando su fichero 	XML 
			infService = Context.LAYOUT_INFLATER_SERVICE; 
			li =(LayoutInflater)getApplicationContext().getSystemService(infService);
			// Inflamos el componente compuesto definido en el XML 
			inflador = li.inflate(R.layout.dialogo_entrada_palabras, null); 
			ventana = new AlertDialog.Builder(this); 
			ventana.setTitle("Indique las nuevas palabras"); 
			// Asignamos el contenido dentro del diálogo (el que hemos inflado previamente) 
			ventana.setView(inflador); 

			//listView
			ListView lvTemas = (ListView)inflador.findViewById(R.id.lvTemas);
			final String[] datos = new String[]{"animales","colores","numeros","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
			adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lvTemas.setAdapter(adaptador);

			//evento cuando seleccionamos una opcion de listview
			lvTemas.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id)
				{
					tablaSpainInsertar = datos[position];
					String tabla = datos[position];
					tvTablaSpainSeleccionada = (TextView)inflador.findViewById(R.id.tvTablaSpainSeleccionada);
					tvTablaSpainSeleccionada.setText(tabla);
				}
			});

			ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() 
			{ 
				public void onClick(DialogInterface dialog, int boton)
				{ 
					etEntradaPalabraSpain = (EditText)inflador.findViewById(R.id.etEntradaPalabraSpain);
					etEntradaPalabraEnglish = (EditText)inflador.findViewById(R.id.etEntradaPalabraEnglish);
					palabraSpainInsertar = etEntradaPalabraSpain.getText().toString();
					palabraEnglishInsertar = etEntradaPalabraEnglish.getText().toString();

					if(palabraSpainInsertar.equals("")|| palabraEnglishInsertar.equals("") || tablaSpainInsertar.equals(""))
					{
						Toast.makeText(getApplicationContext(), "Existe algún campo sin rellenar" , Toast.LENGTH_LONG).show();
					}
					else
					{
						db.execSQL("INSERT INTO "+tablaSpainInsertar+" (nombre) VALUES ('"+palabraSpainInsertar+"')");

						for(int i=0; i < tablasSpain.length; i++)
						{
							if(tablaSpainInsertar.equals(tablasSpain[i]))
							{
								tablaEnglishInsertar = tablasEnglish[i];
							}
						}

						db.execSQL("INSERT INTO "+tablaEnglishInsertar+" (nombre) VALUES ('" + palabraEnglishInsertar +"')");	
						Toast.makeText(getApplicationContext(), "Palabra agragada correctamente", 1).show(); 
					}
				} 

			}); 

			ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() 
			{ 
				public void onClick(DialogInterface dialog, int boton)
				{ 

				} 
			});
			ventana.show();
			return true;

		case R.id.MenuEliminar:
			
			//Creamos el Intent
			Intent intent = new Intent(ActivityInicial.this, ActivityBorrarPalabra.class);
			//Iniciamos la nueva actividad
			startActivity(intent);
						
			/*
			// Primero preparamos el interior de la ventana de diálogo inflando su fichero 	XML 
			infService = Context.LAYOUT_INFLATER_SERVICE; 
			li =(LayoutInflater)getApplicationContext().getSystemService(infService);
			// Inflamos el componente compuesto definido en el XML 
			inflador = li.inflate(R.layout.pantalla_eliminar_palabras, null); 
			ventana = new AlertDialog.Builder(this); 
			ventana.setTitle("Indique la palabra a eliminar"); 
			// Asignamos el contenido dentro del diálogo (el que hemos inflado previamente) 
			ventana.setView(inflador);
			
			
			
			
			
			//////////////////////////
			//listView
			lvTemas = (ListView)inflador.findViewById(R.id.lvTemas);
			final String[] datos1 = new String[]{"animales","colores","numeros","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
			ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos1);
			adaptador1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lvTemas.setAdapter(adaptador1);	
			
			//evento cuando seleccionamos una opcion de listview
			final ArrayList<String> palabras = new ArrayList<String>();
			lvTemas.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id)
				{
					//tablaSpainInsertar = datos1[position];
					String tabla = datos1[position];
					
				
					Cursor c = db.rawQuery("SELECT nombre FROM animales",null); 
					//Nos aseguramos de que existe al menos un registro
					if (c.moveToFirst()) 
					{
						int contador = 0;
						//Recorremos el cursor hasta que no haya más registros		
						do {
							palabras.add(c.getString(0).toString());
							contador++;
						} while(c.moveToNext());
					}
					
				
					
					
					
					
				}
			});
			///////////////////////
			
			
			
			
			
			Cursor c = db.rawQuery("SELECT nombre FROM animales",null); 
			//Nos aseguramos de que existe al menos un registro
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			
			
			
			

			//final ArrayList<String> palabras = new ArrayList<String>();

			//consulta bd tabla animales
			/*Cursor c = db.rawQuery("SELECT nombre FROM animales",null); 
			//Nos aseguramos de que existe al menos un registro
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM colores",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM numeros",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM naturaleza",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM frutas",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM cuerpoHumano",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM vestuario",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM hogar",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}
			c = db.rawQuery("SELECT nombre FROM escuela",null);
			if (c.moveToFirst()) 
			{
				int contador = 0;
				//Recorremos el cursor hasta que no haya más registros		
				do {
					palabras.add(c.getString(0).toString());
					contador++;
				} while(c.moveToNext());
			}

			//listview
			ListView lvPalabraEliminar = (ListView)inflador.findViewById(R.id.lvPalabraEliminar);
			ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, palabras);
			adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lvPalabraEliminar.setAdapter(adaptador2);

			lvPalabraEliminar.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id)
				{
					palabraSpainEliminar = palabras.get(position);
				}
			});



			ventana.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() 
			{ 
				public void onClick(DialogInterface dialog, int boton)
				{ 
					//etTablaSpainEliminar = (EditText)inflador.findViewById(R.id.etTablaSpainEliminar);
					//etEliminarPalabraSpain = (EditText)inflador.findViewById(R.id.etEliminarPalabraSpain);
					//tablaSpainEliminar = etTablaSpainEliminar.getText().toString();
					//palabraSpainEliminar = etEliminarPalabraSpain.getText().toString();
					int n = 0;

					if(palabraSpainEliminar!=null)
					{
						//sentencia SQL
						if(db != null) 
						{
							Cursor c = db.rawQuery("SELECT id FROM "+tablaSpainEliminar+" WHERE nombre = "+"'"+palabraSpainEliminar+"'",null); 
							if (c.moveToFirst()) 
							{
								//Recorremos el cursor hasta que no haya más registros		
								do {
									n =(c.getInt(0));
								} while(c.moveToNext());
							}
							//c = db.rawQuery("SELECT nombre FROM"+tablaEnglishEliminar+"WHERE nombre = "+palabraSpainEliminar,null); 
							//eliminarpalabras tabla ingles
							//String [] tablasSpain = new String[]{"animales","numeros","colores","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
							//String [] tablasEnglish = new String[]{"animals","numbers","colors","nature","fruits","body","wardrobe","home","school"};
							for(int i=0; i < tablasSpain.length; i++)
							{
								if(tablaSpainEliminar.equals(tablasSpain[i]))
								{
									tablaEnglishEliminar = tablasEnglish[i];
								}
							}
							if(tablaSpainEliminar.equals("animales"))
							{
								tablaEnglishEliminar = "animals";
							}
							else if(tablaSpainEliminar.equals("numeros"))
							{
								tablaEnglishEliminar = "numbers";
							}
							else if(tablaSpainEliminar.equals("colores"))
							{
								tablaEnglishEliminar = "colors";
							}
							else if(tablaSpainEliminar.equals("naturaleza"))
							{
								tablaEnglishEliminar = "nature";
							}
							else if(tablaSpainEliminar.equals("frutas"))
							{
								tablaEnglishEliminar = "fruits";
							}
							else if(tablaSpainEliminar.equals("cuerpoHumano"))
							{
								tablaEnglishEliminar = "body";
							}
							else if(tablaSpainEliminar.equals("vestuario"))
							{
								tablaEnglishEliminar = "wardrobe";
							}
							else if(tablaSpainEliminar.equals("hogar"))
							{
								tablaEnglishEliminar = "home";
							}
							else if(tablaSpainEliminar.equals("escuela"))
							{
								tablaEnglishEliminar = "school";
							}
							db.execSQL("DELETE FROM "+tablaSpainEliminar+" WHERE id = "+n);
							db.execSQL("DELETE FROM "+tablaEnglishEliminar+" WHERE id = "+n);

						}
						Toast.makeText(getApplicationContext(), "Palabras eliminadas correctamente", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "La palabra o tabla no existe", Toast.LENGTH_LONG).show();
					}

				} 

			}); 

			ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() 
			{ 
				public void onClick(DialogInterface dialog, int boton)
				{ 

				} 
			});
			ventana.show();
			return true;

			*/
		default:
			return super.onOptionsItemSelected(item);
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
