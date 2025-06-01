package main

import (
	"fmt"
)

var OVER_MAX_VALUE = 5*10_000 + 1

type Stack []int

func (this *Stack) push(num int) {
	*this = append(*this, num)
}

func (stack *Stack) pop() int {
	var size = len(*stack)
	var result = (*stack)[size-1]
	*stack = (*stack)[0 : size-1]
	return result
}

func (stack *Stack) peek() int {
	var size = len(*stack)
	return (*stack)[size-1]
}

func (stack *Stack) empty() bool {
	return len(*stack) == 0
}

func maxWidthRamp(nums []int) int {
	var stack = &Stack{}
	stack.push(0)

	for i := range nums {
		var lowestIndex = stack.peek()
		if nums[i] < nums[lowestIndex] {
			stack.push(i)
		}
	}

	var maxLength = 0
	for i := len(nums) - 1; i >= 0 && !stack.empty(); i-- {
		for stack.peek() >= i {
			stack.pop()

			if stack.empty() {
				return maxLength
			}
		}

		var stackIndex = stack.peek()
		for nums[i] >= nums[stackIndex] {
			var length = i - stackIndex
			if length > maxLength {
				maxLength = length
			}

			stack.pop()
			if stack.empty() {
				return maxLength
			}
			stackIndex = stack.peek()
		}
	}

	return maxLength
}

func main() {
	var result = maxWidthRamp([]int{9, 9, 3, 5, 4, 0, 2, 0, 4, 1})
	fmt.Println(result)
}
