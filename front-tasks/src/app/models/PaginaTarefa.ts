import { Tarefa } from "./Tarefa";

export class PaginaTarefa{

        content?: Tarefa[];
        last?: boolean;
        totalPages?: number;
        totalElements?: number;
        size?: number;
        number?: number;
        first?: boolean;
        numberOfElements?: number;
        empty?: boolean;
    
        constructor(){}
}