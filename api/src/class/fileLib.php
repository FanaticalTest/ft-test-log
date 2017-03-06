<?php
class fileLib
{
    public function write($fileName , $message)
    {
        $myfile = fopen($fileName, "w") or die("Unable to open file for write!");
        fwrite($myfile, $message);
        fclose($myfile);
    }

    public function read($fileName)
    {
        $message = "";
        $myfile = fopen($fileName, "r") or die("Unable to open file for read!");
        $message = fread($myfile,filesize($fileName));
        fclose($myfile);
        return $message;
    }
}
?>