<?php

include("connect.php");

	$query=mysql_query("SELECT * FROM employee");
	$rownumbers=mysql_num_rows($query);
	
	if($rownumbers!=0){
		while($row=mysql_fetch_assoc($query)){
				$output[]=$row;
		}
		print(json_encode($output));
			}
		else
	  die("No data found");
	?>
