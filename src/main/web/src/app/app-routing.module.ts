import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PartComponent} from "./part/part.component";
import {AppComponent} from "./app.component";
import {EbomComponent} from "./ebom/ebom.component";
import {FileuploadComponent} from "./fileupload/fileupload.component";
import {PartCrudComponent} from "./part-crud/part-crud.component";

const routes: Routes = [
   { path: 'bom', component: AppComponent },
   { path: 'part', component: PartComponent },
   { path: 'ebom', component: EbomComponent },
   { path: 'fileupload', component: FileuploadComponent},
   { path: 'partcrud', component: PartCrudComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
