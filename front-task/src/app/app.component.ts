import { Component } from '@angular/core';
import { HeaderComponentComponent } from './component/header-component/header-component.component';
import { SidebarComponentComponent } from './component/sidebar-component/sidebar-component.component';
import { FooterComponentComponent } from './component/footer-component/footer-component.component';
import { RouterOutlet } from '@angular/router';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    standalone: true,
    imports:[
      HeaderComponentComponent,
      SidebarComponentComponent,
      FooterComponentComponent,
      RouterOutlet,]
    
})
export class AppComponent {
  
}
