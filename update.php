<?php
    $con = mysqli_connect("mysql.hostinger.in", "u648788261_user", "Jakeyoung1995", "u648788261_data");
    
    //$name = $_POST["name"];
    //$age = $_POST["age"];
    //$username = $_POST["username"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "Update user (password) VALUES (?)");
    mysqli_stmt_bind_param($statement, "s",$password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>