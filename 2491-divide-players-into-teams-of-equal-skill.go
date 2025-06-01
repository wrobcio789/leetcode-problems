package main

import (
	"fmt"
)

func dividePlayers(skill []int) int64 {
	var totalSkill = 0
	var skillCountMap = map[int]int{}

	for _, num := range skill {
		totalSkill += num
		skillCountMap[num]++
	}
	var skillInEachTeam = totalSkill / (len(skill) / 2)

	var result int64 = 0
	for k, v := range skillCountMap {
		var oppositeTeamCount = skillCountMap[skillInEachTeam-k]
		if oppositeTeamCount != v {
			return -1
		}

		result += int64(v * k * (skillInEachTeam - k))
	}
	result /= 2

	return int64(result)

}

func main() {
	var result = dividePlayers([]int{1, 1, 2, 3})
	fmt.Println(result)
}
