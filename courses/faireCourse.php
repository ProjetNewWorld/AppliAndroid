<?php
include("commun.php");
//requete sql
if(isset($_GET['action']))
{

	$action=$_GET['action'];
	switch($action)
	{
		case "acheter":break;
		case "annuler": break;
		case "reporter": break;

	}
}
$sql = "select produit.produitId as produitId , produitLib , contenuListe.listeQte , rayon.rayonId as rayonId , rayonLib , dansCaddy from produit inner join rayon on rayon.rayonId=produit.rayonId inner join contenuListe on contenuListe.produitId = produit.produitId inner join liste on liste.listeId=contenuListe.listeId where enCours=true and liste.listeId=$noListeEnCours";
//echo $sql; 
//execution
$result = mysql_query($sql);
//le tableau
$monTableau = array();
if(mysql_num_rows($result))//s'il y a un resultat
{
	while($ligne=mysql_fetch_assoc($result))
	{
		$monTableau['coursesAFaire'][]=$ligne;
	}
}
echo json_encode($monTableau); 
?> 
