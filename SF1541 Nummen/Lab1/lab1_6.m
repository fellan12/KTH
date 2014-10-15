%Newton-Raphssons metod f|r ekvationssystem
%filnamn: snewton.m Kompletterande exempel 16  
format compact
%Indata
f1 = @(x, y, z) sin(x) + y.^2 + log(z) - 3;
f2 = @(x, y ,z) 3*x + 2^y - z.^3;
f3 = @(x, y ,z) x.^2 + y.^2 + z.^3 -6;

%Omgjorda formler
g1 = @(y, z) sin((z.^3 - 2.^y)/3) + y.^2 + log(z) - 3;
g2 = @(y, z) ((z.^3 - 2.^y)/3).^2 + y.^2 + z.^3 - 6;

%Berakning
phi =@(y,z) g1(y,z).^2 + g2(y,z).^2;

%Skapar en meshgrid
[X,Y] = meshgrid([-10:0.01:10], [0:0.01:10]);

%Sätter värden från f som är större än 0.5 till Inf
f = phi(X,Y);
index = (f>0.5);
f(index) = Inf;

%plotta for att approximera startvärden
surfc(X,Y,f)
axis([-3 1.5 -2 3 0 0.05])

%Derivator
dyg1 =@(y,z) 2*y-(1/3)*(2^y)*cos((1/3)*((z^3)-(2^y)))*log(2);
dyg2 =@(y,z) (2/9)*((2^y)*log(2)*((2^y)-(z^3))+9*y);
dzg1 =@(y,z) z^(-1) + (z^2)*cos(((-2^y + z^3)/3));
dzg2 =@(y,z) (((z^2)*(9 - 2^(1 + y) + 2*(z^3)))/3);

%Jacobian matrix
J=@(x) [dyg1(x(1),x(2)) dzg1(x(1),x(2))
         dyg2(x(1),x(2)) dzg2(x(1),x(2))]; 

%Rotterna Y och Z
rootsY = [];
rootsZ = [];

%Itererar genom x-varden tagna fran plotten
for x =[[-2.5 0.05]', [-1.3 1.5]', [1.5 1.5]', [2.0 0.8]']
    %Start-values
    h=x;
    iter=0;
    error = 1;
    limit = 1.E-6;
    
    %Newtons-metod for systems
    while (error > limit & (iter < 20)),
      errorprev = error;

      %Functions matrix
      f =[g1(x(1), x(2))
          g2(x(1), x(2))];
      
      %Newtons-metod
      x=x+(-J(x)\f);
      
      %Iterate counter
      iter=iter+1;

      %Error counter
      error = abs(x-errorprev)/abs(error);

      disp('     cords        f(x)         h=J\f(x)      error');
      disp([x f h error]);
      disp(' ')
      disp(' ')

    end

    rootsY = [rootsY; x(1)];
    rootsZ = [rootsZ; x(2)];
end

%Print the roots
disp('    Roots Y      Roots Z')
disp([rootsY rootsZ])
