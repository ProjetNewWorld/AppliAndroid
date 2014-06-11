package com.example.ldcorig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connexion extends BaseActivity
{

	private Button boutonConnexion;
	private Button boutonNewUser;

	boolean notFirstTime=false; 
	boolean connectionEtablie=false;
	public EditText editTextLogin;
	public EditText editTextMdp;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ---------- INTERFACE UTILISATEUR ----------//
		// Choix de l'UI
		this.setContentView(R.layout.activity_connexion);
		
		// ---------- BOUTON CONNEXION ----------//
		// lier le code correspondant au bouton à son interface graphique
		boutonConnexion=(Button)findViewById(R.id.buttonConnexion);
		// choix du listener de l'action quand on clic dessus
		boutonConnexion.setOnClickListener(listenerConnexion);

		// ---------- BOUTON NOUVEL UTILISATEUR ----------//
		// boutonNewUser=(Button)findViewById(R.id.buttonConnexion);
		// choix du listener de l'action quand on clic dessus
		// boutonNewUser.setOnClickListener(listenerNewUser);

		// ---------- CONNEXION AUTOMATIQUE SI ENREGISTRER ----------//
		mesPreferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
		//récupération du login
		if(mesPreferences.contains(PREF_LOGIN))
		{
			//liaison entre les 2 activités
			this.finish();
			Intent contexte = new Intent(Connexion.this, FaireCourseActivity.class);
			//lancement de la seconde activité
			startActivity(contexte);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connexion, menu);
		return true;
	}
	// ---------- ONCLICK BOUTON CONNEXION ----------//
	private OnClickListener listenerConnexion=new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// lier le code des editText à l'UI
			editTextLogin=(EditText)findViewById(R.id.editTextLogin);
			editTextMdp=(EditText)findViewById(R.id.editTextMotdePasse);
			// récupération du contenu des editText
			String login=editTextLogin.getText().toString();
			String mdp=editTextMdp.getText().toString();
			// debug
			Log.i("ListeDeCourse",login+" "+mdp);
			// Toast.makeText(Connexion.this,login+" "+mdp,Toast.LENGTH_LONG).show();
			// création de l'adresse du webservice
			String adresse=baseUrl+"connexion.php?action=connect&login="+login+"&passwd="+mdp;
			// debug
			Log.i("ListeDeCourse",adresse);
			// exécuter le webservice
			accessWebService(adresse);
		}//fin du onClick
	};

	private String url = "connexion.php";
	public String url()
	{
		return baseUrl+url;
	}
	String PREFS_NAME="com.example.ldcorig";
	//les preferences de l'applic
	SharedPreferences mesPreferences;

	// ---------- RECEPTION DES DONNEES ----------//
	@Override
	void traiterDonneesRecues(String jsonResult)
	{
		try
		{
			JSONObject jsonResponse = new JSONObject(jsonResult);
			JSONArray jsonMainNode = jsonResponse.optJSONArray("login");
			//si le Json est bien formé
			if(jsonMainNode!=null)
			{
				//recuperation du login et password envoyer en Json stocker dans des strings
				JSONObject jsonChildNode = jsonMainNode.getJSONObject(0);
				String login = jsonChildNode.optString("membreLogin");
				String password = jsonChildNode.optString("membreMdp");
				// connexion établie
				Toast.makeText(Connexion.this, "Connexion établie", Toast.LENGTH_SHORT).show();
				connectionEtablie=true;
				// enregistrement des préférences
				Editor monEditeur= mesPreferences.edit();
				monEditeur.putString(PREF_LOGIN, login);
				monEditeur.putString(PREF_PASSWORD, password);
				monEditeur.commit();
				// liaison entre les 2 activités
				this.finish();
				Intent contexte = new Intent(Connexion.this, FaireCourseActivity.class);
				// lancement de la seconde activité
				startActivity(contexte);
			}//fin du if
			// Erreur de connexion
			else
			{
				Toast.makeText(getApplicationContext(), "Erreur: Login ou Password incorrect",Toast.LENGTH_SHORT).show();
			}
		}
		catch (JSONException e)
		{
			if(notFirstTime && !connectionEtablie)
			{
				Toast.makeText(getApplicationContext(), "Erreur: Login ou Password incorrect",Toast.LENGTH_SHORT).show();
			}
			notFirstTime=true;
		}	
	}
}
