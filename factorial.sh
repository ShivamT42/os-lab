echo "Enter a number:"
read n

result=1

for ((i = 1; i <= n; i++));
do
    result=$((i * result))
done

echo $result