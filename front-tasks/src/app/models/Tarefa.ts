import { Profile } from "./Profile";
import { Projeto } from "./Projeto";

export class Tarefa{
    
    titulo?: string;
    prioridade?: string;
    descricao?: string;
    responsavel?: Profile; 
    tipo?: string;
    tags?: string[]; 
    id?: string;
    status?:string;
    projeto?: Projeto;
    dataCriacao?: Date;
    dataConclusao?: Date;
    
}
