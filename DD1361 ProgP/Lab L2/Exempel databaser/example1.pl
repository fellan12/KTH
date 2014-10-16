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
% ?- spider(X).
% X = ...

%%% För att felsöka sin kod och hänga med i exekveringen använder man trace:
%%% Innan anropet "spider(X)." kör man:
% ?- trace.
% true.
%
%%% När man nu kör "spider(X)." kommer man få stega igenom exekveringen.
% [trace]  ?- spider(X).
%    Call: (6) spider(_G386) ?
% ...
%%% Man kan stega framåt genom att trycka Enter.  Man kan även hoppa
%%% över steg och undersöka i mer detalj vad som pågår, tryck "h" för
%%% att få hjälp om vilka alternativ som finns.
%%%
%%% Det finns även ett trevligt GUI till trace som man kan använda.
%%% För att använda det anropar man predikated "guitracer" (helst
%%% *innan* man aktiverar trace så man slipper trace:a igenom anropet
%%% till guitracer)

% I den här databasen är det bara "spider" som kan vara spindeln i
% nätet i en konspiration (högst oväntat!)
person(spider).
person(conspirator1).
person(conspirator2).
person(other1).
person(other2).
knows(spider,conspirator1).
knows(spider,conspirator2).
knows(conspirator1,other1).
knows(conspirator2,other2).
