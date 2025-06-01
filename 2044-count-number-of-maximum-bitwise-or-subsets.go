package main

import (
	"fmt"
)

func countMaxOrSubsets(nums []int) int {
	var numsLength = len(nums)

	var maxOrSum = 0
	for _, num := range nums {
		maxOrSum |= num
	}

	var result = 0

	for subsetMask := range 1 << numsLength {
		var orSum = 0
		for bitIndex := range numsLength {
			if isBitOn(subsetMask, bitIndex) {
				orSum |= nums[bitIndex]
			}
		}

		if orSum == maxOrSum {
			result++
		}
	}

	return result
}

func isBitOn(mask int, bitIndex int) bool {
	return mask&(1<<bitIndex) != 0
}

func main() {
	var result = countMaxOrSubsets([]int{3, 2, 1, 5})
	fmt.Println(result)
}
