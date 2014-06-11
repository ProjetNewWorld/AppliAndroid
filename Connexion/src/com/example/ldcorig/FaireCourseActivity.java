package com.example.ldcorig;

import com.example.ldcorig.AdapterExpandableRayonsProduits;
import com.example.ldcorig.AdapterExpandableRayonsProduits.ViewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSInput;

import com.example.ldcorig.R;


public class FaireCourseActivity extends BaseActivity {
	
//quelques propriétés de la classe:
	
	 EnsembleRayons ensRayon=new EnsembleRayons();
	 private String url = "faireCourse.php";
	 public String url(){return baseUrl+url;};
	 private ExpandableListView listeViewDesProduitsDeLaListeParRayon;
	 private Button buttonAddCaddy;
	 private Button buttonAnuler;
	 private AdapterExpandableRayonsProduits monAdapteur = new AdapterExpandableRayonsProduits(FaireCourseActivity.this);
		public List<ModelArticle> listeDesProduits = new ArrayList<ModelArticle>();



 //code s'exécutant à la création de l'activité
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		//on charge la bonne interface (layout)
		setContentView(R.layout.activity_faire_courses);

		listeViewDesProduitsDeLaListeParRayon=(ExpandableListView)findViewById(R.id.expandableListViewProduitDansListe);
		buttonAddCaddy = (Button)findViewById(R.id.buttonPoserDansCaddy);
		buttonAddCaddy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("ListeDeCourse","Supression demandée");
				int nombreDeRayon = listeDesProduits.size();
				String adresse=baseUrl+"listeRayons.php?action=delete";
				Log.i("ListeDeCourse",String.valueOf(nombreDeRayon));
				boolean supressionAEffectuer=false;
				for(int i=0;i<nombreDeRayon; i++)
				{
					if(listeDesProduits.get(i).isSelected())
					{
						String noDuRayon=listeDesProduits.get(i).getNo();
						adresse+="&tabNoRayon[]="+noDuRayon;
						supressionAEffectuer=true;
						Log.i("ListeDeCourse","salut salut");
					}

				}
				Log.i("ListeDeCourse",adresse);
				
				
			}
		});
		buttonAnuler = (Button)findViewById(R.id.ButtonAnnulerAchat);
		buttonAnuler.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
		         finish();
		         startActivity(intent); 
				
			}
		});

//				Toast.makeText(getApplicationContext(), "On clique",Toast.LENGTH_SHORT).show();
//			 Log.i("ListeDeCourse","On clique");

			

	}


	 public void traiterDonneesRecues(String jsonResult){

		 try {
			   JSONObject jsonResponse = new JSONObject(jsonResult);
			   JSONArray jsonMainNode = jsonResponse.optJSONArray("coursesAFaire");
			   // on vide la liste des produits
			   listeDesProduits.clear();
			   // on choppe l'élément de la layout
				listeViewDesProduitsDeLaListeParRayon = (ExpandableListView) findViewById(R.id.expandableListViewProduitDansListe);
			   //
			   for (int i = 0; i < jsonMainNode.length(); i++) {
			    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
				   // récup des infos du json

			    String productName = jsonChildNode.optString("produitLib");
			    String productNumber = jsonChildNode.optString("produitId");
			    
			    String rayName = jsonChildNode.optString("rayonLib");
			    String rayNumber = jsonChildNode.optString("rayonId");
			    
			    String listeQte= jsonChildNode.optString("listeQte");
			   // on stocke ces infos quelque part 
			    listeDesProduits.add(new ModelArticle(productNumber, productName, listeQte));

//			    ModelRayonGarni nouveauRayon = new ModelRayonGarni(rayNumber, rayName);
//			    ModelArticle nouvelArticle = new ModelArticle(productNumber, productName, listeQte);
//			    nouveauRayon.add(nouvelArticle);
//			    ensRayon.add(nouveauRayon);
			    
				
			    ensRayon.addArticle(productNumber, productName, rayNumber , rayName, listeQte);
			   }
			  } catch (JSONException e) {
			   //Toast.makeText(getApplicationContext(), "Error" + e.toString(),Toast.LENGTH_SHORT).show();
			  }
			 


		  //création de l'adapteur pour le choix du rayon qd on fait la liste de course
		  monAdapteur.setEnsRayons(ensRayon);
		
		  try{ 
		  //on associe l'adaptateur à la listView
		  listeViewDesProduitsDeLaListeParRayon.setAdapter(monAdapteur);
		  
		 }
		 catch(NullPointerException e){

			 Log.i("ListeDeCourse","adapter défectueux");
		 
		 }

	 }
	 
	 private HashMap<String, String> creerMapRayon(String name, String number) {
	  HashMap<String, String> mapRayon = new HashMap<String, String>();
	  mapRayon.put("rayonId", number);
	  mapRayon.put("rayonLib", name);
	  return mapRayon;
	 }
}
