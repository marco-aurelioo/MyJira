import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponentComponent } from './component/header-component/header-component.component';
import { SidebarComponentComponent } from './component/sidebar-component/sidebar-component.component';
import { FooterComponentComponent } from './component/footer-component/footer-component.component';
import { LayoutComponentComponent } from './component/layout-component/layout-component.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: []  
})
export class AppModule { }
