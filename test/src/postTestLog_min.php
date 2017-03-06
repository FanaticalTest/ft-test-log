<?php
$feature = "toto" ;
$testSuite = "NULL";
$scenarioId = "NULL";
$scenarioName = "NULL";
$tags = "NULL";
$testStatus = "NULL";
$testTimeout = "NULL";
$testWindowsSize = "NULL";
$screenshotUrl = "NULL";
$testStartDate = "NULL";
$testEndDdate = "NULL";


$data = array("projectId" => "ft1", "feature" => $feature , "testSuite" => $testSuite , "scenarioId" => $scenarioId , "scenarioName" => $scenarioName , "tags" => $tags , "testStatus" => $testStatus , "testTimeout" => $testTimeout , "testWindowsSize" => $testWindowsSize , "screenshotUrl" => $screenshotUrl , "testStartDate" => $testStartDate , "testEndDdate" => $testEndDdate);                                                                 
$data_string = json_encode($data);                                                                                   
                                                                                                                    
$ch = curl_init('http://'.getenv('API_SERVER').':'.getenv('API_PORT').'/insertTestLog.php');                                                                      
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);                                                                  
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
    'Content-Type: application/json',                                                                                
    'Content-Length: ' . strlen($data_string))                                                                       
);                                                                                                                   
                                                                                                                    
$result = curl_exec($ch);
echo $result;
?>