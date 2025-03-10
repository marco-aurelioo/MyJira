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

  constructor(private fb: FormBuilder, private service: ProjetosService){
    this.projeto = new Projeto('','','',false);
    this.loadMyProjetos();
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
      },
      (erro) => {
        console.error('Falha ao enviar projeto:', erro);
      }
    )
  }

  mudarPagina(pagina: number){
    this.myProjetosPage = pagina;
    this.loadMyProjetos();  
  }

  getPaginasDisponiveis(): number[] {
    if (!this.pageProjetos || this.pageProjetos.totalPages! <= 1) {
      return [];
    }
  
    return Array.from({ length: this.pageProjetos.totalPages! }, (_, i) => i);
  }

}


