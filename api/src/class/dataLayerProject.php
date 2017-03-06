<?php
require_once "mySqlLib.php";
require_once "jsonLib.php";
require_once "validationLib.php";

class dataLayerProject extends mySqlLib
{
    public function getProjectListByCustomer($customerId)
    {
        $sql = "SELECT `project_id`, `name`
        FROM `projects`
        WHERE `customer_id` = '".$customerId."' ;";
        $result = parent::sQuery($sql);
        $jsonEncode = new jsonLib();
        echo $jsonEncode->jsonEncodeSqlResult($result);
    }
}

?>