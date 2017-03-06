<?php
$apiUrl = "http://".getenv('API_SERVER').":".getenv('API_PORT')."/";
$veryLongText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque pellentesque massa a volutpat. In fringilla quam justo. Praesent ultricies erat ex, non scelerisque purus vestibulum sit amet. Mauris nec fermentum sapien. Nunc enim augue, tempor ac mi at, pharetra posuere enim. Sed quis turpis sed ante posuere finibus. Aenean vel odio condimentum, sodales orci nec, molestie tellus. Pellentesque commodo pulvinar condimentum. Praesent ut aliquam massa. Donec feugiat tortor vel ultricies convallis. Aenean tincidunt, nisl ac elementum sollicitudin, dolor justo pharetra sem, a vulputate erat odio fermentum tortor. Suspendisse tristique lorem eget placerat sagittis. Proin porta diam non sem sagittis rutrum. Curabitur quis pretium sem, eleifend suscipit nibh. Donec neque velit, posuere at faucibus eu, accumsan eu mauris.Curabitur ac ex non leo dictum ornare vitae non dui. Proin eleifend erat ligula, nec viverra diam aliquet in. Nulla ac odio tortor. Sed ultricies arcu justo. Proin eu libero risus. Donec sed.Proin porta diam non sem sagittis rutrum. Curabitur quis pretium sem, eleifend suscipit nibh. Donec neque velit, posuere at faucibus eu, accumsan eu mauris.Curabitur ac ex non leo dictum ornare vitae non dui. Proin eleifend erat ligula, nec viverra diam aliquet in. Nulla ac odio tortor. Sed ultricies arcu justo. Proin eu libero risus. Donec sed.";
$longText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla scelerisque pellentesque massa a volutpat. In fringilla quam justo. Praesent ultricies erat ex, non scelerisque purus vestibulum sit amet. Praesent ultricies erat ex, non scelerisque purus vestibulum sit amet.";
$urlBuild = "projectId=ft1&feature=".$veryLongText."&testSuite=".$longText."&scenarioId=".$longText."&scenarioName=".$longText."&tags=".$longText."&testStatus=".$longText."&testTimeout=".$longText."&testWindowsSize=".$longText."&screenshotUrl=".$longText."&testStartDate=2017-02-22 13:21:09:00000&testEndDdate=2017-02-22 13:21:09:00000";
?>
<html>
<head>
    <title>ft-test-log-php-test</title>
</head>
<body>
    <h3>Home - Test</h3>
    <ul>
        <li><a href="<?php echo $apiUrl ;?>setupdb.php" target="_blank">Setup database (Only use if the automated creation failed)</a></li>
        <li><a href="<?php echo $apiUrl ;?>testLogList.php" target="_blank">Test Log List (Json)</a></li>
        <li><a href="<?php echo $apiUrl ;?>testLogList.php?customerId=Z1" target="_blank">Test Log List By Customer (Json)</a></li>
        <li><a href="<?php echo $apiUrl ;?>testLogList.php?projectId=ft1" target="_blank">Test Log List By Project (Json)</a></li>
        <li><a href="<?php echo $apiUrl ;?>projectList.php?customerId=Z1" target="_blank">Project List by Customer (Json)</a></li>
        <li><a href="<?php echo $apiUrl ;?>insertTestLog.php?projectId=ft1&feature=toto&testSuite=toto&scenarioId=toto&scenarioName=toto&tags=toto&testStatus=toto&testTimeout=toto&testWindowsSize=toto&screenshotUrl=toto&testStartDate=2017-02-22 13:21:09&testEndDdate=2017-02-22 13:21:09" target="_blank">Insert Test Log - happy path(GET)</a></li>
        <li><a href="<?php echo $apiUrl ;?>insertTestLog.php?projectId=ft1" target="_blank">Insert Test Log - minimal param (GET)</a></li>
        <li><a href="<?php echo $apiUrl ;?>insertTestLog.php?<?php echo $urlBuild ;?>" target="_blank">Insert Test Log - max value (GET)</a></li>
        <li><a href="postTestLog_hp.php" target="_blank">Insert Test Log - happy path (POST)</a></li>
        <li><a href="postTestLog_min.php" target="_blank">Insert Test Log - min value (POST)</a></li>
        <li><a href="postTestLog_max.php" target="_blank">Insert Test Log - max value (POST)</a></li>
        <li><a href="phpinfo.php" target="_blank">PhpInfo</a></li>
    </ul>
<p>IP Client :     
<?php
if (!empty($_SERVER['HTTP_CLIENT_IP'])) {
    $ip = $_SERVER['HTTP_CLIENT_IP'];
} elseif (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {
    $ip = $_SERVER['HTTP_X_FORWARDED_FOR'];
} else {
    $ip = $_SERVER['REMOTE_ADDR'];
}
echo $ip;
?></p> 
</body>
</html>