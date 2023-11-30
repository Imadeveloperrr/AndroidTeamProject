<?php
	$con = mysqli_connect("localhost", "kdu", "1234", "table_name");
	mysqli_query($con, 'SET NAMES utf8mb4');

	$requestMethod = $_SERVER["REQUEST_METHOD"];

	switch($requestMethod){
		case 'GET':
        $id = $_GET["id"];
        $like_email = $_GET["like_email"];
        $postlike = $_GET["postlike"];
        $type = $_GET["type"];
        $result = array();


        $likeStatement = mysqli_prepare($con, "select id, like_email from like_table where id = ?");
        $insertStatement = mysqli_prepare($con, "insert into like_table (id, like_email) values (?, ?)");
        $deleteStatement = mysqli_prepare($con, "delete from like_table where id = ? and like_email = ?");
        $updatePostStatement = mysqli_prepare($con, "update post_table set postlike = ? where id = ?");
        $postStatement = mysqli_prepare($con, "select postlike from post_table where id = ?");

        // --------------------------------- bind 해주기
        mysqli_stmt_bind_param($likeStatement, "i", $id);
        mysqli_stmt_bind_param($insertStatement, "is", $id, $like_email);
        mysqli_stmt_bind_param($deleteStatement, "is", $id, $like_email);
        mysqli_stmt_bind_param($updatePostStatement, "ii", $postlike, $id);
        mysqli_stmt_bind_param($postStatement, "i", $id);

        // --------------------------------- 좋아요 누른 사람중 나를 찾는 과정
        if($type != 0) {
        mysqli_stmt_execute($likeStatement);
        mysqli_stmt_store_result($likeStatement);
        mysqli_stmt_bind_result($likeStatement, $id, $existing_like_email);

        	// like_table안에있는 컬럼 email 데이터와 like_email이 같은지 확인 (본인확인과정) 
        	$recordExists = false;
	        while (mysqli_stmt_fetch($likeStatement)) {
	            if ($existing_like_email == $like_email) {
	                $recordExists = true;
	                break;
	            }
	        }

	        // --------------------------------- 체크하고 삭제 삽입
	        if ($recordExists) {
	            mysqli_stmt_execute($deleteStatement); // 좋아요 취소하면 like_table에서 삭제
	        } else {
	            mysqli_stmt_execute($insertStatement); // 좋아요 누르면 데이터 추가
	        }
	    }

        // 업데이트 시켜주고
        mysqli_stmt_execute($updatePostStatement);
 		
 		// --------------------------------- likeStatement, 바인딩 값 초기화 
        mysqli_stmt_execute($likeStatement);
        mysqli_stmt_store_result($likeStatement);
        mysqli_stmt_bind_result($likeStatement, $id, $like_email);

		// ---------------------------------

		if(mysqli_stmt_num_rows($likeStatement) != 0) { // like_table에 데이터를 삭제햇을때
	        while (mysqli_stmt_fetch($likeStatement)) { // 데이터가 없기떄문에 num_row로 확인
	            mysqli_stmt_execute($postStatement);
	            mysqli_stmt_store_result($postStatement);
	            mysqli_stmt_bind_result($postStatement, $postlike);

	            if (mysqli_stmt_fetch($postStatement)) {
	                array_push($result, array(
	                    "id" => $id,
	                    "like_email" => $like_email,
	                    "postlike" => $postlike));
	            }
	        }
		}
		else { // 행 없으면 null값으로 보내기
			mysqli_stmt_execute($postStatement); 
            mysqli_stmt_store_result($postStatement);
            mysqli_stmt_bind_result($postStatement, $postlike);

            if (mysqli_stmt_fetch($postStatement)) {
                array_push($result, array(
                    "id" => null,
                    "like_email" => null,
                    "postlike" => $postlike));
            }
		}
        header('Content-Type: application/json');
		echo json_encode($result);
		break;
	}
	mysqli_close($con);
?>