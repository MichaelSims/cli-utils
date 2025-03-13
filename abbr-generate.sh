#!/usr/bin/env zsh

# This script was written by Junie AI.
# It generates zsh multi-stage abbreviations based on user input.
# This is not very robust, but I (@MichaelSims) didn't really need it to be.

# Function to generate zsh multi-stage abbreviations
generate_zsh_multi_stage_abbreviations() {
    local -a abbr_parts
    local -a command_parts

    # Split the input strings into arrays
    abbr_parts=("${(s/ /)1}")
    command_parts=("${(s/ /)2}")

    # First abbreviation: first letter to first word
    echo "abbr \"${abbr_parts[1]}\"=\"${command_parts[1]}\""

    # Progressive abbreviations
    for ((i=2; i<=${#abbr_parts}; i++)); do
        local abbr_cmd=""
        local cmd=""

        # Build the left side (abbreviation)
        for ((j=1; j<i; j++)); do
            if [[ -n "$abbr_cmd" ]]; then
                abbr_cmd+=" "
            fi
            abbr_cmd+="${command_parts[j]}"
        done

        # Build the right side (command)
        for ((j=1; j<=i; j++)); do
            if [[ -n "$cmd" ]]; then
                cmd+=" "
            fi
            cmd+="${command_parts[j]}"
        done

        echo "abbr \"$abbr_cmd ${abbr_parts[i]}\"=\"$cmd\""
    done

    # Final abbreviation: all letters combined to full command
    local short_abbr
    short_abbr=$(echo "${abbr_parts[@]}" | tr -d ' ')
    local full_cmd
    full_cmd="${command_parts[*]}"

    echo "abbr \"$short_abbr\"=\"$full_cmd\""
}

# Method 1: Command-line arguments
 if [[ $# -eq 2 ]]; then
     left_side="$1"
     right_side="$2"
 else
     # Method 2: Interactive prompts
     echo -n "Enter the abbreviation with spaces between each letter/group of letters (e.g., 'g r c'): "
     read -r left_side
     echo -n "Enter the full command (e.g., 'git rebase --continue'): "
     read -r right_side
 fi

# Generate abbreviations
generate_zsh_multi_stage_abbreviations "$left_side" "$right_side"
