<?php
include("commun.php");
//requete sql
if(isset($_GET['action'])&& $_GET['action']=='connect')
{
	$login=$_GET['login'];
	$password=$_GET['passwd'];
	$req="select membreNom,membrePrenom,membreLogin,membreMdp from membre where membreLogin='$login' and membreMdp='$password'";
	//echo $req;
	$result=mysql_query($req);
	//le tableau
	$monTableau = array();
	if(mysql_num_rows($result))//s'il y a un resultat
	{
		$ligne=mysql_fetch_assoc($result);

		$monTableau['login'][]=$ligne;

	}
}
echo json_encode($monTableau);
?>
