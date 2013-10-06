package es.mentor.ProyectoFinal;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityBorrarPalabra extends Activity implements OnItemClickListener
{


	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pantalla_eliminar_palabras);

		//hacemos una primera carga de datos a las tablas
		//Abrimos la base de datos 'DBPalabras.db'
		PalabrasSQLiteHelper palabrasBD = new PalabrasSQLiteHelper(this, "BDPalabras.db", null, 1);
		// Modo escritura
		final SQLiteDatabase db = palabrasBD.getWritableDatabase();


		final ArrayList<String> palabras = new ArrayList<String>();


		//listView
		final String[] datos = new String[]{"animales","colores","numeros","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
		ListView lvTemas = (ListView)findViewById(R.id.lvTemas);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lvTemas.setAdapter(adaptador);	

		lvTemas.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id)
			{
				//tablaSpainInsertar = datos1[position];
				String tabla = datos[position];



				Cursor c = db.rawQuery("SELECT nombre FROM animales",null); 
				//Nos aseguramos de que existe al menos un registro
				if (c.moveToFirst()) 
				{
					int contador = 0;
					//Recorremos el cursor hasta que no haya más registros		
					do
					{
						palabras.add(c.getString(0).toString());
						contador++;
					} while(c.moveToNext());
				}

				Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_LONG).show();
			}
		});


		//listview
		//final String[] tablas = new String[]{"animales","colores","numeros","naturaleza","frutas","cuerpoHumano","vestuario","hogar","escuela"};
		ListView lvPalabraEliminar = (ListView)findViewById(R.id.lvPalabraEliminar);
		ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, palabras);
		adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lvPalabraEliminar.setAdapter(adaptador2);



	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		// TODO Auto-generated method stub

	}

}
