echo "enter the number:"
read n

reult=0
while [ $n -gt 0 ]
do
    d=$((n / 10))
    l=$((n % 10))
    result=$((result + l))
    n=$d
done

echo $result