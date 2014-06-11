package com.example.ldcorig;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class EnsembleRayons {

	private List<ModelRayonGarni> lesRayons;
	int getNbRayons()
	{
		return lesRayons.size();
	}
	void add (ModelRayonGarni leRayon)
	{
		lesRayons.add(leRayon);
	}
	ModelRayonGarni getRayon(int indice)
	{
		return lesRayons.get(indice);
	}
	public EnsembleRayons() {
		
		lesRayons=new ArrayList<ModelRayonGarni>();
	}
	/**
	 * cette méthode renvoie null si le rayon n'a pas été trouvé dans la liste 
	 * ou le rayon si celui-ci existe
	 * @param noRayon
	 * @return null si le rayon n'a pas été trouvé dans la liste ou le rayon si celui-ci existe
	 */
	ModelRayonGarni getRayonById(String noRayon)
	{

		ModelRayonGarni leRayon=null;
		int indiceRayon=0;

		while(!(indiceRayon==lesRayons.size() || lesRayons.get(indiceRayon).getNo().equals(noRayon)))
		{
		  indiceRayon++;	
		}

		if(!(indiceRayon==lesRayons.size()))
		{
		  leRayon=lesRayons.get(indiceRayon);
		}

		return leRayon;
	}
	/**
	 * ajoute un rayon et son article ou ajoute au rayon specifié son article
	 * @param no numero de l'article
	 * @param libelle libelle de l'article
	 * @param noR numero du rayon
	 * @param libR libelle du rayon
	 * @param qte qte A ACHETER
	 */
	public void addArticle(String no,String libelle, String noR,String libR, String qte)
	{
	   
		
		if(getRayonById(noR)==null)// si le rayon n existe pas
		{
			ModelRayonGarni monRayon=new ModelRayonGarni(noR, libR);// on le crée 
			lesRayons.add(monRayon); // on l'ajoute à la liste de rayons
		}

		
		ModelArticle monModele=new ModelArticle(no, libelle, qte); //on crée l article


		getRayonById(noR).ajout(monModele); //on l ajoute au rayon concerné

    }
}
