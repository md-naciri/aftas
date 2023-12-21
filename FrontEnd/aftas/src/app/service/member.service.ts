import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private readonly BASEURL = environment.BASE_URL+"/member"
  constructor(private http: HttpClient) {}
  getMembers(): Observable<any>{
    return this.http.get(this.BASEURL);
  }
  createMember(data: any): Observable<any>{
    return this.http.post(this.BASEURL, data)
  }
  searchForMember(search: string): Observable<any>{
    return this.http.get(this.BASEURL+"/search/"+search)
  }
}
