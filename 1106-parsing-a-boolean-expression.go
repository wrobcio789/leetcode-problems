package main

import (
	"fmt"
	"strings"
)

type SyntaxStack []IndirectNode

func (this *SyntaxStack) push(num IndirectNode) {
	*this = append(*this, num)
}

func (stack *SyntaxStack) pop() IndirectNode {
	var size = len(*stack)
	var result = (*stack)[size-1]
	*stack = (*stack)[0 : size-1]
	return result
}

func (stack *SyntaxStack) peek() *IndirectNode {
	var size = len(*stack)
	return &(*stack)[size-1]
}

type NodeType byte

const (
	ALTERNATIVE NodeType = iota
	CONJUNCTION
	NEGATION
)

type IndirectNode struct {
	id                NodeType
	childrenEvaluated []bool
}

func NewIndirectNode(id NodeType) IndirectNode {
	return IndirectNode{id, []bool{}}
}

func parseBoolExpr(expression string) bool {

	var stack = &SyntaxStack{}
	stack.push(NewIndirectNode(ALTERNATIVE))

	for i := 0; i < len(expression); {
		var exprLeft = expression[i:]
		if strings.HasPrefix(exprLeft, ",") {
			i++
		} else if strings.HasPrefix(exprLeft, "t") {
			i++
			incorporate(stack, true)
		} else if strings.HasPrefix(exprLeft, "f") {
			i++
			incorporate(stack, false)
		} else if strings.HasPrefix(exprLeft, "!(") {
			i += 2
			stack.push(NewIndirectNode(NEGATION))
		} else if strings.HasPrefix(exprLeft, "&(") {
			i += 2
			stack.push(NewIndirectNode(CONJUNCTION))
		} else if strings.HasPrefix(exprLeft, "|(") {
			i += 2
			stack.push(NewIndirectNode(ALTERNATIVE))
		} else if strings.HasPrefix(exprLeft, ")") {
			i++
			var stackTop = stack.pop()
			var value = evaluate(&stackTop)
			incorporate(stack, value)
		}
	}

	return evaluate(stack.peek())
}

func evaluate(node *IndirectNode) bool {
	switch node.id {
	case NEGATION:
		return !node.childrenEvaluated[0]
	case ALTERNATIVE:
		for _, child := range node.childrenEvaluated {
			if child {
				return true
			}
		}
		return false

	case CONJUNCTION:
		for _, child := range node.childrenEvaluated {
			if !child {
				return false
			}
		}
		return true
	default:
		panic("unknown id")
	}
}

func incorporate(stack *SyntaxStack, value bool) {
	var top = stack.peek()
	top.childrenEvaluated = append(top.childrenEvaluated, value)
}

func main() {
	var result = parseBoolExpr("|(f,f,&(t,t,&(t,t,|(f,t))))")
	fmt.Println(result)
}
