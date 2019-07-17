<?php

$id_history = $_POST['id_history'];
 
 require_once('connect.php');

 $sql = "DELETE FROM history WHERE id_history=$id_history;";
 
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