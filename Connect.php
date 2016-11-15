<?php
	//Define and instantiate variables
	define('HOST', 'c14706715.96.lt');
	define('USER', 'u648788261_root');
	define ('PASSWORD', 'Jakeyoung1995');
	define ('DB', 'u648788261_user');

	//Create a connection with the database specified 
	$con = mysqli_connect(HOST, USER, PASSWORD, DB);

	//Cath variables and send them to the local database
	$Name = $_POST['Name'];
	$Email = $_POST['Email'];
	$Password = $_POST['Password'];
	
	$sql = "insert into Person(Name, Email, Password) values ('$Name', '$Email', '$Password')";
	if(mysqli_query($con, $sql)){
		echo 'Insertion Successful';
	}
	else{
		echo 'Insertion Failure';
	}
	mysqli_close($con);
?>