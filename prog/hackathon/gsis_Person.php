<?php
require_once "connect.php";
class Person{
	public $id;
	private $db;
	function __construct(){
		$sql = new mysql();
		$this->db = $sql->db;
		
	}
	function Exist($AFM){
		$this->id=0;
		$stmt = $this->db->prepare("SELECT '1'AS `Exist` FROM `gsis_person` WHERE `AFM` = :AFM");
		$stmt->execute(array(':AFM'=>$AFM));
		$row = $stmt->fetch();
		
		if(empty($row['Exist']))
			return 0;
		return 1;
	}
	
}
?>
