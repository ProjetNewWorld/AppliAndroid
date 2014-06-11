<?php
$host="127.0.0.1"; //replace with database hostname 
$username="user"; //replace with database username 
$password="passwd"; //replace with database password 
$db_name="listedescourses"; //replace with database name
//echo $host." ".$username." ".$password;
mysql_connect($host, $username, $password)or die("cannot connect"); 
mysql_select_db("$db_name")or die("cannot select DB");
$noListeEnCours=0;
?>
