
//Author Felix De Silva

package main

import (
    "fmt"
    "math"
)

//Using newton-method for aproximating sqrt
func Sqrt(x float64) float64 {
	z := x
	compare := float64(1)

	//Iterates to the difference is between -0.000001 and 0.000001
	//Then prints the result
	for{
		z = z - (z*z - x)/ (2 * z) 

		if z-compare < 0.000001 && z-compare > -0.000001{
			fmt.Println("\nNewtons-method: ")
			return z
		}
		compare = z

	}
	fmt.Println("\nNewtons-method: ")
	return float64(z)
}

func main() {
    fmt.Println(Sqrt(2))
    //For comparson with the maths method
    fmt.Println("Maths-method: ")
    fmt.Print(math.Sqrt(2))
}