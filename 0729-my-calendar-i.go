package main

import (
	"fmt"
)

type Segment struct {
	left   *Segment
	right  *Segment
	actual int
	lazy   int
	start  int
	end    int
}

func NewSegment(start int, end int) *Segment {
	return &Segment{nil, nil, 0, 0, start, end}
}

func (this *Segment) getMidpoint() int {
	return (this.start + this.end) / 2
}

func (this *Segment) getTotal() int {
	return this.lazy + this.actual
}

func (this *Segment) query(start int, end int) int {
	if this.start > end || this.end < start {
		return 0
	}
	if start <= this.start && this.end <= end {
		return this.getTotal();
	}

	this.updateLazy()
	var midpoint = this.getMidpoint()
	var leftSegment = getOrDefault(this.left, start, midpoint)
	var rightSegment = getOrDefault(this.right, midpoint+1, end)

	return max(leftSegment.query(start, end), rightSegment.query(start, end))
}

func (this *Segment) add(start int, end int, value int) {
	if this.start > end || this.end < start {
		return
	}
	if start <= this.start && this.end <= end {
		this.lazy += value
		return
	}

	this.updateLazy()
	this.makeSureChildrenExist()
	this.left.add(start, end, value)
	this.right.add(start, end, value)
	this.actual = max(this.left.getTotal(), this.right.getTotal())
}

func (this *Segment) updateLazy() {
	if this.lazy != 0 {
		this.makeSureChildrenExist()
		this.left.lazy += this.lazy
		this.right.lazy += this.lazy
		this.actual += this.lazy
		this.lazy = 0
	}
}

func getOrDefault(segment *Segment, start int, end int) *Segment {
	if segment == nil {
		return NewSegment(start, end)
	}
	return segment
}

func (this *Segment) makeSureChildrenExist() {
	var midpoint = this.getMidpoint()
	if this.left == nil {
		this.left = NewSegment(this.start, midpoint)
	}
	if this.right == nil {
		this.right = NewSegment(midpoint+1, this.end)
	}
}

type MyCalendar struct {
	root *Segment
}

func Constructor() MyCalendar {
	var root = &Segment{nil, nil, 0, 0, 0, 1000_000_000}
	return MyCalendar{root}
}

func (this *MyCalendar) Book(start int, end int) bool {
	if this.root.query(start, end-1) > 0 {
		return false
	}
	this.root.add(start, end-1, 1)
	return true
}

func main() {
	var calendar = Constructor()
	fmt.Println(calendar.Book(10, 20))
	fmt.Println(calendar.Book(15, 25))
	fmt.Println(calendar.Book(20, 30))
}
