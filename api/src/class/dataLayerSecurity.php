<?php
require_once "mySqlLib.php";

class dataLayerSecurity extends mySqlLib
{
    public function isIpWhiteListed($val)
    {
        $sql = "SELECT * FROM `ip_white_list`
        WHERE `ip_address` in ('".trim($val)."','0.0.0.0') ;";
        $result = parent::sQuery($sql);
        $row_cnt = $result->num_rows;
        return intval($row_cnt);
    }
}

?>