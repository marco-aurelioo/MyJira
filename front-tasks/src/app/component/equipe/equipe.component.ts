import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/models/Pessoa';
import { Projeto } from 'src/app/models/Projeto';
import { TeamInvite } from 'src/app/models/TeamInvite';
import { PessoaService } from 'src/app/services/pessoa.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-equipe',
  templateUrl: './equipe.component.html',
  styleUrls: ['./equipe.component.css']
})
export class EquipeComponent implements OnInit {
  myProjects: Projeto[] = [];
  activeTeams: any[] = [];
  pendingInvites: TeamInvite[] = [];

  //itens para busca de pessoas
  searchTerm: string = '';
  results: Pessoa[] = [];
  pageSize: number = 10;
  currentPage: number = 0;
  totalElements: number = 0;


  constructor(private pessoaService: PessoaService) {}

  ngOnInit() {
    this.loadMyProjects();
    this.loadActiveTeams();
    this.loadPendingInvites();
    this.loadPessoas();
  }

  loadPessoas() {
    console.log("chamou a consulta!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    this.pessoaService.getPaginaPessoa(this.searchTerm, this.currentPage, this.pageSize)
      .subscribe(response => {
        this.results = response.content;
        this.totalElements = response.totalElements;
      });
  }

   onPageChange(event: PageEvent) {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
  }

  loadMyProjects() {
    //this.teamService.getMyProjects().subscribe(
    //  projects => this.myProjects = projects
    //);
  }

  loadActiveTeams() {
    //this.teamService.getActiveTeams().subscribe(
    //  teams => this.activeTeams = teams
    //);
  }

  loadPendingInvites() {
    //this.teamService.getPendingInvites().subscribe(
    //  invites => this.pendingInvites = invites
    //);
  }

  sendInvite(projectId: number, email: string) {
    //this.teamService.sendInvite(projectId, email).subscribe(
    //  response => {
        // Handle success
    //  },
    //  error => {
        // Handle error
    //  }
    //);
  }
}
