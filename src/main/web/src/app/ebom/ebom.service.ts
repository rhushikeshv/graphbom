
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TreeNode} from 'primeng/api';


@Injectable()
export class EBOMService {

  constructor(private http: HttpClient) {}

  loadBOM(name: string) {
    console.log('Loding bom');
    return this.http.get<any>('/bom/services/partservice/loadbom?type=Part' + '&name=' + name + '&rev=1')
      .toPromise()
      .then(res => res.data as TreeNode[]);

  }
}
