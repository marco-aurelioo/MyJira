import { Profile } from "./Profile";
import { Projeto } from "./Projeto";

export class Participar {
    
    id?: string;
    projectId: string;
    project?: Projeto;
    comentario: string;
    feedback?: string;
    status?: string;
    categorias: string[];
    dataCriacao?: Date;
    dataAtualizacao?: Date;
    usuario?: Profile;

    constructor(
        projectId: string,
        comentario: string,
        categorias: string[]
    ){
        this.comentario = comentario;
        this.projectId = projectId;
        this.categorias = categorias;
    }

  }