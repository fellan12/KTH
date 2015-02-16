
% check(T, L, S, U, F)
% T - The transitions in form of adjacency lists
% L - The labeling
% S - Current state
% U - Currently recorded states
% F - CTL Formula to check.

verify(Input) :-
		see(Input), read(T), read(L), read(S), read(F), 
		seen,
		check(T, L, S, [], F).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% RULES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% P
check(_, L, S, [], X) :- 
		% Get all lables from the current state
		getAssocList(L, S, P),
		% Check that the X is in the lsit of lables
		member(X, P).

% neg P
check(_, L, S, [], neg(X)) :- 
		% Get all lables from the current state
		getAssocList(L, S, P),
		% Check that X isn't in the lsit of lables
		not(member(X, P)). 

% AND
check(T, L, S, [], and(F,G)) :- 
		% Ensure F hold in the current state.
		check(T, L, S, [], F),
		% Ensure G hold in the current state.
		check(T, L, S, [], G).

% OR 1
check(T, L, S, [], or(F,_)) :- 
		% Ensure F hold in the current state.
		once(check(T, L, S, [], F)).
% OR 2
check(T, L, S, [], or(_,G)) :- 
		% Ensure G hold in the current state.
		once(check(T, L, S, [], G)). 

% AX - for all next possible states
check(T, L, S, U, ax(X)) :- 
		% Get all adjacent states for the current state
		getAssocList(T, S, P),
		%Check all that all adjacent states contains X
		checkAdjecent(T, L, P, U, X).					

% AG1 - for all possible paths and all states in those paths
check(_, _, S, U, ag(_)) :-
		% Check if the current state has been visited 
		member(S, U).

% AG2 - for all possible paths and all states in those paths
check(T, L, S, U, ag(X)) :- 
		% Check if the current state hasn't been visited 
		not(member(S, U)),	
		% Ensure X hold in the current state.
		check(T, L, S, [], X),
 		% Ensure that ag(X) holds in all those paths.
		check(T, L, S, [S|U], ax(ag(X))).

% AF1 - for all possible paths, for Some state 
check(T, L, S, U, af(X)) :- 
		% Check if the current state hasn't been visited 
		not(member(S, U)),
		% Ensure X hold in the current state.
		check(T, L, S, [], X).	
		

% AF2 - for all possible paths, for Some state
check(T, L, S, U, af(X)) :- 
		% Check if the current state hasn't been visited 
		not(member(S,U)), 
 		% Ensure that af(X) holds in all those paths.
		check(T, L, S, [S|U], ax(af(X))). 	 	

% EX - for Some path, for all next states 
check(T, L, S, _, ex(X)) :- 
		% Get all adjacent states for the current state
		getAssocList(T, S, P),
		% Find a next state (N) in the list of states
		% and check is it is valid for the given X
		% If not, find next state and do over.
		member(N, P),
		check(T, L, N, [], X).

% EG1 - for Some path, all states in that path
check(_, _, S, U, eg(_)) :- 
		% Check if the current state has been visited 
		once(member(S, U)).

% EG2 - - for Some path, all states in that path
check(T, L, S, U, eg(X)) :- 
		% Check if the current state hasn't been visited 
		not(member(S, U)),
		% Ensure X hold in the current state.
		check(T, L, S, [], X),
		% Get all adjacent states for the current state
		getAssocList(T, S, P),
		% Find a next state (N) in the list of states
		% and check is it is valid for the given X
		% If not, find next state and do over.
		member(N,P),
		check(T, L, N, [S|U], eg(X)). 

% EF1 - for Some path, there is Some state 
check(T, L, S, U, ef(X)) :- 
		% Check if the current state hasn't been visited 
		not(member(S,U)),
		% Ensure X hold in the current state.
		check(T, L, S, [], X).		

% EF2 - for Some path, there is Some state 
check(T, L, S, U, ef(X)) :- 
		not(member(S,U)),
		% Get all adjacent states for the current state
		getAssocList(T, S, P),
		% Find a next state (N) in the list of states
		% and check is it is valid for the given X
		% If not, find next state and do over.
		member(N,P),
		check(T, L, N, [S|U], ef(X)). 		


%%%%%%%%%%%%%%%%%%%%%%%%%% HELP FUNCTIONS %%%%%%%%%%%%%%%%%%%%%%%%%

% Get the specific list from the input.
% Examample:
%
%   getList([s0, [s1, s2]], s0, P).
%   P = [s1,s2]
%
%   getList([s0, [r, q]], s0, F).
%   F = [r, q]
%
getAssocList([[S, P]|_], S, P) :- !.
getAssocList([_|T], S, P) :- getAssocList(T, S, P).

% Check that the given F is valid in all the adjacent states
checkAdjecent(_,_,[],_,_).
checkAdjecent(T, L, [S|R], U, F) :- 
		once(check(T, L, S, U, F)),
		checkAdjecent(T, L, R, U, F). 

