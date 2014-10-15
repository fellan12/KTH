% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% READ LINES %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Read the file and call valid_proof with file inputs
verify(InputFileName) :- 
                see(InputFileName),
                read(Prems), read(Goal), read(Proof),
                seen, 
                valid_proof(Prems, Goal, Proof). 


% Main function that returns Yes if Proof is correct, and no if Proof is incorrect
valid_proof(Prems, Goal, Proof) :- 
                %Returns true if the goal is correct
                check_goal(Goal, Proof),
                %Returns true if the proof is valid                                
                check_proof(Prems, Goal, Proof, []).   


% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% CHECK GOAL %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Check that the intentended goal is same as the actual (by proof) goal
check_goal(Goal, Proof) :- 
                %Reverse the proof, so the last line is at the top     
                reverse(Proof,ReversedProof),
                %Set middle value in first line to be ProofGoal                   
                [[_,ProofGoal,_]|_] = ReversedProof,
                %True if ProofGoal is equal to (given) Goal           
                ProofGoal == Goal.                              


% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% CHECK PROOF %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Base case
check_proof(_,_,[], _).

%Check every line recusivly
check_proof(Prems, Goal, [ProofHead|ProofTail], CheckedRules) :-
        %Split ProofHead to its components
        [Row, Seq, Rule] = ProofHead,
        %Send the ProofHead components for rule checking
        check_rule(Prems, Goal, Seq, Rule,CheckedRules),
        %if valid put it in the CheckedRules
        append([(Row, Seq)], CheckedRules, CheckedRules1),
        %Recursivly check next proof.
        check_proof(Prems, Goal, ProofTail, CheckedRules1).

% Open a box
% When appending to our list of acceptable sequences, if we're within a box, add a seconday pair of braces.
% Doing this allows us to differentiate between which sequences are open and which sequences are within boxes.
check_proof(Prems, Goal, [[[FirstRowNumber, FirstSequence, assumption]|RestOfBoxProof]|RestOfProof], CheckedRules) :-
        %Append First line and rest of the line in the box to BoxProof
        append([[FirstRowNumber, FirstSequence, assumption]], RestOfBoxProof, BoxProof),
        %Check the proofs inside the box
        check_proof(Prems, Goal, RestOfBoxProof, [(FirstRowNumber, FirstSequence)|CheckedRules]),
        %check if the last line is the last line in the list BoxProof 
        last(BoxProof,[LastRowNumber, LastSequence, _]),
        %Check rest of the proof
        check_proof(Prems, Goal, RestOfProof, [[(FirstRowNumber, FirstSequence), (LastRowNumber, LastSequence)]|CheckedRules]).


% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% CHECK_RULE %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%PREMISE RULE
check_rule(Prems,_, Seq, premise,_) :- 
            %Check if the sequences is part of out known premises
            member(Seq, Prems).


%COPY RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, A, copy(X), CheckedRules) :-
            %Check if the row X and sequences A is part of our CheckedRules
            member((X,A), CheckedRules).


%AND INTRODUCTION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, and(A,B), andint(X,Y), CheckedRules) :- 
            %Check if the row X and sequences A is part of our CheckedRules
            member((X,A), CheckedRules),
            %Check if the row Y and sequences B is part of our CheckedRules
            member((Y,B), CheckedRules).                    


%AND ELIMINATION RULE 1
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, A, andel1(X), CheckedRules) :- 
            %Check if the row X and sequences around A is part of our CheckedRules
            member((X, and(A,_)), CheckedRules).


%AND ELIMINATION RULE 2
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, B, andel2(X), CheckedRules) :- 
            %Check if the row X and sequences around A is part of our CheckedRules
            member((X, and(_,B)), CheckedRules).


%OR INTRODUCTION RULE 1
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, or(A,_), orint1(X), CheckedRules) :-
            %Check if the row X and sequences A is part of our CheckedRules
            member((X, A), CheckedRules).


%OR INTRODUCTION RULE 2
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, or(_,B), orint2(X), CheckedRules) :-
            %Check if the row X and sequences B is part of our CheckedRules
            member((X, B), CheckedRules).  


%OR ELIMINATION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, R, orel(X,Y,U,V,W), CheckedRules) :- 
            %Check if the row X and sequences around A and B is part of our CheckedRules
            member((X, or(A,B)), CheckedRules),
            %Check if the double pair is part of our CheckedRules
            member([(Y, A),(U, R)], CheckedRules),
            %Check if the double pair is part of our CheckedRules
            member([(V, B),(W, R)], CheckedRules).


%IMPLIKATION INTRODUCTION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, imp(A,B), impint(X,Y), CheckedRules) :- 
            %Check if the double pair is part of our CheckedRules
            member([(X,A),(Y,B)], CheckedRules).                       


%IMPLIKATION ELIMINATION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, B, impel(X,Y), CheckedRules) :- 
            %Check if the row X and sequences A is part of our CheckedRules
            member((X,A), CheckedRules),
            %Check if the row Y and sequences around A and B is part of our CheckedRules
            member((Y,imp(A,B)), CheckedRules).


%NOT INTRODUCTION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, neg(A), negint(X,Y), CheckedRules) :-
            %Check if the double pair is part of our CheckedRules
            member([(X, A),(Y, cont)], CheckedRules).

%NOT ELIMINATION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, cont, negel(X,Y), CheckedRules) :-
            %Check if the row X and sequences A is part of our CheckedRules
            member((X, A), CheckedRules),
            %Check if the row Y and sequences around A is part of our CheckedRules
            member((Y, neg(A)), CheckedRules).

%CONTRARY ELIMINATION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_,_, contel(X), CheckedRules) :-
            %Check if the row X and sequences A is part of our CheckedRules
            member((X, A), CheckedRules),
            %Check is the sequences A is equal too cont
            A == cont. 


%NOT NOT INTRODUCTION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, neg(neg(A)), negnegint(X), CheckedRules) :- 
            %Check if the row X and sequences A is part of our CheckedRules
            member((X, A), CheckedRules).


%NOT NOT ELIMINATION RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, Seq, negnegel(X), CheckedRules) :- 
            %Check if the row X and sequences A is part of our CheckedRules
            member((X, A), CheckedRules),
            %Check is the sequences A is equal too neg(neg(Seq))
            A == neg(neg(Seq)).


%MT RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, neg(A), mt(X,Y), CheckedRules) :-
            %Check if the row X and sequences around A and B is part of our CheckedRules 
            member((X, imp(A,B)), CheckedRules),
            %Check if the row Y and sequences around B is part of our CheckedRules
            member((Y, neg(B)), CheckedRules).


%PBC RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, A, pbc(X,Y), CheckedRules) :- 
            %Check if the double pair is part of our CheckedRules
            member([(X, neg(A)),(Y, cont)], CheckedRules).


%LEM RULE
%Check if the the sequences follows the pattern shown in the Seq slot
check_rule(_,_, or(A,neg(B)), lem,_) :-
            %Check if the sequences A and B är equal
            A == B.

