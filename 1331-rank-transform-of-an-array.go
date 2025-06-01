package main

import (
	"fmt"
	"slices"
	"sort"
)

func arrayRankTransform(arr []int) []int {
	if len(arr) == 0 {
		return []int{}
	}

	var arrCopy = make([]int, len(arr))
	copy(arrCopy, arr)
	sort.Ints(arrCopy)
	arrCopy = slices.Compact(arrCopy)

	var result = make([]int, len(arr))
	for index, elem := range arr {
		result[index] = binarySearchIndex(arrCopy, elem) + 1
	}

	return result

}

func binarySearchIndex(arr []int, elem int) int {
	var left, right = 0, len(arr)
	for left < right {
		var center = (left + right) / 2
		if arr[center] > elem {
			right = center
		} else if arr[center] < elem {
			left = center
		} else {
			return center
		}
	}
	return -1
}

func main() {
	var result = arrayRankTransform([]int{10, 10, 10})
	fmt.Println(result)
}
