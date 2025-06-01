package main

import (
	"fmt"
	"slices"
)

func maximumSwap(num int) int {
	var numCpy = num
	var digits = make([]int, 0)
	for num > 0 {
		digits = append(digits, num%10)
		num /= 10
	}
	slices.Reverse(digits)

	for i := range digits {
		var candidateIndex = -1
		for j := i + 1; j < len(digits); j++ {
			if digits[i] < digits[j] && (candidateIndex == -1 || digits[j] >= digits[candidateIndex]) {
				candidateIndex = j
			}
		}

		if candidateIndex != -1 {
			digits[candidateIndex], digits[i] = digits[i], digits[candidateIndex]

			var result = 0
			for i := range digits {
				result *= 10
				result += digits[i]
			}

			return result
		}
	}
	return numCpy
}

func main() {
	var result = maximumSwap(9973)
	fmt.Println(result)
}
