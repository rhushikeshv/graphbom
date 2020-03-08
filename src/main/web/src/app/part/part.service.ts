import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Part} from "./part";

@Injectable()
export class PartService {
  constructor(private http: HttpClient) {}

  getParts() {
    return this.http.get<any>('/bom/services/partservice/findparts')
      .toPromise()
      .then(res => res)
      .then(data => data);
  }
  updatePart(part:Part){
   console.log(part);
    return this.http.put<any>('/bom/services/partservice/updatepart',part).toPromise().then(res=>res).then(data=>data);
  }
}



