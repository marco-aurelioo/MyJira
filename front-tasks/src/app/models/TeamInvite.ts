import { Profile } from "./Profile";

export class TeamInvite{

    id?:   String;  
    projeto?: String;
    responsavel?: Profile;
    
        constructor(id: string, projeto: string, responsavel: Profile){
            this.id = id;
            this.projeto = projeto;
            this.responsavel = responsavel;
        }
    
    }