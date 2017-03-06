<?php
require_once "mySqlLib.php";
require_once "jsonLib.php";

class schemaCreation extends mySqlLib
{
    public function customerTable($message)
    {
        // Create table customers
        $sql = "CREATE TABLE IF NOT EXISTS `customers` ( ";
        $sql .= "`id` int(11) NOT NULL AUTO_INCREMENT, ";
        $sql .= "`customer_id` varchar(20) NOT NULL, ";
        $sql .= "`name` varchar(255) NOT NULL, ";
        $sql .= "UNIQUE (`customer_id`), ";
        $sql .= "PRIMARY KEY (`id`) ";
        $sql .=  ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8; ";
        $newMessage = array ("is_table_created_customers"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function projectTable($message)
    {
        $sql = "CREATE TABLE IF NOT EXISTS `projects` ( ";
        $sql .= "`id` int(11) NOT NULL AUTO_INCREMENT, ";
        $sql .= "`project_id` varchar(20) NOT NULL, ";
        $sql .= "`customer_id` varchar(20) NOT NULL, ";
        $sql .= "`name` varchar(255) NOT NULL, ";
        $sql .= "UNIQUE (`project_id`), ";
        $sql .= "PRIMARY KEY (`id`) ";
        $sql .=  ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8; ";
        $newMessage = array ("is_table_created_projects"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function testLogTable($message)
    {
        $sql = "CREATE TABLE IF NOT EXISTS `test_logs` ( ";
        $sql .= "`id` int(11) NOT NULL AUTO_INCREMENT, ";
        $sql .= "`project_id` varchar(20) NOT NULL, ";
        $sql .= "`feature` varchar(1024), ";
        $sql .= "`test_suite` varchar(1024), ";
        $sql .= "`scenario_id` varchar(255), ";
        $sql .= "`scenario_name` varchar(1024), ";
        $sql .= "`screenshot_url` varchar(1024), ";
        $sql .= "`tags` varchar(1024), ";
        $sql .= "`test_start_date` datetime, ";
        $sql .= "`test_end_date` datetime, ";
        $sql .= "`test_status` varchar(20), ";
        $sql .= "`test_timeout` varchar(255), ";
        $sql .= "`test_windows_size` varchar(255), ";
        $sql .= "PRIMARY KEY (`id`) ";
        $sql .=  ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8; ";
        $newMessage = array ("is_table_created_test_logs"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function ipWhiteList($message)
    {
        $sql = "CREATE TABLE IF NOT EXISTS `ip_white_list` ( ";
        $sql .= "`id` int(11) NOT NULL AUTO_INCREMENT, ";
        $sql .= "`ip_address` varchar(45) NOT NULL, ";
        $sql .= "`description` varchar(255), ";
        $sql .= "UNIQUE (`ip_address`), ";
        $sql .= "PRIMARY KEY (`id`) ";
        $sql .=  ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8; ";
        $newMessage = array ("is_table_created_ip_white_list"=>parent::sQuery($sql));
        array_push( $message, $newMessage);
        return $message;
    }

    public function isDbInstalled($fileName)
    {
        $message = "";
        $file = new fileLib();
        $message = $file->read($fileName);
        return $message;
    }

    public function setDbInstalled($fileName)
    {
        $txt = "true";
        $file = new fileLib();
        $file->write($fileName, $txt);
        return $txt;
    }
}
?>