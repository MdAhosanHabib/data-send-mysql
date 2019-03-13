<?php

include("connect.php");

$employee_name=$_POST['name'];
$employee_phone=$_POST['phone'];
$employee_address=$_POST['address'];







$q=mysqli_query($connect,"insert into employee (name,phone,address) values ('$employee_name','$employee_phone','$employee_address')");




if($q>0){

echo "Succeed";


}else{

echo "Failed";

}

?>