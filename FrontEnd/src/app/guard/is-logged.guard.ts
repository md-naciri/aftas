import { CanActivateFn } from '@angular/router';
import { JwtTokenService } from '../service/jwt-token.service';

export const isLoggedGuard: CanActivateFn = (route, state) => {
 const token = localStorage.getItem("token") 

 if(token){
  return true
 }
 
  return false;
};
