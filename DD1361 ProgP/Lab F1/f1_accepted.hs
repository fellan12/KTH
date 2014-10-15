module F1 where

import Data.List.Split
import Data.Char

--Fibonacchi
fib :: Integer -> Integer
fib n
  |n == 0 = 0
  |n == 1 = 1
  |otherwise = fib (n-1) + fib (n-2)

--Rovarspraket
 
isVokal1 c = elem c ['a', 'o', 'u', 'e', 'i', 'y' , ' ']

rovarsprak :: String -> String
rovarsprak s = rovar_help s

rovar_help :: [Char] -> [Char]
rovar_help [] = []
rovar_help (h:t) = 
        if isVokal1 h  == True 
        then h:rovar_help t 
        else h:('o'):h:rovar_help t
 
--Karpsravor

isVokal2 (c) = elem c ['a', 'o', 'u', 'e', 'i', 'y', ' ']

karpsravor :: String -> String
karpsravor s = karps_help(s)

karps_help :: [Char] -> [Char]
karps_help [] = []
karps_help (h:t) = 
        if isVokal2 (h) == True
        then h:karps_help(t)
        else h:karps_help(drop 2 t)

--Medellangd
medellangd :: String -> Double
medellangd s = 
               if(countWords 0.0 s == 0.0)
               then 0
               else (count 0.0 s / countWords 0.0 s)

count :: Double -> String -> Double
count n [] = n
count n (f:r) =
               if isAlpha f 
               then count(n+1) r
               else count n r

countWords :: Double -> String -> Double
countWords x [] = x
countWords x (y:y2:ys)
        |((isAlpha y2 == False) && (isAlpha y == True)) = countWords(x+1) (y2:ys)
        |otherwise = countWords x (y2:ys)

countWords x [y]
        |(isAlpha y == True) = x+1
        |otherwise = x
        
--Listskyffling

dela :: [t] -> [t]
dela [] = []
dela (x:xs) = 
        if(length xs) == 0
        then [x]
        else x:dela(tail xs)

skyffla :: [a] -> [a]
skyffla [] = [] 
skyffla s = 
        if(length s) == 1
        then s
        else dela s ++ skyffla(dela (tail s))