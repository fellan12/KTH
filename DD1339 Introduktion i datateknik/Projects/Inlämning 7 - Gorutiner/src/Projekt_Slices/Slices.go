
//Author Felix De Silva

package main

import "code.google.com/p/go-tour/pic"

func Pic(dx, dy int) [][]uint8 {
    //skapar en slice i en slice 
    slice := make([][]uint8, dy)
    
    //skapar en slice i platsen "i" i den inre slicen 
    for i:= 0; i<len(slice); i++ {
    	slice[i] = make([]uint8, dx)
    }
    
    //sätter in uint8 tal i row y
    for y, row := range slice {
        row[y] = uint8(y+2)
        //sätter in uint8 tal i row x 
        for x := range row {
            row[x] = uint8((x*x + y*y)/2)
        }
    }
    return slice
}

func main() {
    pic.Show(Pic)
}