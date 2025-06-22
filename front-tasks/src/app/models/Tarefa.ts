import { Profile } from "./Profile";

export class Tarefa{
    
    titulo?: string;
 prioridade?: string;
 descricao?: string;
 responsavel?: Profile; 
 tags?: string[]; 
id:string = "123";
status?:string;
prazo?:string;

}