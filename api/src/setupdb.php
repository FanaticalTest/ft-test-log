<?php
/*ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);*/

include_once include $_SERVER['DOCUMENT_ROOT']."/class/dataLayerSecurity.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/jsonLib.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/schemaCreation.php";
include_once include $_SERVER['DOCUMENT_ROOT']."/class/schemaDataCreation.php";


function main()
{
    $json = new jsonLib();
    $message = array();

    $schema = new schemaCreation();
    $message = $schema->customerTable($message);
    $message = $schema->projectTable($message);
    $message = $schema->testLogTable($message);
    $message = $schema->ipWhiteList($message);

    $schemaData = new schemaDataCreation();
    $message = $schemaData->customerData($message);
    $message = $schemaData->projectData($message);
    $message = $schemaData->testLogSuccessData($message);
    $message = $schemaData->testlogFailData($message);
    $message = $schemaData->ipWhiteListData($message);

    return $json->jsonEncodeArray($message);
}

$sqlInstall = new schemaCreation();

if ($isInstalled = $sqlInstall->isDbInstalled($_SERVER['DOCUMENT_ROOT']."/config/sqlSetup.txt")=="true") 
{
    header('HTTP/1.0 403 Forbidden');
    echo "Forbidden";
    exit();
}
else
{
    echo main();
    $sqlInstall->setDbInstalled($_SERVER['DOCUMENT_ROOT']."/config/sqlSetup.txt");
}
?>