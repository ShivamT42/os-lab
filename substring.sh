function check_substring() {
    string="$1"
    substring="$2"

    if [ -z "$substring" ]; then
        echo "Error: Substring cannot be empty."
        return 1
    fi

    len1=${#string}
    len2=${#substring}
    index=0

    while [ "$index" -le "$((len1 - len2))" ]; do
        if [ "${string:index:len2}" = "$substring" ]; then
            return 0
        fi
        index=$((index + 1))
    done

    return 1
}


read -p "Enter the main string: " main_string
read -p "Enter the substring to check: " sub_string

if check_substring "$main_string" "$sub_string"; 
then
    echo "Substring '$sub_string' is present in '$main_string'."
else
    echo "Substring '$sub_string' is not present in '$main_string'."
fi

