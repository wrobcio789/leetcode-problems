package main

import (
	"fmt"
	"sort"
)

type IntervalPoint struct {
	point   int
	isBegin bool
}

func minGroups(intervals [][]int) int {
	var points = make([]IntervalPoint, 2*len(intervals))
	for index, interval := range intervals {
		points[2 * index].point = interval[0]
		points[2 * index].isBegin = true
		points[2 * index + 1].point = interval[1]
		points[2 * index + 1].isBegin = false
	}
	sort.Slice(points, func(i, j int) bool {
		var a, b = points[i], points[j]
		if a.point == b.point {
			return a.isBegin
		}

		return a.point < b.point
	})

	var index, result = 0, 0

	for _, elem := range points {
		if elem.isBegin {
			index++
			result = max(index, result)
		} else {
			index--
		}
	}

	return result
}

func main() {
	var result = minGroups([][]int{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}})
	fmt.Println(result)
}
