
//Author Felix De Silva

package main

import (
	"fmt"
)

// Add adds the numbers in a and sends the result on res.
func Add(a []int, ch chan<- int) {
	sum := 0

	//Iterates though a
	for _, s := range a {
		sum += s
	}

	//send sum to the chanel
	ch <- sum
}

func main() {
	a := []int{1, 2, 3, 4, 5, 6, 7}

	n := len(a)
	ch := make(chan int)
	go Add(a[:n/2], ch)
	go Add(a[n/2:], ch)

	//Add the two subsums to a total
	total := <- ch + <- ch

	//Print the total
	fmt.Println(total)

}
