<?php
require_once "fileLib.php";

class mySqlLib
{
    public function oMysqli()
    {
        return new mysqli(getenv('SQL_SERVER'),getenv('SQL_USER'),getenv('SQL_PASSWORD'),getenv('SQL_DB'),getenv('SQL_PORT'));
    }

    public function logError($errorMessage)
    {
        /*$fileName = $_SERVER['DOCUMENT_ROOT']."/logs/".date("Y-m-d-H-i-s").".txt";
        $myfile = fopen($fileName, "w") or die("Unable to open file for write!");
        fwrite($myfile, $errorMessage);
        fclose($myfile);*/
        $file = new fileLib();
        $file->write($_SERVER['DOCUMENT_ROOT']."/logs/".date("Y-m-d-H-i-s").".txt", $errorMessage);
        return $errorMessage;
    }

    public function escape($val)
    {
        try 
        {
            $escape = $this->oMysqli();
            $val = $escape->real_escape_string($val);
            $escape->close();
            return $val;
        }
        catch (Exception $e)
        {
            $this->logError("Unexpcted error: " . $e->getMessage());
            return $e->getMessage();
        }
    }

    public function sQuery($sql)
    {
        try
        {
            $mysqli = $this->oMysqli();

            if ($mysqli->connect_errno)
            {
                $this->logError("Failed to connect to MySQL: " . $mysqli->connect_error);
                exit();
            }

            $result = $mysqli->query($sql);

            $mysqli->close();

            //TODO : Test
            (($result==0) ? ($this->logError("Issue with the query: " . $sql)) : true ) ;

            return $result;
        }
        catch (Exception $e)
        {
            $this->logError("Unexpcted error: " . $e->getMessage());
            return $e->getMessage();
        }
    }
}
?>