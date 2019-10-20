import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class PartService {
  constructor(private http: HttpClient) {}

  getParts() {
    return this.http.get<any>('/bom/services/partservice/findparts')
      .toPromise()
      .then(res => res)
      .then(data => data);
  }
}



