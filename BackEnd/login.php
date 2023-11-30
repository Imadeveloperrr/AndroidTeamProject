<?php

	$con = mysqli_connect("localhost", "kdu", "1234", "table_name");
	mysqli_query($con, 'SET NAMES utf8mb4');

	$email = $_POST["email"];
	$pw = $_POST["pw"];

	$statement = mysqli_prepare($con, "SELECT * FROM user_table WHERE email = ? AND pw = ?");
	mysqli_stmt_bind_param($statement, "ss", $email, $pw);
	mysqli_stmt_execute($statement);

	//mysqli_stmt_store_result($statement); 결과 집합만 갖고오는거
	mysqli_stmt_bind_result($statement, $seq ,$pw, $name, $email, $number, $tall, $weight, $gender, $foot, $post, $following, $follower);

	$response = array();
	//$response["success"] = false;

	while(mysqli_stmt_fetch($statement)) // 한번 실행할때마다 결과 집합에서 다음 하나의 행만 실행함
	{
		//$response["success"] = true;
		$response["seq"]=$seq;
		$response["email"] = $email;
		$response["pw"] = $pw;
		$response["name"] = $name;
		$response["number"] = $number;
		$response["tall"] = $tall;
		$response["weight"] = $weight;
		$response["gender"] = $gender;
		$response["foot"] = $foot;
		$response["post"] = $post;
		$response["following"] = $following;
		$response["follower"] = $follower;
	}

	header('Content-Type: application/json');
	echo json_encode($response);
?>