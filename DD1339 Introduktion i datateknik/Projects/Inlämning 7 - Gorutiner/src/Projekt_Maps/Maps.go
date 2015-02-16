
//Author Felix De Silva

package main

import (
   "code.google.com/p/go-tour/wc"
    "strings"
)

func WordCount(s string) map[string]int {
    //skapar en map
    var words = make(map[string]int)
    //delar strängen till substrängar
    parts := strings.Fields(s)
    //sätter in substrängarna in mapen
    for _, v:= range parts {
    	words[v] += 1
    }
    
    return words
}

func main() {
    wc.Test(WordCount)
}