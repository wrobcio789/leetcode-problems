package main

import (
	"fmt"
)

func minimumSteps(s string) int64 {
	var lastLeadingIndex = -1
	var result int64 = 0
	for index, char := range s {
		if char == '0' {
			result += int64(index - lastLeadingIndex - 1)
			lastLeadingIndex++
		}

	}

	return result
}

func main() {
	s := "100"
	result := minimumSteps(s)
	fmt.Print(result)
}
