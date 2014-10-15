%1-------------------------------------------------------------
%Basfall
fib(0,0).

%Om X är större än 0
fib(X,Y) :- X > 0, fib(X,Y,_).

%Basfall
fib(1,1,0).

%Om X är större än 1
fib(X, Y1, Y2) :-
			X > 1,
			%Lägg X1 till X-1
			X1 is X-1,
			%Invänta resultat till Y2 och Y3
			fib(X1, Y2, Y3),
			%Och lägg Y1 till Y2 + Y3
			Y1 is Y2 + Y3.

%2-------------------------------------------------------------
%Om X är en vokal
isVowsels(X) :- member(X,[97,101,105,111,117,121]).

%Basfall
rovarsprak([],[]).

%Om H är inte en vokal, lägg till H plus 111 och H
rovarsprak([H|T1], [H,111,H|T2]) :-
				\+isVowsels(H),
				rovarsprak(T1,T2).

%Om H är en vokal
rovarsprak([H|T1], [H|T2]) :-
				isVowsels(H),
				rovarsprak(T1,T2).


%3-------------------------------------------------------------
medellangd(X,Y):-
	countChar(X,Y2),
	countWords(X,Y3),
	Y is Y2/Y3.

%COUNTCHAR
%Basfall
countChar([],0).

%Om första elementet i listan är en char
countChar([H|T],Y):-
	is_alpha(H),
	%Invänta resultat från Y1
	countChar(T,Y1),
	%Lägg till 1 till Y
	Y is 1+Y1.

%Om första elementet i listan är inte en char
countChar([H|T],Y):-
	\+is_alpha(H),
	%Invänta resultat från Y1
	countChar(T,Y1),
	%Lägg till 0 till Y
	Y is 0+Y1.

%COUNTWORDS
%Basfall
countWords([],0).

%Om båda elementen är en char
countWords([H,H1|T],Y):-
	is_alpha(H), is_alpha(H1),
	%Invänta resultat från Y1
	countWords([H1|T],Y1),
	%Lägg till 0 till Y
	Y is 0+Y1.

%Om första elementet är en char och andra är inte en char.
countWords([H,H1|T],Y):-
	is_alpha(H), \+is_alpha(H1),
	%Invänta resultat från Y1
	countWords(T,Y1),
	%Lägg till 1 till Y
	Y is 1+Y1.

%Om första elementet är inte en char och andra är en char.
countWords([H,H1|T],Y):-
	\+is_alpha(H), is_alpha(H1),
	%Invänta resultat från Y1
	countWords([H1|T],Y1),
	%Lägg till 0 i Y1
	Y is 0+Y1.

%Om båda elementen är inte en char.
countWords([H,H1|T],Y):-
	\+is_alpha(H), \+is_alpha(H1),
	%Invänta resultat från Y1
	countWords(T,Y1),
	%Lägg till 1 till Y
	Y is 0+Y1.

%Om listan har enbart ett element och elementet är en char 
%sätt Y till 1
countWords(X,Y):-
	%Finns det bara ett element i listan ger det ett false, som i sin tur ger ett true.
	\+[_,_|_]=X,
	%Sätter H till det enda elementet i listan		
	[H|_]=X,
	is_alpha(H),
	%Sätt Y till 1
	Y is 1.

%Om listan har enbart ett element och elementet är inte en char
%sätt Y till 0
countWords(X,Y):-
	%Finns det bara ett element i listan ger det ett false, som i sin tur ger ett true.
	\+[_,_|_]=X,
	%Sätter H till det enda elementet i listan		
	[H|_]=X,
	\+is_alpha(H),
	%Sätt Y till 0
	Y is 0.

%4-------------------------------------------------------------
%Skyffla en lista
%Input [1,2,3,4,5,6,7]
%Output [1,3,5,7,2,6,4]

%Basfall
skyffla([],[]).

%Tar in en lista och skyfflar den
skyffla([H|T], Skyfflad) :-
	%Lägger till alla element med udda index i en lista Odd
	dela([H|T], Odd),  
	%Lägger till alla element med jämna index i en lista Even   
	dela(T, Even), 
	%Skyffla Even listan       
	skyffla(Even,EvenSkyfflad),
	%Lägg till Odd,EvenSkyfflad i Skyfflad     
	append(Odd,EvenSkyfflad,Skyfflad).      

%Basfall ifall tom lista eller endast ett element i listan
dela([],[]).
dela([H],[H]).

%Dela listan så du får en listan med varannan element
dela([H,_|T], [H|VLista]) :-
	dela(T, VLista).