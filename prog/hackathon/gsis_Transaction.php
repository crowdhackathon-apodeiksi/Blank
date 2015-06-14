<?php
require_once "connect.php";
class Transaction{

	private $db;
	function __construct(){
		$sql = new mysql();
		$this->db = $sql->db;
		
	}
	function add($Person,$Company,$Sum,$Date_time,$local){
		$this->id=0;
		$stmt = $this->db->prepare("INSERT INTO `gsis_transaction`(`person`,`company`,`sum`,`local_id`,`date_time`) VALUES (:person,:company,:sum,:date_time,:local_id)");
		$stmt->execute(array(':person'=>$Person,':company'=>$Company,':sum'=>$Sum,':date_time'=>$Date_time,':local_id'=>$local));
	
		return $stmt->errorCode();
	}
	
}
?>
