<?php
class mysql{
	public $db;
	
	function __construct(){
		//echo"mysql<br>";
		$this->db = new PDO('mysql:host=localhost;dbname=hackathon;charset=utf8', 'root', 'ascent');	
	}
}
?>
