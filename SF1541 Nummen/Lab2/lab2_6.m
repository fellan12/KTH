clc
clf
global H L d A g fm k hh tc h0 u0

H=30; L=600; d=0.6; g=9.81; fm=0.024; hh = 20; k = 1.05; tc = 5;

% Rakna ut u0 och h0 med givna konstanter
C = (1/2*fm)*(L/d)*((16*k^2)/(d^4*pi^2));
h0= (g*H + hh*C)/(g+C);
u0 = ((4*k*sqrt(h0-hh))/(pi*d^2));

mopt = odeset('RelTol', 1.E-6);

% ode45(
%   @funktion fun sadan f([y y']) = [y' y''],
%   [start(givet) slut]
%   [y(0) y'(0)]
% )

% beraknar och plottar funktionen for A=1
A = 1;
[T,Y] = ode45(@lab2_6_function, [-20 500], [h0 u0], mopt);
subplot(1,2,1),plot(T,Y(:,1), 'b'), hold on;
subplot(1,2,2),plot(T,Y(:,2), 'b'), hold on;

% beraknar och plottar funktionen for A=2
A = 2;
[T,Y] = ode45(@lab2_6_function, [-20 500], [h0 u0], mopt);
subplot(1,2,1),plot(T,Y(:,1), 'g');
subplot(1,2,2),plot(T,Y(:,2), 'g');

% beraknar och plottar funktionen for A=4
A = 4;
[T,Y] = ode45(@lab2_6_function, [-20 500], [h0 u0], mopt);
subplot(1,2,1),plot(T,Y(:,1), 'r');
subplot(1,2,2),plot(T,Y(:,2), 'r');

% beraknar och plottar funktionen for A=10
A = 10;
[T,Y] = ode45(@lab2_6_function, [-20 500], [h0 u0], mopt);

subplot(1,2,1),plot(T,Y(:,1), 'k');
legend('A=1','A=2','A=4','A=10')
axis([-20,500,20,40])
title('h(t), vattenhastigheten i forbindelseledningen')
xlabel('x') % x-axis label
ylabel('y') % y-axis label

subplot(1,2,2),plot(T,Y(:,2), 'k');
legend('A=1','A=2','A=4','A=10')
axis([-20,500,-2,4])
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('u(t), vattenhojden i stigtanken')
