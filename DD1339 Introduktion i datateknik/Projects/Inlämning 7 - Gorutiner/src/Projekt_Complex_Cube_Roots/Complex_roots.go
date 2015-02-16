
//Author Felix De Silva

package main

import "fmt"

func Cbrt(x complex128, accuracy int) complex128 {
	z := x 

	//iterates with the desired accuracy
	for i:=0 ; i<accuracy; i++{
		z = z - (z*z*z-x)/(3*z*z)
	}
	fmt.Println("\nNewtons-method: ")
		return complex128(z)
}

func main() {
	fmt.Println(Cbrt(2, 10))
}
