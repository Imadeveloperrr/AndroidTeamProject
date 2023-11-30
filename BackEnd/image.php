<?php
	$con = mysqli_connect("localhost", "kdu", "1234", "table_name");
	mysqli_query($con, 'SET NAMES utf8mb4');

	$requestMethod = $_SERVER["REQUEST_METHOD"];


	switch($requestMethod) {
		case 'GET' : // GET 방식 이메일 아이디에 맞게 사진 전부다 불러오기
		if (isset($_GET["user_email"])) {
			$user_email = $_GET["user_email"];

			$statement = mysqli_prepare($con, "select * from post_table where user_email = ?");
			mysqli_stmt_bind_param($statement, "s", $user_email);
		}
		else {
			$statement = mysqli_prepare($con, "select * from post_table");
		}
			mysqli_stmt_execute($statement);
			mysqli_stmt_bind_result($statement, $id, $user_email, $title, $image, $postlike);

			$result = array();
			while(mysqli_stmt_fetch($statement)){
				array_push($result, array(
				"id" => $id,
				"user_email" => $user_email,
				"title" => $title,
				"image" => $image,
				"postlike" => $postlike));
			}
			echo json_encode($result);
			break;		
			
		case 'POST' : // POST 방식, 업로드 기능
			//$id = $_POST["id"];
			$user_email = $_POST["user_email"];
			$title = $_POST["title"];
			$image = $_POST["image"];
			//$postlike = $_POST["postlike"];


			$statement = mysqli_prepare($con, "INSERT INTO post_table(id, user_email, title, image, postlike) VALUES (?,?,?,?,?)");	
			mysqli_stmt_bind_param($statement, "isssi", $id, $user_email, $title, $image, $postlike);
			mysqli_stmt_execute($statement);

			$response = array();
			$response["success"] = true;
			echo json_encode($response);
			break;
			
		default : 
		break;
	}
	mysqli_close($con);
?>