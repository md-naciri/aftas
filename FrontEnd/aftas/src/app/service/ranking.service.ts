import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private readonly BASEURL = environment.BASE_URL+"/ranking"

  constructor(private http: HttpClient) {}

  assignMemberForCompetition(data: any): Observable<any>{
    return this.http.post(this.BASEURL, data)
  }

  getMembersInCompetition(code: string): Observable<any>{
    return this.http.get(`${this.BASEURL}/competition/${code}`)
  }
}
