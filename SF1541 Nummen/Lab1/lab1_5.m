%Uppgift 5
format short e 
format compact 

%Indata
f = @(x) 60*x - ((x.^2+x+0.1).^6)/((x+1).^6) - 10*x*exp(-x);
df = @(x) (6*(x^2+x+0.1)^6)/(x+1)^7-(6*(2*x+1)*(x^2+x+0.1)^5)/(x+1)^6-10*exp(-x)+10*exp(-x)*x+60;

%Berakning
roots = [];
felKonstant = [];

for i = [0.5, 2.5],
    %Index så att man inte itererar mer än ett bestämt värdet i while-lopen
    n = 0;
    %Start värde för x
    x = i
    %Start värde för error
    error = 1; 
    %Start värde för errorprev
    errorPrev = 1; 
    
    disp('       x            f(x)        df(x)        error        konv           n   ')
    
    %Newton Raphson metoden har generellt sett kvadratisk konvergens
    while (error > (10^(-6)) && n < 20)
        errorPrev = error;
        fi = f(x);
        dfi = df(x);
        error = abs(fi/dfi);
        n = n + 1;
        x = x - (fi/dfi);
        konv = error/(errorPrev)^2;
        disp([x fi dfi error konv n])
        
    
    end
    
    roots = [roots; x];
    felKonstant = [felKonstant; konv];
    
    disp(' ')
end

%De funna rötterna
roots
disp(' ')

%Felkonstanen
felKonstant 
disp(' ')

%Plottar kurvan och en x-axel
y1 = @(x) (60*x) - (((x^2+x+0.1)^6)/((x+1)^6)) - (10*x*exp(-x));
y2 = @(x) x;
fplot(y1,[-0.5,3]), hold on
fplot(y2, [-0.5,3], 'r--')
plot(roots(1), 0, 'g+', roots(2), 0,'g+');
legend('f(x)', 'x-axel')
axis([-0.5 3 -200 200])
xlabel('x') % x-axis label
ylabel('y') % y-axis label
title('Nollstallen for f(x)')

hold off


