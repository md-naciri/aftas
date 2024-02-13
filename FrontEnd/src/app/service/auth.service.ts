import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserRegister } from '../entity/user-register';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly BASEURL = environment.BASE_URL+"/auth"

  constructor(private _https: HttpClient) { }


  login(user : UserRegister): Observable<any>{

    return this._https.post<any>(this.BASEURL+"/signup",user)
  }
}
