%De givna formlerna, skrivna mha finitadifferensmetodens
function g = lab2_2b_function(x,u)
g =[u(2) 
    u(1)/9-pi*u(1)*exp(x/3)*(2*u(2)*sin(pi*x)+pi*u(1)*cos(pi*x))
    pi*(u(1)^2)];
