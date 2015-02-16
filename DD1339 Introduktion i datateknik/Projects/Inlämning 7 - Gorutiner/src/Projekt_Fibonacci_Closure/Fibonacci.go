
//Author Felix De Silva

package main

import "fmt"

//Fibonacci is a function that returns
// a function that returns an int.
func fibonacci(n int) func(int) int {
		//Initierar en variabel till en funtion av int
		x := func(n int) int{
			//returnar fib talen till x
			return fib(n)
		}
	return x
}

// Calculate the next fibonacci number
func fib(n int) int {
	if (n == 0){	
		return int(0)
	}else if (n == 1){
		return int(1)
	}else{
		return fib(n - 1) + fib(n -2)
	}
}

// Main method
// Printing out numbers
func main() {
    f := fibonacci(0)
    for i := 0; i < 10; i++ {
        fmt.Println(f(i))
    }
}