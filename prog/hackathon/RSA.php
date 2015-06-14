<?php
echo "test";
include('fullRSA.php');
$key = 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtixUGzGpLXgZ7AV1HfmIHV/FEF+fww77FekRc2oLhUOd4HitwCPo76fjtdsQBEt8w9HZ3CXVphaAU2BA6MEZJ3ShVMsdAXb2ZA1C+lu7k1GV9M/BhucTg35HujSK647Sc5MwVLwFsN80dAnGsZF8gwb2TNUzXHwzbAb30T01zuqf8RCM75OwKZFYqzu7FOVrtk/w9mh92MOXG0l7WSqNIctu8Kxka/tEJJIA5nqMGNMocjwprXy66NS7FFy1GY+NnxfFLtODqq0tllc50UCDsnqSvNmj2wcnAcsCzNOoxPPgp7t8S+sQvOzgc5W3CDjIsYEiGD+vzSVNkGiRou577wIDAQAB';

$rsa = new Crypt_RSA();
$rsa->loadKey($key);
$rsa->setPublicKey($key);

echo $rsa->getPublicKey();

//$rsa = new MyEncryption();
echo "test";
//echo "encr: ".$rsa->encrypt("lo omg 6");
?>
