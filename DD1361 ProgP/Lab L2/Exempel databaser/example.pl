%%% Man kan testa sin kod på exempeldatabasen på följande sätt.
%
%%% Starta swipl
%%% Ladda in databasen:
% ?- [example1].   (eller: consult(example1).)
% % example1 compiled 0.00 sec, 1,296 bytes
% true.
%%% Ladda in er lösning (om den ligger i filen l2.pl, annars byter man
%%% l2 mot vad man har valt att kalla sin fil):
% ?- [l2].
% ...
%%% Sök efter spindeln:
% ?- 1(X).
% X = ...

%%% För att felsöka sin kod och hänga med i exekveringen använder man trace:
%%% Innan anropet "1(X)." kör man:
% ?- trace.
% true.
%
%%% När man nu kör "1(X)." kommer man få stega igenom exekveringen.
% [trace]  ?- 1(X).
%    Call: (6) 1(_G386) ?
% ...
%%% Man kan stega framåt genom att trycka Enter.  Man kan även hoppa
%%% över steg och undersöka i mer detalj vad som pågår, tryck "h" för
%%% att få hjälp om vilka alternativ som finns.
%%%
%%% Det finns även ett trevligt GUI till trace som man kan använda.
%%% För att använda det anropar man predikated "guitracer" (helst
%%% *innan* man aktiverar trace så man slipper trace:a igenom anropet
%%% till guitracer)

% I den här databasen är det bara "1" som kan vara spindeln i
% nätet i en konspiration (högst oväntat!)
person(1).
person(2).
person(3).
person(4).
person(5).
knows(1,2).
knows(1,3).
knows(2,4).
knows(3,5).
