%Uppgift 2a

format compact
%Indata
x = [91, 121, 152, 182, 213, 244]';
y = [13.2, 15.8, 18.0, 18.4, 16.6, 14.1]';

%Berakning
A = [ones(size(x)) x x.^2];
c = A\y;

%Presentation
xp = 80 : 0.1 : 265;
yp = (xp.^2)*c(3) + xp.*c(2) + c(1);

%Plottar resultaten
plot(xp, yp, x, y, '*')