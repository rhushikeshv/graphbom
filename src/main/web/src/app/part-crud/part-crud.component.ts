import { Component, OnInit } from '@angular/core';
import {Part} from "../part/part";
import {PartService} from "../part/part.service";
import {Message} from "primeng/api";

@Component({
  selector: 'app-part-crud',
  templateUrl: './part-crud.component.html',
  styleUrls: ['./part-crud.component.css']
})
export class PartCrudComponent implements OnInit {

  parts: Part[] =[];
  msgs: Message[] = [];

  constructor(private partService:PartService) {

  }

  ngOnInit() {
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



  onAddPart() {
      let p:Part = {
        name:'',
        description:'',
        level:'',
        id:'',
        uuid:'',
        quantity:''
      };
      this.parts.push(p);
  }

  onSave(rowData) {
    window.alert(rowData.uuid);
    if(rowData.uuid) {
      this.partService.updatePart(rowData).then(res => {
        this.parts = res.data;
        if (res.message === 'FAIL') {
          this.msgs.push({
            severity: 'error', summary: res.message, detail: 'Failed to update Part '
          });
        } else {
          this.msgs.push({
            severity: 'success', summary: res.message, detail: 'Part updated successfully'
          });
        }
      });
    }
    else {
      rowData.type='Part';
      this.partService.createPart(rowData).then(res => {
        this.parts = res.data;
        if (res.message === 'FAIL') {
          this.msgs.push({
            severity: 'error', summary: res.message, detail: 'Failed to create Part '
          });
        } else {
          this.msgs.push({
            severity: 'success', summary: res.message, detail: 'Part created successfully'
          });
        }
      });
    }

  }
}
