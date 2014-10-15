%Uppgift 1
format compact

%Mangd
fi = 0:pi/200:pi;

%Funktionerna 
x = (cos(fi)).^3;
y = 1.5*sin(fi) - sin(fi).^3;

hold on

%Plottar funktionerna
plot(x,y)

%Newton Raphson metod med startv?rden, ska hitta ett nollst?lle (forskjutna)
h = 10;
x = 1
disp('       x1        h')
while abs(h) > 1.0e-10*abs(x),
  y = 1.5*sin(x) - (sin(x))^3 - 0.64;
  dy = -3*((sin(x))^2 - 0.5) * cos(x);
  h =y/dy;
  x=x-h;
  disp([x    h])
end

%Plotta det funna nollastallet
x = (cos(x))^3;
%F?rsta punkten
plot(x, 0.64, '*'), axis equal
%Spegling av f?rsta
plot(-x, 0.64, '*'), axis equal


%Newton Raphson metod med startv?rden, ska hitta ett nollst?lle (forskjutna)
h = 10;
x = 0
disp('       x2        h')
while abs(h) > 1.0e-10*abs(x),
  y = 1.5*sin(x) - (sin(x))^3 - 0.64;
  dy = -3*((sin(x))^2 - 0.5) * cos(x);
  h =y/dy;
  x=x-h;
  disp([x    h])
end

%Plotta det funna nollastallet
x = (cos(x))^3;
%andra punkten
plot(x, 0.64, '*'), axis equal
%Spegling av andra
plot(-x, 0.64, '*'), axis equal


hold off