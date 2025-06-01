package main

import (
	"fmt"
)

func minSwaps(s string) int {
	var openedCount = 0
	for _, char := range s {
		if char == '[' {
			openedCount++
		} else if openedCount > 0 {
			openedCount--
		}
	}

	return (openedCount + 1) / 2
}

func main() {
	var result = minSwaps("]]][[[")
	fmt.Println(result)
}
