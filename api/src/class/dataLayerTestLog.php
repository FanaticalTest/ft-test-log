<?php
require_once "mySqlLib.php";
require_once "jsonLib.php";
require_once "validationLib.php";

class dataLayerTestLog extends mySqlLib
{
    // Insert function
    public function insertTestLog($projectId, $feature, $testSuite, $scenarioId, $scenarioName, $tags, $testStatus, $testTimeout, $testWindowsSize, $screenshotUrl, $testStartDate, $testEndDdate )
    {
        $json = new jsonLib();
        $validation = new validationLib();

        // check for injection pattern in query string and validate 
        $projectId = parent::escape($validation->truncateStringLength($projectId,20));
        $feature = parent::escape($validation->truncateStringLength($feature,1024));
        $testSuite = parent::escape($validation->truncateStringLength($testSuite,1024));
        $scenarioId = parent::escape($validation->truncateStringLength($scenarioId,255));
        $scenarioName = parent::escape($validation->truncateStringLength($scenarioName,1024));
        $tags = parent::escape($validation->truncateStringLength($tags,1024));
        $testStatus = parent::escape($validation->truncateStringLength($testStatus,20));
        $testTimeout = parent::escape($validation->truncateStringLength($testTimeout,255));
        $testWindowsSize = parent::escape($validation->truncateStringLength($testWindowsSize,255));
        $screenshotUrl = parent::escape($validation->truncateStringLength($screenshotUrl,1024));
        $testStartDate = parent::escape($validation->truncateStringLength($testStartDate,19));
        $testEndDdate = parent::escape($validation->truncateStringLength($testEndDdate,19));

        // Insert data
        $sql = "INSERT INTO `test_logs` (`project_id`, `feature`, `test_suite`, `scenario_id`, `scenario_name`, `tags`, `test_status`, `test_timeout`, `test_windows_size`, `screenshot_url`, `test_start_date`, `test_end_date`) VALUES " ;
        $sql .= "( ".(($projectId=='NULL')?"NULL":("'".$projectId."'"))." ,";
        $sql .= " ".(($feature=='NULL')?"NULL":("'".$feature."'"))." ,";
        $sql .= " ".(($testSuite=='NULL')?"NULL":("'".$testSuite."'"))." ,";
        $sql .= " ".(($scenarioId=='NULL')?"NULL":("'".$scenarioId."'"))." ,";
        $sql .= " ".(($scenarioName=='NULL')?"NULL":("'".$scenarioName."'"))." ,";
        $sql .= " ".(($tags=='NULL')?"NULL":("'".$tags."'"))." ,";
        $sql .= " ".(($testStatus=='NULL')?"NULL":("'".$testStatus."'"))." ,";
        $sql .= " ".(($testTimeout=='NULL')?"NULL":("'".$testTimeout."'"))." ,";
        $sql .= " ".(($testWindowsSize=='NULL')?"NULL":("'".$testWindowsSize."'"))." ,";
        $sql .= " ".(($screenshotUrl=='NULL')?"NULL":("'".$screenshotUrl."'"))." ,";
        $sql .= " ".(($testStartDate=='NULL')?"NULL":("'".$testStartDate."'"))." ,";
        $sql .= " ".(($testEndDdate=='NULL')?"NULL":("'".$testEndDdate."'"))." ); ";
        return $json->jsonEncodeSqlReturnedMessage("is_insertion_success", parent::sQuery($sql));
    }

    //function to get the data from db
    public function getTestLog()
    {
        $sql = "SELECT `c`.`name` AS `customer_name`,
        `c`.`customer_id` AS `customer_id`,
        `p`.`name` AS `project_name`,
        `tl`.`project_id` AS `project_id`,
        `tl`.`feature` AS `feature`,
        `tl`.`test_suite` AS `test_suite`,
        `tl`.`scenario_id` AS `scenario_id`,
        `tl`.`scenario_name` AS `scenario_name`,
        `tl`.`screenshot_url` AS `screenshot_url`,
        `tl`.`tags` AS `tags`,
        `tl`.`test_start_date` AS `test_start_date`,
        `tl`.`test_end_date` AS `test_end_date`,
        `tl`.`test_status` AS `test_status`,
        `tl`.`test_timeout` AS `test_timeout`,
        `tl`.`test_windows_size` AS `test_windows_size`
        FROM `test_logs` AS `tl` 
        INNER JOIN `projects` AS `p` 
        ON `p`.`project_id` = `tl`.`project_id`
        INNER JOIN `customers` AS `c` 
        ON `p`.`customer_id` = `c`.`customer_id`
        ORDER BY `tl`.`id` DESC;";
        $result = parent::sQuery($sql);
        $jsonEncode = new jsonLib();
        echo $jsonEncode->jsonEncodeSqlResult($result);
    }

    //function to get the data from db
    public function getTestLogByCustomer($customerId)
    {
        $sql = "SELECT `c`.`name` AS `customer_name`,
        `c`.`customer_id` AS `customer_id`,
        `p`.`name` AS `project_name`,
        `tl`.`project_id` AS `project_id`,
        `tl`.`feature` AS `feature`,
        `tl`.`test_suite` AS `test_suite`,
        `tl`.`scenario_id` AS `scenario_id`,
        `tl`.`scenario_name` AS `scenario_name`,
        `tl`.`screenshot_url` AS `screenshot_url`,
        `tl`.`tags` AS `tags`,
        `tl`.`test_start_date` AS `test_start_date`,
        `tl`.`test_end_date` AS `test_end_date`,
        `tl`.`test_status` AS `test_status`,
        `tl`.`test_timeout` AS `test_timeout`,
        `tl`.`test_windows_size` AS `test_windows_size`
        FROM `test_logs` AS `tl` 
        INNER JOIN `projects` AS `p` 
        ON `p`.`project_id` = `tl`.`project_id`
        INNER JOIN `customers` AS `c` 
        ON `p`.`customer_id` = `c`.`customer_id`
        WHERE `c`.`customer_id` = '".$customerId."' ;";
        $result = parent::sQuery($sql);
        $jsonEncode = new jsonLib();
        echo $jsonEncode->jsonEncodeSqlResult($result);
    }

    //function to get the data from db
    public function getTestLogByProject($projectId)
    {
        $sql = "SELECT `p`.`name` AS `project_name`,
        `tl`.`project_id` AS `project_id`,
        `tl`.`feature` AS `feature`,
        `tl`.`test_suite` AS `test_suite`,
        `tl`.`scenario_id` AS `scenario_id`,
        `tl`.`scenario_name` AS `scenario_name`,
        `tl`.`screenshot_url` AS `screenshot_url`,
        `tl`.`tags` AS `tags`,
        `tl`.`test_start_date` AS `test_start_date`,
        `tl`.`test_end_date` AS `test_end_date`,
        `tl`.`test_status` AS `test_status`,
        `tl`.`test_timeout` AS `test_timeout`,
        `tl`.`test_windows_size` AS `test_windows_size`
        FROM `test_logs` AS `tl` 
        INNER JOIN `projects` AS `p` 
        ON `p`.`project_id` = `tl`.`project_id`
        WHERE `p`.`project_id` = '".$projectId."' ;";
        $result = parent::sQuery($sql);
        $jsonEncode = new jsonLib();
        echo $jsonEncode->jsonEncodeSqlResult($result);
    }
}
?>