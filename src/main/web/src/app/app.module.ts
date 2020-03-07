import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PartComponent } from './part/part.component';

import {TableModule} from 'primeng/table';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MultiSelectModule} from 'primeng/multiselect';
import {PartService} from './part/part.service';
import {HttpClient} from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { EbomComponent } from './ebom/ebom.component';

import {TreeTableModule} from 'primeng/treetable';
import {EBOMService} from './ebom/ebom.service';

import {AccordionModule, FileUploadModule, MessageService, PanelModule} from 'primeng/primeng';

import {ProgressBarModule} from 'primeng/primeng';

import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {FormsModule} from "@angular/forms";
import { FileuploadComponent } from './fileupload/fileupload.component';
import { PartCrudComponent } from './part-crud/part-crud.component';

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    PartComponent,
    EbomComponent,
    FileuploadComponent,
    PartCrudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    BrowserAnimationsModule,
    MultiSelectModule,
    HttpClientModule,
    TreeTableModule,
    AccordionModule,
    ProgressBarModule,
    MessagesModule,
    MessageModule,
    FormsModule,
    FileUploadModule,
    PanelModule,
  ],
  providers: [EBOMService, PartService, HttpClient,MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
