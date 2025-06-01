package main

import (
	"fmt"
	"math"
	"strings"
)

func areSentencesSimilar(sentence1 string, sentence2 string) bool {
	var shorter, longer = orderSentences(sentence1, sentence2)
	var wordsShorter, wordsLonger = strings.Split(shorter, " "), strings.Split(longer, " ")
	var lengthDif = len(wordsLonger) - len(wordsShorter)

	var leftIndex, rightIndex = math.MinInt, math.MaxInt
	for i := 0; i < len(wordsShorter) && wordsShorter[i] == wordsLonger[i]; i++ {
		leftIndex = i
	}

	var leftBoundary = max(0, leftIndex)
	for i := len(wordsShorter) - 1; i >= leftBoundary && wordsShorter[i] == wordsLonger[i+lengthDif]; i-- {
		rightIndex = i
	}

	return rightIndex == 0 || leftIndex+1 == len(wordsShorter) || (rightIndex-leftIndex <= 1 && rightIndex != math.MaxInt && leftIndex != math.MinInt)
}

func orderSentences(sentence1 string, sentence2 string) (string, string) {
	if len(sentence1) < len(sentence2) {
		return sentence1, sentence2
	} else {
		return sentence2, sentence1
	}
}

func main() {
	var result = areSentencesSimilar("qbaVXO Msgr aEWD v ekcb", "Msgr aEWD ekcb")
	fmt.Println(result)
}
