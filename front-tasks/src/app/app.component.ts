// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { KeycloakService } from './services/keycloak.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = "Pagina Inicial";
  isCollapsed = true;

  constructor(public keycloakService: KeycloakService) {}

  ngOnInit(): void {

  }

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }
}
