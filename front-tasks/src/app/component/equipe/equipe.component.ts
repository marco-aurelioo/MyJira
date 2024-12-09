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
import { TeamService } from 'src/app/services/team.service';
import { MyInviteProject } from 'src/app/models/MyInviteProject';

@Component({
  selector: 'app-equipe',
  templateUrl: './equipe.component.html',
  styleUrls: ['./equipe.component.css']
})
export class EquipeComponent implements OnInit {

  myOrganizations: Organizacao[] = [];
  myProjects: Projeto[] = [];
  activeTeams: Projeto[] = [];
  pendingInvites: TeamInvite[] = [];

  myInvites: MyInviteProject[] = [];

  formCadastroOrganizacao: FormGroup;
  formCadastroProjeto: FormGroup;
  selectedProjectId: string | undefined;


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
    private teamService: TeamService,
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
      console.log(response);  
      this.myOrganizations = response;
    });
  }

  onSalvaOrganizacao() {
    console.log("cadastrando organizacao");
    this.organizacaoService.addOrganizacao( {id:'', titulo: this.formCadastroOrganizacao.value.titulo}).subscribe(
      response => {
         console.log(response);
      }
    );
    this.loadMyOrganizations();
  }

  loadMyProjects() {
    console.log("loadMyProjects");
    if(this.organizationSelected){
      this.projetosService.getProjetos( this.organizationSelected! ).subscribe(
        response => {
          console.log(response);
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


  aceitarProjeto(inviteid: string){
    console.log('aceita inviteid:'+inviteid);
    this.teamService.confirmMyInvites(inviteid,true).subscribe(
      response => {
        console.log(response);
      }
    );
    this.loadPendingInvites();
  }


  rejeitarProjeto(inviteid: string){
    console.log('rejeita inviteid:'+inviteid);
    this.teamService.confirmMyInvites(inviteid,false).subscribe(
      response => {
        console.log(response);
      }
    );
    this.loadPendingInvites();
  }


  loadActiveTeams() {
    this.teamService.getMyTeams().subscribe(
      response => {
        this.activeTeams = response.content;
      }
    );
  }

  loadPendingInvites() {
    this.teamService.getMyInvites().subscribe(
      response => {
        console.log(response);

        this.myInvites = response.content;
      }
    )
  }

  sendInvite(pessoaId: string) {
    this.teamService.createInvite(pessoaId,this.organizationSelected!,this.selectedProjectId!).subscribe(
      response => {
        console.log(response);
      }
    );
    this.loadPendingInvites();
  }
}
