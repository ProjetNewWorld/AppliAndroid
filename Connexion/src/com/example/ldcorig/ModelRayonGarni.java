package com.example.ldcorig;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class ModelRayonGarni {
	private String nomRayon;
	private String noRayon;
	public List<ModelArticle> listeDesArticles;

	public ModelRayonGarni(String no,String nom){
		nomRayon=nom;
		noRayon=no;
		listeDesArticles =new ArrayList<ModelArticle>();
	}
	public String getNom() {
		return nomRayon;
	}

	public void setNom(String nom) {
		nomRayon = nom;
	}
	public String getNo() {
		return noRayon;
	}

	public void setNo(String no) {
		noRayon = no;
	}
	void ajout(ModelArticle article)
	{
		listeDesArticles.add(article);

	}
	int getNbarticle()
	{
		return listeDesArticles.size();
	}
	ModelArticle getArticle(int indice)
	{
		return listeDesArticles.get(indice);
	}


}
