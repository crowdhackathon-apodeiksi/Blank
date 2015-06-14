<?php
require_once "connect.php";
class Company{
	public $id;
	private $db;
	function __construct(){
		$sql = new mysql();
		$this->db = $sql->db;
		
	}
	function Exist($AFM){
		$stmt = $this->db->prepare("SELECT '1'AS `Exist` FROM `gsis_company` WHERE `AFM` = :AFM");
		$stmt->execute(array(':AFM'=>$AFM));
		$row = $stmt->fetch();
		
		if(empty($row['Exist']))
			return 0;
		
		$this->id = $AFM;
		return 1;
	}
	function getPubKey(){
		echo'comp: id: '.$this->id.'<br>';
		$stmt = $this->db->prepare("SELECT `RSA` FROM `gsis_company` WHERE `AFM` = :AFM");
		$stmt->execute(array(':AFM'=>$this->id));
		$row = $stmt->fetch();
		
		if(empty($row['RSA']))
			return 0;
		
		return $row['RSA'];
	}
	function PrintAllJson($start, $end){
		$stmt = $this->db->prepare("SELECT `AFM`,`Name`,`RSA` FROM `gsis_company` LIMIT ".$start.", ".$end);
		$stmt->execute();
		while(($row = $stmt->fetch())!=null)
			json_encode($row);
	}
}
?>
