import {Component, Input, OnInit} from '@angular/core';
import {EBOMService} from './ebom.service';
import {TreeNode} from 'primeng/api';

@Component({
  selector: 'app-ebom',
  templateUrl: './ebom.component.html',
  styleUrls: ['./ebom.component.css']
})
export class EbomComponent implements OnInit {

  nodes: TreeNode[];
  cols: any[];
  public show = false;

  name:string;

  constructor(private ebomService: EBOMService) { }

  ngOnInit() {
    console.log(' EBOM comp loaded');
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'description', header: 'Description' },
      { field: 'type', header: 'Type' },
      { field: 'rev', header: 'Revision' },
      { field: 'quantity', header: 'Quantity' }
    ];

  }

  find() {
    this.show = true;
    this.ebomService.loadBOM(this.name).then(nodes => {
      console.log(nodes);
      this.nodes = nodes;
      this.show = false;
      console.log(' turning off the progress  bar');
    });

  }
}
