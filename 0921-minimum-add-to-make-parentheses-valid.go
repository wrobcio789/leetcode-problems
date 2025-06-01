package main

import (
	"fmt"
)

func minAddToMakeValid(s string) int {
	var result = 0
	var openedCount = 0
	for _, char := range s {
		if char == '(' {
			openedCount++
		} else {
			if openedCount > 0 {
				openedCount--
			} else {
				result++
			}
		}
	}
	result += openedCount

	return result
}

func main() {
	var result = minAddToMakeValid("(((()))")
	fmt.Println(result)
}
