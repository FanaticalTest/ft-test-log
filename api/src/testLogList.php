<?php
include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerTestLog.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerSecurity.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/securityLib.php";

$security = new securityLib();
$sqlSecurity = new dataLayerSecurity();
$ipClient = $security->getIpClient();

function main()
{
    $sqlTestLog = new dataLayerTestLog();
    if(isset($_GET['customerId']))
    {
        return $sqlTestLog->getTestLogByCustomer($_GET['customerId']);
    }
    elseif (isset($_GET['projectId']))
    {
        return $sqlTestLog->getTestLogByProject($_GET['projectId']);
    }
    else
    {
        return $sqlTestLog->getTestLog();
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
    echo main();
}
?>