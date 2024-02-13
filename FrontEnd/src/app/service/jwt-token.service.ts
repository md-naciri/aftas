import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtTokenService {

  constructor() { }



  saveToken(token:string){
    localStorage.setItem("token",token)
  }
}
