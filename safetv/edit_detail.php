<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $nama = $_POST['nama'];
    $id = $_POST['id'];

    require_once 'connect.php';

    $sql = "UPDATE user SET nama='$nama' WHERE id='$id' ";

    if (mysqli_query($conn, $sql)) {
        $result["success"] = "1";
        $result["message"] = "success";

        echo json_encode($result);
        mysqli_close($conn);
    } else{
        $result["success"] = "0";
        $result["message"] = "error!";

        echo json_encode($result);
        mysqli_close($conn);
    }
}
?>