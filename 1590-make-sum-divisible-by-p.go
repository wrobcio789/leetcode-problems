package main

import (
	"fmt"
	"math"
)

func minSubarray(nums []int, p int) int {
	var residual = 0
	for _, num := range nums {
		residual = (residual + num) % p
	}

	if residual == 0 {
		return 0
	}

	var result = math.MaxInt
	var summedResidual = 0
	var summedResidualToIndexMap = map[int]int{
		0: -1,
	}
	for i, num := range nums {
		if num == residual {
			return 1
		}

		summedResidual = (summedResidual + num) % p
		var leftResidual = (summedResidual - residual + p) % p
		if index, exists := summedResidualToIndexMap[leftResidual]; exists {
			var difInIndex = i - index
			if difInIndex < result {
				result = difInIndex
			}
		}

		summedResidualToIndexMap[summedResidual] = i
	}

	if result == len(nums) {
		return -1
	}

	return result
}

func main() {
	var result = minSubarray([]int{3, 6, 8, 1}, 8)
	fmt.Println(result)
}
