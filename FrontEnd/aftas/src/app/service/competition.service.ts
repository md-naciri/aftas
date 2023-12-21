import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly BASEURL = environment.BASE_URL+"/competition"

  constructor(private http: HttpClient) {}
  
  getCompetitionsPagination(page: number, size: number): Observable<any>{
    const params = new HttpParams().set('page', page.toString()).set('size',size.toString())
    return this.http.get(this.BASEURL+"/pagination",{params});
  }
  getCompetitions(): Observable<any>{
    return this.http.get(this.BASEURL);
  }
  createCompetition(data: any): Observable<any>{
    return this.http.post(this.BASEURL, data)
  }
  filterCompetitions(filter: string): Observable<any>{
    return this.http.get(this.BASEURL+"/filter/"+filter)
  }
}
