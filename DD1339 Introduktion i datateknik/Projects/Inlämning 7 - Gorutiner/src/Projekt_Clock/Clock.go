
//Author Felix De Silva

package main

import (
	"fmt"
	"time"
)

func Remind(text string, pause time.Duration) {
	//Creating a ticker that ticks for the desired duration
	var t = time.NewTicker(pause)

	go func() {
		//after the desired duration print the text
		for _ = range t.C {
			fmt.Println("Time is " + time.Now().Format("15.04.05") + ": " + text)
		}
	}()
}

//Help method for getting the correct duration
func duration(hour string) time.Duration {
	duration, _ := time.ParseDuration(hour)
	return duration
}

func main() {
	Remind("Time to eat", duration("1s"))
	Remind("Time to work", duration("2s"))
	Remind("Time to sleep", duration("3s"))
	//For preventing the main() for ending
	select {}
}
