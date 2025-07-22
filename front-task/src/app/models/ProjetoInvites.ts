import { Projeto } from "./Projeto";

export class ProjetoInvites{
    project: Projeto;
    qtdRequest: number;
    qtdInvite: number;
    constructor(projeto: Projeto,
        qtdRequest: number,
        qtdInvite: number){
            this.project = projeto;
            this.qtdRequest = qtdRequest;
            this.qtdInvite = qtdInvite
        }

}