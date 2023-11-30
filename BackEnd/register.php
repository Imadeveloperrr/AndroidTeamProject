<?php
	$con = mysqli_connect("localhost", "kdu", "1234", "table_name");
	mysqli_query($con, 'SET NAMES utf8mb4');

	$email = $_POST["email"];
	$pw = $_POST["pw"];
	$name = $_POST["name"];
	$number = $_POST["number"];
	$tall = $_POST["tall"];
	$weight = $_POST["weight"];
	$gender = $_POST["gender"];
	$foot = $_POST["foot"];

	// -----------------		auto_increment 초기화 하는 코드			-------------------
	// mysqli_query($con, "SET @COUNT = 0");
	// mysqli_query($con, "UPDATE user_table SET seq = @COUNT:=@COUNT+1");
	// mysqli_query($con, "ALTER TABLE user_table AUTO_INCREMENT=1");
	// ------------------------------------------------------------------------------------

	$statement = mysqli_prepare($con, "INSERT INTO user_table VALUES (?,?,?,?,?,?,?,?,?,0,0,0)");
	mysqli_stmt_bind_param($statement, "issssssss", $seq ,$pw, $name, $email, $number, $tall, $weight, $gender, $foot);
	$result = mysqli_stmt_execute($statement);

	$response = array();
	$response["success"] = true;
	if($result){
		$response["success"] = true;
	} else {
		$response["success"] = false;
	}

	header('Content-Type: application/json');
	echo json_encode($response);
?>