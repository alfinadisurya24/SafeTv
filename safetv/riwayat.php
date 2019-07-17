<?php

    $video_user_id = $_POST['video_user_id'];
    $banding_id = $_POST['video_user_id']; 
    $user_id = $_POST['user_id'];  

    require_once 'connect.php';

    $sql = "INSERT INTO history (video_user_id, user_id) VALUES ('$video_user_id' ,'$user_id')";

    if (mysqli_query($conn, $sql)) {  
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



?>
