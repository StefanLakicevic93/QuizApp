import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  createTest(testDto : any): Observable<any> {
    return this.http.post(baseUrl + "/api/test", testDto);
  }

  getAllTests(): Observable<any> {
    return this.http.get(baseUrl + "/api/test");
  }

  addQuestionInTest(questionDto : any): Observable<any> {
    return this.http.post(baseUrl + "/api/test/question", questionDto);
  }

  getTestQuestions(id: number): Observable<any> {
    return this.http.get(baseUrl + `/api/test/${id}`);
  }

  getTestResults(): Observable<any> {
    return this.http.get(baseUrl + "/api/test/view-test-results");
  }
}
