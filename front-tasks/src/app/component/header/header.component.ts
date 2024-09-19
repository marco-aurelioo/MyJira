import { Component, OnInit } from '@angular/core';
import { KeycloakTokenParsed } from 'keycloak-js';
import { KeycloakService } from 'src/app/services/keycloak.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  userName?: KeycloakTokenParsed;

  constructor(public keycloakService: KeycloakService) {
    if(keycloakService.isAuthenticated()){
      this.userName = keycloakService.getUserName();
    }

  }

  ngOnInit(): void {
  }



}
