<?php

$veryLongText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque pellentesque massa a volutpat. In fringilla quam justo. Praesent ultricies erat ex, non scelerisque purus vestibulum sit amet. Mauris nec fermentum sapien. Nunc enim augue, tempor ac mi at, pharetra posuere enim. Sed quis turpis sed ante posuere finibus. Aenean vel odio condimentum, sodales orci nec, molestie tellus. Pellentesque commodo pulvinar condimentum. Praesent ut aliquam massa. Donec feugiat tortor vel ultricies convallis. Aenean tincidunt, nisl ac elementum sollicitudin, dolor justo pharetra sem, a vulputate erat odio fermentum tortor. Suspendisse tristique lorem eget placerat sagittis. Proin porta diam non sem sagittis rutrum. Curabitur quis pretium sem, eleifend suscipit nibh. Donec neque velit, posuere at faucibus eu, accumsan eu mauris.Curabitur ac ex non leo dictum ornare vitae non dui. Proin eleifend erat ligula, nec viverra diam aliquet in. Nulla ac odio tortor. Sed ultricies arcu justo. Proin eu libero risus. Donec sed.Proin porta diam non sem sagittis rutrum. Curabitur quis pretium sem, eleifend suscipit nibh. Donec neque velit, posuere at faucibus eu, accumsan eu mauris.Curabitur ac ex non leo dictum ornare vitae non dui. Proin eleifend erat ligula, nec viverra diam aliquet in. Nulla ac odio tortor. Sed ultricies arcu justo. Proin eu libero risus. Donec sed.";

$feature = $veryLongText ;
$testSuite = $veryLongText;
$scenarioId = $veryLongText;
$scenarioName = $veryLongText;
$tags = $veryLongText;
$testStatus = $veryLongText;
$testTimeout = $veryLongText;
$testWindowsSize = $veryLongText;
$screenshotUrl = $veryLongText;
$testStartDate = "2017-02-22 13:21:09:00000";
$testEndDdate = "2017-02-22 13:21:09:00000";


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