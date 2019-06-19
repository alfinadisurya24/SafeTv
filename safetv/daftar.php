<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    $password = $_POST['password'];
    $email = $_POST['email'];
    $nama = $_POST['nama'];

    $password = password_hash($password, PASSWORD_DEFAULT);

    require_once 'connect.php';

    $sql = "INSERT INTO user (password, email, nama) VALUES ('$password', '$email', '$nama')";

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
}

?>