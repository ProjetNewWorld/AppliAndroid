<?php
include("commun.php");
if(isset($_GET['action']))
{
	$action=$_GET['action'];
	$noProduit=$_GET['produitId'];
	$qte=$_GET['qte'];

	if($action=="ajout")
	{
		$sql = "insert into contenuliste(listeId,produitId,listeQte) values(0,$noProduit,$qte)"; 
		echo $sql;
		$result = mysql_query($sql);
	}
}
$json = array();
$sql2="select contenuliste.produitId as produitId ,produitLib,listeQte from contenuliste inner join produit on produit.produitId=contenuliste.produitId";
$result2=mysql_query($sql2);
while($row=mysql_fetch_assoc($result2))
{
	$json['listeDeCourse'][]=$row;
}
echo json_encode($json);
?> 
