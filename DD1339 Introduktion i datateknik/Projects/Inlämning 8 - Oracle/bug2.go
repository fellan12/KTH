//Author Felix De Silva

package main

import (
	"fmt"
)

// This program should go to 11, but sometimes it only prints 1 to 10.
func main() {
	ch := make(chan int)
	go Print(ch)
	for i := 1; i <= 11; i++ {
		ch <- i
	}
	close(ch)

}

// Print prints all numbers sent on the channel.
// The function returns when the channel is closed.
func Print(ch <-chan int) {

	for n := range ch { // reads from channel until it's closed
		fmt.Println(n)
	}
}

//För mig så finns det inte problem
//Jag har kört programmet flera gånger och får inget 10
//Men jag antar att problemet är datarace
//att kanalen hinner stängas innan sista talet är inne
//Detta kan bero på att ena tråden är snabbare än andra
//En lösning kan vara att använda sync så man försäkrar att
//allt är inne innan man stänger kanelen

/*//Lösning
package main

import (
		"fmt"
		"sync"
	)

// This program should go to 11, but sometimes it only prints 1 to 10.
func main() {
	ch := make(chan int)
	wait := new(sync.WaitGroup)
	go Print(ch, wait)
	wait.Add(11)
	for i := 1; i <= 11; i++ {
		ch <- i
	}
	wait.Wait()
	close(ch)

}

// Print prints all numbers sent on the channel.
// The function returns when the channel is closed.
func Print(ch <-chan int, wait *sync.WaitGroup) {

	for n := range ch { // reads from channel until it's closed
		fmt.Println(n)
		wait.Done()
	}
}

*/
