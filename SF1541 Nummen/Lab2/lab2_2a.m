clc
clf

%Minimum felvarde
mopt=odeset('RelTol',1.e-6);

%X-varden, givna punkter
xStart = 0;
xSlut = 2.6;
Xspan = [xStart xSlut];
U0 = [1 -1/3];

%beraknar formlen mha finitadifferensmetoden och ODE45
[X,U] = ode45('lab2_2a_function', Xspan, U0, mopt);

%Formeln fran uppgift 1
x = 0:0.1:2.6;
f = (exp(-x/3)./(2 - cos(pi*x))).^2;

%presentera linjerna i samma figur
plot(X,U(:,1), x, f, 'r--')
legend('U kurvan fran ODE45', 'kurvan fran upg. 1')
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('Funktionen f(x)')