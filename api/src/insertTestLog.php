<?php
/*ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);*/

include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerTestLog.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/securityLib.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerSecurity.php";

$security = new securityLib();
$sqlSecurity = new dataLayerSecurity();
$ipClient = $security->getIpClient();

function main()
{
    $feature = ((isset($_GET['feature'])) ? $_GET['feature'] : "NULL");
    $testSuite = ((isset($_GET['testSuite'])) ? $_GET['testSuite'] : "NULL");
    $scenarioId = ((isset($_GET['scenarioId'])) ? $_GET['scenarioId'] : "NULL");
    $scenarioName = ((isset($_GET['scenarioName'])) ? $_GET['scenarioName'] : "NULL");
    $tags = ((isset($_GET['tags'])) ? $_GET['tags'] : "NULL");
    $testStatus = ((isset($_GET['testStatus'])) ? $_GET['testStatus'] : "NULL");
    $testTimeout = ((isset($_GET['testTimeout'])) ? $_GET['testTimeout'] : "NULL");
    $testWindowsSize = ((isset($_GET['testWindowsSize'])) ? $_GET['testWindowsSize'] : "NULL");
    $screenshotUrl = ((isset($_GET['screenshotUrl'])) ? $_GET['screenshotUrl'] : "NULL");
    $testStartDate = ((isset($_GET['testStartDate'])) ? $_GET['testStartDate'] : "NULL");
    $testEndDdate = ((isset($_GET['testEndDdate'])) ? $_GET['testEndDdate'] : "NULL");

    //recieve data 
    if(isset($_GET['projectId']))
    {
        // if get method
        $data = array("projectId" => $_GET['projectId'], "feature" => $feature , "testSuite" => $testSuite , "scenarioId" => $scenarioId , "scenarioName" => $scenarioName , "tags" => $tags , "testStatus" => $testStatus , "testTimeout" => $testTimeout , "testWindowsSize" => $testWindowsSize , "screenshotUrl" => $screenshotUrl , "testStartDate" => $testStartDate , "testEndDdate" => $testEndDdate); 
    }
    else
    {
        // if post method
        $data = json_decode(file_get_contents('php://input'), true);
    }
    //insert data in db
    $sqlTestLog = new dataLayerTestLog();
    return $sqlTestLog->insertTestLog($data[projectId], $data[feature], $data[testSuite], $data[scenarioId], $data[scenarioName], $data[tags], $data[testStatus], $data[testTimeout], $data[testWindowsSize], $data[screenshotUrl], $data[testStartDate], $data[testEndDdate]);
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