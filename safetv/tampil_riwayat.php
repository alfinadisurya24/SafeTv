<?php 

	require_once 'connect.php';
	
	$user_id = $_POST['user_id'];

	$sql = "SELECT history.id_history,history.user_id,video_user.judul,video_user.kategori,video_user.thumbnail, user.nama, user.photo
            FROM video_user
            INNER JOIN user ON video_user.user_id = user.id INNER JOIN history ON video_user.id = history.video_user_id 
            WHERE history.user_id = '$user_id' ORDER BY history.id_history desc;";
	
	
	$r = mysqli_query($conn,$sql);
	
	
	$result = array();
	
	while($row=mysqli_fetch_array($r)){
		
		array_push($result,array(
		"id_history"=>$row['id_history'],
		"judul"=>$row['judul'],
		"kategori"=>$row['kategori'],
        "thumbnail"=>$row['thumbnail'],
        "nama"=>$row['nama'],
        "photo"=>$row['photo']
		));
		
	}
	
	
	
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);

?>