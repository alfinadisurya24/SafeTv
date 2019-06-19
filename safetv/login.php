<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $password = $_POST['password'];
    $email = $_POST['email'];

    require_once 'connect.php';

    $sql = "SELECT * FROM user where email='$email' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();

    if (mysqli_num_rows($response) === 1) {
       $row = mysqli_fetch_assoc($response);
       
       if (password_verify($password, $row['password']) ) {
           $index['nama'] = $row['nama'];
           $index['email'] = $row['email'];

           array_push($result['login'], $index);

           $result['success'] = "1";
           $result['message'] = "success";
           echo json_encode($result);

           mysqli_close($conn);

       } else{
        $result['success'] = "0";
        $result['message'] = "error";
        echo json_encode($result);
        
        mysqli_close($conn);
        }
   } 
}

?>