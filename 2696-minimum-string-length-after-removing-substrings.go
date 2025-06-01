package main

import (
	"fmt"
)

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

func (stack *Stack) clear() {
	*stack = []int{}
}

func minLength(s string) int {
	var stack = &Stack{}

	var reducedCharCount = 0
	for _, char := range s {
		if char == 'A' || char == 'C' {
			stack.push(int(char))
		} else if char == 'B' || char == 'D' {
			if stack.empty() {
				continue
			}

			if (char == 'D' && stack.peek() == 'C') || (char == 'B' && stack.peek() == 'A') {
				stack.pop()
				reducedCharCount += 2
			} else {
				stack.clear()
			}
		} else {
			stack.clear()
		}
	}

	return len(s) - reducedCharCount
}

func main() {
	var result = minLength("AAACABDBBB")
	fmt.Println(result)
}
