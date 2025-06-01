package main

import (
	"fmt"
)

func findKthBit(n int, k int) byte {
	if findKthBitBool(n, k-1) {
		return '1'
	} else {
		return '0'
	}
}

func findKthBitBool(n int, k int) bool {
	if k == 0 && n == 1 {
		return false
	}

	var strLength = (1 << n) - 1
	var halfLength = strLength / 2

	if k < halfLength {
		return findKthBitBool(n-1, k)
	} else if k > halfLength {
		return !findKthBitBool(n-1, 2*halfLength-k)
	} else {
		return true
	}
}

func main() {
	var result = findKthBit(3, 1)
	fmt.Println(string(rune(result)))
}
