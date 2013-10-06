package es.mentor.ProyectoFinal;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorPersonalizado extends BaseAdapter 
{
	private Context context;
	private String[] opciones = new String[] {"Animales","Colores","Números","Naturaleza","Frutas","C. Humado","Vestuario","Hogar","M. Escolar"};
	TextView tvOpcion;
	ImageView imgOpcion;

	public AdaptadorPersonalizado (Context context) 
	{
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView;

		if (convertView == null)
		{
			gridView = new View(context);
			gridView = inflater.inflate(R.layout.grid_item, null);
			tvOpcion = (TextView) gridView.findViewById(R.id.tvOpcion);
			tvOpcion.setText(opciones[position]);
			
			//cambio de fuente
			Typeface fuente = Typeface.createFromAsset(context.getAssets(), "Miss Smarty Pants.ttf");
			tvOpcion.setTypeface(fuente);

			imgOpcion = (ImageView) gridView.findViewById(R.id.imgOpcion);
			String tema = opciones[position];
			if (tema.equals("Animales"))
			{
				imgOpcion.setImageResource(R.drawable.animales);
			} 
			else if (tema.equals("Colores")) 
			{
				imgOpcion.setImageResource(R.drawable.colores);
			} 
			else if (tema.equals("Números"))
			{
				imgOpcion.setImageResource(R.drawable.numeros);
			}
			else if (tema.equals("Naturaleza"))
			{
				imgOpcion.setImageResource(R.drawable.naturaleza);
			} 
			else if (tema.equals("Frutas")) 
			{
				imgOpcion.setImageResource(R.drawable.frutas);
			} 
			else if (tema.equals("C. Humado"))
			{
				imgOpcion.setImageResource(R.drawable.cuerpohumano);
			}
			else if (tema.equals("Vestuario")) 
			{
				imgOpcion.setImageResource(R.drawable.vestuario);
			} 
			else if (tema.equals("Hogar")) 
			{
				imgOpcion.setImageResource(R.drawable.hogar);
			} 
			else
			{
				imgOpcion.setImageResource(R.drawable.escolar);
			}
		} 
		else 
		{
			gridView = (View) convertView;
		}
		return gridView;
	}

	@Override
	public int getCount()
	{
		return opciones.length;
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}
}

