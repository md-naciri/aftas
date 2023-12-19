import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class HuntingService {
  private readonly BASEURL = environment.BASE_URL+"/hunting"
  constructor(private http: HttpClient) {}
  instertHunt(data: any): Observable<any>{
    return this.http.post(this.BASEURL, data)
  }
}
