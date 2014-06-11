package com.example.ldcorig;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.example.ldcorig.FaireCourseActivity;
import com.example.ldcorig.RayonAdapter.ViewHolder;
import com.example.ldcorig.R;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterExpandableRayonsProduits extends BaseExpandableListAdapter {

	private EnsembleRayons lesRayons;
	private final Activity context;
	protected ArrayList<String> tabProdCoches = new ArrayList<String>();

	
	public AdapterExpandableRayonsProduits(Activity TheContext)
	{
		super();
		lesRayons = new EnsembleRayons();
		context = TheContext;
		//this.list = list;


	}
	public EnsembleRayons getEnsembleRayon()
	{
		return lesRayons;
	}
	
	public void setEnsRayons(EnsembleRayons lesJolisRayons){
		lesRayons=lesJolisRayons;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		
		return lesRayons.getRayon(groupPosition).getArticle(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		
		return Long.valueOf(lesRayons.getRayon(groupPosition).getArticle(childPosition).getNo());
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		// renvoi le nombre d'article a l indice grouPosition
		return lesRayons.getRayon(groupPosition).getNbarticle();
	}

	@Override
	public Object getGroup(int groupPosition) {
		
		return lesRayons.getRayon(groupPosition);
	}

	@Override
	public int getGroupCount() {
		
		//renvoi le nombre de rayons
		return lesRayons.getNbRayons();
	}

	@Override
	public long getGroupId(int groupPosition) {
		
		return Long.parseLong(lesRayons.getRayon(groupPosition).getNo());
	}
	static class ViewHolder {
		protected TextView textNumeroProduit;
		protected TextView textLibelleProduit;
		protected TextView textNumeroRayon;
		protected TextView textLibelleRayon;
		protected TextView textQteProduit;
		protected CheckBox checkbox;
	}
	public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {

		View v = convertView;
		if (v == null) {
			LayoutInflater inflator = context.getLayoutInflater();
		      v = inflator.inflate(R.layout.produitrayonexplist_layout, null);
	        
	    }
	    TextView rayonName = (TextView) v.findViewById(R.id.itemLibelleProduit);		
	    
	    String nomRayon = lesRayons.getRayon(groupPosition).getNom().toString();
	    rayonName.setText(nomRayon);
		    return v;
	
	}

	public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 View view = null;
		 //tabProdCoches.clear();
		    if (convertView == null) {
		      LayoutInflater inflator = context.getLayoutInflater();
		      view = inflator.inflate(R.layout.produit_layout_checkable, null);
		      final ViewHolder viewHolder = new ViewHolder();
		      viewHolder.textNumeroProduit = (TextView) view.findViewById(R.id.itemNumeroProduitDsListe);
		      viewHolder.textLibelleProduit = (TextView) view.findViewById(R.id.itemLibelleProduitDsListe);
		      viewHolder.textNumeroRayon = (TextView) view.findViewById(R.id.itemNumeroRayonProduitDsListe);
		      viewHolder.textLibelleRayon = (TextView) view.findViewById(R.id.itemLibelleRayonProduitDsListe);
		      viewHolder.textQteProduit= (TextView) view.findViewById(R.id.itemQuantiteAAcheter);		    
		      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.itemCheckBoxProdDsListe);
		      viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

		            @Override
		            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
		            	Log.i("ListeDeCourse",String.valueOf(isChecked));
		              ModelArticle element = (ModelArticle) viewHolder.checkbox.getTag();
		              element.setSelected(buttonView.isChecked());
		              Log.i("ListeDeCourse",String.valueOf(element.getNom()));
		         
		    		


		            }
		          });
		      view.setTag(viewHolder);
		      viewHolder.checkbox.setTag(lesRayons.getRayon(groupPosition).getArticle(childPosition));
		      //viewHolder.checkbox.setTag(gestProd.getProduit(groupPosition, childPosition));
		    } 
		    else 
		    {
		      view = convertView;
		     ((ViewHolder) view.getTag()).checkbox.setTag(lesRayons.getRayon(groupPosition).getArticle(childPosition));
		     }
		    ViewHolder holder = (ViewHolder) view.getTag();
		    holder.textNumeroProduit.setText(lesRayons.getRayon(groupPosition).getArticle(childPosition).getNo());
		    holder.textLibelleProduit.setText(lesRayons.getRayon(groupPosition).getArticle(childPosition).getNom());
		    holder.textNumeroRayon.setText(lesRayons.getRayon(groupPosition).getNo());
		    holder.textLibelleRayon.setText(lesRayons.getRayon(groupPosition).getNom());
			holder.textQteProduit.setText(lesRayons.getRayon(groupPosition).getArticle(childPosition).getQte());
			holder.checkbox.setChecked(lesRayons.getRayon(groupPosition).getArticle(childPosition).isSelected());
		    return view;

	}


	@Override
	public boolean hasStableIds() {
		
		return true;
	}

	
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		
		return true;
	}

	public ArrayList<String> getCasesCoches()
	{
		return tabProdCoches;
		
	}
}
