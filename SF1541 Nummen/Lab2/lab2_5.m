clc,clf,hold off
format short e, format compact

%globala variabler, tillgangliga i funktionsfilerna
global x h yL yR

%Steglangden h, de yttre punkterna hos x
N=20; 
xstart=1; 
xslut=3; 
h=(xslut-xstart)/(N+1); 

%Y start och slutvardena 
yL=2; 
yR=4;

%Intervallet med steglangden h (endast de inre punkterna)
x=[xstart+h:h:xslut-h]';

%startgissning (spelar ingen roll vilket varde ist for 2)
z=yL+2*x;
dz=z;           %Steg for iteration = z

n = 0;
disp('   Iteration    Fel')
%newton raphson tills felvardet ar mindre an 1.E-12
while norm(dz,inf) >1.E-12*norm(z,inf),
n = n+1;
% funktionen
F=lab2_5_function(z); 
% jacobianen
J=minjac('lab2_5_function',z);
% felvardet
dz=-J\F;
% nasta iteration 
z=z+dz;
disp([n norm(dz,inf)])
end

% kombinera yttre och inre punkter av x och y och plotta dem
xp=[xstart;x;xslut]; yp=[yL;z;yR];
plot(xp,yp);
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('Differentialekvationen ')