#!/bin/bash
factorial(){
    result=$1

    if [ $result -le 2 ];
    then
        echo $result
    else
        f=$(($result - 1))
        f=$(factorial $f)
        f=$((f * result))
        echo $f
    fi
}

echo "Enter a number:"
read num

if [ num = 0 ];
then
    echo 1
else
    factorial $num
fi