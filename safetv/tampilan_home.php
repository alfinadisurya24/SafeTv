<?php 

    require_once 'connect.php';
	

	$sql = "SELECT video_user.id,video_user.judul, video_user.thumbnail, user.nama, user.photo
            FROM video_user
            INNER JOIN user ON video_user.user_id = user.id;";
	
	
	$r = mysqli_query($conn,$sql);
	
	
	$result = array();
	
	while($row=mysqli_fetch_array($r)){
		
		array_push($result,array(
		"id"=>$row['id'],
        "judul"=>$row['judul'],
        "thumbnail"=>$row['thumbnail'],
        "nama"=>$row['nama'],
        "photo"=>$row['photo']
		));
		
	}
	
	
	
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);

?>