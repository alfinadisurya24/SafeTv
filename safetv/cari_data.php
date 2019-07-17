<?php 
	require_once 'connect.php';
	

    $keyword = $_POST['keyword'];

	$sql = "SELECT video_user.id,video_user.judul,video_user.kategori,video_user.thumbnail,video_user.video, user.nama, user.photo
            FROM video_user
            INNER JOIN user ON video_user.user_id = user.id WHERE video_user.judul LIKE '%".$keyword."%';";
	
	
	$r = mysqli_query($conn,$sql);
	
	
	$result = array();
	
	while($row=mysqli_fetch_array($r)){
		
		array_push($result,array(
		"id"=>$row['id'],
		"judul"=>$row['judul'],
		"kategori"=>$row['kategori'],
        "thumbnail"=>$row['thumbnail'],
        "nama"=>$row['nama'],
		"photo"=>$row['photo'],
		"video"=>$row['video']
		));
		
	}

	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
?>