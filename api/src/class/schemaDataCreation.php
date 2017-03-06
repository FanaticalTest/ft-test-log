<?php
require_once "mySqlLib.php";
require_once "jsonLib.php";

class schemaDataCreation extends mySqlLib
{
    public function customerData($message)
    {
        $sql = "INSERT INTO `customers` (`id` ,`customer_id` ,`name`) VALUES";
        $sql .= "(1 ,'Z1' ,'Ziridis'),";
        $sql .= "(2 ,'F2' ,'FanaticalTest'); ";
        $newMessage = array ("is_test_data_inserted_customers"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function projectData($message)
    {
        $sql = "INSERT INTO `projects` (`id` ,`project_id` , `customer_id` ,`name`) VALUES";
        $sql .= "(1 ,'ft1' ,'Z1' ,'ft-test-log-php'),";
        $sql .= "(2 ,'ft2' ,'Z1' ,'ft-demo-website'); ";
        $newMessage = array ("is_test_data_inserted_projects"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function testLogSuccessData($message)
    {
        $date = date("Y-m-d H:i:s");
        $sql = "INSERT INTO `test_logs` (`id` ,`project_id` ,`feature` ,`test_suite`, `scenario_id`, `scenario_name`, `tags`, `test_status`, `test_timeout`, `test_windows_size`, `test_start_date`, `test_end_date`) ";
        $sql .= "VALUES (1 ,'ft1' ,'Shopping cart' ,'Boutique', '12.3', 'Add item in shoppig cart', 'tag1 tag2', 'PASSED', '20', '1024 by 800', '{$date}', '{$date}');";
        $newMessage = array ("is_test_data_inserted_test_logs_success"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }
    public function testlogFailData($message)
    {
        $date = date("Y-m-d H:i:s");
        $sql = "INSERT INTO `test_logs` (`id` ,`project_id` ,`feature` ,`test_suite`, `scenario_id`, `scenario_name`, `tags`, `test_status`, `test_timeout`, `test_windows_size`, `screenshot_url`, `test_start_date`, `test_end_date`) ";
        $sql .= "VALUES (2 ,'ft1' ,'Security' ,'Authetication' , '14.3', 'Login with correct user', 'tag1 tag2', 'FAILED', '20', '1024 by 800', 'screenshot.png', '{$date}', '{$date}');";
        $newMessage = array ("is_test_data_inserted_test_logs_fail"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function ipWhiteListData($message)
    {
        $sql = "INSERT INTO `ip_white_list` (`id` ,`ip_address` , `description` ) VALUES";
        $sql .= "(1 ,'127.0.0.1' ,'Local adress'),";
        $sql .= "(2 ,'192.168.99.100' ,'Old Docker IP adress'), ";
        $sql .= "(3 ,'192.168.0.145' ,'Mac Docker IP adress - Home'), ";
        $sql .= "(4 ,'172.10.20.3' ,'Mac Docker IP adress - Outside'), ";
        $sql .= "(5 ,'192.168.99.1' ,'Old Docker IP adress'), ";
        $sql .= "(6 ,'0.0.0.0' ,'All allowed. Need remopve this line when in prod.'); ";
        $newMessage = array ("is_test_data_inserted_ip_white_list"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }
}
?>