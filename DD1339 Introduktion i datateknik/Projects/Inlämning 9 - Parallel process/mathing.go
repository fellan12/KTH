// http://www.nada.kth.se/~snilsson/concurrency/
package main

import (
	"fmt"
	"sync"
)

// This programs demonstrates how a channel can be used for sending and
// receiving by any number of goroutines. It also shows how  the select
// statement can be used to choose one out of several communications.
func main() {
	people := []string{"Anna", "Bob", "Cody", "Dave", "Eva"}
	match := make(chan string, 1) // Make room for one unmatched send.
	wg := new(sync.WaitGroup)
	//var wg sync.WaitGroup
	wg.Add(len(people))
	for _, name := range people {
		go Seek(name, match, wg)
		//Seek(name, match, wg)
	}
	wg.Wait()
	select {
	case name := <-match:
		fmt.Printf("No one received %sâ€™s message.\n", name)
	default:
		// There was no pending send operation.
	}
}

// Seek either sends or receives, whichever possible, a name on the match
// channel and notifies the wait group when done.
func Seek(name string, match chan string, wg *sync.WaitGroup) {
	fmt.Println(name)
	select {
	case peer := <-match:
		fmt.Printf("%s sent a message to %s.\n", peer, name)
	case match <- name:
		// Wait for someone to receive my message.
	}
	wg.Done()
}

/**
Vad händer om man tar bort go-kommandot från Seek-anropet i main-funktionen?
Teori: Det kommer att fungera som förut bortsett från att seek metoden kommer att
köras en gång itaget för varje name, med tanke på att det inte görs i separata gorutioner
Vad som händer: Det fungerar som det ska, men tror att det tar längre tid.
Anledning: Bara för att det inte är go före seek() så måste det köras en gång
itaget vilket gör att det tar mycket längre tid för att köra allt.

Vad händer om man byter deklarationen wg := new(sync.WaitGroup)
mot var wg sync.WaitGroup och parametern wg *sync.WaitGroup mot wg sync.WaitGroup?
Teori: Det borde inte fungera för nu sätter du in hela wg som parameter och inte dess pekare.
Vad som händer: Du får ett deadlock efter ett tag, dem första saker skrivs ut, men sista ge deadlock
Anledning: Det är för att wg.Add(len(people)) innehåller 5 och när alla go routiner körs så får alla 
routiner själva variabeln wg som är 5 och alla minskar det med 1, vilket gör att routine 1 minskar det till 4 och routin 2 minskar det
från 5 till 4 och så gör det alla. Det resulterar att wg går bara ner till 4 och sen händer inget, vilket ger deadlock.

Vad händer om man tar bort bufferten på kanalen match?
Teori: Då kommer det troligen att få deadlock.
Vad som händer: Du får ett deadlock efter sista personen i people
Anledning: Detta händer för att Eva skickas in i en kanal,
men nu finns det inget som hämtar ur Eva, vilket gör att det kommer att stänna där förevigt
och sedan ge deadlock

Vad händer om man tar bort default-fallet från case-satsen i main-funktionen?
Teori: Inget kommer att hände för inget finns i default
Vad som händer: Programmet fugnerar som det ska. Men när man har ett jämnt antal personer så får man deadlock
Anledning: Detta är för att när man kommer till select satsen så finns det inget att skicka ut från kanalen, 
vilket gör att man fastnar i select satsen och får deadlock.
*/
