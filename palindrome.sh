
read -p "Enter a string: " user_input

length=${#user_input}
i=0
j=$((length - 1))

while [ "$i" -lt "$j" ]; do
    if [ "${user_input:i:1}" != "${user_input:j:1}" ]; then
        echo "The string is not a palindrome."
        exit
    fi
    ((i++))
    ((j--))
done

echo "The string is a palindrome."

