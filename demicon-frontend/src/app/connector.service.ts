import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Countries } from './countries';

@Injectable({
  providedIn: 'root'
})
export class ConnectorService {


  private apiURL = "http://localhost:8080/api/v1";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<Countries[]> {
    return this.httpClient.get<Countries[]>(this.apiURL + '/user')
    .pipe(
      catchError(this.errorHandler)
    )
  }
  
  errorHandler(error: any) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
 }
}
