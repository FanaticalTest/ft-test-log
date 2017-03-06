<?php
class validationLib
{
    public function truncateStringLength($val,$length)
    {
        return substr ($val, 0,$length);
    }
}
?>