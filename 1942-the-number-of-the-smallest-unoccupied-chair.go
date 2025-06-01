package main

import (
	"container/heap"
	"fmt"
	"sort"
)

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x any) {
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type GatingPoint struct {
	point       int
	isArrival   bool
	friendIndex int
}

func smallestChair(times [][]int, targetFriend int) int {
	var availableChairs = &IntHeap{}
	heap.Init(availableChairs)
	for index := range times {
		heap.Push(availableChairs, index)
	}

	var friendToChairMap = make(map[int]int)

	var points = make([]GatingPoint, 2*len(times))
	for index, time := range times {
		points[2*index].point = time[0]
		points[2*index].isArrival = true
		points[2*index].friendIndex = index
		points[2*index+1].point = time[1]
		points[2*index+1].isArrival = false
		points[2*index+1].friendIndex = index
	}
	sort.Slice(points, func(i, j int) bool {
		var a, b = points[i], points[j]
		if a.point == b.point {
			return !a.isArrival
		}
		return a.point < b.point
	})

	for _, elem := range points {
		if elem.friendIndex == targetFriend {
			return heap.Pop(availableChairs).(int)
		}

		if elem.isArrival {
			var occupiedChair = heap.Pop(availableChairs).(int)
			friendToChairMap[elem.friendIndex] = occupiedChair
		} else {
			var occupiedChair = friendToChairMap[elem.friendIndex]
			heap.Push(availableChairs, occupiedChair)
		}
	}

	return -1
}

func main() {
	var result = smallestChair([][]int{{4, 5}, {12, 13}, {5, 6}, {1, 2}, {8, 9}, {9, 10}, {6, 7}, {3, 4}, {7, 8}, {13, 14}, {15, 16}, {14, 15}, {10, 11}, {11, 12}, {2, 3}, {16, 17}}, 15)
	fmt.Println(result)
}
