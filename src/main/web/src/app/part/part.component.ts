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

  parts: Part[];

  cols: any[];
  msgs: Message[] = [];

  constructor(private partService: PartService) {

  }

  ngOnInit() {
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'description', header: 'Description' },
      { field: 'level', header: 'Level' },
      { field: 'id', header: 'ID' }
    ];

    // this.carService.getCarsSmall().then(cars => this.cars = cars);
    this.partService.getParts().then(res => {
      this.parts = res.data;
      if (res.message === 'FAIL') {
        this.msgs.push({severity: 'error', summary: res.message, detail: 'Failed ' +
            ' to find Parts in BOM '});
      } else {
        this.msgs.push({severity: 'success', summary: res.message, detail: 'Found' +
            ' BOM Parts'
            });
      }
    });
  }


}
