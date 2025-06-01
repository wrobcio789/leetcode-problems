package main

import (
	"fmt"
	"slices"
	"sort"
	"strings"
)

type CharCount struct {
	count int
	char  rune
}

func longestDiverseString(a int, b int, c int) string {
	var array = []CharCount{{a, 'a'}, {b, 'b'}, {c, 'c'}}
	array = slices.DeleteFunc(array, func(i CharCount) bool { return i.count == 0 })
	sort.Slice(array, func(i, j int) bool {
		return array[i].count > array[j].count
	})

	var result strings.Builder

	for len(array) > 0 {
		var length = len(array)
		if length == 1 {
			count := array[0].count
			for i := 0; i < min(count, 2); i++ {
				result.WriteRune(array[0].char)
			}
			break
		}

		if array[0].count >= array[1].count+2 {
			var leadingChar = array[0].char
			result.WriteRune(leadingChar)
			result.WriteRune(leadingChar)
			result.WriteRune(array[1].char)
			array[0].count -= 2
			array[1].count -= 1
		} else {
			result.WriteRune(array[0].char)
			result.WriteRune(array[1].char)
			array[0].count -= 1
			array[1].count -= 1
		}

		array = slices.DeleteFunc(array, func(i CharCount) bool { return i.count == 0 })
		sort.Slice(array, func(i, j int) bool {
			return array[i].count > array[j].count
		})

	}

	return result.String()

}

func main() {
	result := longestDiverseString(6, 5, 5)
	fmt.Print(result)
}
