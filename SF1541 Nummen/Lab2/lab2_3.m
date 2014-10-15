clc
clf
clearvars
format short

%ga igenom kubisk beizer fr?n 0-1 med steget 0.01
t=(0:0.01:1)';
%kubisk bezier som radvektor av fyra element
%Formel for kubisk bezier kurvor
%r(x) = ((1-3)^3)*p1 + 3*t*((1-t)^2)*b + 3*t*(1-t)*c + (t^3)*p2
bez=[(1-t).^3 3*t.*(1-t).^2 3*(1-t).*t.^2 t.^3]; 

% Kubisk bezierkurva bestar utav tva randpunkter och 2 styrpunkter
p1=[1 0];           %start
p2=[0 0.5];         %slut
b=[1 0.75];         %punkt pa kurvan
c=[0.12 0.87];      %punkt pa kurvan


%Forma b.z.kurvan utefter start, slut och styrpunkterna
k=bez*[p1; b; c; p2];

%Mangd
fi = 0:pi/200:pi/2;

%Funktionerna for kaustikakurvan lab1
x = (cos(fi)).^3;
y = 1.5*sin(fi) - sin(fi).^3;

%Plottar funktionerna
plot(k(:,1),k(:,2), x,y, 'r'), view(-90,-90), hold on
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('Kubisk bezierkurva')


%Plotta ogon
r = 0.05;
xpos1 = 0.2;
ypos1 = 0.3;
xpos2 = 0.35;
ypos2 = 0.15;

fi=0:pi/20:2*pi; 
xp=r*cos(fi);
yp=r*sin(fi);
fill(xpos1+xp,ypos1+yp, 'b')
fill(xpos2+xp,ypos2+yp, 'b')