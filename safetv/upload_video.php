<?php

    $user_id = $_POST['user_id'];
    $judul = $_POST['judul'];
    $kategori = $_POST['kategori'];
    $thumbnail = $_POST['thumbnail'];
    $video = $_POST['video'];

    $file_name = $_FILES['myFile']['name'];
	$file_size = $_FILES['myFile']['size'];
	$file_type = $_FILES['myFile']['type'];
    $temp_name = $_FILES['myFile']['tmp_name'];
    

    $path =  "thumbnail/$judul.jpeg";
    $finalPath = "http://192.168.5.31/safetv/".$path; 

    $path1 =  "video/$file_name.mp4";
    $finalPath1 = "http://192.168.5.31/safetv/".$path1; 

    require_once 'connect.php';

    $sql = "INSERT INTO video_user (judul, kategori, thumbnail, video, user_id) VALUES ('$judul', '$kategori', '$finalPath', '$finalPath1', '$user_id')";

    if (mysqli_query($conn, $sql)) {
        if (file_put_contents($path, base64_decode($thumbnail)) && file_put_contents($path1, base64_decode($video))) {
            
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
    } else{
        $result["success"] = "0";
        $result["message"] = "error";

        echo json_encode($result);
        mysqli_close($conn);
    }
}


?>
