import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PartComponent} from "./part/part.component";
import {AppComponent} from "./app.component";
import {EbomComponent} from "./ebom/ebom.component";

const routes: Routes = [
   { path: 'bom', component: AppComponent },
   { path: 'part', component: PartComponent },
   { path: 'ebom', component: EbomComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }