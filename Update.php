<?php 
 if($_SERVER['REQUEST_METHOD']=='POST'){

 $username = $_POST['username'];
 $password = $_POST['password'];

//importing database connection script 
 require_once('dbConnect.php');

 //Creating sql query 
 $sql = "UPDATE user SET password = '$password' WHERE username = $username";

 //Updating database table 
 if (mysqli_query($con,$sql)) {
 echo ' User is updatedSuccessfully';
 }
 else {
 echo 'Could not update user, Try Again';
 }

 //closing connection 
 mysqli_close($con);
 }
?>