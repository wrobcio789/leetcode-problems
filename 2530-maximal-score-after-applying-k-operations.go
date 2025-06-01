package main

import (
	"container/heap"
	"fmt"
)

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] > h[j] }
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

func maxKelements(nums []int, k int) int64 {
	var h = &IntHeap{}
	heap.Init(h)
	for _, num := range nums {
		heap.Push(h, num)
	}

	var result int64 = 0
	for _ = range k {
		var top = heap.Pop(h).(int)
		result += int64(top)
		heap.Push(h, (top+2)/3)
	}

	return result
}

func main() {
	result := maxKelements([]int{10, 10, 10, 10, 10}, 5)
	fmt.Print(result)
}
