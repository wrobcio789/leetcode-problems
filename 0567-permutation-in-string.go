package main

import (
	"fmt"
)

type RuneCountMap = map[rune]int

func checkInclusion(str string, container string) bool {
	if len(str) > len(container) {
		return false
	}

	var strFootprint = RuneCountMap{}
	for _, char := range str {
		strFootprint[char]++
	}

	var accumulator = RuneCountMap{}
	var strLength = len(str)

	var i, j = 0, 0
	for i = 0; i < len(container); i++ {
		for j = i; j < len(container); j++ {

			var char = rune(container[j])
			var _, couldAdd = shouldBeAdded(char, strFootprint, accumulator)

			if couldAdd {
				accumulator[char]++

				if j-i >= strLength {
					var lastChar = rune(container[i])
					accumulator[lastChar]--
					i++
				}

				if isAllCollected(strFootprint, accumulator) {
					return true
				}

			} else {
				i = j
				accumulator = RuneCountMap{}
				break
			}
		}

		if isAllCollected(strFootprint, accumulator) {
			return true
		}

		if j == len(container) {
			break
		}
	}

	return false
}

func isAllCollected(footprint RuneCountMap, accumulator RuneCountMap) bool {
	if len(footprint) != len(accumulator) {
		return false
	}
	for k, v := range footprint {
		if accumulator[k] != v {
			return false
		}
	}

	return true
}

func shouldBeAdded(char rune, footprint RuneCountMap, accumulator RuneCountMap) (bool, bool) {
	return accumulator[char] < footprint[char], footprint[char] > 0
}

func main() {
	var result = checkInclusion("adc", "dcda")
	fmt.Println(result)
}
