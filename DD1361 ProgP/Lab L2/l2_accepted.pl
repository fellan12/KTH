%Main funktion
spider(Spider) :-
	person(Spider), 			%check that the input "Spider" is a person in the database
	spiderMain(Spider).			%call spiderMain that checks if spider is the spider of the network


%Make knows relationship both ways,
%so you can write the opposite of the database,
%and still get true
knows2(X,Y) :-	
	knows(X,Y); 
	knows(Y,X).


%spiderMain takes potential spider as input, 
%makes a list of its friends and of all people in the network. 
spiderMain(Spider) :- 
	findall(X, person(X), AllObjects),						%all people in the network
	findall(X, knows2(Spider, X), PotentialConspirators), 	%people directly connected to the spider
	validateSpider(PotentialConspirators, AllObjects), !. 


%basic case for validateSpider
%if all objects list is empty, true
validateSpider(_,[]).

%validateSpider goes through all of spiders friends 
%and removes itself and its friends
validateSpider([FirstPC|RestPC], AllObjects) :-
	select(FirstPC, AllObjects, AllObjects1),				%removes itself
	removeFriends(FirstPC, AllObjects1, AllObjects2), 		%removes its friends
	(validateSpider(RestPC, AllObjects2);					%check rest of spider friends with FirtPCs connections
	validateSpider(RestPC, AllObjects)).					%check rest of friends with entire list

%if FirtPC is not part of the newwork, continue
validateSpider([FirstPC|RestPC], AllObjects) :-
	\+ member(FirstPC, AllObjects), !,
	validateSpider(RestPC, AllObjects).


%removeFriends removes all friends of Person from the entire network list
removeFriends(Person, Lista, NewList) :-
	findall(First, knows2(Person, First), PersonsFriends),
	removeFriend(Lista, PersonsFriends, NewList).

%go through the Person's friends one by one,
%check if it is in the entire network list, 
%if true, continue
removeFriend([],_,[]). 
removeFriend([First|Rest], Y, Z) :-
	member(First,Y),
	removeFriend(Rest, Y, Z), !.

%if false, add friend to new list z and continue
%new list z is a list of people that person doesnt know 
removeFriend([First|Rest], Y, [First|Zs]) :-
	removeFriend(Rest, Y, Zs).






