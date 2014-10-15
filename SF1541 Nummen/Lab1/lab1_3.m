%Uppgift 3

format compact
format short
%Input
x = 0 : 1 : 100;
f =@(x) 60*x - ((x.^2+x+0.1).^6)/((x+1).^6) - 10*x*exp(-x);

%a.
%Funktionen df (derivatan) returnerar ett varde baserat pa ett givet x  (FIXA DEN AR FEL!!!!)
df =@(x) (6*(x^2+x+0.1)^6)/(x+1)^7-(6*(2*x+1)*(x^2+x+0.1)^5)/(x+1)^6-10*exp(-x)+10*exp(-x)*x+60;

%b.
%Berakna derivatan for f numeriskt med framatdifferens
df2 = @(x,h) (f(x+h) - f(x)) ./h;

%Berakna derivatan for f numeriskt med centraldifferens
df3 = @(x,h) (f(x+h) - f(x-h)) ./(2*h);

%Skapa tomma listor for tabellen och loglog plotten
A = [];
lx = [];
ly = [];

%Feltabell
for h=[1.E-3 1.E-4 1.E-5 1.E-6 1.E-7 1.E-8 1.E-9 1.E-10 1.E-11 1.E-12 1.E-13]
   A = [A; h, abs(df(0.2)-df2(0.2, h)), abs(df(0.2)-df3(0.2, h)), abs(df(1)-df2(1, h)), abs(df(1)-df3(1, h))];
   ly = [ly; abs(df(0.2)-df2(0.2, h)), abs(df(0.2)-df3(0.2, h)), abs(df(1)-df2(1, h)), abs(df(1)-df3(1, h))];
   lx = [lx; h];
end

%Skapa en tabell for skillnaden i derivata for samtliga fall
T = array2table(A, 'VariableNames', {'h' 'FramDiff02' 'CentralDiff02' 'FramDiff1' 'CentralDiff1'})

%Plotta skillnaden i derivata for h p? x-axeln
loglog(lx, ly)
legend('x=0.2, frammatdifferens', 'x=0.2, centraldifferens', 'x=1, frammatdifferens', 'x=1, centraldifferens')
xlabel('h') % x-axis label
ylabel('Differens fran analytisk derivata') % y-axis label
title('Differens av numerisk derivata for framatdifferens och centraldifferens, vardera for x=0.2 och x=1')
%grid on 