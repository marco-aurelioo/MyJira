import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'src/app/services/keycloak.service';

@Component({
  selector: 'app-naveg',
  templateUrl: './naveg.component.html',
  styleUrls: ['./naveg.component.css']
})
export class NavegComponent  implements OnInit {

  isCollapsed = true;

  
  constructor(public keycloakServie: KeycloakService) {}

  ngOnInit(): void {

  }

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

}
