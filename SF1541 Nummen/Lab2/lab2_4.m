clc
clf

t=(0:0.01:1)';
%kubisk bezier som radvektor av fyra element
%Formel for kubisk bezier kurvor
%r(x) = ((1-3)^3)*p1 + 3*t*((1-t)^2)*b + 3*t*(1-t)*c + (t^3)*p2
bez=[(1-t).^3 3*t.*(1-t).^2 3*(1-t).*t.^2 t.^3];  

%startvarden (gemensamma for bada sidor av hjartat)
p1=[0 5]; 
p2=[0 2.5]; 

%Symmetriska punkter som formar b.z.kurvan 
prh =[2 7];
prl =[3.2 3.5];
plh =[-2 7];
pll =[-3.2 3.5];

%Ber?kna b.z.kurvan utefter de givna punkterna
k1=bez*[p1; prh; prl; p2];
k2=bez*[p1; plh; pll; p2];

%Mangd
fi = 0:pi/200:pi/2;

%Funktionerna for kaustikakurvan lab1
x = (cos(fi)).^3;
y = 1.5*sin(fi) - sin(fi).^3;

%Plottar funktionen #codedwithlove
axis equal
hold on
fill(k1(:,1),k1(:,2),'r')
fill(k2(:,1),k2(:,2),'r')
plot([0 0], [2.5 5], 'r') %Fyller svarta linjen mellan hj?rthalvorna
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('En karlekshistoria med 2 bezierkurver')
