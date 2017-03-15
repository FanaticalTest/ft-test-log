<?php
/*ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);*/

include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerProject.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerSecurity.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/securityLib.php";

$security = new securityLib();
$sqlSecurity = new dataLayerSecurity();
$ipClient = $security->getIpClient();

function main()
{
    $sqlProject = new dataLayerProject();
    
    if(isset($_GET['customerId']))
    {
        return $sqlProject->getProjectListByCustomer($_GET['customerId']);
    }
    else
    {
        $json = new jsonLib();
        $message = array("is_param_available"=>"false");
        return $json->jsonEncodeArray($message);
    }
}

if ($sqlSecurity->isIpWhiteListed($ipClient)==0) 
{
    header('HTTP/1.0 403 Forbidden');
    echo "Forbidden";
    exit();
}
else
{
    header('Content-Type: application/json; charset=utf-8');
    echo main();
}
?>