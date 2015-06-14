<?php
require_once "connect.php";
class OpenData{
	
	private $db;
	function __construct(){
		//echo"Account<br>";
		$sql = new mysql();
		$this->db = $sql->db;
		
	}
	function ADD($item,$date_time,$price,$location){
		$this->id=0;
		$stmt = $this->db->prepare("INSERT INTO `blank_OPEN_DATA` VALUES(:item,:date_time,:price,:location)");
		$stmt->execute(array(':item'=>$item,':date_time'=>$date_time,':price'=>$price,':location'=>$location));
		$row = $stmt->fetch();
		return $stmt->errorCode();
	}
	/*
	function getData($i,$pass){
		$stmt = $this->db->prepare("INSERT INTO `blank_user` (user, pass,company) VALUES (:user, :pass,false)");
		$stmt->bindParam(':user', $user);
		$stmt->bindParam(':pass', $pass);
		$stmt->execute();
		return $stmt->errorCode();
	}
	*/
	
}
?>
