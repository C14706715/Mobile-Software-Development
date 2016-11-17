<?php 
 
 require_once('dbConnect.php');
 
 $sql = "SELECT * FROM user";
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
 "name"=>$res['name'],
 "username"=>$res['username'],
 "password"=>$res['password']
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }