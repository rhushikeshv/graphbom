import { Component, OnInit } from '@angular/core';
import {Part} from "../part/part";

@Component({
  selector: 'app-part-crud',
  templateUrl: './part-crud.component.html',
  styleUrls: ['./part-crud.component.css']
})
export class PartCrudComponent implements OnInit {

  parts: Part[] =[];

  constructor() {

  }

  ngOnInit() {
    let p1:any ={
      name:'Wheel',
      level:1,
      description:'Bike front wheel',
      id:'123',
      uuid:'123adfdfadsf'
    }
    this.parts.push(p1)

  }

  onDeletePart(rowData) {
    window.alert(rowData.name);
  }
}
