import { CanActivateFn } from '@angular/router';

export const isAlerdyLoginGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem("token") 

  if(token){
   return false
  }
  
   return true;
};
