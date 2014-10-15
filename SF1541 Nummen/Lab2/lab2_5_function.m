% finitadifferensmetoden
% Byter ut och skriver y'' som en funktion av och y 
function G=lab2_5_function(z);
global x h yL yR

% Satter variabler pa upprepade termer
N=length(z); 
h2=h^2; 


% vektorn y bestar av y startvarde,z(itererar mellan slut och startvarde),slutv?rde
y=[yL; z; yR];

% t.ex. y(3:..) symboliserar det tredje vardet i vektorn y
% funktionen ar pa standardform av orginalfunktionen
G=( y(3:N+2)-2*y(2:N+1)+y(1:N) )/h2 -(x.^2).*y(2:N+1).*( y(2:N+1) -1 );
