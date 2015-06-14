<?php
require_once "connect.php";
class Account{
	public $id;
	private $db;
	function __construct(){
		//echo"Account<br>";
		$sql = new mysql();
		$this->db = $sql->db;
		
	}
	function Authenticate($user,$pass){
		$this->id=0;
		$stmt = $this->db->prepare("SELECT `id` FROM `blank_user` WHERE `user` = :user AND `pass` = :pass");
		$stmt->execute(array(':user'=>$user,':pass'=>$pass));
		$row = $stmt->fetch();
		
		if(empty($row['id']))
			return 0;
		$this->id = $row['id'];
		return 1;
	}
	function Registration($user,$pass){
		$stmt = $this->db->prepare("INSERT INTO `blank_user` (user, pass,company) VALUES (:user, :pass,false)");
		$stmt->bindParam(':user', $user);
		$stmt->bindParam(':pass', $pass);
		$stmt->execute();
		return $stmt->errorCode();
	}
	function AddCompany($Company_AFM ,$SignedCompany_AFM){
		if(empty($this->id))
			return -1;
		$stmt = $this->db->prepare("UPDATE `blank_user` SET `company`=:company WHERE `id`=:ID");
		$compAFM = $this->DecryptCompany($Company_AFM,$SignedCompany_AFM);
		$stmt->bindParam(':company', $compAFM);
		$stmt->bindParam(':ID', $this->id);
		$stmt->execute();
		return $stmt->errorCode();
	}
	function DecryptCompany($CompanyAFM,$cipherAFM){
		$this->getPubKeyFromGSIS($CompanyAFM);
	}
	function getPubKeyFromGSIS($CompanyAFM){
		$json = file_get_contents('http://192.168.1.2:8080/gsis_Interface.php?action=SHOW&action2=RSA&COMPANY='.$CompanyAFM);
		$obj = json_decode($json);
		echo $obj->access_token;
		
		
	}
	function getCompaniesForComment(){
		$stmt = $this->db->prepare("SELECT `id` FROM `blank_transaction` WHERE `user` = :user");
		$stmt->execute(array(':user'=>$this->id));
		echo 'id: '.$this->id;
		$arr=array();
		$i=0;
		while(($row = $stmt->fetch())){
			array_push($arr,array($i,$row['id']));
			$i++;
		}
		return $arr;
	}
	function addComment($id,$msg,$rating){
		$stmt = $this->db->prepare("INSERT INTO `blank_comment` VALUES(:id,:person,:comment,:rating)");
		$stmt->execute(array(':id'=>$id,':person'=>$this->id,':comment'=>$msg,':rating'=>$rating));
		return $stmt->errorCode();
	}
	function getOffers(){
		$stmt = $this->db->prepare("SELECT `id`,`compName`,`text`,`point` FROM `Blank_offers`");
		$stmt->execute();
		$arr = array();
		while(($row=$stmt->fetch())){
			array_push($arr, array("id"=>$row['id'],"compName"=>$row['compName'],"text"=>$row['text'],"points"=>$row['point']));
		}
		return $arr;
	}
	function setOffers($nameComp,$text,$points){
		$stmt = $this->db->prepare("INSERT INTO `Blank_offers`(`compAFM`,`compName`,`text`,`point`) VALUES (:compAFM, :compName, :text, :point)");
		$stmt->execute(array(":compAFM"=>$this->getMyCompAFM(),":compName"=>$nameComp,":text"=>$text,":point"=>$points));
		return $stmt->errorCode();
	}
	function getMyCompAFM(){
		$stmt = $this->db->prepare("SELECT `comp` FROM `blank_user` WHERE `id`=:id");
		$stmt->execute(array("id"=>$this->id));
		$row = $stmt->fetch();
		return $row['id'];
	}
	function addTicket($AFM,$Time,$sum){
		$stmt = $this->db->prepare("INSERT INTO `User`,`AFM`,`time`,`sum` FROM `blank_user` WHERE `id`=:id");
		$stmt->execute(array("id"=>$this->id));
		$row = $stmt->fetch();
		return $row['id'];
	}
	function getTicket(){
		$stmt = $this->db->prepare("SELECT `AFM`,`time`,`sum` FROM `blank_mobile_copy_if_user_agree` WHERE `User`=:id");
		$stmt->execute(array(":id"=>$this->id));
		$arr = array();
		while($row=$stmt->fetch()){
			array_push($arr,array("AFM"=>$row['AFM'],"time"=>$row['time'],"sum"=>$row['sum']));
		}
		$row = $stmt->fetch();
		return $row['id'];
	}
}//Account
?>
