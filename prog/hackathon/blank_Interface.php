<?php
	
	require "Blank_Account.php";
	$acc = new Account();
	if($_GET['action']=="REGISTER"){
		if(isset($_GET['user']) && isset($_GET['pass'])){//registration
			$returnVal = $acc->Registration($_GET['user'],$_GET['pass']);
			echo json_encode(array("code"=>$returnVal));
		}
	}else if($_GET['action']=="LOGIN"){
			//echo "login";
			$returnVal = $acc->Authenticate($_GET['user'],$_GET['pass']);
			echo json_encode(array("code"=>$returnVal));
			if($_GET['action2']=="REGISTERCOMPANY"){
				$acc->AddCompany($_GET['companyAFM'],$_GET['cipherAFM']);
			}else if($_GET['action2']=="COMPANY_NOTIFICATION"){
			}else if($_GET['action2']=="COMMENTVIEW"){
				
			}else if($_GET['action2']=="COMMENTADD"){
				$res = $acc->addComment($_GET['TransactionID'],$_GET['TransactionID'],$_GET['Comment'],$_GET['Rating']);
				echo json_encode(array("Code"=>$res));
			}else if($_GET['action2']=="GET_COMP_FOR_COMMENT"){
				$res=  $acc->getCompaniesForComment();
				echo json_encode(array("Companies"=>$res));
			}else if($_GET['action2']=="GETPROSFORES"){
				$res = $acc->getOffers();
				echo json_encode(array("Offers",$res));
			}else if($_GET['action2']=="ADDPROSFORA"){
				$res = $acc->setOffers($_GET['compNAme'],$_GET['text'],$_GET['points']);
				echo json_encode(array("Code",$res));
			}else if($_GET['action2']=="ADDAPODIKSI"){
				$res = $acc->addTicket($_GET['AFM'],$_GET['time'],$_GET['sum']);
				echo json_encode(array("Code",$res));
			}else if($_GET['action2']=="GETAPODIKSIS"){
				$res = $acc->getTicket;
				//echo json_encode(array("Code",$res));
			}
			
		
	}else{
		echo json_encode(array("error"=>"unknown Action"));
	}
			
	
?>