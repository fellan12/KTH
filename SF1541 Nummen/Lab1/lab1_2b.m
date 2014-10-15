%Uppgift 2b

format compact
%Indata kompletterad från uppgift 2a
x = [1, 32, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335]';
y = [6.1, 8.0, 10.4, 13.2, 15.8, 18.0, 18.4, 16.6, 14.1, 11.4, 8.7, 6.6]';
T=365;
w=((2*pi)/T);


%Berakning
A = [ones(size(x)) cos(x.*w) sin(x.*w)];
c = A\y;


%Presentation
xp = 1 : 1 : 365;
yp = c(1) + c(2)*cos(xp.*w) + c(3)*sin(xp.*w);

%Plottar funktionen
plot(x, y, '*', xp, yp), hold on


%Residualvektorn består av differensen mellan yp och y i punkterna x
for i=1:length(x)
    resY(i) = abs(yp(x(i))-y(i));
    plot(x(i), resY(i),'r')
end


%Felkvadratssumman
Felkvadratsumman = resY*resY'

%Berakna dagens langd den 6 juni = 157 dagar
langd_Nationaldag = c(1) + c(2)*cos(157*w) + c(3)*cos(157*w)

%plotta nationaldagen
plot(157,langd_Nationaldag, 'r*')

hold off