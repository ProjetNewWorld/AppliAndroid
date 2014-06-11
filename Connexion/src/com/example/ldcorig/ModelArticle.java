package com.example.ldcorig;

public class ModelArticle {
	private String nomArticle;
	private String noArticle;
	private String qteAcheter;
	private boolean selected;
	public ModelArticle(String no,String nom,String qte){
		nomArticle=nom;
		noArticle=no;
		qteAcheter=qte;
		selected=false;
	}
	public String getNom() {
		return nomArticle;
	}

	public void setNom(String nom) {
		nomArticle = nom;
	}
	public String getNo() {
		return noArticle;
	}

	public void setNo(String no) {
		noArticle = no;
	}
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selectionne) {
		this.selected = selectionne;
	}

	public String getQte(){
		return qteAcheter;
	}
	public void setQte(String q) {
		qteAcheter=q;

	}

}
