import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from '../auth/user-storage.service';

const baseUrl = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  getAllTests(): Observable<any> {
      return this.http.get(baseUrl + "/api/test");
    }

  getTestQuestions(id: number): Observable<any> {
      return this.http.get(baseUrl + `/api/test/${id}`);
    }

  submitTest(data: any): Observable<any> {
      return this.http.post(baseUrl + "/api/test/submit-test", data);
    }

  getMyTestResults(): Observable<any> {
      return this.http.get(baseUrl + `/api/test/view-test-results/${UserStorageService.getUserId()}`);
    }
}
