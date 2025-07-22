import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParticiparProjetosComponent } from "./participar-projetos/participar-projetos.component";
import { MinhasSolicitacoesComponent } from './minhas-solicitacoes/minhas-solicitacoes.component';
import { AprovarSolicitacoesComponent } from './aprovar-solicitacoes/aprovar-solicitacoes.component';

@Component({
  selector: 'app-participar',
  templateUrl: './participar.component.html',
  styleUrls: ['./participar.component.css'],
  standalone: true,
  imports: [CommonModule,ParticiparProjetosComponent, MinhasSolicitacoesComponent, AprovarSolicitacoesComponent]
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