clc
clf
format long, format compact
%Indata
f = @(x) exp(-x/3)./(2 - cos(pi*x));
L = 2.6;

a = 0;                           %Integral start varde
b = L;                           %Integral slut vardet
i = 0;                           %Iteration start varde

%Trapetsregeln
%Approximera en integral från a till b för ett visst  f(x)
%Noggranhetsordning 2
%T(h)=h*(y(a)/2 + y(a+h) +y(a+2h) + .... y(a+nh) + y(b)/2)
for h = [0.1 0.05]               %For-loop med steglangderna
i = i+1;                         %Iteration
x = a:h:b;                       %Intervall
n=(b - a)/h;                     %Antal trapetser
y=pi*f(x).^2;                    %Tillagd pi och ^2 for att rakna volymen
V(i)=h*(sum(y)-(y(1)+y(end))/2); %volymen
end

%Quad
f2 =@(x) pi*f(x).^2;
VQ = quad(f2,0,L);

%Simpson varde (Extrapolera)
S = V(2) + (V(2)-V(1))/3;


%Plotta figuren
f = @(x) exp(-x/3)./(2 - cos(pi*x));
x = (a:h:b)';
fi = 0:2*pi/30:2*pi;
X=x*ones(size(fi)); 
Y=f(x)*cos(fi); 
Z=f(x)*sin(fi);

%Presentation
V1 = V(1), V2 = V(2), VQ, S
subplot(2,1,1), plot(x,f(x)), title('Funktionskurvan')
subplot(2,1,2), mesh(X,Y,Z), view(30,20), title('Funktionens rotationsvolym')