import { Component, OnInit } from '@angular/core';
import {PartService} from './part.service';
import {Part} from './part';
import {Message} from 'primeng/api';

@Component({
  selector: 'app-part',
  templateUrl: './part.component.html',
  styleUrls: ['./part.component.css']
})
export class PartComponent implements OnInit {

  parts: Part[] =[];

  cols: any[];
  msgs: Message[] = [];
  name: string="";

  constructor(private partService: PartService) {

  }

  ngOnInit() {
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'description', header: 'Description' },
      { field: 'level', header: 'Level' },
      { field: 'id', header: 'ID' },
      { field: 'rev', header: 'Revision' },
      { field: 'quantity', header: 'Quantity' },
    ];
  }


  find() {
    while(this.msgs.length > 0){ // clear all  messages if any
      this.msgs.pop();
    }
    console.log(' the part name to search is ' + this.name);
    if (this.name === "*" || this.name!=='') {
      this.partService.getParts().then(res => {
        this.parts = res.data;
        if (res.message === 'FAIL') {
          this.msgs.push({
            severity: 'error', summary: res.message, detail: 'Failed ' +
              ' to find Parts in BOM '
          });
        } else {
          this.msgs.push({
            severity: 'success', summary: res.message, detail: 'Found' +
              ' BOM Parts'
          });
        }
      });
    }
    else{
      this.msgs.push({
        severity: 'error', summary: 'Cannot search empty part name', detail: ' '
      });
    }
  }
}
