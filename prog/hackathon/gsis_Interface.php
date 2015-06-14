<?php
	require_once "gsis_Transaction.php";
	require_once "gsis_Person.php";
	require_once "gsis_Company.php";
	
	
	if($_GET['action']=="ADD"){
		$per = new Person();
		echo "Exist: "+$per->Exist("111");
		//$tran = new Transaction();
		//$tran->Add();
	}else if($_GET['action']=="SHOW"){
		if($_GET['action2']=="RSA"){
			$comp =new Company();
			$comp->Exist($_GET['COMPANY']);
			echo json_encode(array("PubKey"=>$comp->getPubKey()));
		}
	}else{
		json_encode(array("error"=>"wrong option"));
	}
?>
