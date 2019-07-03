<?php  
 require_once 'connect.php';
	

 $sql = "SELECT * FROM user;";
 
 
 $r = mysqli_query($conn,$sql);
 
 
 $result = array();
 
 while($row=mysqli_fetch_array($r)){
     
     array_push($result,array(
     "id"=>$row['id'],
     "photo"=>$row['photo']
     ));
     
 }
 
 
 
 echo json_encode(array('result'=>$result));
 
 mysqli_close($conn);
?>