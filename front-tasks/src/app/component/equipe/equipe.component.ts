import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/app/models/Pessoa';
import { Projeto } from 'src/app/models/Projeto';
import { TeamInvite } from 'src/app/models/TeamInvite';
import { PessoaService } from 'src/app/services/pessoa.service';
import { PageEvent } from '@angular/material/paginator';
import { OrganizacaoService } from 'src/app/services/organization.service';
import { Organizacao } from 'src/app/models/Organizacao ';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProjetosService } from 'src/app/services/projetos.service';

@Component({
  selector: 'app-equipe',
  templateUrl: './equipe.component.html',
  styleUrls: ['./equipe.component.css']
})
export class EquipeComponent implements OnInit {

  myOrganizations: Organizacao[] = [];
  myProjects: Projeto[] = [];
  activeTeams: any[] = [];
  pendingInvites: TeamInvite[] = [];

  formCadastroOrganizacao: FormGroup;
  formCadastroProjeto: FormGroup;
  selectedProjectId: number | undefined;


  organizationSelected: string| undefined;
  projectSelected: string| null = null;

  //itens para busca de pessoas
  searchTerm: string = '';
  results: Pessoa[] = [];
  pageSize: number = 10;
  currentPage: number = 0;
  totalElements: number = 0;


  constructor(
    private pessoaService: PessoaService,
    private organizacaoService: OrganizacaoService,
    private projetosService: ProjetosService,
    private fb: FormBuilder,
  ) {
    this.formCadastroOrganizacao = this.fb.group({
      titulo: ['']
    })
    this.formCadastroProjeto = this.fb.group({
      titulo:['']
    })
  }

  ngOnInit() {
    this.loadActiveTeams();
    this.loadPendingInvites();
    this.loadPessoas();
    this.loadMyOrganizations();
    this.loadMyProjects();
  }

  setOrganization(organization: string){
    this.organizationSelected = organization;
    console.log("load projects da organizacao "+organization)
    this.loadMyProjects();
  }

  loadPessoas() {
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

  loadMyOrganizations(){
    console.log("carregando organizations;");
    this.organizacaoService.getOrganizacoes().subscribe( response => {
      this.myOrganizations = response;
    });
  }

  onSalvaOrganizacao() {
    console.log("cadastrando organizacao");
    this.organizacaoService.addOrganizacao( {titulo: this.formCadastroOrganizacao.value.titulo}).subscribe(
      response => {
         console.log(response);
      }
    );
    this.loadMyOrganizations();
  }

  loadMyProjects() {
    console.log("cadastrando organizacao");
    if(this.organizationSelected){
      this.projetosService.getProjetos( this.organizationSelected! ).subscribe(
        response => {
          this.myProjects = response;
        }
      );
    }else{
      console.log('organizacao deve ser selecionada!');
    }
  }

  onSalvaProjeto(){
    console.log("cadastrando organizacao");
    if(this.organizationSelected){
      this.projetosService.addProjeto( this.organizationSelected! , {nome: this.formCadastroOrganizacao.value.titulo}).subscribe(
        response => {
          console.log(response);
        }
      );
      this.loadMyProjects();
    }else{
      console.log('organizacao deve ser selecionada!');
    }

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

  sendInvite(pessoaId: string) {

    console.log(">>>>>>>>> pessoaId:"+pessoaId)
    console.log(">>>>>>>>>>>>>>>>>> selecionado:"+this.selectedProjectId)
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
