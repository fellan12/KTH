% Funktionen for h(t) och u(t)
function f = lab2_6_function(t, y)
global H L d A g fm k tc

f = [((y(2)*pi*d^2)/4-k*(1-max(min(t,5),0)/tc)*sqrt(y(1)-20))/A 
     (g*(H-y(1)))/L - 1/2*fm*1/d*y(2)*abs(y(2))];
end