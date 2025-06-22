import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Participar } from 'src/app/models/Participar';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { ParticiparService } from 'src/app/services/participar.service';

@Component({
  selector: 'app-participar',
  templateUrl: './participar.component.html',
  styleUrls: ['./participar.component.css']
})
export class ParticiparComponent implements OnInit {
  
  constructor(
    private router: Router
  ) {}
  
  tabAtiva: string = 'buscar'

  mudarTab(tab : string ){
    this.tabAtiva = tab;
  }

  ngOnInit(): void {
  }

  

  
  
  
  
  



}