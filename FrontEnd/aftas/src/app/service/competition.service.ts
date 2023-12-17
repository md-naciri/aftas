import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly BASEURL = environment.BASE_URL+"/competition"

  constructor(private http: HttpClient) {}
  
  getCompetitions(): Observable<any>{
    return this.http.get(this.BASEURL);
  }
  createCompetition(data: any): Observable<any>{
    return this.http.post(this.BASEURL, data)
  }
}
