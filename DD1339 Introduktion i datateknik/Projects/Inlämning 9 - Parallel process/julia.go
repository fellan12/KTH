// Stefan Nilsson 2013-02-27

// This program creates pictures of Julia sets (en.wikipedia.org/wiki/Julia_set).
package main

import (
	"fmt"
	"image"
	"image/color"
	"image/png"
	"log"
	"math/cmplx"
	"os"
	"runtime"
	"strconv"
	"sync"
	"time"
)

type ComplexFunc func(complex128) complex128

var Funcs []ComplexFunc = []ComplexFunc{
	func(z complex128) complex128 { return z*z - 0.61803398875 },
	func(z complex128) complex128 { return z*z + complex(0, 1) },
	func(z complex128) complex128 { return z*z + complex(-0.835, -0.2321) },
	func(z complex128) complex128 { return z*z + complex(0.45, 0.1428) },
	func(z complex128) complex128 { return z*z*z + 0.400 },
	func(z complex128) complex128 { return cmplx.Exp(z*z*z) - 0.621 },
	func(z complex128) complex128 { return (z*z+z)/cmplx.Log(z) + complex(0.268, 0.060) },
	func(z complex128) complex128 { return cmplx.Sqrt(cmplx.Sinh(z*z)) + complex(0.065, 0.122) },
}

func main() {
	//skapar en variabel för den tiden då programmet startas
	t := time.Now()
	
	//skapar en waitgroup
	var wgm sync.WaitGroup
	wgm.Add(len(Funcs))
	for n, fn := range Funcs {

		go func(i int, fu func(complex128) complex128) {
			err := CreatePng("picture-"+strconv.Itoa(i)+".png", fu, 1024)
			if err != nil {
				log.Fatal(err)
			}
			wgm.Done()
		}(n, fn)
	}
	wgm.Wait()
	//Skriver ut information om tid och datorns CPUer
	fmt.Print("Jag är tar ")
	fmt.Print(time.Since(t))
	fmt.Print(" på mig att köra klart programmet")
}

// CreatePng creates a PNG picture file with a Julia image of size n x n.
func CreatePng(filename string, f ComplexFunc, n int) (err error) {
	file, err := os.Create(filename)
	if err != nil {
		return
	}
	defer file.Close()
	err = png.Encode(file, Julia(f, n))
	return
}

// Julia returns an image of size n x n of the Julia set for f.
func Julia(f ComplexFunc, n int) image.Image {
	bounds := image.Rect(-n/2, -n/2, n/2, n/2)

	//skaper en waitgroup
	var wgj sync.WaitGroup
	wgj.Add(bounds.Max.X - bounds.Min.X)
	img := image.NewRGBA(bounds)
	s := float64(n / 4)
	for i := bounds.Min.X; i < bounds.Max.X; i++ {
		go func(iLocal int, sLocal float64) {
			for j := bounds.Min.Y; j < bounds.Max.Y; j++ {
				n := Iterate(f, complex(float64(iLocal)/sLocal, float64(j)/sLocal), 256)
				r := uint8(n % 32 * 8 + 20)
				g := uint8(0)
				b := uint8(0)
				img.Set(iLocal, j, color.RGBA{r, g, b, 255})
			}
			wgj.Done()
		}(i, s)
	}
	wgj.Wait()
	return img
}

// Iterate sets z_0 = z, and repeatedly computes z_n = f(z_{n-1}), n â‰¥ 1,
// until |z_n| > 2  or n = max and returns this n.
func Iterate(f ComplexFunc, z complex128, max int) (n int) {
	for ; n < max; n++ {
		if real(z)*real(z)+imag(z)*imag(z) > 4 {
			break
		}
		z = f(z)
	}
	return
}

func init() {
	numcpu := runtime.NumCPU()
	runtime.GOMAXPROCS(numcpu) // Allows use of all available processors.
	fmt.Print("Jag har ")
	fmt.Print(numcpu)
	fmt.Print(" logiska CPUer\n")
}
