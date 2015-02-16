package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"time"
)

func main() {
	server := []string{
		"http://localhost:8080",
		"http://localhost:8081",
		"http://localhost:8082",
	}
	for {
		before := time.Now()
		//res := Get(server[0])
		//res := Read(server[0], time.Second)
		res := MultiRead(server, time.Second)
		after := time.Now()
		fmt.Println("Response:", *res)
		fmt.Println("Time:", after.Sub(before))
		fmt.Println()
		time.Sleep(500 * time.Millisecond)
	}
}

type Response struct {
	Body       string
	StatusCode int
}

// Get makes an HTTP Get request and returns an abbreviated response.
// Status code 200 means that the request was successful.
// The function returns &Response{"", 0} if the request fails
// and it blocks forever if the server doesn't respond.
func Get(url string) *Response {
	res, err := http.Get(url)
	if err != nil {
		return &Response{}
	}
	// res.Body != nil when err == nil
	defer res.Body.Close()
	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		log.Fatalf("ReadAll: %v", err)
	}
	return &Response{string(body), res.StatusCode}
}

// FIXME
// I've found two insidious bugs in this function; both of them are unlikely
// to show up in testing. Please fix them right away â€“ and don't forget to
// write a doc comment this time.
func Read(url string, timeout time.Duration) (res *Response) {
	done := make(chan bool)
	//Create a variable for temporary storing of get()
	var temp *Response
	go func() {
		//storing get() in temp
		temp = Get(url)
		done <- true
	}()
	select {
	//if done is true set res to temp
	case <-done:
		res = temp

	//if timeout time has been, set res to gateway message
	case <-time.After(timeout):
		res = &Response{"Gateway timeout\n", 504}
	}
	return
}

// MultiRead makes an HTTP Get request to each url and returns
// the response of the first server to answer with status code 200.
// If none of the servers answer before timeout, the response is
// 503 â€“ Service unavailable.
func MultiRead(urls []string, timeout time.Duration) (res *Response) {
	temp :=make(chan *Response, 1)
	for i := 0; i < len(urls); i++ {
		go func(index int) {
			//Sends get() to temp chanel
			temp <- Get(urls[index])
		}(i)
		
	}

	select{
		//The first response from Get()
		case res = <-temp:

		//Timeout case
		case <-time.After(timeout):
		res = &Response{"Service unavailable\n", 503}
	}
	return
}

/*
En buggen här är att när programmet körs och det blir en timeout,
då kommer res att skrivas som res = &Response{"Gateway timeout\n", 504}.
Men när Read() är klar så kört go routinen fortfarande och kan sedan
ge ett värde till res, vilket inte ska ge när metoden är klar.
*/