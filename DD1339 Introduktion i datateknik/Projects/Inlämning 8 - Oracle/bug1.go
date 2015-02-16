//Author Felix De Silva

//Problemet
package main

import (
	"fmt"
)

// I want this program to print "Hello world!", but it doesn't work
func main() {
	ch := make(chan string)
	ch <- "Hello world!"
	fmt.Println(<-ch)
}

//Problemet är att man får deadlock.
//Det är eftersom att du försöker att skicka in en sag och skriva ut en sak i samma tråd.
//Du måste slänga in strängen till kanalen i en gorutin och sedan skriva ut den

/*
//Lösning 1
package main

import (
	"fmt"
)

// I want this program to print "Hello world!", but it doesn't work
func main() {
	ch := make(chan string)
	go func(){
		ch <- "Hello world!"
	}()
	fmt.Println(<-ch)

//Lösning 2
package main

import (
	"fmt"
)

// I want this program to print "Hello world!", but it doesn't work
func main() {
	ch := make(chan string, 1)
	ch <- "Hello world!"
	fmt.Println(<-ch)
}*/
