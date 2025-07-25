import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProjetosService } from 'src/app/services/projetos.service';
import { Projeto } from 'src/app/models/Projeto';
import { PageProjeto } from 'src/app/models/PageProjeto';

@Component({
  selector: 'app-projetos',
  templateUrl: './projetos.component.html',
  styleUrls: ['./projetos.component.css']
})
export class ProjetosComponent implements OnInit{

  projectForm: FormGroup = this.fb.group({
    projectId: [''],
    name: [''],
    description: [''],
    isPublic: [''],
  }); 

  projeto: Projeto;
  pageProjetos?: PageProjeto;
  myProjetosPage: Number = 0
  myProjetosSize: Number = 5

  
  pageProjetosQueSouMembro?: PageProjeto;
  myProjetosPageQueSouMembro: Number = 0
  myProjetosSizeQueSouMembro: Number = 5


  constructor(private fb: FormBuilder, private service: ProjetosService){
    this.projeto = new Projeto('','','',false);
    this.loadMyProjetos();
    this.loadMyProjetosQueSouMembro();
  }

  loadMyProjetos(){
    this.service.findMyProjetos(
      this.myProjetosPage,
      this.myProjetosSize).subscribe(
      (page) =>{
        console.log("carregando meus projetos");
        console.log(page);
        this.pageProjetos = page;

      },
      (error) =>{
        console.error("falha ao carregar meus projetos.");
      }
    )
    console.log(this.pageProjetos);
  }

  loadMyProjetosQueSouMembro(){
    this.service.findMyProjetosQueSouMembro(
      this.myProjetosPageQueSouMembro,
      this.myProjetosSizeQueSouMembro).subscribe(
      (page) =>{
        console.log("carregando meus projetosQueSouMembro");
        console.log(page);
        this.pageProjetosQueSouMembro = page;

      },
      (error) =>{
        console.error("falha ao carregar meus projetosQueSouMembro.");
      }
    )
    console.log(this.pageProjetosQueSouMembro);
  }


  ngOnInit(): void {
    
  }

  onSubmit(){
    console.log("criando projeto")
    this.service.criarProjeto(
      this.projectForm.value.name,
      this.projectForm.value.description,
      this.projectForm.value.isPublic
    ).subscribe(
      (projeto) => {
        this.projeto = projeto;
        this.projectForm.reset();
        this.loadMyProjetos();
      },
      (erro) => {
        console.error('Falha ao enviar projeto:', erro);
        alert('Erro ao cadastrar.')
        this.projectForm.reset();
      }
    )
  }

  mudarPagina(pagina: number){
    this.myProjetosPage = pagina;
    this.loadMyProjetos();  
  }


  mudarPaginaQueSouMembro(pagina: number){
    this.myProjetosPageQueSouMembro = pagina;
    this.loadMyProjetosQueSouMembro();  
  }

  getPaginasDisponiveisQueSouMembro(): number[] {
    if (!this.pageProjetosQueSouMembro || this.pageProjetosQueSouMembro.totalPages! <= 1) {
      return [];
    }
  
    return Array.from({ length: this.pageProjetosQueSouMembro.totalPages! }, (_, i) => i);
  }

  getPaginasDisponiveis(): number[] {
    if (!this.pageProjetos || this.pageProjetos.totalPages! <= 1) {
      return [];
    }
  
    return Array.from({ length: this.pageProjetos.totalPages! }, (_, i) => i);
  }

}


