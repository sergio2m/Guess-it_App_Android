package es.mentor.ProyectoFinal;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PalabrasSQLiteHelper extends SQLiteOpenHelper 
{
	//Sentencia SQL para crear todas las tablas
	static String creaTablaAnimales = "CREATE TABLE animales (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaAnimals = "CREATE TABLE animals (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaNumeros = "CREATE TABLE numeros (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaNumbers = "CREATE TABLE numbers (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaColores = "CREATE TABLE colores (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaColors = "CREATE TABLE colors (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaNaturaleza = "CREATE TABLE naturaleza (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaNature = "CREATE TABLE nature (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaFrutas = "CREATE TABLE frutas (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaFruits = "CREATE TABLE fruits (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaCuerpoHumano = "CREATE TABLE cuerpoHumano (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaBody = "CREATE TABLE body (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaVestuario = "CREATE TABLE vestuario (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaWardrobe = "CREATE TABLE wardrobe (id integer primary key autoincrement, nombre TEXT)";	
	static String creaTablaHogar = "CREATE TABLE hogar (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaHome = "CREATE TABLE home (id integer primary key autoincrement, nombre TEXT)";	
	static String creaTablaEscuela = "CREATE TABLE escuela (id integer primary key autoincrement, nombre TEXT)";
	static String creaTablaSchool = "CREATE TABLE school (id integer primary key autoincrement, nombre TEXT)";

	// Definimos el constructor indicando el contexto de la aplicación,
	// el nombre de la base de datos y la versión de la BD
	public PalabrasSQLiteHelper(Context onClickListener, String name, CursorFactory factory, int version)
	{
		super(onClickListener, name, factory, version);
	}

	@Override
	// Si la BD no existe, Android llama a este método
	public void onCreate(SQLiteDatabase db) 
	{

		//Se ejecuta la sentencia SQL de creación de las tablas
		db.execSQL(creaTablaAnimales);
		db.execSQL(creaTablaAnimals);
		db.execSQL(creaTablaNumeros);
		db.execSQL(creaTablaNumbers);
		db.execSQL(creaTablaColores);
		db.execSQL(creaTablaColors);
		db.execSQL(creaTablaNaturaleza);
		db.execSQL(creaTablaNature);
		db.execSQL(creaTablaFrutas);
		db.execSQL(creaTablaFruits);
		db.execSQL(creaTablaCuerpoHumano);
		db.execSQL(creaTablaBody);
		db.execSQL(creaTablaVestuario);
		db.execSQL(creaTablaWardrobe);
		db.execSQL(creaTablaHogar);
		db.execSQL(creaTablaHome);
		db.execSQL(creaTablaEscuela);
		db.execSQL(creaTablaSchool);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) 
	{
		//Se elimina la versión anterior de las tablas
		db.execSQL("DROP TABLE IF EXISTS animales");
		db.execSQL("DROP TABLE IF EXISTS animals");
		db.execSQL("DROP TABLE IF EXISTS numeros");
		db.execSQL("DROP TABLE IF EXISTS numbers");
		db.execSQL("DROP TABLE IF EXISTS colores");
		db.execSQL("DROP TABLE IF EXISTS colors");
		db.execSQL("DROP TABLE IF EXISTS naturaleza");
		db.execSQL("DROP TABLE IF EXISTS nature");
		db.execSQL("DROP TABLE IF EXISTS frutas");
		db.execSQL("DROP TABLE IF EXISTS fruits");
		db.execSQL("DROP TABLE IF EXISTS cuerpoHumano");
		db.execSQL("DROP TABLE IF EXISTS body");
		db.execSQL("DROP TABLE IF EXISTS vestuario");
		db.execSQL("DROP TABLE IF EXISTS wardrobe");
		db.execSQL("DROP TABLE IF EXISTS hogar");
		db.execSQL("DROP TABLE IF EXISTS home");
		db.execSQL("DROP TABLE IF EXISTS escuela");
		db.execSQL("DROP TABLE IF EXISTS school");

		//Se crea la nueva versión de las tablas
		db.execSQL(creaTablaAnimales);
		db.execSQL(creaTablaAnimals);
		db.execSQL(creaTablaNumeros);
		db.execSQL(creaTablaNumbers);
		db.execSQL(creaTablaColores);
		db.execSQL(creaTablaColors);
		db.execSQL(creaTablaNaturaleza);
		db.execSQL(creaTablaNature);
		db.execSQL(creaTablaFrutas);
		db.execSQL(creaTablaFruits);
		db.execSQL(creaTablaCuerpoHumano);
		db.execSQL(creaTablaBody);
		db.execSQL(creaTablaVestuario);
		db.execSQL(creaTablaWardrobe);
		db.execSQL(creaTablaHogar);
		db.execSQL(creaTablaHome);
		db.execSQL(creaTablaEscuela);
		db.execSQL(creaTablaSchool);
	}
	
	
}