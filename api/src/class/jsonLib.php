<?php
class jsonLib
{
    public function jsonEncodeSqlResult($result)
    {
        $rows = array();
        while($r = mysqli_fetch_assoc($result)) {
            $rows[] = $r;
        }
        return $this->jsonEncodeArray($rows);
    }

    public function jsonEncodeSqlReturnedMessage($titleMessage,$valueMessage)
    {
        $rows = array();
        $rows = array($titleMessage => $valueMessage); 
        return $this->jsonEncodeArray($rows);
    }

    public function jsonEncodeArray($data)
    {
        return json_encode($data);
    }
}
?>